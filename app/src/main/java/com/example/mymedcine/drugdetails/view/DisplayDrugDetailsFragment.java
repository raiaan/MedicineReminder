package com.example.mymedcine.drugdetails.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymedcine.R;
import com.example.mymedcine.add_dose.views.AddDoseDialog;
import com.example.mymedcine.database.ConcreteLocalSource;
import com.example.mymedcine.drugdetails.presenter.DisplayDrugPresentable;
import com.example.mymedcine.drugdetails.presenter.DisplayDrugPresenter;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.Prescription;
import com.example.mymedcine.model.Repository;
import com.example.mymedcine.network.FireBaseConnection;
import com.example.mymedcine.refill_dialog.views.RefillDialog;
import com.example.mymedcine.utils.Communator;
import com.example.mymedcine.utils.IconsFactory;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DisplayDrugDetailsFragment extends Fragment implements DrugDisplayer{

    TextView drugName ,drugState;
    TextView lastTaken,lastTakenLabel;
    ImageView iconType,editItem , removeItem;
    Button suspendBTN ,refillBTN ,addDoseBTN;
    TextView strength;
    TextView occurence,reminders;
    TextView instructions , reasons;
    TextView refills;
    DisplayDrugPresentable presentable;
    RefillDialog refillDialog;
    AddDoseDialog addDoseDialog;
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
        presentable = new DisplayDrugPresenter(this, Repository.getInstance(FireBaseConnection.getInstance(),ConcreteLocalSource.getInstance(getContext())
                , getContext()));
        return view;
    }
    void initUI(View view){
        iconType = view.findViewById(R.id.display_drug_details_icon);
        drugName = view.findViewById(R.id.display_drug_details_title);
        lastTaken = view.findViewById(R.id.display_drug_details_last_taken);
        lastTakenLabel = view.findViewById(R.id.display_drug_details_last_taken_label);
        drugState = view.findViewById(R.id.display_drug_details_state);
        suspendBTN = view.findViewById(R.id.display_drug_details_change);
        occurence = view.findViewById(R.id.display_drug_details_occurence);
        reminders = view.findViewById(R.id.display_drug_details_reminding_hours);
        instructions = view.findViewById(R.id.display_drug_details_instruct);
        reasons = view.findViewById(R.id.display_drug_details_reasons);
        refills = view.findViewById(R.id.display_drug_details_refills);
        editItem = view.findViewById(R.id.display_drug_edit);
        removeItem = view.findViewById(R.id.display_drug_details_delete);
        strength = view.findViewById(R.id.display_drug_details_strength);
        refillBTN = view.findViewById(R.id.display_drug_details_refill_btn);
        addDoseBTN = view.findViewById(R.id.display_drug_add_dose);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initUI(view);
        if(getArguments() != null){
            displayDrugDetails( (Drug) getArguments().getSerializable("drug"));
        }
    }
    private void diplayBasicDrugInfo(String name , String type,String strengthUnit , String strengthValue){
        drugName.setText(name);
        iconType.setImageDrawable(IconsFactory.getIcon(this.getContext() , type));
        strength.setText(strengthValue+strengthUnit);
    }
    @Override
    public void displayDrugDetails(Drug drug) {
        if(drugName !=null){
            diplayBasicDrugInfo(drug.getName() , drug.getType(),drug.getStrongUnit(),drug.getStrongValue());
            refillDialog = new RefillDialog(getContext(),drug,this);
            addDoseDialog = new AddDoseDialog(getContext() , drug,this);
            drugState.setText(" ("+drug.getState()+")");
            if (drug.lastTimeTaken != null){
                lastTakenLabel.setVisibility(View.VISIBLE);
                lastTaken.setVisibility(View.VISIBLE);
                lastTaken.setText(drug.lastTimeTaken+" By "+drug.lastTimeDoseGiver);
            }
            if (drug.getHours()!= null){
                reminders.setText(new SimpleDateFormat("hh:mm:ss").format(new Date(drug.getHours().get(0))));
            }
            if (drug.getInstructions()!= null){
                instructions.setText(drug.getInstructions());
            }
            if (drug.getReasons() != null){
                reasons.setText(drug.getReasons());
            }
            editItem.setOnClickListener(view -> {
                navigateToEditDrug(drug);
            });
            removeItem.setOnClickListener(view -> presentable.deleteDrug(drug));
            changeStateUi(drug);
            changeQTYcallback(drug.getLeft(),drug.getRefillRemindCount());
            suspendBTN.setOnClickListener(view -> presentable.changeState(drug));
            refillBTN.setOnClickListener(subView -> {
                int width = (int)(getContext().getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getContext().getResources().getDisplayMetrics().heightPixels*0.90);
                getActivity().getWindow().setLayout(width, height);
                refillDialog.show();
            });
            addDoseBTN.setOnClickListener(view -> {
                addDoseDialog.show();
            });
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

    @Override
    public void changeStateCallback(Drug drug) {
        changeStateUi(drug);
        ((TextView)getView().findViewById(R.id.display_drug_details_state)).setText(drug.getState());
    }

    @Override
    public void addDoseCallback(Drug drug) {
        displayDrugDetails(drug);
    }

    private void changeStateUi(Drug drug){
        if (drug.getState().equals("active")){
            suspendBTN.setText("Suspend");
            suspendBTN.setTextColor(getResources().getColor(R.color.red_danger) );
        }else{
            suspendBTN.setText("Activate");
            suspendBTN.setTextColor(getResources().getColor(R.color.dark_navy_blue) );
        }
    }

    @Override
    public void changeQTYcallback(int items, int remindnumber) {
        if (remindnumber != -1){
            refills.setText(items+"\nremind at: "+remindnumber);
        }else{
            refills.setText(String.valueOf(items));
        }
    }
}