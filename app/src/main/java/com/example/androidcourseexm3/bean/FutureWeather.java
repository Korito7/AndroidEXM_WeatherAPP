package com.example.androidcourseexm3.bean;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.androidcourseexm3.R;

import java.util.HashMap;

public class FutureWeather{
    String weatherIcon;
    String description;
    String temperature;

    HashMap<String, Integer> imgMap = new HashMap<>();

    public FutureWeather(String weatherIcon, String description, String temperature){
        this.weatherIcon = weatherIcon;
        this.description = description;
        this.temperature = temperature;

        imgMap.put("img1", R.drawable.sun);
    }

    public String getDescription() {
        return description;
    }

    public String getTemperature() {
        return temperature;
    }

    public int getWeatherIcon() {
        int mIcon = imgMap.get(this.weatherIcon);
        return mIcon;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }
}
