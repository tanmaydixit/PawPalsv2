package com.macbitsgoa.pawpals;

import android.content.Intent;
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

public class FeedHistoryActivity extends AppCompatActivity {


    String spDogName,spDogId,spDogImage;
    ImageView imageView;
    TextView name,id;
    private RecyclerView recyclerView2;
    RecyclerView.Adapter adapter2;
    List<ListItem2> listItem2s;
    String dogNametemp2;
    String feederName,foodItem,timeOfFeeding;

    DatabaseReference databaseReference2= FirebaseDatabase.getInstance().getReference().child("dogs");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_history);
        Intent data=this.getIntent();

        spDogName=data.getExtras().getString("DogName");
        spDogId=data.getExtras().getString("DogId");
        spDogImage=data.getExtras().getString("DogImage");

        name=(TextView) findViewById(R.id.spName);
        id=(TextView) findViewById(R.id.spId);
        imageView=(ImageView) findViewById(R.id.spImage);

        name.setText(spDogName);
        id.setText(spDogId);
        Picasso.get().load(spDogImage).into(imageView);


        recyclerView2=(RecyclerView) findViewById(R.id.recyclerview2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        listItem2s=new ArrayList<>();

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child:dataSnapshot.getChildren()){
                    dogNametemp2 = child.child("dogId").getValue(String.class);
                    if (dogNametemp2==spDogId){
                        for(DataSnapshot snapshot:child.child("feed").getChildren()){
                            feederName=snapshot.child("name").getValue(String.class);
                            foodItem=snapshot.child("food").getValue(String.class);
                            timeOfFeeding=snapshot.child("dateTime").getValue(String.class);

                            ListItem2 listItem2=new ListItem2(feederName,foodItem,timeOfFeeding);
                            listItem2s.add(listItem2);
                        }
                        break;
                    }
                }
                adapter2=new MyAdapter2(listItem2s,FeedHistoryActivity.this);
                recyclerView2.setAdapter(adapter2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
