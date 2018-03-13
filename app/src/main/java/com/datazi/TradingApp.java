package com.datazi;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.security.PublicKey;

/**
 * Created by Ashish on 2/26/2018.
 */

public class TradingApp extends Application {
    public StorageReference mStorageRef;
    public DatabaseReference dbref;

    @Override
    public void onCreate() {
        super.onCreate();
        dbref = FirebaseDatabase.getInstance().getReference();

    }
}
