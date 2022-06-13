package com.example.maskup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import androidx.fragment.app.Fragment;

public class WeatherFragment extends Fragment
{
    TextView currentTemp;
    TextView showTown;
    ImageView currentIcon;
    ListView listView;
    CustomAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_weather,container,false);

        listView = view.findViewById(R.id.id_listView);
        currentTemp = view.findViewById(R.id.id_currentTemp);
        showTown = view.findViewById(R.id.id_showTown);
        currentIcon = view.findViewById(R.id.id_currentIcon);
        adapter = new CustomAdapter(MainActivity.context, R.layout.adapter_custom,MainActivity.forecasts);
        listView.setAdapter(adapter);

        currentTemp.setText(MainActivity.forecasts.get(0).getTemp()+"°");
        Picasso.with(getContext()).load("https://openweathermap.org/img/wn/" + MainActivity.forecasts.get(0).getImage() + "@2x.png").into(currentIcon);
        showTown.setText(MainActivity.town);

        return view;
    }

}
