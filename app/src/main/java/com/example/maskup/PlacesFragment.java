package com.example.maskup;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class PlacesFragment extends Fragment
{
    MaterialButton addPlace;
    ListView placesListView;
    CustomAdapterPlace placeAdapter;

    AlertDialog.Builder dialogBuilder;
    AlertDialog dialog;
    EditText enterPlaceName;
    EditText enterHours;
    Button addInfo;
    Button cancel;
    CheckBox sunday;
    CheckBox monday;
    CheckBox tuesday;
    CheckBox wednesday;
    CheckBox thursday;
    CheckBox friday;
    CheckBox saturday;
    CheckBox checkCrowded;
    CheckBox checkMaskMandate;

    String name;
    ArrayList<Boolean> days = new ArrayList<>();
    int hours = 0;
    boolean crowded;
    boolean maskMandate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_places,container,false);

        placesListView = view.findViewById(R.id.id_placesListView);
        addPlace = view.findViewById(R.id.id_addPlaceButton);

        placeAdapter = new CustomAdapterPlace(MainActivity.context, R.layout.adapter_custom_place,MainActivity.places);
        placesListView.setAdapter(placeAdapter);

        days.add(false);
        days.add(false);
        days.add(false);
        days.add(false);
        days.add(false);
        days.add(false);
        days.add(false);

        addPlace.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                createNewPlaceDialog();
            }
        });
        return view;
    }

    public void createNewPlaceDialog()
    {
        dialogBuilder = new AlertDialog.Builder(MainActivity.context);
        final View placePopupView = getLayoutInflater().inflate(R.layout.popup_place,null);

        enterPlaceName = placePopupView.findViewById(R.id.id_placeName);
        enterPlaceName.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                name = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

        enterHours = placePopupView.findViewById(R.id.id_hours);
        enterHours.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(!(charSequence.toString().equals("")))
                    hours = Integer.parseInt(charSequence.toString());
                else
                {
                    hours = 0;
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

        addInfo = placePopupView.findViewById(R.id.id_add);
        addInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(!name.equals("") && hours != 0)
                {
                    Place place = new Place(name,days,hours,crowded,maskMandate);
                    MainActivity.places.add(place);
                    placeAdapter.notifyDataSetChanged();
                    Log.d("adapter",""+placeAdapter.getCount());

                    dialog.dismiss();
                    name = "";
                    days.clear();
                    days.add(false);
                    days.add(false);
                    days.add(false);
                    days.add(false);
                    days.add(false);
                    days.add(false);
                    hours = 0;
                    crowded = false;
                    maskMandate = false;
                }
                else
                {
                    Toast.makeText(MainActivity.context,"Please fill out all empty fields", Toast.LENGTH_LONG).show();
                }
            }
        });

        cancel = placePopupView.findViewById(R.id.id_cancel);
        cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dialog.dismiss();
                name = "";
                days.clear();
                days.add(false);
                days.add(false);
                days.add(false);
                days.add(false);
                days.add(false);
                days.add(false);
                hours = 0;
                crowded = false;
                maskMandate = false;
            }
        });

        sunday = placePopupView.findViewById(R.id.id_sunday);
        monday = placePopupView.findViewById(R.id.id_monday);
        tuesday = placePopupView.findViewById(R.id.id_tuesday);
        wednesday = placePopupView.findViewById(R.id.id_wednesday);
        thursday = placePopupView.findViewById(R.id.id_thursday);
        friday = placePopupView.findViewById(R.id.id_friday);
        saturday = placePopupView.findViewById(R.id.id_saturday);
        checkCrowded = placePopupView.findViewById(R.id.id_crowded);
        checkMaskMandate = placePopupView.findViewById(R.id.id_maskMandate);

        checkMaskMandate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if(b)
                {
                    maskMandate = true;
                    Log.d("mask",""+maskMandate);
                }
                else
                {
                    maskMandate = false;
                    Log.d("mask",""+maskMandate);
                }
            }
        });

        checkCrowded.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if(b)
                {
                    crowded = true;
                    Log.d("crowded",""+crowded);
                }
                else
                {
                    crowded = false;
                    Log.d("crowded",""+crowded);
                }
            }
        });

        sunday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if(b)
                {
                    days.set(0,true);
                    Log.d("days",days.toString());
                }
                else
                {
                    days.set(0,false);
                    Log.d("days",days.toString());
                }
            }
        });

        monday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if(b)
                {
                    days.set(1,true);
                    Log.d("days",days.toString());
                }
                else
                {
                    days.set(1,false);
                    Log.d("days",days.toString());
                }
            }
        });

        tuesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if(b)
                {
                    days.set(2,true);
                    Log.d("days",days.toString());
                }
                else
                {
                    days.set(2,false);
                    Log.d("days",days.toString());
                }
            }
        });

        wednesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if(b)
                {
                    days.set(3,true);
                    Log.d("days",days.toString());
                }
                else
                {
                    days.set(3,false);
                    Log.d("days",days.toString());
                }
            }
        });

        thursday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if(b)
                {
                    days.set(4,true);
                    Log.d("days",days.toString());
                }
                else
                {
                    days.set(4,false);
                    Log.d("days",days.toString());
                }
            }
        });

        friday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if(b)
                {
                    days.set(5,true);
                    Log.d("days",days.toString());
                }
                else
                {
                    days.set(5,false);
                    Log.d("days",days.toString());
                }
            }
        });

        saturday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if(b)
                {
                    days.set(6,true);
                    Log.d("days",days.toString());
                }
                else
                {
                    days.set(6,false);
                    Log.d("days",days.toString());
                }
            }
        });

        dialogBuilder.setView(placePopupView);
        dialog = dialogBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }
}
