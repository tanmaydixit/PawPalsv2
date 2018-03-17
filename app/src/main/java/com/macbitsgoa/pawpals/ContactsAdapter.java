package com.macbitsgoa.pawpals;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by admin on 16-Mar-18.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsViewHolder> implements ValueEventListener{

    private DatabaseReference reference;
    ArrayList<Contacts> contacts = new ArrayList<>();

    public ContactsAdapter(){
        contacts = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference().child("contacts");
        reference.addValueEventListener(this);
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ContactsViewHolder(layoutInflater.inflate(R.layout.contacs_layout,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {

        holder.name.setText(contacts.get(position).getName());
        holder.designation.setText(contacts.get(position).getDesignation());
        holder.email.setText(contacts.get(position).getEmail());
        holder.phoneNo.setText(contacts.get(position).getPhoneNo());
        Picasso.get().load(contacts.get(position).getPhotoUrl()).into(holder.contactPhoto);

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
            contacts = new ArrayList<>();
            int id = 1;
            for(DataSnapshot c :
                    dataSnapshot.getChildren()){

                contacts.add(new Contacts(
                                        c.child("name").getValue(String.class),
                                        c.child("imageUrl").getValue(String.class),
                                        c.child("phone").getValue(String.class),
                                        c.child("email").getValue(String.class),
                                        c.child("role").getValue(String.class)
                                        )
                            );
                id+=1;
                notifyDataSetChanged();
            }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
