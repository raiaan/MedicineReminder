package com.example.mymedcine.homescreen.homeFragment.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mymedcine.R;
import com.example.mymedcine.database.ConcreteLocalSource;
import com.example.mymedcine.homescreen.homeFragment.presenter.HomeFragmentPresenter;
import com.example.mymedcine.homescreen.homeFragment.presenter.HomeFragmentPresenterInterface;
import com.example.mymedcine.model.Repository;
import com.example.mymedcine.network.FireBaseConnection;
import com.example.mymedcine.network.FireBaseConnectionInterface;
import com.example.mymedcine.network.FirebaseConnectionDelegated;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarListener;

public class HomeFragment extends Fragment implements HomeFragmentViewInterface,FirebaseConnectionDelegated{

    Calendar endDate;
    Calendar startDate;
    HorizontalCalendar horizontalCalendar;

    RecyclerView mainItem;

    HomeFragmentPresenterInterface presenter;
    FireBaseConnectionInterface fireBaseConnection;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** end after 1 month from now */
        endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        /** start now */
        startDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        presenter = new HomeFragmentPresenter(getContext(),Repository.getInstance(FireBaseConnection.getInstance(), ConcreteLocalSource.getInstance(getContext()),getContext()),this);

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
                .datesNumberOnScreen(5)
                .showMonthName(false)
                .textColor(R.color.black, R.color.teal_200)
                .build();
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Date date, int position) {
                String day = date.toString().substring(0,3);
                Toast.makeText(view.getContext(), day, Toast.LENGTH_SHORT).show();
                presenter.getAllDrugsOfTheDay(HomeFragment.this);
            }
        });

        mainItem = view.findViewById(R.id.mainItemRV);
    }

    @Override
    public void onCompleteResultSuccess(FirebaseUser user) {

    }

    @Override
    public void onFailureResult(String errorMessage) {

    }
}