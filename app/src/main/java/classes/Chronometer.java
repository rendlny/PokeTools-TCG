package classes;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import rendelaney.poketools_tcg.PlayTools;

/**
 * Created by Ren on 01/03/2018.
 */

public class Chronometer implements Runnable{
    //conversion rate for time
    public static final long MILLIS_TO_MINUTES = 60000;
    public static final long MILLIS_TO_HOURS = 3600000;

    private Context mContext;
    private long startTime;
    private  boolean isRunning;

    public Chronometer(Context context)
    {
        mContext = context;
    }

    public void start(){
        startTime = System.currentTimeMillis();
        isRunning = true;
    }

    public void stop(){
        isRunning = false;
    }

    public void pause(){
        this.stop();
        isRunning = false;
    }

    public void continueTimer(String continueTime){

        //changing time from string to milliseconds so it will work w/ run method

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            Date date = sdf.parse("1970-01-01 " + continueTime);
            long timeInMillis = date.getTime();
            startTime = timeInMillis;
            isRunning = true;
        }catch(java.text.ParseException ex){

        }
    }

    @Override
    public void run() {
        while (isRunning)
        {
            long since = System.currentTimeMillis() - startTime;

            int seconds = (int)((since / 1000) % 60);
            int minutes = (int)((since / MILLIS_TO_MINUTES) % 60);
            int hours = (int)((since / MILLIS_TO_HOURS) % 24);
           // int millis = (int)(since % 1000);

            ((PlayTools)mContext).updateTimerText(String.format(
                    "%02d:%02d:%02d", hours, minutes, seconds
            ));
            try {
                Thread.sleep(10);
            }catch (Exception e){

            }
        }
    }


}
