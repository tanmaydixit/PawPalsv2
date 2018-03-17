package com.macbitsgoa.pawpals;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AddFeedActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtName, edtDogId, edtFeed, edtFeedTime;
    ImageButton btnSubmit;
    DatabaseReference reference;
    Feed feed;
    FeedIds feedIds = new FeedIds();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feed);

        edtName = findViewById(R.id.EdtName);
        edtDogId = findViewById(R.id.EdtDogId);
        edtFeed = findViewById(R.id.EdtFeed);
        edtFeedTime = findViewById(R.id.EdtFeedTime);
        btnSubmit = findViewById(R.id.BtnSubmit);
        feed = new Feed();
        reference = FirebaseDatabase.getInstance().getReference().child("dogs");
//        Log.e("Err1","Db reference set");
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot c : dataSnapshot.getChildren()) {
//                    int i=1;
//                    Toast.makeText(AddFeedActivity.this,"OuterId about to be set" + i,Toast.LENGTH_SHORT).show();
                    feedIds.outId.add(c.getKey());
                    //feedIds.outId.add(c.child("dogId").getValue().toString());
//                    Log.e("Err4","outId retrieved ");
                    feedIds.dogId.add(c.child("dogId").getValue().toString());
//                    Log.e("Err5","DogId retrieved ");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        Log.e("Err2","Value event listener set");
        btnSubmit.setOnClickListener(this);
//        Log.e("Err3","On click listener set");
//        Toast.makeText(AddFeedActivity.this,"On click listener set",Toast.LENGTH_SHORT).show();

        setTimePickerDialog();

    }

    private void setTimePickerDialog() {

        edtFeedTime.setKeyListener(null);
        Calendar c = Calendar.getInstance();
        final int Year = c.get(Calendar.YEAR);
        final int Month = c.get(Calendar.MONTH);
        final int Day = c.get(Calendar.DAY_OF_MONTH);
        final int Hour = c.get(Calendar.HOUR_OF_DAY);
        final int Minute = c.get(Calendar.MINUTE);
        edtFeedTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TimePickerDialog tpd = new TimePickerDialog(AddFeedActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        edtFeedTime.setText(
                                new DecimalFormat("00").format(Day) + "-" + new DecimalFormat("00").format(Month) + "-"
                                        + new DecimalFormat("0000").format(Year)
                                        + " " + new DecimalFormat("00").format(Hour) + ":" + new DecimalFormat("00").format(Minute)
                                        + ":" + "00");

                    }
                }, Hour, Minute, true);
                tpd.setTitle("Select Time");
                tpd.show();
            }
        });

    }

    private int setFeed() {
        try {
            feed.setDogId(edtDogId.getText().toString());
            feed.setName(edtName.getText().toString());
            feed.setDateTime(edtFeedTime.getText().toString());
            feed.setFood(edtFeed.getText().toString());
        } catch (NullPointerException e) {
            Toast.makeText(AddFeedActivity.this, "Please enter all values", Toast.LENGTH_SHORT).show();
            Log.e("NPE", "setFeed not working");
            return 1;
        }
        if (feed.getName().length() == 0 || feed.getDateTime().length() == 0 || feed.getFood().length() == 0 || feed.getDogId().length() == 0) {
            Toast.makeText(AddFeedActivity.this, "Please enter all values", Toast.LENGTH_SHORT).show();
            return 1;
        }
        return 0;
    }

    public void BtnSubmit() {

        int dogIdIndex = feedIds.dogId.indexOf(feed.getDogId());
        if (dogIdIndex == -1) {
            Toast.makeText(AddFeedActivity.this, "Dog ID Invalid", Toast.LENGTH_SHORT).show();
            return;
        } else {
            String id = reference.child(feedIds.outId.get(dogIdIndex)).child("feed").push().getKey();
            reference.child(feedIds.outId.get(dogIdIndex)).child("feed").child(id).child("dateTime").setValue(feed.getDateTime());
            reference.child(feedIds.outId.get(dogIdIndex)).child("feed").child(id).child("name").setValue(feed.getName());
            reference.child(feedIds.outId.get(dogIdIndex)).child("feed").child(id).child("food").setValue(feed.getFood());
        }
        Toast.makeText(AddFeedActivity.this, "Updated Feed History", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.BtnSubmit) {
            if (setFeed() != 1)
                BtnSubmit();
        }

    }

//    @Override
//    public void onDataChange(DataSnapshot dataSnapshot) {
//        for(DataSnapshot c: dataSnapshot.getChildren()){
//            int i=1;
//            Toast.makeText(AddFeedActivity.this,"OuterId about to be set" + i,Toast.LENGTH_SHORT).show();
//            feedIds.outId.add(c.getKey());
//            Log.e("Err4","outId retrieved " + i);
//            feedIds.dogId.add(c.child("dogId").getValue().toString());
//            Log.e("Err5","DogId retrieved " + i);
//        }
//    }
//
//    @Override
//    public void onCancelled(DatabaseError databaseError) {
//
//    }
}

class FeedIds {

    ArrayList<String> outId = new ArrayList<>();
    ArrayList<String> dogId = new ArrayList<>();
}
