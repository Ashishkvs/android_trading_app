package com.datazi;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashish on 3/13/2018.
 */

public class CheckUser {
    DatabaseReference mDatabase;
    List<User> userList;

   /* public CheckUser() {
        mDatabase = FirebaseDatabase.getInstance().getReference("User");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userList=new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    User user=postSnapshot.getValue(User.class);
                    userList.add(user);

                }
                //Log.i("User view",arrayList.toString());
                // textView.setText(arrayList.get(0).getName()+" "+arrayList.get(0).getEmail());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i("INFO", "Failed to read value.", error.toException());
            }
        });

    }
    public boolean userExist(String phoneNumber){
       for(User user:userList){
           if(user.getPhoneNumber().equals(phoneNumber)){
               return true;
           }else{
               return false;
           }


    }*/

}
