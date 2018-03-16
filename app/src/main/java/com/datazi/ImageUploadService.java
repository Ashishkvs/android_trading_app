package com.datazi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

/**
 * Created by Ashish on 3/15/2018.
 */

public class ImageUploadService {




    private StorageReference mStorageRef;
   public static String profileImageUrl;

    Context mContext;
    String fileDirectory="profilePic/";

    public ImageUploadService(Context mContext,String fileDirectory) {
        this.mContext = mContext;
        this.fileDirectory=fileDirectory;
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }


    //TO UPLOAD FILE TO FIREBASE STORAGE
    public void uploadFile(Uri filePath) {
        //final String[] profileImageUrl = new String[1];
        //set progress bar
        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(mContext);
            progressDialog.setTitle("Uploading . . ");
            progressDialog.show();
            //generate unique file name
            String profilePicName = "" + System.currentTimeMillis() + ".jpg";
            Log.i("profile pic image name", profilePicName);
            //Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
            StorageReference riversRef = mStorageRef.child(fileDirectory + profilePicName);

            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            Uri uri = taskSnapshot.getDownloadUrl();

                            ImageUploadService.profileImageUrl = uri.toString();

                            Log.i("Url PATH", ImageUploadService.profileImageUrl);
                            progressDialog.dismiss();
                            Toast.makeText(mContext, "File Uploaded", Toast.LENGTH_SHORT).show();


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                            progressDialog.dismiss();
                            Toast.makeText(mContext, "File Uploaded Error" + exception.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //set progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage(((int) progress) + "% uploaded ..");
                        }
                    });

        }
        //display an error toast
        else {
            Toast.makeText(mContext, "plz select Profile pic ", Toast.LENGTH_SHORT).show();

        }
    }


   /* public User profileUrl(String imageUrl){
        User user=new User();
        user.setImageUrl(imageUrl);
        return user;
    }*/
}
