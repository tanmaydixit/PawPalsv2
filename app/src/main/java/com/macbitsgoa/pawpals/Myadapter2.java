package com.macbitsgoa.pawpals;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by priyesh srivastava on 3/15/2018.
 */

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {
    private List<FeederInfoItem> listItem2s;
    Context context;

    public MyAdapter2(List<FeederInfoItem> listItem2s, Context context) {
        this.listItem2s = listItem2s;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2,parent,false);
        return new MyAdapter2.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final FeederInfoItem listItem2=listItem2s.get(position);
        holder.textViewFeederName.setText(listItem2.getFeederName());
        holder.textViewFoodItem.setText(listItem2.getFoodItem());
        holder.textViewTimeOfFeeding.setText(listItem2.getFeedingTime());

    }

    @Override
    public int getItemCount() {
        return listItem2s.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewFeederName;
        public TextView textViewFoodItem;
        public TextView textViewTimeOfFeeding;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewFeederName=(TextView) itemView.findViewById(R.id.feedername);
            textViewFoodItem=(TextView) itemView.findViewById(R.id.fooditem);
            textViewTimeOfFeeding=(TextView) itemView.findViewById(R.id.feedingtime);
        }
    }
}
