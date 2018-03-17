package com.macbitsgoa.pawpals;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.ACTION_SEND;
import static android.content.Intent.EXTRA_TEXT;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String dogNametemp,dogIdtemp,lastFedTimetemp,dogImagetemp;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<ListItem> listItems;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("dogs");
        listItems=new ArrayList<>();
        //databaseReference.keepSynced(true);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child:dataSnapshot.getChildren()){
                    dogIdtemp= child.child("dogId").getValue(String.class);
                    dogNametemp= child.child("dogName").getValue(String.class);


                    for(DataSnapshot subchild:child.child("feed").getChildren()){
                        lastFedTimetemp=subchild.child("dateTime").getValue(String.class);
                    }
                        dogImagetemp = child.child("dogUrl").getValue(String.class);

                    ListItem listItem=new ListItem(dogNametemp,dogIdtemp,lastFedTimetemp,dogImagetemp);
                    listItems.add(listItem);
                }

                adapter=new MyAdapter(listItems,MainActivity.this);
                recyclerView.setAdapter(adapter);
                }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this,AddFeedActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_emergency_contacts) {

            startActivity(new Intent(MainActivity.this,EmergencyContactsActivity.class));

        } else if (id == R.id.nav_feed_dog){

            startActivity(new Intent(MainActivity.this,AddFeedActivity.class));

        } else if (id == R.id.nav_report_emergency) {
            //TODO Make activity Report Emergency
            //startActivity(new Intent(MainActivity.this,ReportEmergencyActivity.class));

        } else if (id == R.id.nav_about_epac) {
            //TODO Make activity About Epac

            //startActivity(new Intent(MainActivity.this,AboutEpacActivity.class));

        } else if (id == R.id.nav_about_mac) {

            startActivity(new Intent(MainActivity.this,AboutMAC.class));

        } else if (id == R.id.nav_share) {
            Intent intent = new Intent();
            intent.setAction(ACTION_SEND);
            // TODO Replace string message_app_share with string for Paw Pals
            intent.putExtra(EXTRA_TEXT, getString(R.string.message_app_share));
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, "Share app url via ... "));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
