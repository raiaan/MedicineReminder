package com.example.mymedcine.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mymedcine.R;

import java.util.List;

public class SimpleSpinnerAdapter extends ArrayAdapter<String> {
    List<String> names;
    LayoutInflater inflater;
    ViewHolder holder = null;
    Context context;
    public SimpleSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        inflater = ((Activity) context).getLayoutInflater();
        this.context = context;
        names = objects;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        String types = names.get(position);
        View row = convertView;

        if (null == row) {
            holder = new ViewHolder();
            row = inflater.inflate(R.layout.custome_types_spinner, parent, false);
            holder.name = row.findViewById(R.id.text_type);
            holder.imgThumb =  row.findViewById(R.id.icon_type);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        holder.name.setText(types);
        holder.imgThumb.setImageDrawable(IconsFactory.getIcon(context, types));

        return row;
    }

    static class ViewHolder {
        TextView name, place;
        ImageView imgThumb;
    }
}
