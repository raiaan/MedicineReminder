package com.example.mymedcine.homescreen.homeFragment.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymedcine.R;
import com.example.mymedcine.medication.view.MedicationAdapter;
import com.example.mymedcine.model.Drug;

import java.sql.Struct;
import java.util.List;

public class MainItemAdapter extends RecyclerView.Adapter<MainItemAdapter.MainItemViewHolder> {

    private List<Drug> list;
    Context context;
    HomeFragmentViewInterface fragment;

    public MainItemAdapter(List<Drug> list, Context context, HomeFragmentViewInterface fragment) {
        this.list = list;
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public MainItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.home_item_layout, parent, false);
        MainItemViewHolder viewHolder = new MainItemViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MainItemViewHolder holder, int position) {
        holder.txtTime.setText("" + position + " : 00");
        LinearLayoutManager linearLayout = new LinearLayoutManager(context);
        linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        holder.subItemRV.setAdapter(new SubItemAdapter(list, context, fragment));
        holder.subItemRV.setLayoutManager(linearLayout);
        holder.subItemRV.setHasFixedSize(true);
    }

    @Override
    public int getItemCount() {
        return list==null? 0 :list.size();
    }

    class MainItemViewHolder extends RecyclerView.ViewHolder{

        LinearLayout linearLayout;
        TextView txtTime;
        RecyclerView subItemRV;

        public MainItemViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.linearLayout);
            txtTime = itemView.findViewById(R.id.txtTime);
            subItemRV = itemView.findViewById(R.id.subItemRV);
        }
    }
}
