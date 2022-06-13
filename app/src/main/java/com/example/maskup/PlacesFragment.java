package com.example.maskup;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class PlacesFragment extends Fragment implements CompoundButton.OnCheckedChangeListener
{
    MaterialButton addPlace;
    ListView placesListView;

    AlertDialog.Builder dialogBuilder;
    AlertDialog dialog;
    EditText enterPlaceName;
    Spinner chooseIcon;
    EditText enterHours;
    Button addInfo;
    Button cancel;

    String name;
    ArrayList<Boolean> days;
    int icon;
    int hours;
    boolean crowded;
    boolean maskMandate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_places,container,false);

        placesListView = view.findViewById(R.id.id_placesListView);
        addPlace = view.findViewById(R.id.id_addPlaceButton);

        days = new ArrayList<Boolean>();

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
                //icon;
                hours = 0;
                crowded = false;
                maskMandate = false;
            }
        });

        dialogBuilder.setView(placePopupView);
        dialog = dialogBuilder.create();
        dialog.show();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked)
    {
        switch(compoundButton.getId())
        {
            case R.id.id_maskMandate:
                if(checked)
                {
                    maskMandate = true;
                }
                else
                {
                    maskMandate = false;
                }
                break;
            case R.id.id_crowded:
                if(checked)
                {
                    crowded = true;
                }
                else
                {
                    crowded = false;
                }
                break;
            case R.id.id_sunday:
                if(checked)
                {
                    days.set(0,true);
                }
                else
                {
                    days.set(0,false);
                }
                break;
            case R.id.id_monday:
                if(checked)
                {
                    days.set(1,true);
                }
                else
                {
                    days.set(1,false);
                }
                break;
            case R.id.id_tuesday:
                if(checked)
                {
                    days.set(2,true);
                }
                else
                {
                    days.set(2,false);
                }
                break;
            case R.id.id_wednesday:
                if(checked)
                {
                    days.set(3,true);
                }
                else
                {
                    days.set(3,false);
                }
                break;
            case R.id.id_thursday:
                if(checked)
                {
                    days.set(4,true);
                }
                else
                {
                    days.set(4,false);
                }
                break;
            case R.id.id_friday:
                if(checked)
                {
                    days.set(5,true);
                }
                else
                {
                    days.set(5,false);
                }
                break;
            case R.id.id_saturday:
                if(checked)
                {
                    days.set(6,true);
                }
                else
                {
                    days.set(6,false);
                }
                break;
        }
    }
}
