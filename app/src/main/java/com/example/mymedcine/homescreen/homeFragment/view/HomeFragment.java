package com.example.mymedcine.homescreen.homeFragment.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymedcine.R;

import java.util.Calendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;

public class HomeFragment extends Fragment {

    Calendar endDate;
    Calendar startDate;
    HorizontalCalendar horizontalCalendar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** end after 1 month from now */
        endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        /** start before 1 month from now */
        startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        horizontalCalendar = new HorizontalCalendar.Builder(view, R.id.calendarView)
                .startDate(startDate.getTime())
                .endDate(endDate.getTime())
                .build();
    }
}