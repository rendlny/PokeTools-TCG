package classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import rendelaney.poketools_tcg.R;

/**
 * Created by Ren on 06/04/2018.
 */

public class SetListAdapter extends ArrayAdapter<Set>{

    public SetListAdapter(Context context, int textViewResourceId){
        super(context, textViewResourceId);
    }

    public SetListAdapter(Context context, int resource, List<Set> sets)
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
