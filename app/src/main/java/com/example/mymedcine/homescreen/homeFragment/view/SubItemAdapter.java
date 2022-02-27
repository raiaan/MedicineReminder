package com.example.mymedcine.homescreen.homeFragment.view;

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
import com.example.mymedcine.utils.IconsFactory;

import java.util.List;

public class SubItemAdapter extends RecyclerView.Adapter<SubItemAdapter.SubItemViewHolder> {

    List<Drug> drugs;
    Context context;
    HomeFragmentViewInterface fragment;

    public SubItemAdapter(List<Drug> drugs, Context context, HomeFragmentViewInterface fragment) {
        this.drugs = drugs;
        System.out.println(drugs.size());
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public SubItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.medication_row, parent, false);
        SubItemViewHolder viewHolder = new SubItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubItemViewHolder holder, int position) {
        Drug drug = drugs.get(position);
        System.out.println(drug.getName());
        holder.txtName.setText(drug.getName());
        holder.txtDetails.setText(drug.getStrongValue() + " " + drug.getStrongUnit());
        holder.icon.setImageDrawable(IconsFactory.getIcon(context, drug.getType()));
    }

    @Override
    public int getItemCount() {
        return drugs.size();
    }

    class SubItemViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout layout;
        TextView txtName;
        ImageView icon;
        TextView txtDetails;

        public SubItemViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.subItemLayout);
            txtName = itemView.findViewById(R.id.txtName);
            txtDetails = itemView.findViewById(R.id.txtDetails);
            icon = itemView.findViewById(R.id.imgIcon);
        }
    }
}
