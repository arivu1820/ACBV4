package com.futureinspirator.acbaradise;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class sendotp extends AppCompatActivity {
    private EditText input1,input2,input3,input4,input5,input6;
    private String number,currentotp,userenteredotp;
    private TextView numtxt,resend;
    private Button btn;
    private ProgressBar progressBar;
    private Handler delay,delayed;
    private int wrongotp=0;
    FirebaseAuth mAuth= FirebaseAuth.getInstance();


    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendotp);

        numtxt = findViewById(R.id.numtxt);
        number = getIntent().getExtras().get("Number").toString();
        currentotp = getIntent().getExtras().get("OTP").toString();
        numtxt.setText("+91-"+number);
        progressBar = findViewById(R.id.progressbar);
        resend = findViewById(R.id.resend);
        delay = new Handler();
        delayed = new Handler();

        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        input3 = findViewById(R.id.input3);
        input4 = findViewById(R.id.input4);
        input5 = findViewById(R.id.input5);
        input6 = findViewById(R.id.input6);

        Runnable run = new Runnable() {
            @Override
            public void run()
            {
                resend.setTextColor(Color.parseColor("#FFEF0A12"));
            }
        };

        delay.postDelayed(run,30000);

        enteringotp();

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91"+ getIntent().getStringExtra("Number"),
                        30,
                        TimeUnit.SECONDS,
                        sendotp.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
                        {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential)
                            {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e)
                            {
                                Toast.makeText(sendotp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String otpcode, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken)
                            {
                                super.onCodeSent(otpcode, forceResendingToken);
                                if(currentotp!=otpcode)
                                {
                                    resend.setTextColor(Color.parseColor("#AA9E9D"));
                                    currentotp = otpcode;
                                    Toast.makeText(sendotp.this, "OTP Send", Toast.LENGTH_SHORT).show();
                                    Runnable runnable = new Runnable() {
                                        @Override
                                        public void run()
                                        {
                                            resend.setTextColor(Color.parseColor("#FFEF0A12"));
                                        }
                                    };
                                    delayed.postDelayed(runnable,30000);
                                }
                            }
                        });
            }
        });


//        new OTP_Receiver().setEditText1(input1);
//        new OTP_Receiver().setEditText2(input2);
//        new OTP_Receiver().setEditText3(input3);
//        new OTP_Receiver().setEditText4(input4);
//        new OTP_Receiver().setEditText5(input5);
//        new OTP_Receiver().setEditText6(input6);


    }


    private void enteringotp()
    {
        input1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!(input1.toString().isEmpty()))
                {
                    enteredotp();
                    input2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        input2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(!(input2.toString().isEmpty()))
                {
                    enteredotp();
                    input3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        input3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(!(input3.toString().isEmpty()))
                {
                    enteredotp();
                    input4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        input4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(!(input4.toString().isEmpty()))
                {
                    enteredotp();
                    input5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        input5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(!(input5.toString().isEmpty()))
                {
                    enteredotp();
                    input6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        input6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(!(input6.toString().isEmpty()))
                {
                    enteredotp();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void enteredotp()
    {
        if(TextUtils.isEmpty(input1.getText().toString())||
                TextUtils.isEmpty(input2.getText().toString())||
                TextUtils.isEmpty(input3.getText().toString())||
                TextUtils.isEmpty(input4.getText().toString())||
                TextUtils.isEmpty(input5.getText().toString())||
                TextUtils.isEmpty(input6.getText().toString()))
        {
            return;
        }
        closekeyboard();
        input1.setEnabled(false);
        input2.setEnabled(false);
        input3.setEnabled(false);
        input4.setEnabled(false);
        input5.setEnabled(false);
        input6.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        userenteredotp = input1.getText().toString()+
                input2.getText().toString()+
                input3.getText().toString()+
                input4.getText().toString()+
                input5.getText().toString()+
                input6.getText().toString();

        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(currentotp,userenteredotp);
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {

                            SharedPreferences prefernces = getSharedPreferences("ACBARADISE.v3",MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefernces.edit();
                            editor.putString("remember.v3","true");
                            editor.apply();



                            HashMap<String,Object> newuserupadte = new HashMap<>();
                            newuserupadte.put("Number",number);
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).updateChildren(newuserupadte);


                            /////////////////////////////////////////////////////////////////////////////////////////////////////

                            HashMap<String,Object> gsadditems = new HashMap<>();
                            gsadditems.put("CassetteAC","0");
                            gsadditems.put("SplitAC","0");
                            gsadditems.put("WindowAC","0");
                            gsadditems.put("SplitACTOTAL","0");
                            gsadditems.put("CassetteACTOTAL","0");
                            gsadditems.put("WindowACTOTAL","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("GeneralService").updateChildren(gsadditems);

                            HashMap<String,Object> wwadditems = new HashMap<>();
                            wwadditems.put("CassetteSSAC","0");
                            wwadditems.put("SplitSSAC","0");
                            wwadditems.put("WindowSSAC","0");
                            wwadditems.put("SplitSSACTOTAL","0");
                            wwadditems.put("CassetteSSACTOTAL","0");
                            wwadditems.put("WindowSSACTOTAL","0");
                            wwadditems.put("Cassette360AC","0");
                            wwadditems.put("Split360AC","0");
                            wwadditems.put("Window360AC","0");
                            wwadditems.put("Split360ACTOTAL","0");
                            wwadditems.put("Cassette360ACTOTAL","0");
                            wwadditems.put("Window360ACTOTAL","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("WaterWash").updateChildren(wwadditems);

                            HashMap<String,Object> sparessplitadditems = new HashMap<>();
                            sparessplitadditems.put("Blower","0");
                            sparessplitadditems.put("BlowerTOTAL","0");
                            sparessplitadditems.put("Capacitor","0");
                            sparessplitadditems.put("CapacitorTOTAL","0");
                            sparessplitadditems.put("Indoorcoil","0");
                            sparessplitadditems.put("IndoorcoilTOTAL","0");
                            sparessplitadditems.put("Indoormotor","0");
                            sparessplitadditems.put("IndoormotorTOTAL","0");
                            sparessplitadditems.put("Outdoorcondenser","0");
                            sparessplitadditems.put("OutdoorcondenserTOTAL","0");
                            sparessplitadditems.put("Outdoorfan","0");
                            sparessplitadditems.put("OutdoorfanTOTAL","0");
                            sparessplitadditems.put("Outdoormotor","0");
                            sparessplitadditems.put("OutdoormotorTOTAL","0");
                            sparessplitadditems.put("Remote","0");
                            sparessplitadditems.put("RemoteTOTAL","0");
                            sparessplitadditems.put("Swingflap","0");
                            sparessplitadditems.put("SwingflapTOTAL","0");
                            sparessplitadditems.put("Swingmotor","0");
                            sparessplitadditems.put("SwingmotorTOTAL","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Spares").child("SplitAC").updateChildren(sparessplitadditems);

                            HashMap<String,Object> spareswindowadditems = new HashMap<>();
                            spareswindowadditems.put("Blower","0");
                            spareswindowadditems.put("BlowerTOTAL","0");
                            spareswindowadditems.put("Capacitor","0");
                            spareswindowadditems.put("CapacitorTOTAL","0");
                            spareswindowadditems.put("Indoorcoil","0");
                            spareswindowadditems.put("IndoorcoilTOTAL","0");
                            spareswindowadditems.put("Indoormotor","0");
                            spareswindowadditems.put("IndoormotorTOTAL","0");
                            spareswindowadditems.put("Outdoorcondenser","0");
                            spareswindowadditems.put("OutdoorcondenserTOTAL","0");
                            spareswindowadditems.put("Outdoorfan","0");
                            spareswindowadditems.put("OutdoorfanTOTAL","0");
                            spareswindowadditems.put("Outdoormotor","0");
                            spareswindowadditems.put("OutdoormotorTOTAL","0");
                            spareswindowadditems.put("Remote","0");
                            spareswindowadditems.put("RemoteTOTAL","0");
                            spareswindowadditems.put("Swingflap","0");
                            spareswindowadditems.put("SwingflapTOTAL","0");
                            spareswindowadditems.put("Swingmotor","0");
                            spareswindowadditems.put("SwingmotorTOTAL","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Spares").child("WindowAC").updateChildren(spareswindowadditems);

                            HashMap<String,Object> sparescassetteadditems = new HashMap<>();
                            sparescassetteadditems.put("Blower","0");
                            sparescassetteadditems.put("BlowerTOTAL","0");
                            sparescassetteadditems.put("Capacitor","0");
                            sparescassetteadditems.put("CapacitorTOTAL","0");
                            sparescassetteadditems.put("Indoorcoil","0");
                            sparescassetteadditems.put("IndoorcoilTOTAL","0");
                            sparescassetteadditems.put("Indoormotor","0");
                            sparescassetteadditems.put("IndoormotorTOTAL","0");
                            sparescassetteadditems.put("Outdoorcondenser","0");
                            sparescassetteadditems.put("OutdoorcondenserTOTAL","0");
                            sparescassetteadditems.put("Outdoorfan","0");
                            sparescassetteadditems.put("OutdoorfanTOTAL","0");
                            sparescassetteadditems.put("Outdoormotor","0");
                            sparescassetteadditems.put("OutdoormotorTOTAL","0");
                            sparescassetteadditems.put("Remote","0");
                            sparescassetteadditems.put("RemoteTOTAL","0");
                            sparescassetteadditems.put("Swingflap","0");
                            sparescassetteadditems.put("SwingflapTOTAL","0");
                            sparescassetteadditems.put("Swingmotor","0");
                            sparescassetteadditems.put("SwingmotorTOTAL","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Spares").child("CassetteAC").updateChildren(sparescassetteadditems);


                            HashMap<String,Object> faultssplitadditems = new HashMap<>();
                            faultssplitadditems.put("Gasleak","0");
                            faultssplitadditems.put("GasleakTOTAL","0");
                            faultssplitadditems.put("Indoorcoil","0");
                            faultssplitadditems.put("IndoorcoilTOTAL","0");
                            faultssplitadditems.put("Outdoorcondenser","0");
                            faultssplitadditems.put("OutdoorcondenserTOTAL","0");
                            faultssplitadditems.put("Pcboard","0");
                            faultssplitadditems.put("PcboardTOTAL","0");
                            faultssplitadditems.put("Remote","0");
                            faultssplitadditems.put("RemoteTOTAL","0");
                            faultssplitadditems.put("Stabilizer","0");
                            faultssplitadditems.put("StabilizerTOTAL","0");
                            faultssplitadditems.put("Waterleak","0");
                            faultssplitadditems.put("WaterleakTOTAL","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Faults").child("SplitAC").updateChildren(faultssplitadditems);


                            HashMap<String,Object> faultswindowadditems = new HashMap<>();
                            faultswindowadditems.put("Gasleak","0");
                            faultswindowadditems.put("GasleakTOTAL","0");
                            faultswindowadditems.put("Indoorcoil","0");
                            faultswindowadditems.put("IndoorcoilTOTAL","0");
                            faultswindowadditems.put("Outdoorcondenser","0");
                            faultswindowadditems.put("OutdoorcondenserTOTAL","0");
                            faultswindowadditems.put("Pcboard","0");
                            faultswindowadditems.put("PcboardTOTAL","0");
                            faultswindowadditems.put("Remote","0");
                            faultswindowadditems.put("RemoteTOTAL","0");
                            faultswindowadditems.put("Stabilizer","0");
                            faultswindowadditems.put("StabilizerTOTAL","0");
                            faultswindowadditems.put("Waterleak","0");
                            faultswindowadditems.put("WaterleakTOTAL","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Faults").child("WindowAC").updateChildren(faultswindowadditems);


                            HashMap<String,Object> faultscassetteadditems = new HashMap<>();
                            faultscassetteadditems.put("Gasleak","0");
                            faultscassetteadditems.put("GasleakTOTAL","0");
                            faultscassetteadditems.put("Indoorcoil","0");
                            faultscassetteadditems.put("IndoorcoilTOTAL","0");
                            faultscassetteadditems.put("Outdoorcondenser","0");
                            faultscassetteadditems.put("OutdoorcondenserTOTAL","0");
                            faultscassetteadditems.put("Pcboard","0");
                            faultscassetteadditems.put("PcboardTOTAL","0");
                            faultscassetteadditems.put("Remote","0");
                            faultscassetteadditems.put("RemoteTOTAL","0");
                            faultscassetteadditems.put("Stabilizer","0");
                            faultscassetteadditems.put("StabilizerTOTAL","0");
                            faultscassetteadditems.put("Waterleak","0");
                            faultscassetteadditems.put("WaterleakTOTAL","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Faults").child("CassetteAC").updateChildren(faultscassetteadditems);

                            HashMap<String,Object> installsplitadditems = new HashMap<>();
                            installsplitadditems.put("Combo","0");
                            installsplitadditems.put("ComboTOTAL","0");
                            installsplitadditems.put("Install","0");
                            installsplitadditems.put("InstallTOTAL","0");
                            installsplitadditems.put("Uninstall","0");
                            installsplitadditems.put("UninstallTOTAL","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child("SplitAC").updateChildren(installsplitadditems);

                            HashMap<String,Object> installwindowadditems = new HashMap<>();
                            installwindowadditems.put("Combo","0");
                            installwindowadditems.put("ComboTOTAL","0");
                            installwindowadditems.put("Install","0");
                            installwindowadditems.put("InstallTOTAL","0");
                            installwindowadditems.put("Uninstall","0");
                            installwindowadditems.put("UninstallTOTAL","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child("WindowAC").updateChildren(installwindowadditems);

                            HashMap<String,Object> installcassetteadditems = new HashMap<>();
                            installcassetteadditems.put("Combo","0");
                            installcassetteadditems.put("ComboTOTAL","0");
                            installcassetteadditems.put("Install","0");
                            installcassetteadditems.put("InstallTOTAL","0");
                            installcassetteadditems.put("Uninstall","0");
                            installcassetteadditems.put("UninstallTOTAL","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child("CassetteAC").updateChildren(installcassetteadditems);

                            HashMap<String,Object> amccassetteacscheme1withoutspare = new HashMap<>();
                            amccassetteacscheme1withoutspare.put("Nospare","0");
                            amccassetteacscheme1withoutspare.put("NospareTOTAL","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("CassetteAC").child("Scheme1").child("Withoutspare").updateChildren(amccassetteacscheme1withoutspare);

                            HashMap<String,Object> amccassetteacscheme1withspare = new HashMap<>();
                            amccassetteacscheme1withspare.put("Limitedspare","0");
                            amccassetteacscheme1withspare.put("LimitedspareTOTAL","0");
                            amccassetteacscheme1withspare.put("Totalspare","0");
                            amccassetteacscheme1withspare.put("TotalspareTOTAL","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("CassetteAC").child("Scheme1").child("Withspare").updateChildren(amccassetteacscheme1withspare);



                            HashMap<String,Object> amcsplitacscheme1withoutspare = new HashMap<>();
                            amcsplitacscheme1withoutspare.put("Nospare","0");
                            amcsplitacscheme1withoutspare.put("NospareTOTAL","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme1").child("Withoutspare").updateChildren(amcsplitacscheme1withoutspare);


                            HashMap<String,Object> amcsplitacscheme1withspare = new HashMap<>();
                            amcsplitacscheme1withspare.put("Limitedspare","0");
                            amcsplitacscheme1withspare.put("LimitedspareTOTAL","0");
                            amcsplitacscheme1withspare.put("Totalspare","0");
                            amcsplitacscheme1withspare.put("TotalspareTOTAL","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme1").child("Withspare").updateChildren(amcsplitacscheme1withspare);



                            HashMap<String,Object> amcsplitacscheme2withoutspare = new HashMap<>();
                            amcsplitacscheme2withoutspare.put("Nospare","0");
                            amcsplitacscheme2withoutspare.put("NospareTOTAL","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme2").child("Withoutspare").updateChildren(amcsplitacscheme2withoutspare);


                            HashMap<String,Object> amcsplitacscheme2withspare = new HashMap<>();
                            amcsplitacscheme2withspare.put("Limitedspare","0");
                            amcsplitacscheme2withspare.put("LimitedspareTOTAL","0");
                            amcsplitacscheme2withspare.put("Totalspare","0");
                            amcsplitacscheme2withspare.put("TotalspareTOTAL","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme2").child("Withspare").updateChildren(amcsplitacscheme2withspare);


                            HashMap<String,Object> amcsplitacscheme3withoutspare = new HashMap<>();
                            amcsplitacscheme3withoutspare.put("Nospare","0");
                            amcsplitacscheme3withoutspare.put("NospareTOTAL","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme3").child("Withoutspare").updateChildren(amcsplitacscheme3withoutspare);


                            HashMap<String,Object> amcsplitacscheme3withspare = new HashMap<>();
                            amcsplitacscheme3withspare.put("Limitedspare","0");
                            amcsplitacscheme3withspare.put("LimitedspareTOTAL","0");
                            amcsplitacscheme3withspare.put("Totalspare","0");
                            amcsplitacscheme3withspare.put("TotalspareTOTAL","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme3").child("Withspare").updateChildren(amcsplitacscheme3withspare);



                            HashMap<String,Object> amcwindowacscheme1withoutspare = new HashMap<>();
                            amcwindowacscheme1withoutspare.put("Nospare","0");
                            amcwindowacscheme1withoutspare.put("NospareTOTAL","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child("Scheme1").child("Withoutspare").updateChildren(amcwindowacscheme1withoutspare);


                            HashMap<String,Object> amcwindowacscheme1withspare = new HashMap<>();
                            amcwindowacscheme1withspare.put("Limitedspare","0");
                            amcwindowacscheme1withspare.put("LimitedspareTOTAL","0");
                            amcwindowacscheme1withspare.put("Totalspare","0");
                            amcwindowacscheme1withspare.put("TotalspareTOTAL","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child("Scheme1").child("Withspare").updateChildren(amcwindowacscheme1withspare);

                            HashMap<String,Object> amcwindowacscheme2withoutspare = new HashMap<>();
                            amcwindowacscheme2withoutspare.put("Nospare","0");
                            amcwindowacscheme2withoutspare.put("NospareTOTAL","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child("Scheme2").child("Withoutspare").updateChildren(amcwindowacscheme2withoutspare);

                            HashMap<String,Object> amcwindowacscheme2withspare = new HashMap<>();
                            amcwindowacscheme2withspare.put("Limitedspare","0");
                            amcwindowacscheme2withspare.put("LimitedspareTOTAL","0");
                            amcwindowacscheme2withspare.put("Totalspare","0");
                            amcwindowacscheme2withspare.put("TotalspareTOTAL","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child("Scheme2").child("Withspare").updateChildren(amcwindowacscheme2withspare);

                            HashMap<String,Object> total = new HashMap<>();
                            total.put("TOTALPRICE","0");
                            total.put("TOTALQUANTITY","0");
                            total.put("TOTALSAVED","0");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").updateChildren(total);
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").removeValue();

                            HashMap<String,Object> location = new HashMap<>();
                            location.put("Address","I'm Here");
                            root.child("Users").child(mAuth.getCurrentUser().getUid()).updateChildren(location);

                            input1.setEnabled(true);
                            input2.setEnabled(true);
                            input3.setEnabled(true);
                            input4.setEnabled(true);
                            input5.setEnabled(true);
                            input6.setEnabled(true);
                            progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(sendotp.this,homepage.class);
                            startActivity(intent);
                        }
                        else
                        {
                            wrongotp++;
                            if(wrongotp==3)
                            {
                                Toast.makeText(sendotp.this, "Please Check your Number", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            input1.setEnabled(true);
                            input2.setEnabled(true);
                            input3.setEnabled(true);
                            input4.setEnabled(true);
                            input5.setEnabled(true);
                            input6.setEnabled(true);
                            progressBar.setVisibility(View.GONE);
                            if(wrongotp!=3) {
                                Toast.makeText(sendotp.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void closekeyboard()
    {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}