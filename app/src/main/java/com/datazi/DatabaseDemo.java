package com.datazi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class DatabaseDemo extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE_REQUEST =123 ;
    ImageView imageView;
    Button fileChooser,fileUpload;
    private Uri filePath;
    DatabaseReference dbref= FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_demo);
        imageView=findViewById(R.id.imageFile);
        fileChooser=findViewById(R.id.fileChooser);
        fileUpload=findViewById(R.id.fileUpload);

        fileChooser.setOnClickListener(this);
        fileUpload.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view==fileChooser){
        //open file chooser
            showFileChooser();

        }
        else if(view==fileUpload){
        //upload file
        }

    }



    private void showFileChooser(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select an imagefile"),PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data != null && data.getData() != null){
                filePath=data.getData();

            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
