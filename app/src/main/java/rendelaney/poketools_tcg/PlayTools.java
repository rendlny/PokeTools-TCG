package rendelaney.poketools_tcg;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import classes.Chronometer;
import classes.DamageTracker;

public class PlayTools extends AppCompatActivity {

    //Timer
    private Button btnTimerStart, btnTimerPause;
    private TextView txtTimerCounter;
    private Chronometer chronometer;
    private Thread threadChrono;
    private Context context;
    private boolean timerPaused = false;
    String timeWhenStopped = null;

    //Coin Flip
    private Button btnCoinFlip;
    private ImageView imgCoin;
    Random r;
    int coinSide; //0-heads, 1-tails

    //Drop-down List
    private Spinner dropdown;
    private String[] items;
    ArrayAdapter<String> adapter;

    //Condition Description box
    private TextView txtCondDesc;
    private String selectedOption;

    //GX-Counter
    private ImageButton btnGX;
    private boolean gxUsed;

    //Active-damage
    private TextView txtActiveCounter;
    private Button btnActivePlus;
    private Button btnActiveMinus;

    //Bench Damage Counters
    //1
    private TextView txtBenchCounter2;
    private Button btnBenchPlus2;
    private Button btnBenchMinus2;

    //2
    private TextView txtBenchCounter3;
    private Button btnBenchPlus3;
    private Button btnBenchMinus3;

    //3
    private TextView txtBenchCounter4;
    private Button btnBenchPlus4;
    private Button btnBenchMinus4;

    //4
    private TextView txtBenchCounter5;
    private Button btnBenchPlus5;
    private Button btnBenchMinus5;

    //5
    private TextView txtBenchCounter1;
    private Button btnBenchPlus1;
    private Button btnBenchMinus1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_tools);

        context = this;

        btnTimerStart = (Button) findViewById(R.id.play_tools_btn_timer_start);
        btnTimerPause = (Button) findViewById(R.id.play_tools_btn_timer_pause);
        txtTimerCounter = (TextView) findViewById(R.id.play_tools_txt_timer_counter);

        btnTimerStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start button on timer has been pressed

                //if timer is already running
                if (chronometer != null) {
                    //stop timer and change stop text back to start in button
                    timeWhenStopped = null;
                    chronometer.stop();

                    //txtTimerCounter.setText("00:00:00");
                    btnTimerStart.setText(R.string.start);
                    btnTimerPause.setText(R.string.pause);
                    chronometer = null;
                    timerPaused = false;
                } else if (chronometer == null && timerPaused == false) {
                    //start timer
                    chronometer = new Chronometer(context);
                    threadChrono = new Thread(chronometer);
                    threadChrono.start();
                    chronometer.start();
                    btnTimerStart.setText("Stop");
                }

            }

        });

        btnTimerPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chronometer != null && timerPaused == false) {
                    //timer is running, pause it
                    chronometer.pause();
                    try {
                        timeWhenStopped = (String) txtTimerCounter.getText();
                        timerPaused = true;
                        // chronometer = null;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    btnTimerPause.setText("Continue");
                } else if (timeWhenStopped != null && timerPaused == true) {
                    //continue timer
                    btnTimerPause.setText("Pause");
                    threadChrono = new Thread(chronometer);
                    threadChrono.start();
                    chronometer = new Chronometer(context);
                    // chronometer.continueTimer();

                    chronometer.start();
                    timeWhenStopped = null;
                    timerPaused = false;
                }
            }
        });

        //Coin Flip

        btnCoinFlip = (Button) findViewById(R.id.play_tools_btn_coin_flip);
        imgCoin = (ImageView)findViewById(R.id.play_tools_img_coin);
        r = new Random();

        btnCoinFlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coinSide = r.nextInt(2);

                if(coinSide == 0){
                    imgCoin.setImageResource(R.drawable.coin_heads);
                }else if(coinSide == 1){
                    imgCoin.setImageResource(R.drawable.coin_tails);
                }
                RotateAnimation rotate = new RotateAnimation(0, 360,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(1000);
                imgCoin.startAnimation(rotate);
            }
        });

        //Drop-down List
        dropdown = (Spinner)findViewById(R.id.play_tools_spinner_list);

        items = new String[]{
                context.getString(R.string.cond_asleep),
                context.getString(R.string.cond_burned),
                context.getString(R.string.cond_confused),
                context.getString(R.string.cond_paralyzed),
                context.getString(R.string.cond_poisoned)
        };
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, items);
        dropdown.setAdapter(adapter);

        //Condition Description box
        txtCondDesc = (TextView) findViewById(R.id.play_tools_txt_cond_desc);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedOption = dropdown.getSelectedItem().toString();
                //switching text description depending on selected item from dropdown
                switch (selectedOption) {
                    case "Asleep":
                        txtCondDesc.setText(R.string.cond_desc_asleep);
                        break;

                    case "Burned":
                        txtCondDesc.setText(R.string.cond_desc_burned);
                        break;

                    case "Confused":
                        txtCondDesc.setText(R.string.cond_desc_confused);
                        break;

                    case "Paralyzed":
                        txtCondDesc.setText(R.string.cond_desc_paralyzed);
                        break;
                    case "Poisoned":
                        txtCondDesc.setText(R.string.cond_desc_poisoned);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                txtCondDesc.setText("");
            }
        });

        //GX-Counter
        btnGX = (ImageButton) findViewById(R.id.play_tools_img_btn_gx);
        gxUsed = false;
        //fade animation
        final Animation fadeIn = new AlphaAnimation(0,1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(1000);

        btnGX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gxUsed == false){
                    gxUsed = true;
                    btnGX.startAnimation(fadeIn);
                    btnGX.setBackgroundResource(R.drawable.gx_used);
                }else{
                    gxUsed = false;
                    btnGX.startAnimation(fadeIn);
                    btnGX.setBackgroundResource(R.drawable.gx);
                }
            }
        });

        //Active Damage Counter
        txtActiveCounter = (TextView) findViewById(R.id.play_tools_txt_active_counter);
        btnActivePlus = (Button) findViewById(R.id.play_tools_btn_active_plus);
        btnActiveMinus = (Button) findViewById(R.id.play_tools_btn_active_minus);

        final Toast high_error = Toast.makeText(getApplication(), R.string.high_error, Toast.LENGTH_SHORT);
        final Toast zero_error = Toast.makeText(getApplication(), R.string.zero_error, Toast.LENGTH_SHORT);

        final DamageTracker activeDmg = new DamageTracker();
        btnActivePlus.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                if(activeDmg.increase() == -1){
                    high_error.show();
                }else{
                    txtActiveCounter.setText(Integer.toString(activeDmg.getDamage()));
                }

            }
        });

        btnActiveMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activeDmg.decrease() == -1){
                   zero_error.show();
                }else{
                    txtActiveCounter.setText(Integer.toString(activeDmg.getDamage()));
                }

            }
        });

        //Bench Damage Counters
        //1
        txtBenchCounter1 = (TextView)findViewById(R.id.play_tools_txt_bench_counter1) ;
        btnBenchPlus1 = (Button)findViewById(R.id.play_tools_btn_bench_plus1);
        btnBenchMinus1 = (Button)findViewById(R.id.play_tools_btn_bench_minus1);

        final DamageTracker bench1_tracker = new DamageTracker();

        btnBenchPlus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bench1_tracker.increase() == -1){
                    high_error.show();
                }else{
                    txtBenchCounter1.setText(Integer.toString(bench1_tracker.getDamage()));
                }
            }
        });

        btnBenchMinus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bench1_tracker.decrease() == -1){
                    zero_error.show();
                }else {
                    txtBenchCounter1.setText(Integer.toString(bench1_tracker.getDamage()));
                }
            }
        });

        //2
        txtBenchCounter2 = (TextView)findViewById(R.id.play_tools_txt_bench_counter2) ;
        btnBenchPlus2 = (Button)findViewById(R.id.play_tools_btn_bench_plus2);
        btnBenchMinus2 = (Button)findViewById(R.id.play_tools_btn_bench_minus2);

        final DamageTracker bench2_tracker = new DamageTracker();

        btnBenchPlus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bench2_tracker.increase() == -1){
                    high_error.show();
                }else{
                    txtBenchCounter2.setText(Integer.toString(bench2_tracker.getDamage()));
                }
            }
        });

        btnBenchMinus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bench2_tracker.decrease() == -1){
                    zero_error.show();
                }else {
                    txtBenchCounter2.setText(Integer.toString(bench2_tracker.getDamage()));
                }
            }
        });

        //3
        txtBenchCounter3 = (TextView)findViewById(R.id.play_tools_txt_bench_counter3) ;
        btnBenchPlus3 = (Button)findViewById(R.id.play_tools_btn_bench_plus3);
        btnBenchMinus3 = (Button)findViewById(R.id.play_tools_btn_bench_minus3);

        final DamageTracker bench3_tracker = new DamageTracker();

        btnBenchPlus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bench3_tracker.increase() == -1){
                    high_error.show();
                }else{
                    txtBenchCounter3.setText(Integer.toString(bench3_tracker.getDamage()));
                }
            }
        });

        btnBenchMinus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bench3_tracker.decrease() == -1){
                    zero_error.show();
                }else {
                    txtBenchCounter3.setText(Integer.toString(bench3_tracker.getDamage()));
                }
            }
        });

        //4
        txtBenchCounter4 = (TextView)findViewById(R.id.play_tools_txt_bench_counter4) ;
        btnBenchPlus4 = (Button)findViewById(R.id.play_tools_btn_bench_plus4);
        btnBenchMinus4 = (Button)findViewById(R.id.play_tools_btn_bench_minus4);

        final DamageTracker bench4_tracker = new DamageTracker();

        btnBenchPlus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bench4_tracker.increase() == -1){
                    high_error.show();
                }else{
                    txtBenchCounter4.setText(Integer.toString(bench4_tracker.getDamage()));
                }
            }
        });

        btnBenchMinus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bench4_tracker.decrease() == -1){
                    zero_error.show();
                }else {
                    txtBenchCounter4.setText(Integer.toString(bench4_tracker.getDamage()));
                }
            }
        });

        //5
        txtBenchCounter5 = (TextView)findViewById(R.id.play_tools_txt_bench_counter5) ;
        btnBenchPlus5 = (Button)findViewById(R.id.play_tools_btn_bench_plus5);
        btnBenchMinus5 = (Button)findViewById(R.id.play_tools_btn_bench_minus5);

        final DamageTracker bench5_tracker = new DamageTracker();

        btnBenchPlus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bench5_tracker.increase() == -1){
                    high_error.show();
                }else{
                    txtBenchCounter5.setText(Integer.toString(bench5_tracker.getDamage()));
                }
            }
        });

        btnBenchMinus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bench5_tracker.decrease() == -1){
                    zero_error.show();
                }else {
                    txtBenchCounter5.setText(Integer.toString(bench5_tracker.getDamage()));
                }
            }
        });


    }

        public void updateTimerText(final String time) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    txtTimerCounter.setText(time);
                }
            });
        }
}
