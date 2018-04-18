package rendelaney.poketools_tcg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    Button btnPlayTools;
    //Button btnDecks;
    Button btnSettings;
    Button btnCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnPlayTools = (Button)findViewById(R.id.btnPlayTools);
        btnPlayTools.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, PlayTools.class));
            }
        });

        //btnDecks = (Button)findViewById(R.id.btnDecks);
        //btnDecks.setOnClickListener(new View.OnClickListener() {
        //                                @Override
        //      public void onClick(View v) {
        //          startActivity(new Intent(Home.this, DecksMenu.class));
        //    }
       // });

        btnCards = (Button)findViewById(R.id.btnCards);
        btnCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, SetsSelection.class));
            }
        });
        btnSettings = (Button)findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, MainActivity.class));
            }
        });
    }
}
