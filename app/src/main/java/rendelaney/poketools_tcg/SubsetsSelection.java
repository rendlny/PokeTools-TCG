package rendelaney.poketools_tcg;

import android.content.Context;
import android.content.Intent;
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

import classes.Subset;

public class SubsetsSelection extends AppCompatActivity {


    private DatabaseReference databaseSubsets;
    private ArrayList<Subset> subsetList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subsets_selection);

        //get set ID
        Intent myIntent = getIntent();
        final int set_id = myIntent.getIntExtra("set_id", 0);

        final Toast db_error = Toast.makeText(getApplication(), R.string.db_error, Toast.LENGTH_SHORT);
        final Toast db_empty = Toast.makeText(getApplication(), R.string.db_empty, Toast.LENGTH_SHORT);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        String set_id_string = "" + set_id;
        Query query = reference.child("subsets").orderByChild("set_id").equalTo(set_id_string);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() == null){
                    db_empty.show();
                    finish();
                }
                ListView subsetListView = (ListView) findViewById(R.id.subsets_list_view);
                //clear list so data not duplicated
                subsetList.clear();
                for(DataSnapshot subsetSnapshot : dataSnapshot.getChildren())
                {
                    Subset subset = subsetSnapshot.getValue(Subset.class);
                    subsetList.add(subset);
                }
                subsetListAdapter adapter = new subsetListAdapter(getApplicationContext(), R.layout.subsetlistrow, subsetList);
                subsetListView.setAdapter(adapter);

                subsetListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent myIntent = new Intent(SubsetsSelection.this, CardsDisplay.class);
                        myIntent.putExtra("set_and_subset_id", (set_id + "#" + (position+1)));
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

class subsetListAdapter extends ArrayAdapter<Subset> {

    public subsetListAdapter(Context context, int textViewResourceId){
        super(context, textViewResourceId);
    }

    public subsetListAdapter(Context context, int resource, List<Subset> subsets)
    {
        super(context, resource, subsets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;

        if(view == null)
        {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.subsetlistrow, null);
        }

        Subset subset = getItem(position);

        if(subset != null)
        {
            TextView t1 = (TextView) view.findViewById(R.id.subsetlistrow_text_view);

            if(t1 != null)
            {
                t1.setText(subset.getSubset_name());
            }
        }

        return view;
    }
}