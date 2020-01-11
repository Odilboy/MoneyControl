package com.example.com.example.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gettersandsetters.DailyUpdates;
import com.example.moneycontrol.R;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>  {

    private LayoutInflater mInflater;
    private ArrayList<DailyUpdates> dailyUpdates;
    public static Context context;

    public RecyclerAdapter(Context context, ArrayList<DailyUpdates> dailyUpdates) {
        this.dailyUpdates = dailyUpdates;
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 0) {
            holder.date.setText(dailyUpdates.get(position).getDate());
            holder.date.setVisibility(View.VISIBLE);
        } else if (position > 0 && !dailyUpdates.get(position-1).getDate().equals(dailyUpdates.get(position).getDate())) {
            holder.date.setText(dailyUpdates.get(position).getDate());
            holder.date.setVisibility(View.VISIBLE);
        }

        if (dailyUpdates.get(position).getType().equals("Cost")) {
            holder.type.setTextColor(Color.RED);
            holder.type.setText(dailyUpdates.get(position).getType());
        } else {
            holder.type.setText(dailyUpdates.get(position).getType());
        }
        holder.description.setText(dailyUpdates.get(position).getDescription());
        holder.amount.setText(dailyUpdates.get(position).getAmount());
    }

    @Override
    public int getItemCount() {

        return dailyUpdates.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView type;
        TextView description;
        TextView amount;
        TextView date;

        ViewHolder(@NonNull View itemView) {

            super(itemView);
            type = itemView.findViewById(R.id.recyclerType);
            description = itemView.findViewById(R.id.recyclerDescription);
            amount = itemView.findViewById(R.id.recyclerAmountOfActivity);
            date = itemView.findViewById(R.id.date);
        }
    }
}
