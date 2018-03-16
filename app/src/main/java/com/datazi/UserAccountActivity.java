package com.datazi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserAccountActivity extends AppCompatActivity {
    DatabaseReference mDatabase;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        // Write a message to the database
    //database table name and it is case sensitive

        mDatabase = FirebaseDatabase.getInstance().getReference("User");
        //Button listener
        Button button = findViewById(R.id.readData);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToDatabase();
            }
        });
      textView=findViewById(R.id.readUser);
        textView.setText("Reading database");
        saveToDatabase();
    }
    public void saveToDatabase() {
        String userId = mDatabase.push().getKey();
       User user = new User(userId,"ashish" ,"7623523523","ashishkvs@gmail.com","abc");
        mDatabase.push().setValue(user);

        Toast.makeText(this, "Your data has been saved", Toast.LENGTH_SHORT).show();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<User> arrayList=new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    User user=postSnapshot.getValue(User.class);
                    arrayList.add(user);

                }
                Log.i("User view",arrayList.toString());
                textView.setText(arrayList.get(0).getName()+" "+arrayList.get(0).getEmail());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i("INFO", "Failed to read value.", error.toException());
            }
        });
    }
}
