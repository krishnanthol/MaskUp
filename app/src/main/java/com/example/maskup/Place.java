package com.example.maskup;

public class Place
{
    String name;
    int hours;
    boolean crowded;
    boolean maskMandate;

    public Place(String name, int hours, boolean crowded, boolean maskMandate)
    {
        this.name = name;
        this.hours = hours;
        this.crowded = crowded;
        this.maskMandate = maskMandate;
    }

    public String getName()
    {
        return name;
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
