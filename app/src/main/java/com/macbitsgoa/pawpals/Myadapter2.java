package com.macbitsgoa.pawpals;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by priyesh srivastava on 3/16/2018.
 */
public class Myadapter2 extends RecyclerView.Adapter<Myadapter2.ViewHolder>{
    private List<FeederInfoItem> feederInfoItemList;
    Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeederInfoItem feederInfoItem=feederInfoItemList.get(position);
        holder.textViewFeederName.setText(feederInfoItem.getFeederName());
        holder.textViewFoodItem.setText(feederInfoItem.getFoodItem());
        holder.textViewFoodItem.setText(feederInfoItem.getFeedingTime());
    }

    @Override
    public int getItemCount() {
        return feederInfoItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewFeederName;
        public TextView textViewFoodItem;
        public TextView textViewFeedingTime;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewFeederName=itemView.findViewById(R.id.feedername);
            textViewFoodItem=itemView.findViewById(R.id.fooditem);
            textViewFeedingTime=itemView.findViewById(R.id.feedingtime);
        }
    }
}