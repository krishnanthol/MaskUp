package com.example.maskup;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import io.paperdb.Paper;

public class ProfileFragment extends Fragment
{
    EditText enterZip;
    CheckBox checkVaccinated;
    CheckBox checkImmuno;
    Button enter;
    String temp;

    boolean v;
    boolean i;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        enterZip = view.findViewById(R.id.id_enterZip);
        checkVaccinated = view.findViewById(R.id.id_checkVaccinated);
        checkImmuno = view.findViewById(R.id.id_checkCompro);

        enter = view.findViewById(R.id.id_goHome);

        if(!MainActivity.zipCode.equals(""))
        {
            enterZip.setText(""+MainActivity.zipCode);
            enterZip.setFocusable(false);
        }
        else
        {
            enterZip.addTextChangedListener(new TextWatcher()
            {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
                {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
                {
                    temp = charSequence.toString();
                }

                @Override
                public void afterTextChanged(Editable editable)
                {

                }
            });
        }

        checkVaccinated.setChecked(MainActivity.vaccinated);
        checkVaccinated.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                v = b;
            }
        });

        checkImmuno.setChecked(MainActivity.immunocompromised);
        checkImmuno.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                i = b;
            }
        });

        enter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(MainActivity.zipCode.equals(""))
                {
                    MainActivity.zipCode = temp;
                    Paper.book().write("zipCode", MainActivity.zipCode);

                    MainActivity.getWeather = new GetWeather();
                    MainActivity.getWeather.execute();

                    MainActivity.getLocation = new GetLocation();
                    MainActivity.getLocation.execute();

                    MainActivity.vaccinated = v;
                    Paper.book().write("vaccinated", MainActivity.vaccinated);
                    MainActivity.immunocompromised = i;
                    Paper.book().write("immuno", MainActivity.immunocompromised);

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container,new PlacesFragment());
                    MainActivity.navigationView.setCheckedItem(R.id.nav_places);
                    fragmentTransaction.commit();
                }
                else if(!MainActivity.zipCode.equals(""))
                {
                    MainActivity.vaccinated = v;
                    Paper.book().write("vaccinated", MainActivity.vaccinated);
                    MainActivity.immunocompromised = i;
                    Paper.book().write("immuno", MainActivity.immunocompromised);

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container,new HomeFragment());
                    MainActivity.navigationView.setCheckedItem(R.id.nav_home);
                    fragmentTransaction.commit();
                }
            }
        });

        return view;
    }

}
