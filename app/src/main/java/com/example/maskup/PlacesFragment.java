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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.button.MaterialButton;

import io.paperdb.Paper;

public class PlacesFragment extends Fragment
{
    MaterialButton addPlace;
    ListView placesListView;
    CustomAdapterPlace placeAdapter;
    int selected;

    Button goHome;

    AlertDialog.Builder dialogBuilder;
    AlertDialog dialog;
    EditText enterPlaceName;
    EditText enterHours;
    Button addInfo;
    Button cancel;

    CheckBox checkCrowded;
    CheckBox checkMaskMandate;
    TextView showHours;
    TextView showMandate;
    TextView showCrowded;

    String name;
    int hours = 0;
    boolean crowded;
    boolean maskMandate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_places,container,false);

        placesListView = view.findViewById(R.id.id_placesListView);
        addPlace = view.findViewById(R.id.id_addPlaceButton);
        goHome = view.findViewById(R.id.id_goHome);

        placeAdapter = new CustomAdapterPlace(MainActivity.context, R.layout.adapter_custom_place,MainActivity.places);
        placesListView.setAdapter(placeAdapter);

        placesListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                selected = i;
                createNewPlaceDisplayDialog();
            }
        });

        addPlace.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                createNewPlaceDialog();
            }
        });

        goHome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(!MainActivity.statsComplete)
                {
                    MainActivity.showSplashAlt();
                    MainActivity.getStats = new GetStats();
                    MainActivity.getStats.execute();

                    final Handler handler2 = new Handler(Looper.getMainLooper());
                    handler2.postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container,new HomeFragment());
                            MainActivity.navigationView.setCheckedItem(R.id.nav_home);
                            fragmentTransaction.commit();
                        }
                    }, 7000);
                }
                else
                {
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
                    Place place = new Place(name,hours,crowded,maskMandate);
                    MainActivity.places.add(place);
                    placeAdapter.notifyDataSetChanged();

                    Paper.book().write("places", MainActivity.places);

                    dialog.dismiss();
                    name = "";
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
                hours = 0;
                crowded = false;
                maskMandate = false;
            }
        });

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

        dialogBuilder.setView(placePopupView);
        dialog = dialogBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    public void createNewPlaceDisplayDialog()
    {
        dialogBuilder = new AlertDialog.Builder(MainActivity.context);
        final View placeDisplayPopupView = getLayoutInflater().inflate(R.layout.popup_place_display,null);

        showHours = placeDisplayPopupView.findViewById(R.id.id_showHours);
        showHours.setText("Hours/Day: "+MainActivity.places.get(selected).getHours());

        showCrowded = placeDisplayPopupView.findViewById(R.id.id_showCrowded);
        showCrowded.setText("Crowded: "+MainActivity.places.get(selected).isCrowded());

        showMandate = placeDisplayPopupView.findViewById(R.id.id_showMandate);
        showMandate.setText("MaskMandate: "+MainActivity.places.get(selected).isMaskMandate());

        dialogBuilder.setView(placeDisplayPopupView);
        dialog = dialogBuilder.create();
        dialog.show();
    }
}
