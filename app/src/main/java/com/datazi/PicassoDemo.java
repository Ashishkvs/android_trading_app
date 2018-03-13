package com.datazi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoDemo extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_demo);
    imageView=findViewById(R.id.imageView2);
        //Loading Image from URL
        Picasso.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/tradingapp-60f0c.appspot.com/o/images%2Fprofile.jpg?alt=media&token=d30fec2e-6c10-4ce5-99b9-fc08deb12167")
                .placeholder(R.drawable.placeholder)   // optional
                .error(R.drawable.error)      // optional
                .resize(400,400)                        // optional
                .into(imageView);
    }
    public void multipleImage(View view){
        Intent intent=new Intent(this,MultipleImageUpload.class);
        startActivity(intent);

    }
}
