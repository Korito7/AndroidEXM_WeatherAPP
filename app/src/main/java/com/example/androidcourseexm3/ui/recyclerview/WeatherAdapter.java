package com.example.androidcourseexm3.ui.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidcourseexm3.R;
import com.example.androidcourseexm3.bean.FutureWeather;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private List<FutureWeather> mWeatherList;

    public WeatherAdapter(List<FutureWeather> weatherList){
        this.mWeatherList = weatherList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_futureweather, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FutureWeather futureWeather = mWeatherList.get(position);
        holder.weatherIcon.setImageResource(futureWeather.getWeatherIcon());
        holder.description.setText(futureWeather.getDescription());
        holder.temperature.setText(futureWeather.getTemperature());
    }

    @Override
    public int getItemCount() {
        return mWeatherList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView weatherIcon;
        TextView description;
        TextView temperature;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weatherIcon = (ImageView)itemView.findViewById(R.id.img_weatherIcon);
            description = (TextView)itemView.findViewById(R.id.text_description);
            temperature = (TextView)itemView.findViewById(R.id.text_temperature);
        }
    }

}
