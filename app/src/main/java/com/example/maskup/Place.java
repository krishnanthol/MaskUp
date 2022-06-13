package com.example.maskup;

import java.util.ArrayList;

public class Place
{
    String name;
    ArrayList<Boolean> days;
    int icon;
    int hours;
    boolean crowded;
    boolean maskMandate;

    public Place(String name, ArrayList<Boolean> days, int icon, int hours, boolean crowded, boolean maskMandate)
    {
        this.name = name;
        this.days = days;
        this.icon = icon;
        this.hours = hours;
        this.crowded = crowded;
        this.maskMandate = maskMandate;
    }
}
