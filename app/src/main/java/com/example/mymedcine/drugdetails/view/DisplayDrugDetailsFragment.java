package com.example.mymedcine.drugdetails.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymedcine.R;
import com.example.mymedcine.drugdetails.presenter.DisplayDrugPresentable;
import com.example.mymedcine.drugdetails.presenter.DisplayDrugPresenter;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.Prescription;
import com.example.mymedcine.utils.Communator;
import com.example.mymedcine.utils.IconsFactory;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DisplayDrugDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayDrugDetailsFragment extends Fragment implements DrugDisplayer{

    TextView drugName ,drugState;
    TextView lastTaken;
    ImageView iconType,editItem , removeItem;
    Button suspendBTN;
    TextView occurence,reminders;
    TextView instructions , reasons;
    TextView refills;
    DisplayDrugPresentable presentable;
    Communator communator;
    public DisplayDrugDetailsFragment() {
        // Required empty public constructor
    }

    public static DisplayDrugDetailsFragment newInstance(Drug drug) {
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
        communator = (Communator)getActivity();
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
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initUI(view);
    }

    @Override
    public void displayDrugDetails(Prescription prescription, int position) {
        Drug drug = prescription.getDrugs().get(position);
        if(drugName !=null){
            drugName.setText(drug.getName());
            drugState.setText(" ("+drug.getState()+")");
            iconType.setImageDrawable(IconsFactory.getIcon(this.getContext() , drug.getType()));
            lastTaken.setText(drug.getLastTime().toString());
            occurence.setText(drug.getRemindingTimes().occurrence);
            reminders.setText(drug.getRemindingTimes().getHours());
            instructions.setText(drug.getInstructions());
            reasons.setText(drug.getReasons());
            refills.setText(drug.getLeft());
            editItem.setOnClickListener(view -> {
                Log.v("edit click listener","true");
                communator.sendMessage(prescription);
            });
        }
    }
}