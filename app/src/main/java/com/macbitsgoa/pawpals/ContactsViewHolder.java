package com.macbitsgoa.pawpals;

/*
  Created by rajath reghunath on 17-Mar-18.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactsViewHolder extends RecyclerView.ViewHolder {

    ImageView contactPhoto;
    TextView name, designation, phoneNo, email;

    public ContactsViewHolder(View itemView) {
        super(itemView);
        contactPhoto = itemView.findViewById(R.id.contacts_photo);
        name = itemView.findViewById(R.id.contacts_name);
        designation = itemView.findViewById(R.id.contacts_designation);
        phoneNo = itemView.findViewById(R.id.contacts_phone_no);
        email = itemView.findViewById(R.id.contacts_email);
    }
}
