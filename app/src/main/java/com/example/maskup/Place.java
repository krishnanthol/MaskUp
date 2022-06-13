package com.example.maskup;

public class Place
{
    String name;
    int icon;
    int hours;
    boolean crowded;
    boolean maskRequirement;

    public Place(String name, int icon, int hours, boolean crowded, boolean maskRequirement)
    {
        this.name = name;
        this.icon = icon;
        this.hours = hours;
        this.crowded = crowded;
        this.maskRequirement = maskRequirement;
    }
}
