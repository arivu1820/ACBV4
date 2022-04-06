package com.futureinspirator.acbaradise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class amccassetteacschemes extends AppCompatActivity {


    private String Category ="CassetteAC";

    private Button scheme1_withspareaddbtn,scheme1_withoutspareaddbtn;
    private ConstraintLayout scheme1_dropdownconstraint;
    private CardView scheme1_dropdowncardview;
    private int scheme1_dropdownconstraintcount = 0;
    private CardView scheme1_withspareaddbtn_cardview1,scheme1_withspareaddbtn_cardview2,scheme1_withoutspareaddbtn_cardview2;
    private TextView scheme1_withsparecount,scheme1_withoutsparecount,scheme2_withsparecount,scheme2_withoutsparecount;
    private Button scheme1_withsparesubbtn,scheme1_withoutsparesubbtn;
    private Button scheme1_withspareplusbtn,scheme1_withoutspareplusbtn;
    private int checking = 0;
    private FrameLayout frontloadingpage;
    private RelativeLayout footer;
    private TextView txttotalitems,txttotalprice;
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());
    private TextView cartt;


    private DatabaseReference cart = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amccassetteacschemes);


        cartt = findViewById(R.id.cartt);

        cartt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(amccassetteacschemes.this,summary.class);
                startActivity(intent);
                finish();
            }
        });

        frontloadingpage = findViewById(R.id.frontloadingpage);

        footer = findViewById(R.id.footer);

        txttotalprice = findViewById(R.id.txttotal);
        txttotalitems = findViewById(R.id.txtitems);

        scheme1_withspareaddbtn = findViewById(R.id.scheme1_withspareaddbtn);

        scheme1_withoutspareaddbtn = findViewById(R.id.scheme1_withoutspareaddbtn);

        scheme1_dropdownconstraint = findViewById(R.id.scheme1_dropdownconstraint);

        scheme1_dropdowncardview = findViewById(R.id.scheme1_dropdowncardview);

        scheme1_withspareaddbtn_cardview1 = findViewById(R.id.scheme1_withspareaddbtn_cardview1);
        scheme1_withspareaddbtn_cardview2 = findViewById(R.id.scheme1_withspareaddbtn_cardview2);

        scheme1_withoutspareaddbtn_cardview2 = findViewById(R.id.scheme1_withoutspareaddbtn_cardview2);

        scheme1_withsparecount = findViewById(R.id.scheme1_withsparecount);
        scheme1_withoutsparecount = findViewById(R.id.scheme1_withoutsparecount);

        scheme1_withsparesubbtn = findViewById(R.id.scheme1_withsparesubbtn);

        scheme1_withspareplusbtn = findViewById(R.id.scheme1_withspareplusbtn);

        //withoutspare

        scheme1_withoutsparesubbtn = findViewById(R.id.scheme1_withoutsparesubbtn);

        scheme1_withoutspareplusbtn = findViewById(R.id.scheme1_withoutspareplusbtn);

        //footer

        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String stringfooteritems = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALQUANTITY").getValue().toString();
                String stringfooterprice = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALPRICE").getValue().toString();
                int footeritems = Integer.parseInt(stringfooteritems);
                int footerprice = Integer.parseInt(stringfooterprice);
                if(footeritems<=0 && footerprice<=0) {
                    footer.setVisibility(View.INVISIBLE);
                }
                else {
                    txttotalitems.setText(stringfooteritems);
                    txttotalprice.setText(stringfooterprice);
                    footer.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //checking frontloadingpage

        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String stringchecking = snapshot.child("Products").child("AMC").child(Category).child("Scheme1").child("Withspare").child("Price").child("Totalspare").getValue().toString();
                checking = Integer.parseInt(stringchecking);
                if(checking!=0)
                {
                    frontloadingpage.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//
//
//        // For cart button visibility
//
        //scheme1
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String strings1wst = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child(Category).child("Scheme1").child("Withspare").child("Totalspare").getValue().toString();
                int s1wst = Integer.parseInt(strings1wst);
                String strings1wsl = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child(Category).child("Scheme1").child("Withspare").child("Limitedspare").getValue().toString();
                int s1wsl = Integer.parseInt(strings1wsl);
                int totals1withspare = s1wsl + s1wst;
                scheme1_withsparecount.setText(""+totals1withspare);
                if(totals1withspare<=0)
                {
                    scheme1_withspareaddbtn_cardview2.setVisibility(View.INVISIBLE);
                }
                else
                {
                    scheme1_withspareaddbtn_cardview2.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String strings1wos = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child(Category).child("Scheme1").child("Withoutspare").child("Nospare").getValue().toString();
                int s1wos = Integer.parseInt(strings1wos);
                scheme1_withoutsparecount.setText(""+s1wos);
                if(s1wos<=0)
                {
                    scheme1_withoutspareaddbtn_cardview2.setVisibility(View.INVISIBLE);
                }
                else
                {
                    scheme1_withoutspareaddbtn_cardview2.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        scheme1_withspareaddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cassetteac_scheme1_withspare_bsd cassetteac_scheme1_withspare_bsd = new cassetteac_scheme1_withspare_bsd();
                cassetteac_scheme1_withspare_bsd.show(getSupportFragmentManager(),cassetteac_scheme1_withspare_bsd.getTag());
            }
        });

        scheme1_withoutspareaddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cassetteac_scheme1_withoutspare_bsd cassetteac_scheme1_withoutspare_bsd = new cassetteac_scheme1_withoutspare_bsd();
                cassetteac_scheme1_withoutspare_bsd.show(getSupportFragmentManager(),cassetteac_scheme1_withoutspare_bsd.getTag());
            }
        }); //

        scheme1_dropdownconstraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(scheme1_dropdownconstraintcount!=1)
                {
                    scheme1_dropdowncardview.setVisibility(View.VISIBLE);
                    scheme1_dropdownconstraintcount++;
                }
                else
                {
                    scheme1_dropdowncardview.setVisibility(View.GONE);
                    scheme1_dropdownconstraintcount=0;
                }
            }
        });

        scheme1_dropdowncardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheme1_dropdowncardview.setVisibility(View.GONE);
            }
        });

        scheme1_withspareplusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cassetteac_scheme1_withspare_bsd cassetteac_scheme1_withspare_bsd = new cassetteac_scheme1_withspare_bsd();
                cassetteac_scheme1_withspare_bsd.show(getSupportFragmentManager(),cassetteac_scheme1_withspare_bsd.getTag());
            }
        });

        scheme1_withsparesubbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cassetteac_scheme1_withspare_bsd cassetteac_scheme1_withspare_bsd = new cassetteac_scheme1_withspare_bsd();
                cassetteac_scheme1_withspare_bsd.show(getSupportFragmentManager(),cassetteac_scheme1_withspare_bsd.getTag());
            }
        });

        scheme1_withoutspareplusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cassetteac_scheme1_withoutspare_bsd cassetteac_scheme1_withoutspare_bsd = new cassetteac_scheme1_withoutspare_bsd();
                cassetteac_scheme1_withoutspare_bsd.show(getSupportFragmentManager(),cassetteac_scheme1_withoutspare_bsd.getTag());
            }
        });  //

        scheme1_withoutsparesubbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cassetteac_scheme1_withoutspare_bsd cassetteac_scheme1_withoutspare_bsd = new cassetteac_scheme1_withoutspare_bsd();
                cassetteac_scheme1_withoutspare_bsd.show(getSupportFragmentManager(),cassetteac_scheme1_withoutspare_bsd.getTag());
            }
        });  //
    }
}