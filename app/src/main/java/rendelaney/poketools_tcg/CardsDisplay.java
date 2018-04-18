package rendelaney.poketools_tcg;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import classes.Card;
import classes.Subset;

public class CardsDisplay extends AppCompatActivity {

    private ArrayList<Card> cardList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards_display);

        //get set ID and subset ID from Intent
        Intent myIntent = getIntent();
        String ids = myIntent.getStringExtra("set_and_subset_id");

        String [] id_components = ids.split("#");
        String set_id = id_components[0];
        String subset_id = id_components[1];

        final Toast db_error = Toast.makeText(getApplication(), R.string.db_error, Toast.LENGTH_SHORT);
        final Toast db_empty = Toast.makeText(getApplication(), R.string.db_empty, Toast.LENGTH_SHORT);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("cards").orderByChild("subset_id").equalTo(subset_id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() == null){
                    db_empty.show();
                    finish();
                }
                GridView cardGridView = (GridView) findViewById(R.id.cards_grid_view);
                cardList.clear();

                for(DataSnapshot cardSnapshot : dataSnapshot.getChildren())
                {
                    Card card = cardSnapshot.getValue(Card.class);
                    cardList.add(card);
                }
                CardGridAdapter adapter = new CardGridAdapter(getApplicationContext(), R.layout.card_grid, cardList);
                cardGridView.setAdapter(adapter);

                cardGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //get card ID and send to CardInfo activity
                        String card_id = cardList.get(i).getCard_id();

                        Intent myIntent = new Intent(CardsDisplay.this, CardInfo.class);
                        myIntent.putExtra("card_id", card_id);
                        Log.e("TEST", "ID = " + card_id);
                        startActivity(myIntent);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                db_error.show();
            }
        });
    }
}

class CardGridAdapter extends ArrayAdapter<Card> {
    public CardGridAdapter(Context context, int textViewResourceId)
    {
        super(context, textViewResourceId);
    }

    public CardGridAdapter(Context context, int resource, List<Card> cards)
    {
        super(context, resource, cards);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parents)
    {
        View view = convertView;

        if(view == null)
        {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.card_grid, null);
        }

        Card card = getItem(position);

        if(card != null)
        {
            TextView t1 = (TextView)view.findViewById(R.id.card_grid_text_view);
            ImageView v1 = (ImageView)view.findViewById(R.id.card_grid_image_view);

            if(t1 != null)
            {
                //t1.setText(card.getCard_name());
            }
            if(v1 != null)
            {
                String url = card.getCard_art();
                Glide.with(getContext()).load(url).into(v1);
            }
        }
        return view;
    }
}