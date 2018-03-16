package com.datazi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class UserRegistration extends AppCompatActivity {

    private TextView textMobile;
    private ImageView userProfile;
    private EditText editUsername;
    private EditText editEmail;
    private Button nextButton;

    private String userProfileUrl="profileUrl";
    private String  userName;
    private String userEmail;
    private String phoneNumber;
    private Uri filePath;
    private static final int PICK_IMAGE_REQUEST = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        textMobile=findViewById(R.id.textMobile);
        userProfile=findViewById(R.id.userProfile);
        editUsername=findViewById(R.id.editUsername);
        editEmail=findViewById(R.id.editEmail);
        nextButton=findViewById(R.id.nextButton);
      /*
         * Receive phone no from intent and set it to textMobile
         */
        Bundle bundle=getIntent().getExtras();
        phoneNumber=bundle.getString("phoneNumber");
        Log.i("PhoneNumber",phoneNumber);
        textMobile.setText("Mob : "+phoneNumber);

        /**
         * on next click save it to database
         *
         */
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               userReg();
                 Toast.makeText(UserRegistration.this,"Registered Succesfully",Toast.LENGTH_SHORT).show();
            }
        });

        //ON CLICK IMAGE VIEW
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });



    }
    //USER REGISTRATION
    public void userReg(){



        //User user=userReg();
        /**
         * upload image and get its url and set it to userProfileUrl
         */
        userProfileUrl=ImageUploadService.profileImageUrl;
        Log.i("userProfileUrl"," :"+userProfileUrl);

        /**
         * Receive from userRegistration Screen
         */

        userName=editUsername.getText().toString();
        userEmail=editEmail.getText().toString();

        Log.i("username"+userName,"email"+userEmail);
        //USER SERVICE
        UserService userService=new UserService();
        User user=new User("dummyId", userName, phoneNumber, userEmail, userProfileUrl);
        userService.createUser(user);
    }

    //CHOOSE IMAGE FROM GALLERY and set it to image View
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select an imagefile"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                userProfile.setImageBitmap(bitmap);
               //onselect profile add image to storage ref
                ImageUploadService imageUploadService=new ImageUploadService(UserRegistration.this,"myimage/");
                imageUploadService.uploadFile(filePath);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
