package com.example.maskup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
    static String zipCode;
    static String county;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Geoapify API Key - a441e89ce9cf4d15b174eda7eec5beaf

        GetCounty getCounty = new GetCounty();
        getCounty.execute();
    }
}