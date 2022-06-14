package com.example.maskup;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapterWeather extends ArrayAdapter<Weather>
{
    Context mainActivityContext;
    ArrayList<Weather> forecasts;

    public CustomAdapterWeather(@NonNull Context context, int resource, @NonNull ArrayList<Weather> forecasts)
    {
        super(context,resource,forecasts);
        mainActivityContext = context;
        this.forecasts = forecasts;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater layoutInflater = (LayoutInflater) mainActivityContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View v = layoutInflater.inflate(R.layout.adapter_custom_weather, null);

        TextView time = v.findViewById(R.id.id_time);
        TextView temp = v.findViewById(R.id.id_temp);
        ImageView imageView = v.findViewById(R.id.id_imageView);
        TextView description = v.findViewById(R.id.id_description);

        time.setText(forecasts.get(position).getHour());

        temp.setText(forecasts.get(position).getTemp() + " F");
        if (Double.parseDouble(forecasts.get(position).getTemp()) <= 32)
        {
            temp.setTextColor(Color.parseColor("#2137ff"));
        }
        else if (Double.parseDouble(forecasts.get(position).getTemp()) <= 52)
        {
            temp.setTextColor(Color.parseColor("#4255ff"));
        }
        else if (Double.parseDouble(forecasts.get(position).getTemp()) <= 90)
        {
            temp.setTextColor(Color.parseColor("#ff4545"));
        }
        else
        {
            temp.setTextColor(Color.parseColor("#ff2929"));
        }

        Picasso.with(getContext()).load("https://openweathermap.org/img/wn/" + forecasts.get(position).getImage() + "@2x.png").into(imageView);

        description.setText(forecasts.get(position).getDescription());

        return v;
    }
}
