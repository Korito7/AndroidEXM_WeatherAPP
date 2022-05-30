package com.example.androidcourseexm3.ui.home;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidcourseexm3.R;
import com.example.androidcourseexm3.bean.FutureWeather;
import com.example.androidcourseexm3.ui.recyclerview.WeatherAdapter;
import com.example.androidcourseexm3.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private List<FutureWeather> mWeatherList = new ArrayList<>();
    private ImageView img_weatherIcon;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //StatusBarUtil.fullScreen(getActivity());
        //StatusBarUtil.addStatusViewWithColor(getActivity(), R.color.Transparent);

        img_weatherIcon = (ImageView)root.findViewById(R.id.imgView_weather);
        img_weatherIcon.setImageResource(R.drawable.sun);
        FutureWeather test = new FutureWeather("img1", "testinfo", "testtemp");
        for (int i = 0;i < 10;i++){
            mWeatherList.add(test);
        }

        RecyclerView recyclerView = (RecyclerView)root.findViewById(R.id.list_futureWeather);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        WeatherAdapter weatherAdapter = new WeatherAdapter(mWeatherList);
        recyclerView.setAdapter(weatherAdapter);
        return root;
    }
}