package com.example.maskup;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment
{
    //true = no mask, false = mask
    TextView profileDesc;
    TextView statsDesc;
    TextView weatherDesc;
    TextView placesDesc;
    Button show;

    boolean temperatureTest;
    boolean statsTest;
    boolean placesTest;

    int fails;
    int totalHours;
    int totalMandate;
    int totalCrowded;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        profileDesc = view.findViewById(R.id.id_profileDesc);
        statsDesc = view.findViewById(R.id.id_statsDesc);
        weatherDesc = view.findViewById(R.id.id_weatherDesc);
        placesDesc = view.findViewById(R.id.id_placesDesc);
        show = view.findViewById(R.id.id_goHome);


        if (MainActivity.averageTemp <= 70)
        {
            temperatureTest = false;
        }
        else
        {
            temperatureTest = true;
        }

        if ((MainActivity.countyNewCases.get(0) - MainActivity.countyNewCases.get(1)) > 0)
        {
            if((((MainActivity.countyNewCases.get(0) - MainActivity.countyNewCases.get(1))/MainActivity.countyNewCases.get(1))*100) > 30)
            {
                Log.d("countyNewCases",MainActivity.countyNewCases.toString());
                statsTest = false;
            }
        }
        else
        {
            statsTest = true;
        }

        Log.d("places",MainActivity.places.toString());

        if(MainActivity.places.size() > 0)
        {
            for (int i = 0; i < MainActivity.places.size(); i++)
            {
                if(MainActivity.places.get(i).isCrowded())
                {
                    totalCrowded++;
                }
                if(MainActivity.places.get(i).isMaskMandate())
                {
                    totalMandate++;
                }
                totalHours+=MainActivity.places.get(i).getHours();

                if(totalHours >= 6)
                {
                    if(totalCrowded >= MainActivity.places.size()/2 || totalMandate >= MainActivity.places.size()/2)
                    {
                        placesTest = false;
                        i = MainActivity.places.size();
                    }
                    else
                    {
                        placesTest = true;
                    }
                }
                else
                {
                    placesTest = true;
                }
            }
        }

        if (!temperatureTest)
        {
            fails++;
            weatherDesc.setText("Fail");
            weatherDesc.setTextColor(Color.parseColor("#fab4b4"));
            Log.d("fail1","");
        }
        else
        {
            weatherDesc.setText("Pass");
            weatherDesc.setTextColor(Color.parseColor("#7da67b"));
        }

        if (!statsTest)
        {
            fails++;
            statsDesc.setText("Fail");
            statsDesc.setTextColor(Color.parseColor("#fab4b4"));
            Log.d("fail2","");
        }
        else
        {
            statsDesc.setText("Pass");
            statsDesc.setTextColor(Color.parseColor("#7da67b"));
        }

        Log.d("places",""+MainActivity.places.size());

        if(MainActivity.places.size() == 0)
        {
            placesDesc.setText("N/A");
            placesDesc.setTextColor(Color.BLACK);
        }
        else if (!placesTest)
        {
            fails++;
            placesDesc.setText("Fail");
            placesDesc.setTextColor(Color.parseColor("#fab4b4"));
            Log.d("fail3","");
        }
        else if(placesTest)
        {
            placesDesc.setText("Pass");
            placesDesc.setTextColor(Color.parseColor("#7da67b"));
        }


        if(!MainActivity.vaccinated)
        {
            fails++;
            profileDesc.setText("Fail");
            profileDesc.setTextColor(Color.parseColor("#fab4b4"));
            Log.d("fail4","");
        }
        Log.d("immuno",""+MainActivity.immunocompromised);
        if(MainActivity.immunocompromised)
        {
            fails++;
            profileDesc.setText("Fail");
            profileDesc.setTextColor(Color.parseColor("#fab4b4"));
            Log.d("fail5","");
        }
        if(!MainActivity.immunocompromised && MainActivity.vaccinated)
        {
            profileDesc.setText("Pass");
            profileDesc.setTextColor(Color.parseColor("#7da67b"));
        }

        if(fails >= 3 && MainActivity.places.size() > 0)
        {
            show.setBackgroundColor(Color.parseColor("#fab4b4"));
            show.setText("MASK NEEDED");
            show.setTextColor(Color.WHITE);
        }
        else if(fails >= 2 && MainActivity.places.size() == 0)
        {
            show.setBackgroundColor(Color.parseColor("#fab4b4"));
            show.setText("MASK NEEDED");
            show.setTextColor(Color.WHITE);
        }
        else
        {
            show.setBackgroundColor(Color.parseColor("#7da67b"));
            show.setText("MASK NOT NEEDED");
            show.setTextColor(Color.WHITE);
        }

        return view;
    }

}
