package com.example.mymedcine.PatientRequests.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymedcine.R;
import com.example.mymedcine.model.Prescription;

import java.util.List;

public class PatientRequestAdapter extends RecyclerView.Adapter<PatientRequestAdapter.ViewHolder> {

    private List<Prescription> healthTakerList;
    private Context context;

    public PatientRequestAdapter(List<Prescription> healthTakerList, Context context) {
        this.healthTakerList = healthTakerList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerview, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.patient_drugs_item,recyclerview,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Prescription healthTaker = healthTakerList.get(position);
        holder.senderName.setText(healthTaker.patient);
        holder.senderEmail.setText(healthTaker.admin_mail);
       // holder.recieverEmail.setText(healthTaker.getDrugs().get(position));
    }

    @Override
    public int getItemCount() {
        return healthTakerList == null? 0 :healthTakerList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

         EditText senderEmail, recieverEmail, senderName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            senderEmail = itemView.findViewById(R.id.edtSenderEmail);
            recieverEmail = itemView.findViewById(R.id.edtRecevierEmail);
            senderName = itemView.findViewById(R.id.edtSenderName);
        }
    }
}
