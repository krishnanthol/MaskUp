package com.example.maskup;

import java.util.ArrayList;

public class Place
{
    String name;
    ArrayList<Boolean> days;
    int hours;
    boolean crowded;
    boolean maskMandate;

    public Place(String name, ArrayList<Boolean> days, int hours, boolean crowded, boolean maskMandate)
    {
        this.name = name;
        this.days = days;
        this.hours = hours;
        this.crowded = crowded;
        this.maskMandate = maskMandate;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Boolean> getDays()
    {
        return days;
    }

    public int getHours()
    {
        return hours;
    }

    public boolean isCrowded()
    {
        return crowded;
    }

    public boolean isMaskMandate()
    {
        return maskMandate;
    }
}
