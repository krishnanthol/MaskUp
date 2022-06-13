package com.example.maskup;

public class Weather
{
    String hour;
    String temp;
    String image;
    String description;

    public Weather (String hour, String temp, String image,String description)
    {
        this.hour = hour;
        this.temp = temp;
        this.image = image;
        this.description = description;
    }

    public String getHour()
    {
        return hour;
    }

    public String getTemp()
    {
        return temp;
    }

    public String getImage()
    {
        return image;
    }

    public String getDescription()
    {
        return description;
    }
}
