package com.futureinspirator.acbaradise;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class premium extends AppCompatActivity implements PaymentResultListener {

    private Button scheme1,scheme2,scheme3;
    private String scheme1price,scheme2price,scheme3price;
    private String amount;
    private String month;
    private boolean permission = false;

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium);

        scheme1 = findViewById(R.id.button);
        scheme2 = findViewById(R.id.button2);
        scheme3 = findViewById(R.id.button3);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                scheme1price = snapshot.child("Premium").child("Scheme1").getValue().toString();
                scheme2price = snapshot.child("Premium").child("Scheme2").getValue().toString();
                scheme3price = snapshot.child("Premium").child("Scheme3").getValue().toString();

                if(!(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Premium").exists()))
                {
                    permission = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        scheme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if(permission==true)
                {
                    month = "3";
                    amount = scheme1price;
                    checkout();

                }
                else if(permission==false)
                {

                    Toast.makeText(premium.this, "You already Subscribed premium membership", Toast.LENGTH_SHORT).show();

                }


            }
        });

        scheme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if(permission==true)
                {
                    month = "6";
                    amount = scheme2price;
                    checkout();

                }
                else if(permission==false)
                {

                    Toast.makeText(premium.this, "You already Subscribed premium membership", Toast.LENGTH_SHORT).show();

                }


            }
        });

        scheme3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if(permission==true)
                {

                    month = "12";
                    amount = scheme3price;
                    checkout();

                }
                else if(permission==false)
                {

                    Toast.makeText(premium.this, "You already Subscribed premium membership", Toast.LENGTH_SHORT).show();

                }


            }
        });


    }


    public void checkout(){
        Checkout checkout = new Checkout();

        checkout.setKeyID("rzp_test_wic5QWXwG8LIPV");

        JSONObject object = new JSONObject();

        try {
            object.put("name","AC Baradise");

            object.put("description","Test Payment");

            object.put("theme.color","#0093DD");

            object.put("currency","INR");

            object.put("amount",amount);

            object.put("prefill.contact","6382219393");

            object.put("prefill.email","jaideepasivakumar02@gmail.com");

            checkout.open(premium.this,object);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onPaymentSuccess(String s) {

        HashMap<String,Object> data = new HashMap<>();

        Calendar check = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
        check.add(Calendar.MONTH, Integer.parseInt(month));
        String date = format.format(check.getTime());

        if(month.equals("3")) {
            data.put("Scheme1",date);
            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Premium").updateChildren(data);
            root.child("Admin").child("PremiumMembership").child(mAuth.getCurrentUser().getUid()).push().updateChildren(data);
            Toast.makeText(this, "Congratulation you successfully subscribed Scheme1 premium membership", Toast.LENGTH_LONG).show();
        }
        else if(month.equals("6")) {
            data.put("Scheme2",date);
            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Premium").updateChildren(data);
            root.child("Admin").child("PremiumMembership").child(mAuth.getCurrentUser().getUid()).push().updateChildren(data);
            Toast.makeText(this, "Congratulation you successfully subscribed Scheme1 premium membership", Toast.LENGTH_LONG).show();
        }
        else if(month.equals("12")) {
            data.put("Scheme3",date);
            root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Premium").updateChildren(data);
            root.child("Admin").child("PremiumMembership").child(mAuth.getCurrentUser().getUid()).push().updateChildren(data);
            Toast.makeText(this, "Congratulation you successfully subscribed Scheme1 premium membership", Toast.LENGTH_LONG).show();
        }
        finish();

    }

    @Override
    public void onPaymentError(int i, String s) {


        Toast.makeText(premium.this, "PAYMENT FAILED", Toast.LENGTH_SHORT).show();
        finish();

    }

}