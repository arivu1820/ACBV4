package com.futureinspirator.acbaradise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class installuninstallcombopage extends AppCompatActivity {

    private Button split_add_btn;
    private Button split_plus_btn;
    private TextView split_txt_addbtn;
    private Button split_minus_btn;

    private Button window_add_btn;
    private Button window_plus_btn;
    private  TextView window_txt_addbtn;
    private Button window_minus_btn;

    private Button cassette_add_btn;
    private Button cassette_plus_btn;
    private TextView cassette_txt_addbtn;
    private Button cassette_minus_btn;
    private RelativeLayout footer;





    private Button split_addbtn_unin;
    private Button window_addbtn_unin;
    private Button cassete_addbtn_unin;

    private CardView split_main_cardview, window_cardview, cassette_cardview;
    private CardView split_install_dialog_cardview, split_uninstall_dialog_cardview, split_combo_dialog_cardview;
    private Button install_uninstall;


    private ScrollView main_page_scrollview1;
    private ScrollView main_page_split_scrollview;
    private ScrollView main_page_window_scrollview;
    private ScrollView main_page_cassette_scrollview;


    private CardView split_unin_cardview;
    private CardView window_unin_cardview;
    private CardView cassette_unin_cardview;


    private ImageView close;
    private CardView main_of_main_dialog_cardview;


    private CardView split_install_pmt_cardview2;
    private CardView split_install_pmt_cardview1;

    private Button split_install_addbutton;
    private Button split_install_minusbtn;
    private Button split_install_plusbtn;
    private TextView split_install_txt;
    private TextView txttotalitems;
    private TextView  txttotalprice;
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());
    private FrameLayout frontloadingpage;
    private DatabaseReference cart = FirebaseDatabase.getInstance().getReference();
    private TextView cartt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installuninstallcombopage);

        cartt = findViewById(R.id.cartt);

        cartt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(installuninstallcombopage.this,summary.class);
                startActivity(intent);
            }
        });


        split_add_btn=findViewById(R.id.splitssbtn);
        split_plus_btn=findViewById(R.id.addsplitss);
        split_txt_addbtn=findViewById(R.id.txtsplitss);
        split_minus_btn=findViewById(R.id.subsplitss);

        window_add_btn=findViewById(R.id.windowssbtn);
        window_plus_btn=findViewById(R.id.addwindowss);
        window_txt_addbtn=findViewById(R.id.txtwindowss);
        window_minus_btn=findViewById(R.id.subwindowss);


        cassette_add_btn=findViewById(R.id.cassettessbtn);
        cassette_plus_btn=findViewById(R.id.addcassettess);
        cassette_txt_addbtn=findViewById(R.id.txtcassettess);
        cassette_minus_btn=findViewById(R.id.subcassettess);

        txttotalitems = findViewById(R.id.txtitems);
        txttotalprice=findViewById(R.id.txttotal);
        footer = findViewById(R.id.footer);
        frontloadingpage = findViewById(R.id.frontloadingpage);


        split_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                splitac_installuninstall_bsd split_fragment = new splitac_installuninstall_bsd();
                split_fragment.show(getSupportFragmentManager(),split_fragment.getTag());
            }
        });

        window_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                windowac_installuninstall_bsd window_fragment = new windowac_installuninstall_bsd();
                window_fragment.show(getSupportFragmentManager(),window_fragment.getTag());
            }
        });

        cassette_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cassetteac_installuninstall_bsd cassette_fragment = new cassetteac_installuninstall_bsd();
                cassette_fragment.show(getSupportFragmentManager(),cassette_fragment.getTag());
            }
        });

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
                String stringchecking = snapshot.child("Products").child("Installuninstall").child("SplitAC").child("Price").child("Install").getValue().toString();
                int checking = Integer.parseInt(stringchecking);
                if(checking!=0)
                {
                    frontloadingpage.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}