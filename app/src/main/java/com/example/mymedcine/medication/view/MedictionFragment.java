package com.example.mymedcine.medication.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymedcine.R;
import com.example.mymedcine.database.ConcreteLocalSource;
import com.example.mymedcine.homescreen.HomeActivity;
import com.example.mymedcine.medication.presenter.MedecationInterface;
import com.example.mymedcine.medication.presenter.MedecationPresenter;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.Repository;
import com.example.mymedcine.network.FireBaseConnection;
import com.google.android.material.button.MaterialButton;

import java.util.Arrays;
import java.util.List;

public class MedictionFragment extends Fragment implements OnMedecationInterface {

    private final static String TAG = "mohamed";

    RecyclerView recyclerView;
    MedicationAdapter adapter;
    MaterialButton btnAddMed;
    MedecationInterface presenter;
    List<Drug> drugList;

    Drug drug = new Drug("l9l", "aaaz", "200", "g", "oeoo");
    Drug drug2 = new Drug("w8w", "aaaa", "200", "g14", "ooor");
    Drug drug3 = new Drug("llel", "11da", "200", "3g", "oooe");
    Drug drug4 = new Drug("l3lq", "adaa", "200", "3g", "oood");
    Drug drug5 = new Drug("lllx", "aaac", "200", "2g", "oooc");
    Drug drug6 = new Drug("555x", "wewe", "200", "2g", "active");
    Drug drug7 = new Drug("999x", "aaac", "200", "2g", "active");
    Drug drug8 = new Drug("koko", "aaac", "200", "2g", "active");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mediction, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvMedication);
        btnAddMed = view.findViewById(R.id.btnAddMed);
        btnAddMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_medictionFragment_to_addMedecineFragment);
            }
        });
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        presenter = new MedecationPresenter(getContext(), Repository.getInstance(FireBaseConnection.getInstance(),ConcreteLocalSource.getInstance(getContext()),getContext())
                ,this);

        presenter.getMedsToSendIt(this);

    }

    @Override
    public void showData(List<Drug> drugs) {
        this.drugList = drugs;

        adapter = new MedicationAdapter(drugs, getContext(), this);
        recyclerView.setAdapter(adapter);
//        Log.i(TAG, "showData: " + drugs.size());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onMedecineClickListener(View view, int position) {

        Drug drugClick = drugList.get(position);
        Bundle outcomeBundle = new Bundle();
        outcomeBundle.putSerializable("drug", drugClick);
        Navigation.findNavController(view).navigate(R.id.action_medictionFragment_to_displayDrugDetailsFragment, outcomeBundle);
    }

    @Override
    public void addMed(Drug drug) {
        presenter.addMedToSendIt(drug);
    }

}