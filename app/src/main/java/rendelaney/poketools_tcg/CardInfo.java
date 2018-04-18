package rendelaney.poketools_tcg;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import classes.Card;

public class CardInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_info);

        //get card ID
        Intent myIntent = getIntent();
        final String card_id = myIntent.getStringExtra("card_id");
        Log.e("TEST", "ID after getting from intent = " + card_id);

        final Toast db_error = Toast.makeText(getApplication(), R.string.db_error, Toast.LENGTH_SHORT);
        final Toast db_empty = Toast.makeText(getApplication(), R.string.db_empty, Toast.LENGTH_SHORT);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("cards").orderByChild("card_id").equalTo(card_id);
        query.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.getValue() == null)
                {
                    Log.e("TEST", "Card ID = " + card_id);
                    db_empty.show();
                    finish();
                }else
                {
                    Log.e("TEST", "Card ID = " + card_id);
                    //set card details into place
                    Card card = dataSnapshot.getValue(Card.class);
                    Log.e("TEST", "snapshot= " + dataSnapshot.getValue());
                    Log.e("TEST", "CARD NAME = " + card.getCard_name());
                    ImageView cardImg = (ImageView) findViewById(R.id.card_image_view);
                    //get image
                    String url = card.getCard_art();
                    Glide.with(getApplicationContext()).load(url).into(cardImg);

                    //get name + set in textview
                    TextView cardName = (TextView) findViewById(R.id.card_name_txt_view);
                    cardName.setText(card.getCard_name());

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                onChildAdded(dataSnapshot, s);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                db_empty.show();
                finish();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                onChildAdded(dataSnapshot, s);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                db_error.show();
                finish();
            }
        });
    }
}
