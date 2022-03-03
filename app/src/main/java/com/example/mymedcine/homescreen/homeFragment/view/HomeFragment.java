package com.example.mymedcine.homescreen.homeFragment.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mymedcine.R;
import com.example.mymedcine.database.ConcreteLocalSource;
import com.example.mymedcine.homescreen.homeFragment.presenter.HomeFragmentPresenter;
import com.example.mymedcine.homescreen.homeFragment.presenter.HomeFragmentPresenterInterface;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.DrugsBerDay;
import com.example.mymedcine.model.Repository;
import com.example.mymedcine.network.FireBaseConnection;
import com.example.mymedcine.network.FireBaseConnectionInterface;
import com.example.mymedcine.network.FirebaseConnectionDelegated;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarListener;

public class HomeFragment extends Fragment implements HomeFragmentViewInterface,FirebaseConnectionDelegated{

    Calendar endDate;
    Calendar startDate;
    HorizontalCalendar horizontalCalendar;

    RecyclerView mainItem;
    MainItemAdapter adapter;

    String day;
    final String TAG = "TAG";

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
                day = date.toString().substring(0,3);
                Toast.makeText(view.getContext(), day, Toast.LENGTH_SHORT).show();
            }
        });

        mainItem = view.findViewById(R.id.mainItemRV);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        adapter  = new MainItemAdapter(new ArrayList<DrugsBerDay>(),getContext(),this);
        mainItem.setAdapter(adapter);
        presenter.getAllDrugsOfTheDay(this, day);
        mainItem.setLayoutManager(linearLayout);
        mainItem.setFitsSystemWindows(true);
    }

    @Override
    public void onCompleteResultSuccess(FirebaseUser user) {

    }

    @Override
    public void onFailureResult(String errorMessage) {

    }

    @Override
    public void desplayDrugs(List<DrugsBerDay> items) {
        /*List<DrugsBerDay> items = convertDrugsIntoDrugsBerDay(drugs);
        items.add(new DrugsBerDay("0:00", drugs));
        items.add(new DrugsBerDay("1:00", drugs));
        items.add(new DrugsBerDay("2:00", drugs));
        for(int i = 0; i < drugs.size(); i ++){
            String[] times =  items.get(i).getDailyDrugs().get(i).getStringHours().split("\n");
            for (int j = 0; j < times.length; j ++){
                Log.i(TAG, "desplayDrugs: drug " + i + " time " + j + " is " + times[j]);
                Date date = new Date(Long.parseLong(times[j]));
                Log.i(TAG, "desplayDrugs: Date is " + date.toString().substring(11,16));
                Log.i(TAG, "desplayDrugs: time is " + date.getTime());
            }
        }*/

      //  List<DrugsBerDay> items = convertDrugsIntoDrugsBerDay(drugs);
        Log.i(TAG, "desplayDrugs: items  "  + items.size());
        adapter = new MainItemAdapter(items, getContext(), this);
        mainItem.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void desplayDummyData(List<Drug> drugs) {
        System.out.println(drugs.size());
        Log.i(TAG, "displayDummyData: " +  drugs.size() );
    }
}

