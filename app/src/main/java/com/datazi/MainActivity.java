package com.datazi;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "PhoneAuth";
    private EditText phoneText;
    private EditText codeText;
    private Button verifyButton;
    private Button sendButton;
    private Button resendButton;
    private Button signOutButton;
    private TextView statusText;


    private String phoneVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationStateChangedCallbacks;
    private PhoneAuthProvider.ForceResendingToken resendingToken;
    private FirebaseAuth fbAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneText = findViewById(R.id.phoneNumber);
        codeText = findViewById(R.id.codeText);
        verifyButton = findViewById(R.id.verifyButton);
        sendButton = findViewById(R.id.sendButton);
        resendButton = findViewById(R.id.resendButton);
        signOutButton = findViewById(R.id.signOutButton);
        statusText = findViewById(R.id.statusTextView);

        verifyButton.setEnabled(false);
        resendButton.setEnabled(false);
        signOutButton.setEnabled(false);
        statusText.setText("signed Out");
        fbAuth = FirebaseAuth.getInstance();
    }

    public void sendCode(View view) {
        String phoneNumber = phoneText.getText().toString();
        setUpVerificationCallbacks();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                verificationStateChangedCallbacks
        );
    }

    private void setUpVerificationCallbacks() {

        verificationStateChangedCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signOutButton.setEnabled(true);
                statusText.setText("Signed in");
                resendButton.setEnabled(false);
                verifyButton.setEnabled(false);
                codeText.setText("");
                //ssignin with credentials
                //CALL HOME INTENT
                callHome(); //next screen
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Log.d(TAG, "INVALID CREDENTIALS" + e.getLocalizedMessage());
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Log.d(TAG, "SMS QUOTA EXCEEDED");
                }

            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                // super.onCodeSent(s, forceResendingToken);
                //
                phoneVerificationId = verificationId;
                resendingToken = forceResendingToken;

                verifyButton.setEnabled(true);
                sendButton.setEnabled(false);
                resendButton.setEnabled(true);
            }
        };
    }

    public void verifyCode(View view) {
        String code = codeText.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(phoneVerificationId, code);
        //
        signInWithPhoneAuthCredential(credential);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        fbAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            signOutButton.setEnabled(true);
                            codeText.setText("");
                            statusText.setText("Signed In");
                            resendButton.setEnabled(false);
                            verifyButton.setEnabled(false);
                            FirebaseUser user = task.getResult().getUser();
                            callHome(); //next screen

                        } else {
                            // Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(MainActivity.this,"Invalid Verification",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }


                });
    }

    public void resendCode(View view) {
        String phoneNumber = phoneText.getText().toString();
        setUpVerificationCallbacks();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(

                phoneNumber, 60, TimeUnit.SECONDS, this, verificationStateChangedCallbacks, resendingToken
        );
    }

    public void signOut(View view) {
        fbAuth.signOut();
        statusText.setText("Signed out");
        signOutButton.setEnabled(false);
        sendButton.setEnabled(true);
    }
    //call HomeScreen intent
    public void callHome(){



        Intent intent=new Intent(this,ProductAddActivity.class);
        startActivity(intent);

    }

    //Home button Button on Click being called
    public void homeButton(View view){
        //HOME
        //Intent intent=new Intent(this,Home.class);

        //USER REGISTRATION
        Intent intent=new Intent(this,UserRegistration.class);
        intent.putExtra("phoneNumber",phoneText.getText().toString());
      //IMAGE UPLOAD
        //Intent intent=new Intent(this,ImageUpload.class);
      startActivity(intent);
    }

}