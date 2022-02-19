package com.example.mymedcine.medication.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mymedcine.R;
import com.example.mymedcine.database.ConcreteLocalSource;
import com.example.mymedcine.medication.presenter.MedecationInterface;
import com.example.mymedcine.medication.presenter.MedecationPresenter;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.Repository;
import com.google.android.material.button.MaterialButton;

import java.util.Arrays;
import java.util.List;

public class MedictionFragment extends Fragment implements OnMedecationInterface {

    private final static String TAG = "mohamed";

    RecyclerView recyclerView;
    MedicationAdapter adapter;
    MaterialButton btnAddMed;
    MedecationInterface presenter;
    List<Drug> drugss;
    Drug drug = new Drug("lll","aaa","eee","ooo");

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
                Navigation.findNavController(view).navigate(R.id.action_medictionFragment_to_addHealthTakerFragment);
            }
        });
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        presenter = new MedecationPresenter(this, Repository.getInstance(ConcreteLocalSource.getInstance(getContext())
                , getContext()));

        presenter.getMeds();
        showData(null);
        addMed(drug);
    }

    @Override
    public void showData(List<Drug> drugs) {
        drugss = Arrays.asList(new Drug("conge", "bard", "1g", "taken"),
                new Drug("conge", "bard", "1g", "active"),
                new Drug("biad", "bard", "1g", "denied"),
                new Drug("npoh", "bard", "2g", "active"),
                new Drug("vvvv", "bard", "1g", "denied"));
        adapter = new MedicationAdapter(drugss, getContext(), this);
        recyclerView.setAdapter(adapter);
        Log.i(TAG, "showData: " + drugss.size());
        //adapter.notifyDataSetChanged();

    }

    @Override
    public void onMedecineClickListener(View view) {
        Toast.makeText(getContext(), "777", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addMed(Drug drug) {
        presenter.addMed(drug);
    }


}