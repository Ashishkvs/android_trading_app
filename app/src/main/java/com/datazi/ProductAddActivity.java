package com.datazi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProductActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    private Button addProductButton;

    private EditText editProductName;
    private EditText editProductDescription;

    private EditText editProductQty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        //ActionBar Modification
        getSupportActionBar().setTitle("Add Product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //CREATE DATABASE
        mDatabase = FirebaseDatabase.getInstance().getReference("products"); //put the table name in database

        //Attach view
        editProductName = findViewById(R.id.editProductName);
        editProductDescription = findViewById(R.id.editProductDescription);
        editProductQty = findViewById(R.id.editProductQty);

        //add product
        addProductButton = findViewById(R.id.addproductButton);
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProduct();
            }
        });


    }

    public void addProduct() {
        // Creating new product node, which returns the unique key value
        // new user node would be /users/$userid/
        String productId = mDatabase.push().getKey();
        //Fetch product info from UI

        int productQty = Integer.parseInt(editProductQty.getText().toString());
        String productName = editProductName.getText().toString();
        String productDesc = editProductDescription.getText().toString();

        // creating product object with above extracted value
        Product product = new Product(productId, productName, productDesc, productQty);

        // pushing Product to 'product' node using the productId
        mDatabase.child(productId).setValue(product);
        Toast.makeText(this, productName + " Added Successfully", Toast.LENGTH_SHORT).show();
    }
public void btnPicaso(View view){
    Intent intent=new Intent(this,PicassoDemo.class);
    startActivity(intent);
}
}
