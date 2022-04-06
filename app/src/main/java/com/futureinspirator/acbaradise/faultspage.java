package com.futureinspirator.acbaradise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class faultspage extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());
    private FrameLayout frontloadingpage;
    private TextView txttotalitems,txttotalprice;
    private RelativeLayout footer;
    private String databasevalue = "0";
    private DatabaseReference cart = FirebaseDatabase.getInstance().getReference();
    private TextView cartt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faultspage);

        cartt = findViewById(R.id.cartt);

        cartt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(faultspage.this,summary.class);
                startActivity(intent);
                finish();
            }
        });

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        txttotalprice = findViewById(R.id.txttotal);
        txttotalitems = findViewById(R.id.txtitems);
        footer = findViewById(R.id.footer);
        frontloadingpage = findViewById(R.id.frontloadingpage);

        tabLayout.setupWithViewPager(viewPager);

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

        //frontloadingpage

        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String stringfooteritems = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALQUANTITY").getValue().toString();
                String stringfooterprice = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALPRICE").getValue().toString();
                databasevalue = snapshot.child("Products").child("Faults").child("SplitAC").child("Price").child("Gasleak").getValue().toString();
                int footeritems = Integer.parseInt(stringfooteritems);
                int footerprice = Integer.parseInt(stringfooterprice);
                if(databasevalue!="0") {
                    frontloadingpage.setVisibility(View.INVISIBLE);
                }
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


        Faults_VPAdapter fault_vpAdapter = new Faults_VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        fault_vpAdapter.addfragment(new fragment_split_faults(),"SPLIT");
        fault_vpAdapter.addfragment(new fragment_window_faults(),"WINDOW");
        fault_vpAdapter.addfragment(new fragment_cassette_faults(),"CASSETTE");


        viewPager.setAdapter(fault_vpAdapter);


    }

}
