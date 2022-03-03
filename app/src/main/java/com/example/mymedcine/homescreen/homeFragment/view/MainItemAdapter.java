package com.example.mymedcine.homescreen.homeFragment.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymedcine.R;
import com.example.mymedcine.medication.view.MedicationAdapter;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.DrugsBerDay;

import java.sql.Struct;
import java.util.List;

public class MainItemAdapter extends RecyclerView.Adapter<MainItemAdapter.MainItemViewHolder> {

    private List<DrugsBerDay> list;
    Context context;
    HomeFragmentViewInterface fragment;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public MainItemAdapter(List<DrugsBerDay> list, Context context, HomeFragmentViewInterface fragment) {
        this.list = list;
        System.out.println(list.size());
        this.context = context;
        this.fragment = fragment;
    }

    public  void setData(List<DrugsBerDay>medicineList){
        this.list = medicineList;
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
        if(list != null){
            DrugsBerDay item = list.get(position);
            LinearLayoutManager linearLayout = new LinearLayoutManager(context);
            linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
            holder.subItemRV.setAdapter(new SubItemAdapter(item.getDailyDrugs(), context, fragment,item.getTime()));
            holder.subItemRV.setLayoutManager(linearLayout);
            holder.subItemRV.setHasFixedSize(true);
            holder.txtTime.setText(item.getTime());
        }

        /*layoutManager.setInitialPrefetchItemCount(item.getDailyDrugs().size());
        holder.subItemRV.setLayoutManager(layoutManager);
        SubItemAdapter subAdapter = new SubItemAdapter(item.getDailyDrugs(), context , fragment);
        holder.subItemRV.setAdapter(subAdapter);
        holder.subItemRV.setRecycledViewPool(viewPool);*/
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
