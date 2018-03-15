package com.macbitsgoa.pawpals;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by priyesh srivastava on 3/13/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    public MyAdapter(List<ListItem> listItem, ValueEventListener mainActivity) {
        this.listItems = listItem;
    }

    private List<ListItem> listItems;
    private Context mContext;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ListItem listItem=listItems.get(position);
        holder.textViewdogName.setText(listItem.getDogName());
        holder.textViewdogId.setText(listItem.getDogID());
        holder.textViewlastFedTime.setText(listItem.getLastFedTime());
        Picasso.get().load(listItem.getDogImage()).into(holder.imageViewdogImage);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,FeedHistoryActivity.class);
                intent.putExtra("DogName",listItem.getDogName());
                intent.putExtra("DogId",listItem.getDogID());
                intent.putExtra("DogName",listItem.getDogImage());
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewdogName;
        public TextView textViewdogId;
        public TextView textViewlastFedTime;
        public ImageView imageViewdogImage;
        public LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewdogName=(TextView) itemView.findViewById(R.id.dogName);
            textViewdogId=(TextView) itemView.findViewById(R.id.dogId);
            textViewlastFedTime=(TextView) itemView.findViewById(R.id.lastFedTime);
            imageViewdogImage=(ImageView) itemView.findViewById(R.id.dogImage);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.linearlayout);
        }
    }
}
