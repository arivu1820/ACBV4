package com.futureinspirator.acbaradise;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class login extends AppCompatActivity {

    private EditText et;
    private String number;
    private ProgressBar progressBar;
    private TextView incode;
    private FrameLayout frame;
    private Handler delay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences preferences = getSharedPreferences("ACBARADISE.v3",MODE_PRIVATE);
        String checkbox = preferences.getString("remember.v3","");

        if (checkbox.equals("true")) {
            Intent intent = new Intent(login.this, homepage.class);
            startActivity(intent);
            finish();
        }



        et = findViewById(R.id.et);
        frame = findViewById(R.id.frame);
        incode = findViewById(R.id.incode);
        progressBar = findViewById(R.id.progressbar);

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                number = et.getText().toString();
                if(number.length()!=10)
                {
                    return;
                }
                else
                {

                            closekeyboard();
                            numberlengthverified();

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        requestSMSPermission();

    }

    private void requestSMSPermission() {

        String permission = Manifest.permission.RECEIVE_SMS;

        int grant = ContextCompat.checkSelfPermission(this, permission);
        if (grant != PackageManager.PERMISSION_GRANTED)
        {
            String[] permission_list = new String[1];
            permission_list[0] = permission;

            ActivityCompat.requestPermissions(this, permission_list,1);
        }

    }

    private void closekeyboard()
    {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void numberlengthverified()
    {
        frame.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+ et.getText().toString(),
                30,
                TimeUnit.SECONDS,
                login.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
                {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential)
                    {
                        progressBar.setVisibility(View.INVISIBLE);
                        frame.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e)
                    {
                        progressBar.setVisibility(View.INVISIBLE);
                        frame.setVisibility(View.VISIBLE);
                        Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String otpcode, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken)
                    {
                        super.onCodeSent(otpcode, forceResendingToken);
                        progressBar.setVisibility(View.INVISIBLE);
                        frame.setVisibility(View.VISIBLE);
                        Intent intent = new Intent(login.this,sendotp.class);
                        intent.putExtra("Number",number);
                        intent.putExtra("OTP",otpcode);
                        startActivity(intent);
                        SharedPreferences preferences = getSharedPreferences("ACBARADISE.v3",MODE_PRIVATE);
                        String checkbox = preferences.getString("remember.v3","");
                        if (checkbox.equals("true")) {
                            finish();
                        }
                    }
                });


    }
}