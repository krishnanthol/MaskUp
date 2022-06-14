package com.example.maskup;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment
{
    //true = no mask, false = mask
    ImageView image;

    boolean temperatureTest;
    boolean statsTest;
    boolean placesTest;

    int fails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        image = view.findViewById(R.id.id_imageV);

        if (MainActivity.averageTemp <= 70)
        {
            temperatureTest = false;
        }
        else
        {
            temperatureTest = true;
        }

        if ((MainActivity.countyNewCases.get(4) - MainActivity.countyNewCases.get(4)) > 0) {
            statsTest = false;
        } else {
            statsTest = true;
        }

        for (int i = 0; i < MainActivity.places.size(); i++)
        {
            if (MainActivity.places.get(i).getHours() > 3)
            {
                placesTest = false;
                i = MainActivity.places.size();
            }
            else if(MainActivity.places.get(i).isCrowded())
            {
                placesTest = false;
                i = MainActivity.places.size();
            }
            else if(MainActivity.places.get(i).isMaskMandate())
            {
                placesTest = false;
                i = MainActivity.places.size();
            }
            else
            {
                placesTest = true;
            }
        }

        if (!temperatureTest)
        {
            fails++;
            Log.d("fail1","");
        }
        if (!statsTest)
        {
            fails++;
            Log.d("fail2","");
        }
        if (!placesTest)
        {
            fails++;
            Log.d("fail3","");
        }
        if(MainActivity.vaccinated)
        {
            fails--;
            Log.d("fail4","");
        }
        if(MainActivity.immunocompromised)
        {
            fails++;
            Log.d("fail5","");
        }

        if(fails >= 2)
        {
            image.setImageResource(R.drawable.maskuplogofull);
        }

        return view;
    }

}
