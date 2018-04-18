package rendelaney.poketools_tcg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

import classes.Cache;
import classes.Constants;
import classes.Set;

public class SetsSelection extends AppCompatActivity {




    private ArrayList<Set> setList = new ArrayList<>();
    private DatabaseReference databaseSets;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets_selection);

        Context context = getParent();
        //Activity mActivity = ;
        //SharedPreferences mSharedPreferences = mActivity.getSharedPreferences("ARRAY_LIST_SETS", Context.MODE_PRIVATE);

        //Error messages
        final Toast db_error = Toast.makeText(getApplication(), R.string.db_error, Toast.LENGTH_SHORT);
        final Toast db_empty = Toast.makeText(getApplication(), R.string.db_empty, Toast.LENGTH_SHORT);

        //AWS Cache
       // String cache = Cache.execute("go");
       // Cache cache = new Cache();
     //   cache.execute();
        //String sets = mSharedPreferences.getString("ARRAY_LIST_SETS", "");
     //   if(mSharedPreferences == null)
      //  {
      //      Log.e("TEST", "SETS not null good job");
      //      Log.e("TEST", "Sets = " + sets);
     //   }

        //Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseSets = database.getReference("sets");

        databaseSets.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ListView setListView = (ListView) findViewById(R.id.sets_list_view);
                //clear list so data not duplicated
                setList.clear();
                for(DataSnapshot setSnapshot : dataSnapshot.getChildren())
                {
                    if(dataSnapshot.getValue() == null){
                        db_empty.show();
                        finish();
                    }
                    Set set = setSnapshot.getValue(Set.class);
                    setList.add(set);
                }
                setListAdapter adapter = new setListAdapter(getApplicationContext(), R.layout.setlistrow, setList);
                setListView.setAdapter(adapter);

                setListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                      Intent myIntent = new Intent(SetsSelection.this, SubsetsSelection.class);
                      //get set ID to send
                       // String set_id = setList.get(position).getSet_id();
                      myIntent.putExtra("set_id", position+1);
                      startActivity(myIntent);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                db_error.show();
                finish();
            }
        });
    }
}
class setListAdapter extends ArrayAdapter<Set>{

    public setListAdapter(Context context, int textViewResourceId){
        super(context, textViewResourceId);
    }

    public setListAdapter(Context context, int resource, List<Set> sets)
    {
        super(context, resource, sets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;

        if(view == null)
        {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.setlistrow, null);
        }

        Set set = getItem(position);

        if(set != null)
        {
            TextView t1 = (TextView) view.findViewById(R.id.setlistrow_text_view);

            if(t1 != null)
            {
                t1.setText(set.getSet_name());
            }
        }

        return view;
    }
}


