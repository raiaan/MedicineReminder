package com.example.mymedcine.drugdetails.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymedcine.R;
import com.example.mymedcine.database.ConcreteLocalSource;
import com.example.mymedcine.drugdetails.presenter.DisplayDrugPresentable;
import com.example.mymedcine.drugdetails.presenter.DisplayDrugPresenter;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.Prescription;
import com.example.mymedcine.model.Repository;
import com.example.mymedcine.utils.Communator;
import com.example.mymedcine.utils.IconsFactory;

import org.w3c.dom.Text;

public class DisplayDrugDetailsFragment extends Fragment implements DrugDisplayer{

    TextView drugName ,drugState;
    TextView lastTaken;
    ImageView iconType,editItem , removeItem;
    Button suspendBTN;
    TextView occurence,reminders;
    TextView instructions , reasons;
    TextView refills;
    DisplayDrugPresentable presentable;
    public DisplayDrugDetailsFragment() {
        // Required empty public constructor
    }

    public static DisplayDrugDetailsFragment newInstance(String name) {
        DisplayDrugDetailsFragment fragment = new DisplayDrugDetailsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display_drug_details, container, false);
        presentable = new DisplayDrugPresenter(this, Repository.getInstance(ConcreteLocalSource.getInstance(getContext())
                , getContext()));
        return view;
    }
    void initUI(View view){
        iconType = view.findViewById(R.id.display_drug_details_icon);
        drugName = view.findViewById(R.id.display_drug_details_title);
        lastTaken = view.findViewById(R.id.display_drug_details_last_taken);
        drugState = view.findViewById(R.id.display_drug_details_state);
        suspendBTN = view.findViewById(R.id.display_drug_details_change);
        occurence = view.findViewById(R.id.display_drug_details_occurence);
        reminders = view.findViewById(R.id.display_drug_details_reminding_hours);
        instructions = view.findViewById(R.id.display_drug_details_instruct);
        reasons = view.findViewById(R.id.display_drug_details_reasons);
        refills = view.findViewById(R.id.display_drug_details_refills);
        editItem = view.findViewById(R.id.display_drug_edit);
        removeItem = view.findViewById(R.id.display_drug_details_delete);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initUI(view);
        if(getArguments() != null){
            displayDrugDetails( (Drug) getArguments().getSerializable("drug"));
        }
    }

    @Override
    public void displayDrugDetails(Drug drug) {
        if(drugName !=null){
            drugName.setText(drug.getName());
            drugState.setText(" ("+drug.getState()+")");
            iconType.setImageDrawable(IconsFactory.getIcon(this.getContext() , drug.getType()));
            if (drug.getLastTime() != null){
                lastTaken.setText(drug.getLastTime().toString());
            }
            if (drug.getHours()!= null){
                reminders.setText(drug.getStringHours());
            }
            if (drug.getInstructions()!= null){
                instructions.setText(drug.getInstructions());
            }
            if (drug.getReasons() != null){
                reasons.setText(drug.getReasons());
            }
            if (drug.getLeft() != null){
                refills.setText(drug.getLeft());
            }
            editItem.setOnClickListener(view -> {
                navigateToEditDrug(drug);
            });
            refills.setText(drug.getLeft());
            removeItem.setOnClickListener(view -> presentable.deleteDrug(drug));
        }
    }

    @Override
    public void deleteDrugSuccess() {
        Navigation.findNavController(this.getView()).popBackStack();
    }

    @Override
    public void navigateToEditDrug(Drug drug) {
        Bundle outcomeBundle = new Bundle();
        outcomeBundle.putSerializable("drug", drug);
        Navigation.findNavController(this.getView()).navigate(R.id.action_displayDrugDetailsFragment_to_editDrugFramgent, outcomeBundle);
    }
}