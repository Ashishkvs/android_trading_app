package com.datazi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DatabaseRead extends AppCompatActivity {
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_read);

        // Write a message to the database

        mDatabase = FirebaseDatabase.getInstance().getReference("products"); //database table name and it is case sensitive

        //Button listener
        Button button = findViewById(R.id.btnSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToDatabase();
            }
        });
    }


    public void saveToDatabase() {
       /* User user = new User("Kumar Baala", 200);
        mDatabase.push().setValue(user);
        Toast.makeText(this, "Your data has been saved", Toast.LENGTH_SHORT).show();
*/
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Product> arrayList=new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                   Product product=postSnapshot.getValue(Product.class);
                   arrayList.add(product);

                }
                Log.i("Product view",arrayList.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i("INFO", "Failed to read value.", error.toException());
            }
        });
    }
    // Read from the database

}
