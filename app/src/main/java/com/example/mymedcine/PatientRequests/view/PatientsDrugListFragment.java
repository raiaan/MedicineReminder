package com.example.mymedcine.PatientRequests.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymedcine.PatientRequests.presenter.PatientDrugListInterface;
import com.example.mymedcine.R;
import com.example.mymedcine.healthTaker.DAOFirebase;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.Prescription;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PatientsDrugListFragment extends Fragment{


    private RecyclerView recyclerView;
    private PatientRequestAdapter adapter;
    private ArrayList<Prescription> healthTakerList;
    //private ArrayList<Drug> drugs;
    private PatientDrugListInterface presenter;

    DAOFirebase daoFirebase;
    FirebaseRecyclerAdapter firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<Prescription> options;
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_patients_drug_list, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseRecyclerAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        firebaseRecyclerAdapter.stopListening();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvPatientDrug);
        recyclerView.setHasFixedSize(true);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        daoFirebase = new DAOFirebase();
        options = new FirebaseRecyclerOptions.Builder<Prescription>()
                    .setQuery(daoFirebase.get(), new SnapshotParser<Prescription>() {
                        @NonNull
                        @Override
                        public Prescription parseSnapshot(@NonNull DataSnapshot snapshot) {
                            Prescription healthTaker = snapshot.getValue(Prescription.class);
                            healthTaker.setKey(snapshot.getKey());

                            //Log.i("TAG", "parseSnapshot: "+healthTaker.getAdmin_mail());

                            return healthTaker;
                        }
                    }).build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter(options) {

            @Override
            protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull Object model) {

                Prescription healthTaker = (Prescription) model;
                Drug drugs = ((Prescription) model).getDrugs().get(position);
                Log.i("TAG", "onBindViewHolder: ");

                PatientRequestAdapter.ViewHolder viewHolder = (PatientRequestAdapter.ViewHolder) holder;
                //viewHolder.senderName.setText(healthTaker.getSenderName());
                viewHolder.senderEmail.setText(healthTaker.getAdmin_mail());
                viewHolder.recieverEmail.setText(healthTaker.getPatient());
                //viewHolder.senderName.setText(drugs.getName());
            }

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerview, int viewType) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.patient_drugs_item,recyclerview,false);

                return new PatientRequestAdapter.ViewHolder(view);
            }
            @Override
            public int getItemViewType(int position) {
                return firebaseRecyclerAdapter.getItemCount();
            }



            @Override
            public void onDataChanged() {
                super.onDataChanged();
                Toast.makeText(getContext(), "Data has changed", Toast.LENGTH_SHORT).show();
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);



        //retrieveDataFromFirebase();
    }

    private void retrieveDataFromFirebase() {

        daoFirebase.getPatientDrugs("medo@gmail.com").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                healthTakerList = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()){

                    Prescription healthTaker = data.getValue(Prescription.class);
                        healthTakerList.add(healthTaker);

                }

                adapter = new PatientRequestAdapter(healthTakerList, getContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



}