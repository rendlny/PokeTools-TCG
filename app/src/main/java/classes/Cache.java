package classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

//aws
import java.net.InetSocketAddress;
import net.spy.memcached.MemcachedClient;

import rendelaney.poketools_tcg.R;

public class Cache extends AsyncTask<String, Void, Object> {
    private Exception exception;
    private Context context;

    public Context getContext() {
        return context;
    }

    @Override
    protected String doInBackground(String... strings) {

        try
        {
            ArrayList<Object> list = new ArrayList<>();

            //setting up client connection
            String configEndpoint = Integer.toString(R.string.cache_endpoint);
            int clusterPort = 11211;

            MemcachedClient client = new MemcachedClient(
                    new InetSocketAddress(configEndpoint, clusterPort)
            );
            //testing set and get from cache
            client.set("test", 3600,"Test Data");
            Object o  = client.get("test");
            String desc = o.toString();
            Log.e("TESTING-CACHE", o.toString());
            list.add(o);
            SharedPreferences  mSharedPreferences = getContext().getSharedPreferences("ARRAY_LIST_SETS",context.MODE_PRIVATE);
            //mSharedPreferences.edit().putStringSet(Constants.ARRAY_LIST_SETS, );
            mSharedPreferences.edit().putString(Constants.ARRAY_LIST_SETS, desc);
            return "GOT";

        }catch(Exception e)
        {
            Log.e("TESTING-CACHE","Failed");
            this.exception = e;
            return null;
        }
    }
}
