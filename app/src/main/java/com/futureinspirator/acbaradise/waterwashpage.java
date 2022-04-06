package com.futureinspirator.acbaradise;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
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

import java.util.HashMap;


public class waterwashpage extends AppCompatActivity {

    private String Category = "WaterWash";
    private CardView cardview_splitss1,cardview_splitss2,cardview_windowss1, cardview_windowss2,
            cardview_cassettess1,cardview_cassettess2,cardview_split3601,cardview_split3602,cardview_cassette3601,cardview_cassette3602;
    private Button splitssbtn,windowssbtn,cassettessbtn,split360btn,cassette360btn;
    private Button addsplitss,addwindowss,addcassettess,addsplit360,addcassette360;
    private Button subsplitss,subwindowss,subcassettess,subsplit360,subcassette360;
    private TextView txtsplitss,txtwindowss,txtcassettess,txtsplit360,txtcassette360;
    private TextView txttotalitems,txttotalprice;
    private RelativeLayout footer;
    private FrameLayout frontloadingpage;
    private int splitssprice = 0,windowssprice=0,cassettessprice = 0,split360price=0,cassette360price=0;
    private int dissplitssprice = 0,diswindowssprice=0,discassettessprice = 0,dissplit360price=0,discassette360price=0;
    private String stringsplitssprice,stringwindowssprice,stringcassettessprice,stringsplit360price,stringcassette360price;
    private CardView splitacprogressbar,windowacprogressbar,cassetteacprogressbar,split360acprogressbar,cassette360acprogressbar;
    private DatabaseReference cart = FirebaseDatabase.getInstance().getReference();
    private String splitssacdatabaseQvalue,windowssacdatabaseQvalue,cassettessacdatabaseQvalue,split360acdatabaseQvalue,cassette360acdatabaseQvalue;
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());
    private TextView cartt;

    private TextView txt_price_split_waterwash,txt_price_windowss_waterwash,txt_price_cassettess_waterwash,txt_price_split360_waterwash,txt_price_cassette360_waterwash;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waterwashpage);

        cartt = findViewById(R.id.cartt);

        cartt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(waterwashpage.this,summary.class);
                startActivity(intent);
                finish();
            }
        });


        DatabaseReference root,discount;
        root = FirebaseDatabase.getInstance().getReference().child("Products").child(Category).child("Price");
        discount = FirebaseDatabase.getInstance().getReference().child("Products").child(Category).child("Discount");


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                stringsplitssprice = snapshot.child("SplitSSAC").getValue().toString();
                stringwindowssprice = snapshot.child("WindowSSAC").getValue().toString();
                stringcassettessprice = snapshot.child("CassetteSSAC").getValue().toString();
                stringsplit360price = snapshot.child("Split360AC").getValue().toString();
                stringcassette360price = snapshot.child("Cassette360AC").getValue().toString();

                txt_price_split_waterwash.setText(""+stringsplitssprice);
                txt_price_cassettess_waterwash.setText(""+stringcassettessprice);
                txt_price_windowss_waterwash.setText(""+stringwindowssprice);
                txt_price_split360_waterwash.setText(""+stringsplit360price);
                txt_price_cassette360_waterwash.setText(""+stringcassette360price);



                splitssprice = Integer.parseInt(stringsplitssprice);
                windowssprice = Integer.parseInt(stringwindowssprice);
                cassettessprice = Integer.parseInt(stringcassettessprice);
                split360price = Integer.parseInt(stringsplit360price);
                cassette360price = Integer.parseInt(stringcassette360price);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        discount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                String disstringsplitssprice = snapshot.child("SplitSSAC").getValue().toString();
                String disstringwindowssprice = snapshot.child("WindowSSAC").getValue().toString();
                String disstringcassettessprice = snapshot.child("CassetteSSAC").getValue().toString();
                String disstringsplit360price = snapshot.child("Split360AC").getValue().toString();
                String disstringcassette360price = snapshot.child("Cassette360AC").getValue().toString();
                dissplitssprice = Integer.parseInt(disstringsplitssprice);
                diswindowssprice = Integer.parseInt(disstringwindowssprice);
                discassettessprice = Integer.parseInt(disstringcassettessprice);
                dissplit360price = Integer.parseInt(disstringsplit360price);
                discassette360price = Integer.parseInt(disstringcassette360price);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                splitssacdatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("SplitSSAC").getValue().toString();
                txtsplitss.setText(""+splitssacdatabaseQvalue);
                int intsplitssacdatabaseQvalue = Integer.parseInt(splitssacdatabaseQvalue);
                if(intsplitssacdatabaseQvalue<=0)
                {
                    cardview_splitss2.setVisibility(View.INVISIBLE);
                }
                else
                {
                    cardview_splitss2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                windowssacdatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("WindowSSAC").getValue().toString();
                txtwindowss.setText(""+windowssacdatabaseQvalue);
                int intwindowssacdatabaseQvalue = Integer.parseInt(windowssacdatabaseQvalue);
                if(intwindowssacdatabaseQvalue<=0)
                {
                    cardview_windowss2.setVisibility(View.INVISIBLE);
                }
                else
                {
                    cardview_windowss2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cassettessacdatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("CassetteSSAC").getValue().toString();
                txtcassettess.setText(""+cassettessacdatabaseQvalue);
                int intcassettessacdatabaseQvalue = Integer.parseInt(cassettessacdatabaseQvalue);
                if(intcassettessacdatabaseQvalue<=0)
                {
                    cardview_cassettess2.setVisibility(View.INVISIBLE);
                }
                else
                {
                    cardview_cassettess2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                split360acdatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("Split360AC").getValue().toString();
                txtsplit360.setText(""+split360acdatabaseQvalue);
                int intsplit360acdatabaseQvalue = Integer.parseInt(split360acdatabaseQvalue);
                if(intsplit360acdatabaseQvalue<=0)
                {
                    cardview_split3602.setVisibility(View.INVISIBLE);
                }
                else
                {
                    cardview_split3602.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cassette360acdatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("Cassette360AC").getValue().toString();
                txtcassette360.setText(""+cassette360acdatabaseQvalue);
                int intcassette360acdatabaseQvalue = Integer.parseInt(cassette360acdatabaseQvalue);
                if(intcassette360acdatabaseQvalue<=0)
                {
                    cardview_cassette3602.setVisibility(View.INVISIBLE);
                }
                else
                {
                    cardview_cassette3602.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String stringfooteritems = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALQUANTITY").getValue().toString();
                String stringfooterprice = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALPRICE").getValue().toString();
                int footeritems = Integer.parseInt(stringfooteritems);
                int footerprice = Integer.parseInt(stringfooterprice);
                if(splitssprice!=0&&windowssprice!=0&&cassettessprice!=0&&split360price!=0&&cassette360price!=0) {
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


        txt_price_split_waterwash=findViewById(R.id.textView8);
        txt_price_cassettess_waterwash=findViewById(R.id.txt_price_cassettess_waterwash);
        txt_price_windowss_waterwash=findViewById(R.id.txt_price_windowss_waterwash);
        txt_price_split360_waterwash=findViewById(R.id.txt_price_split360_waterwash);
        txt_price_cassette360_waterwash=findViewById(R.id.txt_price_cassette360_waterwash);


        cardview_splitss1 = findViewById(R.id.cardview_splitss1);
        cardview_splitss2 = findViewById(R.id.cardview_splitss2);

        cardview_windowss1 = findViewById(R.id.cardview_windowss1);
        cardview_windowss2 = findViewById(R.id.cardview_windowss2);

        cardview_cassettess1 = findViewById(R.id.cardview_cassettess1);
        cardview_cassettess2 = findViewById(R.id.cardview_cassettess2);

        cardview_split3601 = findViewById(R.id.cardview_split3601);
        cardview_split3602 = findViewById(R.id.cardview_split3602);

        cardview_cassette3601 = findViewById(R.id.cardview_cassette3601);
        cardview_cassette3602 = findViewById(R.id.cardview_cassette3602);

        splitssbtn = findViewById(R.id.splitssbtn);
        windowssbtn = findViewById(R.id.windowssbtn);
        cassettessbtn = findViewById(R.id.cassettessbtn);
        split360btn = findViewById(R.id.split360btn);
        cassette360btn = findViewById(R.id.cassette360btn);

        addsplitss = findViewById(R.id.addsplitss);
        addwindowss = findViewById(R.id.addwindowss);
        addcassettess = findViewById(R.id.addcassettess);
        addsplit360 = findViewById(R.id.addsplit360);
        addcassette360 = findViewById(R.id.addcassette360);

        subsplitss = findViewById(R.id.subsplitss);
        subwindowss = findViewById(R.id.subwindowss);
        subcassettess = findViewById(R.id.subcassettess);
        subsplit360 = findViewById(R.id.subsplit360);
        subcassette360 = findViewById(R.id.subcassette360);


        txtsplitss = findViewById(R.id.txtsplitss);
        txtwindowss = findViewById(R.id.txtwindowss);
        txtcassettess = findViewById(R.id.txtcassettess);
        txtsplit360 = findViewById(R.id.txtsplit360);
        txtcassette360 = findViewById(R.id.txtcassette360);

        txttotalitems = findViewById(R.id.txtitems);
        txttotalprice = findViewById(R.id.txttotal);
        footer = findViewById(R.id.footer);
        frontloadingpage = findViewById(R.id.frontloadingpage);

        splitacprogressbar = findViewById(R.id.splitssacprogressbar);
        windowacprogressbar = findViewById(R.id.windowssacprogressbar);
//        cassetteacprogressbar = findViewById(R.id.cassetteacprogressbar);
        split360acprogressbar = findViewById(R.id.split360acprogressbar);
        cassette360acprogressbar = findViewById(R.id.cassette360acprogressbar);




        splitssbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                cardview_splitss1.setVisibility(View.INVISIBLE);
                splitacprogressbar.setVisibility(View.VISIBLE);
                splitssquantity("add");
                totalquantity("add");
                totalprice("add",splitssprice,dissplitssprice,"SplitSSAC");
                wwsplitssprice("add",splitssprice );
                btn("false");
            }
        });
        addsplitss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                cardview_splitss1.setVisibility(View.INVISIBLE);
                splitacprogressbar.setVisibility(View.VISIBLE);
                splitssquantity("add");
                totalquantity("add");
                totalprice("add",splitssprice - dissplitssprice,dissplitssprice,"SplitSSAC");
                wwsplitssprice("add",splitssprice - dissplitssprice);
                totalsaved("add",dissplitssprice,"SplitSSAC");
                btn("false");
            }
        });
        subsplitss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                cardview_splitss1.setVisibility(View.INVISIBLE);
                splitacprogressbar.setVisibility(View.VISIBLE);
                splitssquantity("sub");
                totalquantity("sub");
                totalprice("sub",splitssprice - dissplitssprice,dissplitssprice,"SplitSSAC");
                wwsplitssprice("sub",splitssprice - dissplitssprice);
                totalsaved("sub",dissplitssprice,"SplitSSAC");
                btn("false");
            }
        });

        windowssbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                cardview_windowss1.setVisibility(View.INVISIBLE);
                windowacprogressbar.setVisibility(View.VISIBLE);
                windowssquantity("add");
                totalquantity("add");
                totalprice("add",windowssprice ,diswindowssprice,"WindowSSAC");
                wwwindowssprice("add",windowssprice);
                btn("false");
            }
        });
        addwindowss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                cardview_windowss1.setVisibility(View.INVISIBLE);
                windowacprogressbar.setVisibility(View.VISIBLE);
                windowssquantity("add");
                totalquantity("add");
                totalprice("add",windowssprice - diswindowssprice,diswindowssprice,"WindowSSAC");
                wwwindowssprice("add",windowssprice - diswindowssprice);
                totalsaved("add",diswindowssprice,"WindowSSAC");
                btn("false");
            }
        });
        subwindowss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                cardview_windowss1.setVisibility(View.INVISIBLE);
                windowacprogressbar.setVisibility(View.VISIBLE);
                windowssquantity("sub");
                totalquantity("sub");
                totalprice("sub",windowssprice  - diswindowssprice,diswindowssprice,"WindowSSAC");
                wwwindowssprice("sub",windowssprice - diswindowssprice);
                totalsaved("sub",diswindowssprice,"WindowSSAC");
                btn("false");
            }
        });

        cassettessbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                cardview_cassettess1.setVisibility(View.INVISIBLE);
                cassetteacprogressbar.setVisibility(View.VISIBLE);
                cassettessquantity("add");
                totalquantity("add");
                totalprice("add",cassettessprice ,discassettessprice,"CassetteSSAC");
                wwcassettessprice("add",cassettessprice);
                btn("false");
            }
        });
        addcassettess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                cardview_cassettess1.setVisibility(View.INVISIBLE);
                cassetteacprogressbar.setVisibility(View.VISIBLE);
                cassettessquantity("add");
                totalquantity("add");
                totalprice("add",cassettessprice - discassettessprice,discassettessprice,"CassetteSSAC");
                wwcassettessprice("add",cassettessprice - discassettessprice);
                totalsaved("add",discassettessprice,"CassetteSSAC");
                btn("false");
            }
        });
        subcassettess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                cardview_cassettess1.setVisibility(View.INVISIBLE);
                cassetteacprogressbar.setVisibility(View.VISIBLE);
                cassettessquantity("sub");
                totalquantity("sub");
                totalprice("sub",cassettessprice - discassettessprice,discassettessprice,"CassetteSSAC");
                wwcassettessprice("sub",cassettessprice - discassettessprice);
                totalsaved("sub",discassettessprice,"CassetteSSAC");
                btn("false");
            }
        });

        split360btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                cardview_split3601.setVisibility(View.INVISIBLE);
                split360acprogressbar.setVisibility(View.VISIBLE);
                split360quantity("add");
                totalquantity("add");
                totalprice("add",split360price ,dissplit360price,"Split360AC");
                wwsplit360price("add",split360price);
                btn("false");
            }
        });
        addsplit360.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                cardview_split3601.setVisibility(View.INVISIBLE);
                split360acprogressbar.setVisibility(View.VISIBLE);
                split360quantity("add");
                totalquantity("add");
                totalprice("add",split360price - dissplit360price,dissplit360price,"Split360AC");
                wwsplit360price("add",split360price - dissplit360price);
                totalsaved("add",dissplit360price,"Split360AC");
                btn("false");
            }
        });
        subsplit360.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                cardview_split3601.setVisibility(View.INVISIBLE);
                split360acprogressbar.setVisibility(View.VISIBLE);
                split360quantity("sub");
                totalquantity("sub");
                totalprice("sub",split360price  - dissplit360price,dissplit360price,"Split360AC");
                wwsplit360price("sub",split360price - dissplit360price);
                totalsaved("sub",dissplit360price,"Split360AC");
                btn("false");
            }
        });

        cassette360btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                cardview_cassette3601.setVisibility(View.INVISIBLE);
                cassette360acprogressbar.setVisibility(View.VISIBLE);
                cassette360quantity("add");
                totalquantity("add");
                totalprice("add",cassette360price,discassette360price,"Cassette360AC");
                wwcassette360price("add",cassette360price);
                btn("false");
            }
        });
        addcassette360.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                cardview_cassette3601.setVisibility(View.INVISIBLE);
                cassette360acprogressbar.setVisibility(View.VISIBLE);
                cassette360quantity("add");
                totalquantity("add");
                totalprice("add",cassette360price - discassette360price,discassette360price,"Cassette360AC");
                wwcassette360price("add",cassette360price - discassette360price);
                totalsaved("add",discassette360price,"Cassette360AC");
                btn("false");
            }
        });
        subcassette360.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                cardview_cassette3601.setVisibility(View.INVISIBLE);
                cassette360acprogressbar.setVisibility(View.VISIBLE);
                cassette360quantity("sub");
                totalquantity("sub");
                totalprice("sub",cassette360price - discassette360price,discassette360price,"Cassette360AC");
                wwcassette360price("sub",cassette360price  - discassette360price);
                totalsaved("sub",discassette360price,"Cassette360AC");
                btn("false");
            }
        });


    }

    // TOTAL QUANTITY

    private void totalquantity(String action) {

        if(action=="add") {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringcartquantity = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALQUANTITY").getValue().toString();
                    int cartquantity = Integer.parseInt(stringcartquantity);
                    cartquantity = cartquantity + 1;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("TOTALQUANTITY", Integer.toString(cartquantity));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").updateChildren(cartt);
//                    footer.setVisibility(View.VISIBLE);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else if(action=="sub") {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringcartquantity = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALQUANTITY").getValue().toString();
                    int cartquantity = Integer.parseInt(stringcartquantity);
                    cartquantity = cartquantity - 1;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("TOTALQUANTITY", Integer.toString(cartquantity));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").updateChildren(cartt);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    // TOTAL PRICE

    private void totalprice(String action,int price , int discount, String Categoryname) {
        if(action=="add") {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringcartprice = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALPRICE").getValue().toString();
                    int cartprice = Integer.parseInt(stringcartprice);
                    cartprice = cartprice + price;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("TOTALPRICE", Integer.toString(cartprice));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").updateChildren(cartt);
//                    footer.setVisibility(View.VISIBLE);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else if(action=="sub") {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringcartprice = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALPRICE").getValue().toString();
                    int cartprice = Integer.parseInt(stringcartprice);

                    int splitdiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child(Categoryname).getValue().toString());
                    if(splitdiscount==1)
                    {
                        cartprice = cartprice - price - discount;
                    }
                    else
                    {
                        cartprice = cartprice - price;
                    }

                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("TOTALPRICE", Integer.toString(cartprice));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").updateChildren(cartt);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    // ACTIVE BUTTON

    private void btn(String action) {
        if(action=="false") {
            cardview_splitss1.setEnabled(false);
            addsplitss.setEnabled(false);
            subsplitss.setEnabled(false);
            cardview_windowss1.setEnabled(false);
            addwindowss.setEnabled(false);
            subwindowss.setEnabled(false);
            cardview_cassettess1.setEnabled(false);
            addcassettess.setEnabled(false);
            subcassettess.setEnabled(false);
            cardview_split3601.setEnabled(false);
            addsplit360.setEnabled(false);
            subsplit360.setEnabled(false);
            cardview_cassette3601.setEnabled(false);
            addcassette360.setEnabled(false);
            subcassette360.setEnabled(false);
        }
        else if(action=="true") {
            cardview_splitss1.setEnabled(true);
            addsplitss.setEnabled(true);
            subsplitss.setEnabled(true);
            cardview_windowss1.setEnabled(true);
            addwindowss.setEnabled(true);
            subwindowss.setEnabled(true);
            cardview_cassettess1.setEnabled(true);
            addcassettess.setEnabled(true);
            subcassettess.setEnabled(true);
            cardview_split3601.setEnabled(true);
            addsplit360.setEnabled(true);
            subsplit360.setEnabled(true);
            cardview_cassette3601.setEnabled(true);
            addcassette360.setEnabled(true);
            subcassette360.setEnabled(true);
        }
    }

    // SPLIT AC

    private void splitssquantity(String action) {

        if(action=="add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringsplitsscount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("SplitSSAC").getValue().toString();
                            int splitsscount = Integer.parseInt(stringsplitsscount);
                            splitsscount = splitsscount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("SplitSSAC", Integer.toString(splitsscount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);
                            cardview_splitss2.setVisibility(View.VISIBLE);
                            splitacprogressbar.setVisibility(View.INVISIBLE);
                            btn("true");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            };
            delay.postDelayed(run,500);
        }
        else if(action=="sub") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringsplitsscount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("SplitSSAC").getValue().toString();
                            int splitsscount = Integer.parseInt(stringsplitsscount);
                            splitsscount = splitsscount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("SplitSSAC", Integer.toString(splitsscount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);
                            if(splitsscount<=0) {
                                cardview_splitss2.setVisibility(View.INVISIBLE);
                                cardview_splitss1.setVisibility(View.VISIBLE);
                                splitacprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                            else {
                                splitacprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            };
            delay.postDelayed(run,500);
        }

    }
    private void wwsplitssprice(String action, int price) {
        if (action=="add") {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringsplitsscount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("SplitSSACTOTAL").getValue().toString();
                    int splitsscount = Integer.parseInt(stringsplitsscount);
                    splitsscount = splitsscount + price;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("SplitSSACTOTAL", Integer.toString(splitsscount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else if(action=="sub"){
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringsplitsscount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("SplitSSACTOTAL").getValue().toString();
                    int splitsscount = Integer.parseInt(stringsplitsscount);

                    int splitdiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("SplitSSAC").getValue().toString());
                    if(splitdiscount==1)
                    {
                        splitsscount = splitsscount - price - dissplitssprice;
                    }
                    else
                    {
                        splitsscount = splitsscount - price;
                    }

                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("SplitSSACTOTAL", Integer.toString(splitsscount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);                  }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }


    // WINDOW AC

    private void windowssquantity(String action) {

        if(action=="add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringwindowsscount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("WindowSSAC").getValue().toString();
                            int windowsscount = Integer.parseInt(stringwindowsscount);
                            windowsscount = windowsscount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("WindowSSAC", Integer.toString(windowsscount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);
                            cardview_windowss2.setVisibility(View.VISIBLE);
                            windowacprogressbar.setVisibility(View.INVISIBLE);
                            btn("true");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            };
            delay.postDelayed(run,500);
        }
        else if(action=="sub") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringwindowsscount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("WindowSSAC").getValue().toString();
                            int windowsscount = Integer.parseInt(stringwindowsscount);
                            windowsscount = windowsscount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("WindowSSAC", Integer.toString(windowsscount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);
                            if(windowsscount<=0) {
                                cardview_windowss2.setVisibility(View.INVISIBLE);
                                cardview_windowss1.setVisibility(View.VISIBLE);
                                windowacprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                            else {
                                windowacprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            };
            delay.postDelayed(run,500);
        }

    }
    private void wwwindowssprice(String action, int price) {
        if (action=="add") {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringwindowsscount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("WindowSSACTOTAL").getValue().toString();
                    int windowsscount = Integer.parseInt(stringwindowsscount);
                    windowsscount = windowsscount + price;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("WindowSSACTOTAL", Integer.toString(windowsscount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else if(action=="sub"){
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringwindowsscount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("WindowSSACTOTAL").getValue().toString();
                    int windowsscount = Integer.parseInt(stringwindowsscount);
                    int windowdiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("WindowSSAC").getValue().toString());
                    if(windowdiscount==1)
                    {
                        windowsscount = windowsscount - price - diswindowssprice;
                    }
                    else
                    {
                        windowsscount = windowsscount - price;
                    }
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("WindowSSACTOTAL", Integer.toString(windowsscount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);                     }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }



    // CASSETTE AC

    private void cassettessquantity(String action) {

        if(action=="add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringcassettesscount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("CassetteSSAC").getValue().toString();
                            int cassettesscount = Integer.parseInt(stringcassettesscount);
                            cassettesscount = cassettesscount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("CassetteSSAC", Integer.toString(cassettesscount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);
                            cardview_cassettess2.setVisibility(View.VISIBLE);
                            cassetteacprogressbar.setVisibility(View.INVISIBLE);
                            btn("true");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            };
            delay.postDelayed(run,500);
        }
        else if(action=="sub") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringcassettesscount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("CassetteSSAC").getValue().toString();
                            int cassettesscount = Integer.parseInt(stringcassettesscount);
                            cassettesscount = cassettesscount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("CassetteSSAC", Integer.toString(cassettesscount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);
                            if(cassettesscount<=0) {
                                cardview_cassettess2.setVisibility(View.INVISIBLE);
                                cardview_cassettess1.setVisibility(View.VISIBLE);
                                cassetteacprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                            else {
                                cassetteacprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            };
            delay.postDelayed(run,500);
        }

    }
    private void wwcassettessprice(String action, int price) {
        if (action=="add") {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringcassettesscount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("CassetteSSACTOTAL").getValue().toString();
                    int cassettesscount = Integer.parseInt(stringcassettesscount);
                    cassettesscount = cassettesscount + price;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("CassetteSSACTOTAL", Integer.toString(cassettesscount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);}

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else if(action=="sub"){
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringcassettesscount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("CassetteSSACTOTAL").getValue().toString();
                    int cassettesscount = Integer.parseInt(stringcassettesscount);
                    int cassettediscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("CassetteSSAC").getValue().toString());
                    if(cassettediscount==1)
                    {
                        cassettesscount =  cassettesscount - price - discassettessprice;
                    }
                    else
                    {
                        cassettesscount =  cassettesscount - price;
                    }
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("CassetteSSACTOTAL", Integer.toString(cassettesscount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);                     }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
    /// WATER WASH


    // SPLIT360 AC

    private void split360quantity(String action) {

        if(action=="add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringsplit360count = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("Split360AC").getValue().toString();
                            int split360count = Integer.parseInt(stringsplit360count);
                            split360count = split360count + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Split360AC", Integer.toString(split360count));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);
                            cardview_split3602.setVisibility(View.VISIBLE);
                            split360acprogressbar.setVisibility(View.INVISIBLE);
                            btn("true");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            };
            delay.postDelayed(run,500);
        }
        else if(action=="sub") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringsplit360count = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("Split360AC").getValue().toString();
                            int split360count = Integer.parseInt(stringsplit360count);
                            split360count = split360count - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Split360AC", Integer.toString(split360count));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);
                            if(split360count<=0) {
                                cardview_split3602.setVisibility(View.INVISIBLE);
                                cardview_split3601.setVisibility(View.VISIBLE);
                                split360acprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                            else {
                                split360acprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            };
            delay.postDelayed(run,500);
        }

    }
    private void wwsplit360price(String action, int price) {
        if (action=="add") {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringsplit360count = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("Split360ACTOTAL").getValue().toString();
                    int split360count = Integer.parseInt(stringsplit360count);
                    split360count = split360count + price;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("Split360ACTOTAL", Integer.toString(split360count));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else if(action=="sub"){
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringsplit360count = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("Split360ACTOTAL").getValue().toString();
                    int split360count = Integer.parseInt(stringsplit360count);
                    int split360discount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("Split360AC").getValue().toString());
                    if(split360discount==1)
                    {
                        split360count  =split360count  - price - dissplit360price;
                    }
                    else
                    {
                        split360count  =  split360count  - price;
                    }
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("Split360ACTOTAL", Integer.toString(split360count));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);                   }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }


    // CASSETTE360 AC

    private void cassette360quantity(String action) {

        if(action=="add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringcassette360count = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("Cassette360AC").getValue().toString();
                            int cassette360count = Integer.parseInt(stringcassette360count);
                            cassette360count = cassette360count + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Cassette360AC", Integer.toString(cassette360count));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);
                            cardview_cassette3602.setVisibility(View.VISIBLE);
                            cassette360acprogressbar.setVisibility(View.INVISIBLE);
                            btn("true");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            };
            delay.postDelayed(run,500);
        }
        else if(action=="sub") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringcassette360count = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("Cassette360AC").getValue().toString();
                            int cassette360count = Integer.parseInt(stringcassette360count);
                            cassette360count = cassette360count - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Cassette360AC", Integer.toString(cassette360count));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);
                            if(cassette360count<=0) {
                                cardview_cassette3602.setVisibility(View.INVISIBLE);
                                cardview_cassette3601.setVisibility(View.VISIBLE);
                                cassette360acprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                            else {
                                cassette360acprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            };
            delay.postDelayed(run,500);
        }

    }
    private void wwcassette360price(String action, int price) {
        if (action=="add") {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringcassette360count = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("Cassette360ACTOTAL").getValue().toString();
                    int cassette360count = Integer.parseInt(stringcassette360count);
                    cassette360count = cassette360count + price;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("Cassette360ACTOTAL", Integer.toString(cassette360count));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);}

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else if(action=="sub"){
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringcassette360count = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("Cassette360ACTOTAL").getValue().toString();
                    int cassette360count = Integer.parseInt(stringcassette360count);
                    int cassette360discount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("Cassette360AC").getValue().toString());
                    if(cassette360discount==1)
                    {
                        cassette360count  = cassette360count - price - discassette360price;
                    }
                    else
                    {
                        cassette360count = cassette360count - price;
                    }
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("Cassette360ACTOTAL", Integer.toString(cassette360count));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);                    }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    //totalsaved

    private void totalsaved(String action,int discount,String categoryname) {
        if(action=="add") {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringcartsaved = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALSAVED").getValue().toString();
                    int cartsaved = Integer.parseInt(stringcartsaved);
                    cartsaved = cartsaved + discount;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("TOTALSAVED", Integer.toString(cartsaved));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").updateChildren(cartt);
                    footer.setVisibility(View.VISIBLE);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else if(action=="sub") {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringcartsaved = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALSAVED").getValue().toString();
                    int cartsaved = Integer.parseInt(stringcartsaved);
                    int totaldiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child(categoryname).getValue().toString());
                    if(totaldiscount==1)
                    {
                        HashMap<String, Object> cartt = new HashMap<>();
                        cartt.put("TOTALSAVED", Integer.toString(cartsaved));
                        cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").updateChildren(cartt);
//                        footer.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        cartsaved = cartsaved - discount;
                        HashMap<String, Object> cartt = new HashMap<>();
                        cartt.put("TOTALSAVED", Integer.toString(cartsaved));
                        cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").updateChildren(cartt);
//                        footer.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }




}