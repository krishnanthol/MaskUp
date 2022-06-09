package com.example.maskup;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Address>
{
    Context mainActivityContext;
    List<Address> addresses;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Address> addresses)
    {
        super(context,resource,addresses);
        mainActivityContext = context;
        this.addresses = addresses;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        TextView address;

        LayoutInflater layoutInflater = (LayoutInflater) mainActivityContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View v = layoutInflater.inflate(R.layout.adapter_custom,null);
        address = v.findViewById(R.id.id_address);
        address.setText(addresses.get(position).getAddressLine(0));
        return v;
    }
}
