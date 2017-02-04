package com.example.harry.mad_assignment1_harrisonjenkins_17967352;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Harry on 05-Jan-17.
 */
public class CustomAdapter extends ArrayAdapter<String> {
    //variables
    private final Context context;
    private final String[] values;

    //constructor
    public CustomAdapter(Context context, String[] values) {
        super(context, R.layout.rowlayout, values);
        this.context = context;
        this.values = values;
    }

    //getters
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        textView.setText(values[position]);

        // Change the icon for pizza type
        String s = values[position];
        if (s.startsWith("Ham and Pineapple") ) {
            imageView.setImageResource(R.drawable.hawaiian);

        }

        return rowView;
    }
}