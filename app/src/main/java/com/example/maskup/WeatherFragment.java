package com.example.maskup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class WeatherFragment extends Fragment
{
    ListView listView;
    CustomAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_weather,container,false);

        listView = view.findViewById(R.id.id_listView);
        adapter = new CustomAdapter(MainActivity.context, R.layout.adapter_custom,MainActivity.forecasts);
        listView.setAdapter(adapter);

        return view;
    }

}
