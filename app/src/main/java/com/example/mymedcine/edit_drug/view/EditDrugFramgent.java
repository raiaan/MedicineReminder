package com.example.mymedcine.edit_drug.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;


import com.example.mymedcine.R;
import com.example.mymedcine.database.ConcreteLocalSource;
import com.example.mymedcine.edit_drug.presenter.UpdateDrugPresenter;
import com.example.mymedcine.edit_drug.presenter.UpdateDrugPresenterInterface;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.Repository;
import com.example.mymedcine.network.FireBaseConnection;
import com.example.mymedcine.utils.IconsFactory;
import com.example.mymedcine.utils.SimpleSpinnerAdapter;
import com.example.mymedcine.utils.ViewDrugConvertor;

import java.util.Arrays;

public class EditDrugFramgent extends Fragment implements EditDrugInterface{


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SimpleSpinnerAdapter typesAdapter;

    private UpdateDrugPresenterInterface presenterInterface;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Drug drug;
    Button updateDrug;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenterInterface = new UpdateDrugPresenter(this,Repository.getInstance(FireBaseConnection.getInstance(),
                ConcreteLocalSource.getInstance(getContext()),getContext()));
       }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_medecine, container, false);
        typesAdapter = new SimpleSpinnerAdapter(this.getActivity(),R.layout.custome_types_spinner, Arrays.asList(IconsFactory.getDrugIconsNames()));
        ((Spinner)view.findViewById(R.id.edit_drug_strength_unit)).setAdapter(
                ArrayAdapter.createFromResource(getContext(),R.array.units, android.R.layout.simple_spinner_item)
        );
        ((Spinner) view.findViewById(R.id.edit_drug_types)).setAdapter(typesAdapter);
        ((Spinner)view.findViewById(R.id.edit_drug_reminding_hours_per_day)).setAdapter(
                ArrayAdapter.createFromResource(getContext(),R.array.reminding_times_of_hours, android.R.layout.simple_spinner_item)
        );
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenterInterface = new UpdateDrugPresenter(this, Repository.getInstance(FireBaseConnection.getInstance()
                ,ConcreteLocalSource.getInstance(getContext())
                , getContext()));
        drug = (Drug) getArguments().getSerializable("drug");
        ((ImageView)view.findViewById(R.id.add_drug_close_btn)).setOnClickListener(view1 -> Navigation.findNavController(view1).popBackStack());
        FillDrugDataHelper.fillData(view,drug);
        updateDrug = view.findViewById(R.id.save_drug);
        updateDrug.setOnClickListener(chieldView -> presenterInterface.updateDrug(ViewDrugConvertor.convertToDrug(view)));
    }

    @Override
    public void updateView(Drug drug) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("drug",drug);
        Navigation.findNavController(getView()).navigate(R.id.action_editDrugFramgent_to_displayDrugDetailsFragment,bundle);
    }
}