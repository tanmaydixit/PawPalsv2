package com.macbitsgoa.pawpals;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FeedHistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView2;
    RecyclerView.Adapter adapter2;
    List<FeederInfoItem> feederInfoItemList;
    DatabaseReference databaseReference2= FirebaseDatabase.getInstance().getReference().child("dogs");

    TextView textViewSpDogName,textViewSpDogId;
    ImageView imageViewSpDogImage;
    String transferredName,transferredId,transferredImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_history);

        textViewSpDogId=(TextView) findViewById(R.id.spId);
        textViewSpDogName=(TextView) findViewById(R.id.spName);
        imageViewSpDogImage=(ImageView) findViewById(R.id.spImage);

        textViewSpDogName.setText(transferredName);
        textViewSpDogId.setText(transferredId);
        Picasso.get().load(transferredImage).into(imageViewSpDogImage);

        recyclerView2=(RecyclerView) findViewById(R.id.recyclerview2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        feederInfoItemList=new ArrayList<>();

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child:dataSnapshot.getChildren()){
                    String dogIdtemp= child.child("dogId").getValue(String.class);
                    if(Objects.equals(dogIdtemp,transferredId)){
                        for(DataSnapshot subchild:child.child("feed").getChildren()){
                            String feederName=subchild.child("name").getValue(String.class);
                            String foodItem=subchild.child("food").getValue(String.class);
                            String feedingTime=subchild.child("dateTime").getValue(String.class);
                            FeederInfoItem feederInfoItem=new FeederInfoItem(feederName,foodItem,feedingTime);
                            feederInfoItemList.add(feederInfoItem);
                        }
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
/*
* Please make an onclicklistener for the previous recycler view and transfer 3 strings transferredName,transferredId,transferredImage
*
* */