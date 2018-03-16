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
 * Created by Ashish on 3/15/2018.
 */

public class UserService {
    DatabaseReference mDatabase;
    private List<User> userList;

    public UserService(){
        mDatabase = FirebaseDatabase.getInstance().getReference("User");
        addAllUser();
    }

    /**
     *create New User upcoming userid will be null we will set user id here useing databse strategy
     */

    public boolean createUser(User user) {
        Log.i(":" + user.getPhoneNumber(), "phone number");
        // if(checkUserExist(user.getPhoneNumber().toString())!=true) {
        String userId = mDatabase.push().getKey();
        user.setId(userId);
        mDatabase.push().setValue(user);
        return true;

    }
    /**
     * Check user existence
     * @param phoneNumber
     * @return
     */
    public boolean checkUserExist(String phoneNumber){
        new UserService();//to intialise userList
        addAllUser();
        Log.i("userList",userList.toString());
       for(User user:userList){
           if(user.getPhoneNumber().equals(phoneNumber)){
               return true;

           }
       }
        Log.i("CheckUserExist","false");
       return false;

    }

    //fetch user data to userList while intializing using constructor
    private void addAllUser(){
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                userList=new ArrayList<User>();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    User user=postSnapshot.getValue(User.class);

                    userList.add(user);

                }
                Log.i("User view",userList.toString());

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i("INFO", "Failed to read value.", error.toException());
            }
        });

    }
}
