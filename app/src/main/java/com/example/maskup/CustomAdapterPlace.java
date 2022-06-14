package com.example.maskup;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import io.paperdb.Paper;

public class CustomAdapterPlace extends ArrayAdapter<Place>
{
    Context mainActivityContext;
    ArrayList<Place> places;

    public CustomAdapterPlace(Context context, int resource, ArrayList<Place> places)
    {
        super(context, resource, places);
        mainActivityContext = context;
        this.places = places;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater layoutInflater = (LayoutInflater) mainActivityContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View v = layoutInflater.inflate(R.layout.adapter_custom_place, null);
        TextView displayName = v.findViewById(R.id.id_displayName);
        Button remove = v.findViewById(R.id.id_remove);

        displayName.setText(places.get(position).getName());
        remove.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                places.remove(position);
                Paper.book().write("places", MainActivity.places);
                notifyDataSetChanged();
            }
        });
        return v;
    }
}
