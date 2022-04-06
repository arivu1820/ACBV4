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

public class amcsplitacschemes extends AppCompatActivity {


    private String Category ="SplitAC";
    private Button scheme1_withspareaddbtn,scheme1_withoutspareaddbtn;
    private Button scheme2_withspareaddbtn,scheme2_withoutspareaddbtn;
    private Button scheme3_withspareaddbtn,scheme3_withoutspareaddbtn;
    private ConstraintLayout scheme1_dropdownconstraint,scheme2_dropdownconstraint,scheme3_dropdownconstraint;
    private CardView scheme1_dropdowncardview,scheme2_dropdowncardview,scheme3_dropdowncardview;
    private int scheme1_dropdownconstraintcount = 0,scheme2_dropdownconstraintcount = 0,scheme3_dropdownconstraintcount = 0;
    private CardView scheme1_withspareaddbtn_cardview1,scheme1_withspareaddbtn_cardview2,scheme1_withoutspareaddbtn_cardview1,scheme1_withoutspareaddbtn_cardview2;
    private CardView scheme2_withspareaddbtn_cardview1,scheme2_withspareaddbtn_cardview2,scheme2_withoutspareaddbtn_cardview1,scheme2_withoutspareaddbtn_cardview2;
    private CardView scheme3_withspareaddbtn_cardview1,scheme3_withspareaddbtn_cardview2,scheme3_withoutspareaddbtn_cardview1,scheme3_withoutspareaddbtn_cardview2;
    private TextView scheme1_withsparecount,scheme1_withoutsparecount,scheme2_withsparecount,scheme2_withoutsparecount,scheme3_withsparecount,scheme3_withoutsparecount;
    private Button scheme1_withsparesubbtn,scheme1_withoutsparesubbtn,scheme2_withsparesubbtn,scheme2_withoutsparesubbtn,scheme3_withsparesubbtn,scheme3_withoutsparesubbtn;
    private Button scheme1_withspareplusbtn,scheme1_withoutspareplusbtn,scheme2_withspareplusbtn,scheme2_withoutspareplusbtn,scheme3_withspareplusbtn,scheme3_withoutspareplusbtn;
    private int checking = 0;
    private FrameLayout frontloadingpage;
    private RelativeLayout footer;
    private TextView txttotalitems,txttotalprice;
    private TextView cartt;
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());



    private DatabaseReference cart = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amcsplitacschemes);

        cartt = findViewById(R.id.cartt);

        cartt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(amcsplitacschemes.this,summary.class);
                startActivity(intent);
                finish();
            }
        });

        frontloadingpage = findViewById(R.id.frontloadingpage);

        footer = findViewById(R.id.footer);

        txttotalprice = findViewById(R.id.txttotal);
        txttotalitems = findViewById(R.id.txtitems);

        scheme1_withspareaddbtn = findViewById(R.id.scheme1_withspareaddbtn);
        scheme2_withspareaddbtn = findViewById(R.id.scheme2_withspareaddbtn);
        scheme3_withspareaddbtn = findViewById(R.id.scheme3_withspareaddbtn);

        scheme1_withoutspareaddbtn = findViewById(R.id.scheme1_withoutspareaddbtn);
        scheme2_withoutspareaddbtn = findViewById(R.id.scheme2_withoutspareaddbtn);
        scheme3_withoutspareaddbtn = findViewById(R.id.scheme3_withoutspareaddbtn);

        scheme1_dropdownconstraint = findViewById(R.id.scheme1_dropdownconstraint);
        scheme2_dropdownconstraint = findViewById(R.id.scheme2_dropdownconstraint);
        scheme3_dropdownconstraint = findViewById(R.id.scheme3_dropdownconstraint);

        scheme1_dropdowncardview = findViewById(R.id.scheme1_dropdowncardview);
        scheme2_dropdowncardview = findViewById(R.id.scheme2_dropdowncardview);
        scheme3_dropdowncardview = findViewById(R.id.scheme3_dropdowncardview);

        scheme1_withspareaddbtn_cardview1 = findViewById(R.id.scheme1_withspareaddbtn_cardview1);
        scheme1_withspareaddbtn_cardview2 = findViewById(R.id.scheme1_withspareaddbtn_cardview2);
        scheme2_withspareaddbtn_cardview1 = findViewById(R.id.scheme2_withspareaddbtn_cardview1);
        scheme2_withspareaddbtn_cardview2 = findViewById(R.id.scheme2_withspareaddbtn_cardview2);
        scheme3_withspareaddbtn_cardview1 = findViewById(R.id.scheme3_withspareaddbtn_cardview1);
        scheme3_withspareaddbtn_cardview2 = findViewById(R.id.scheme3_withspareaddbtn_cardview2);

        scheme1_withoutspareaddbtn_cardview2 = findViewById(R.id.scheme1_withoutspareaddbtn_cardview2);
        scheme2_withoutspareaddbtn_cardview2 = findViewById(R.id.scheme2_withoutspareaddbtn_cardview2);
        scheme3_withoutspareaddbtn_cardview2 = findViewById(R.id.scheme3_withoutspareaddbtn_cardview2);

        scheme1_withsparecount = findViewById(R.id.scheme1_withsparecount);
        scheme1_withoutsparecount = findViewById(R.id.scheme1_withoutsparecount);
        scheme2_withsparecount = findViewById(R.id.scheme2_withsparecount);
        scheme2_withoutsparecount = findViewById(R.id.scheme2_withoutsparecount);
        scheme3_withsparecount = findViewById(R.id.scheme3_withsparecount);
        scheme3_withoutsparecount = findViewById(R.id.scheme3_withoutsparecount);

        scheme1_withsparesubbtn = findViewById(R.id.scheme1_withsparesubbtn);
        scheme2_withsparesubbtn = findViewById(R.id.scheme2_withsparesubbtn);
        scheme3_withsparesubbtn = findViewById(R.id.scheme3_withsparesubbtn);


        scheme1_withspareplusbtn = findViewById(R.id.scheme1_withspareplusbtn);
        scheme2_withspareplusbtn = findViewById(R.id.scheme2_withspareplusbtn);
        scheme3_withspareplusbtn = findViewById(R.id.scheme3_withspareplusbtn);

        //withoutspare

        scheme1_withoutsparesubbtn = findViewById(R.id.scheme1_withoutsparesubbtn);
        scheme2_withoutsparesubbtn = findViewById(R.id.scheme2_withoutsparesubbtn);
        scheme3_withoutsparesubbtn = findViewById(R.id.scheme3_withoutsparesubbtn);

        scheme1_withoutspareplusbtn = findViewById(R.id.scheme1_withoutspareplusbtn);
        scheme2_withoutspareplusbtn = findViewById(R.id.scheme2_withoutspareplusbtn);
        scheme3_withoutspareplusbtn = findViewById(R.id.scheme3_withoutspareplusbtn);

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
                String stringchecking = snapshot.child("Products").child("AMC").child("SplitAC").child("Scheme1").child("Withspare").child("Price").child("Totalspare").getValue().toString();
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


        // For cart button visibility

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
        //scheme2
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String strings2wst = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child(Category).child("Scheme2").child("Withspare").child("Totalspare").getValue().toString();
                int s2wst = Integer.parseInt(strings2wst);
                String strings2wsl = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child(Category).child("Scheme2").child("Withspare").child("Limitedspare").getValue().toString();
                int s2wsl = Integer.parseInt(strings2wsl);
                int totals2withspare = s2wsl + s2wst;
                scheme2_withsparecount.setText(""+totals2withspare);
                if(totals2withspare<=0)
                {
                    scheme2_withspareaddbtn_cardview2.setVisibility(View.INVISIBLE);
                }
                else
                {
                    scheme2_withspareaddbtn_cardview2.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String strings2wos = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child(Category).child("Scheme2").child("Withoutspare").child("Nospare").getValue().toString();
                int s2wos = Integer.parseInt(strings2wos);
                scheme2_withoutsparecount.setText(""+s2wos);
                if(s2wos<=0)
                {
                    scheme2_withoutspareaddbtn_cardview2.setVisibility(View.INVISIBLE);
                }
                else
                {
                    scheme2_withoutspareaddbtn_cardview2.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //scheme3
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String strings3wst = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child(Category).child("Scheme3").child("Withspare").child("Totalspare").getValue().toString();
                int s3wst = Integer.parseInt(strings3wst);
                String strings3wsl = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child(Category).child("Scheme3").child("Withspare").child("Limitedspare").getValue().toString();
                int s3wsl = Integer.parseInt(strings3wsl);
                int totals3withspare = s3wsl + s3wst;
                scheme3_withsparecount.setText(""+totals3withspare);
                if(totals3withspare<=0)
                {
                    scheme3_withspareaddbtn_cardview2.setVisibility(View.INVISIBLE);
                }
                else
                {
                    scheme3_withspareaddbtn_cardview2.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String strings3wos = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child(Category).child("Scheme3").child("Withoutspare").child("Nospare").getValue().toString();
                int s3wos = Integer.parseInt(strings3wos);
                scheme3_withoutsparecount.setText(""+s3wos);
                if(s3wos<=0)
                {
                    scheme3_withoutspareaddbtn_cardview2.setVisibility(View.INVISIBLE);
                }
                else
                {
                    scheme3_withoutspareaddbtn_cardview2.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        scheme1_withspareaddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splitac_scheme1_withspare_bsd splitac_scheme1_withspare_bsd = new splitac_scheme1_withspare_bsd();
                splitac_scheme1_withspare_bsd.show(getSupportFragmentManager(),splitac_scheme1_withspare_bsd.getTag());
            }
        });
        scheme1_withoutspareaddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splitac_scheme1_withoutspare_bsd splitac_scheme1_withoutspare_bsd = new splitac_scheme1_withoutspare_bsd();
                splitac_scheme1_withoutspare_bsd.show(getSupportFragmentManager(),splitac_scheme1_withoutspare_bsd.getTag());
            }
        });

        scheme2_withspareaddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splitac_scheme2_withspare_bsd splitac_scheme2_withspare_bsd = new splitac_scheme2_withspare_bsd();
                splitac_scheme2_withspare_bsd.show(getSupportFragmentManager(),splitac_scheme2_withspare_bsd.getTag());
            }
        });
        scheme2_withoutspareaddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splitac_scheme2_withoutspare_bsd splitac_scheme2_withoutspare_bsd = new splitac_scheme2_withoutspare_bsd();
                splitac_scheme2_withoutspare_bsd.show(getSupportFragmentManager(),splitac_scheme2_withoutspare_bsd.getTag());
            }
        });

        scheme3_withspareaddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splitac_scheme3_withspare_bsd splitac_scheme3_withspare_bsd = new splitac_scheme3_withspare_bsd();
                splitac_scheme3_withspare_bsd.show(getSupportFragmentManager(),splitac_scheme3_withspare_bsd.getTag());
            }
        });
        scheme3_withoutspareaddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splitac_scheme3_withoutspare_bsd splitac_scheme3_withoutspare_bsd = new splitac_scheme3_withoutspare_bsd();
                splitac_scheme3_withoutspare_bsd.show(getSupportFragmentManager(),splitac_scheme3_withoutspare_bsd.getTag());
            }
        });


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
        scheme2_dropdownconstraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(scheme2_dropdownconstraintcount!=1)
                {
                    scheme2_dropdowncardview.setVisibility(View.VISIBLE);
                    scheme2_dropdownconstraintcount++;
                }
                else
                {
                    scheme2_dropdowncardview.setVisibility(View.GONE);
                    scheme2_dropdownconstraintcount=0;
                }
            }
        });
        scheme3_dropdownconstraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(scheme3_dropdownconstraintcount!=1)
                {
                    scheme3_dropdowncardview.setVisibility(View.VISIBLE);
                    scheme3_dropdownconstraintcount++;
                }
                else
                {
                    scheme3_dropdowncardview.setVisibility(View.GONE);
                    scheme3_dropdownconstraintcount=0;
                }
            }
        });

        scheme1_dropdowncardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheme1_dropdowncardview.setVisibility(View.GONE);
            }
        });
        scheme2_dropdowncardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheme2_dropdowncardview.setVisibility(View.GONE);
            }
        });
        scheme3_dropdowncardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheme3_dropdowncardview.setVisibility(View.GONE);
            }
        });

        scheme1_withspareplusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splitac_scheme1_withspare_bsd splitac_scheme1_withspare_bsd = new splitac_scheme1_withspare_bsd();
                splitac_scheme1_withspare_bsd.show(getSupportFragmentManager(),splitac_scheme1_withspare_bsd.getTag());
            }
        });
        scheme2_withspareplusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splitac_scheme2_withspare_bsd splitac_scheme2_withspare_bsd = new splitac_scheme2_withspare_bsd();
                splitac_scheme2_withspare_bsd.show(getSupportFragmentManager(),splitac_scheme2_withspare_bsd.getTag());
            }
        });
        scheme3_withspareplusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splitac_scheme3_withspare_bsd splitac_scheme3_withspare_bsd = new splitac_scheme3_withspare_bsd();
                splitac_scheme3_withspare_bsd.show(getSupportFragmentManager(),splitac_scheme3_withspare_bsd.getTag());
            }
        });

        scheme1_withsparesubbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splitac_scheme1_withspare_bsd splitac_scheme1_withspare_bsd = new splitac_scheme1_withspare_bsd();
                splitac_scheme1_withspare_bsd.show(getSupportFragmentManager(),splitac_scheme1_withspare_bsd.getTag());
            }
        });
        scheme2_withsparesubbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splitac_scheme2_withspare_bsd splitac_scheme2_withspare_bsd = new splitac_scheme2_withspare_bsd();
                splitac_scheme2_withspare_bsd.show(getSupportFragmentManager(),splitac_scheme2_withspare_bsd.getTag());
            }
        });
        scheme3_withsparesubbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splitac_scheme3_withspare_bsd splitac_scheme3_withspare_bsd = new splitac_scheme3_withspare_bsd();
                splitac_scheme3_withspare_bsd.show(getSupportFragmentManager(),splitac_scheme3_withspare_bsd.getTag());
            }
        });


        scheme1_withoutspareplusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splitac_scheme1_withoutspare_bsd splitac_scheme1_withoutspare_bsd = new splitac_scheme1_withoutspare_bsd();
                splitac_scheme1_withoutspare_bsd.show(getSupportFragmentManager(),splitac_scheme1_withoutspare_bsd.getTag());
            }
        });
        scheme2_withoutspareplusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splitac_scheme2_withoutspare_bsd splitac_scheme2_withoutspare_bsd = new splitac_scheme2_withoutspare_bsd();
                splitac_scheme2_withoutspare_bsd.show(getSupportFragmentManager(),splitac_scheme2_withoutspare_bsd.getTag());
            }
        });
        scheme3_withoutspareplusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splitac_scheme3_withoutspare_bsd splitac_scheme3_withoutspare_bsd = new splitac_scheme3_withoutspare_bsd();
                splitac_scheme3_withoutspare_bsd.show(getSupportFragmentManager(),splitac_scheme3_withoutspare_bsd.getTag());
            }
        });

        scheme1_withoutsparesubbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splitac_scheme1_withoutspare_bsd splitac_scheme1_withoutspare_bsd = new splitac_scheme1_withoutspare_bsd();
                splitac_scheme1_withoutspare_bsd.show(getSupportFragmentManager(),splitac_scheme1_withoutspare_bsd.getTag());
            }
        });
        scheme2_withoutsparesubbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splitac_scheme2_withoutspare_bsd splitac_scheme2_withoutspare_bsd = new splitac_scheme2_withoutspare_bsd();
                splitac_scheme2_withoutspare_bsd.show(getSupportFragmentManager(),splitac_scheme2_withoutspare_bsd.getTag());
            }
        });
        scheme3_withoutsparesubbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splitac_scheme3_withoutspare_bsd splitac_scheme3_withoutspare_bsd = new splitac_scheme3_withoutspare_bsd();
                splitac_scheme3_withoutspare_bsd.show(getSupportFragmentManager(),splitac_scheme3_withoutspare_bsd.getTag());
            }
        });




    }
}