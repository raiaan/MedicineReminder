package com.example.mymedcine.medication.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymedcine.R;
import com.example.mymedcine.model.Drug;

import java.util.List;

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.ViewHolder> {

    private List<Drug> drugList;
    private Context context;
    private OnMedecationInterface medicationInterface;

    public MedicationAdapter(List<Drug> drugList, Context context, OnMedecationInterface medicationInterface) {
        this.drugList = drugList;
        this.context = context;
        this.medicationInterface = medicationInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recycleview, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.medication_row,recycleview,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Drug drug = drugList.get(position);
        holder.txtMedDetail.setText(drug.getReasons());
        holder.txtMedName.setText(drug.getName());
        holder.txtMedDosage.setText(drug.getStrongUnit());
        holder.imgMed.setImageResource(R.drawable.nurse);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medicationInterface.onMedecineClickListener();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout layout;
        ImageView imgMed;
        TextView txtMedName;
        TextView txtMedDosage;
        TextView txtMedDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.layoutRowMedication);
            imgMed = itemView.findViewById(R.id.imgMedicationIcon);
            txtMedName = itemView.findViewById(R.id.txtMedName);
            txtMedDosage = itemView.findViewById(R.id.txtDosage);
            txtMedDetail = itemView.findViewById(R.id.txtDetails);
        }
    }
}
