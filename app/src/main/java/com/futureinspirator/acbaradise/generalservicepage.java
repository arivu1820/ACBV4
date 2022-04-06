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


public class generalservicepage extends AppCompatActivity {

    private String Category = "GeneralService";
    private CardView cardview_split1, cardview_split2, cardview_window1, cardview_window2,cardview_cassette1, cardview_cassette2;
    private Button splitbtn, windowbtn, cassettebtn;
    private Button addsplit, addwindow, addcassette;
    private Button subsplit, subwindow, subcassette;
    private TextView txtsplit, txtwindow, txtcassette;
    private TextView txttotalitems,txttotalprice;
    private RelativeLayout footer;
    private FrameLayout frontloadingpage;
    private int splitprice = 0, windowprice = 0, cassetteprice = 0;
    private int discountsplitprice = 0, discountwindowprice = 0, discountcassetteprice = 0;
    private String stringsplitprice, stringwindowprice, stringcassetteprice;
    private CardView splitacprogressbar,windowacprogressbar,cassetteacprogressbar;
    private DatabaseReference cart = FirebaseDatabase.getInstance().getReference();
    private String splitacdatabaseQvalue,windowacdatabaseQvalue,cassetteacdatabaseQvalue;
    private String splitacprice,windowacprice,cassetteacprice;
    private TextView cartt;
    private TextView txt_price_split,txt_price_window,txt_price_cassette;
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generalservicepage);



        cartt = findViewById(R.id.cartt);

        cartt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(generalservicepage.this,summary.class);
                startActivity(intent);
                finish();
            }
        });


        // Price for all AC

        DatabaseReference root,discount;
        root = FirebaseDatabase.getInstance().getReference().child("Products").child(Category).child("Price");
        discount = FirebaseDatabase.getInstance().getReference().child("Products").child(Category).child("Discount");

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                stringsplitprice = snapshot.child("SplitAC").getValue().toString();
                stringwindowprice = snapshot.child("WindowAC").getValue().toString();
                stringcassetteprice = snapshot.child("CassetteAC").getValue().toString();
                splitprice = Integer.parseInt(stringsplitprice);
                windowprice = Integer.parseInt(stringwindowprice);
                cassetteprice = Integer.parseInt(stringcassetteprice);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
        discount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String disstringsplitprice = snapshot.child("SplitAC").getValue().toString();
                String disstringwindowprice = snapshot.child("WindowAC").getValue().toString();
                String disstringcassetteprice = snapshot.child("CassetteAC").getValue().toString();
                discountsplitprice = Integer.parseInt(disstringsplitprice);
                discountwindowprice = Integer.parseInt(disstringwindowprice);
                discountcassetteprice = Integer.parseInt(disstringcassetteprice);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });


        // Auto count of all AC

        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                splitacdatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("SplitAC").getValue().toString();
                txtsplit.setText(""+splitacdatabaseQvalue);
                int intsplitacdatabaseQvalue = Integer.parseInt(splitacdatabaseQvalue);
                if(intsplitacdatabaseQvalue<=0)
                {
                    cardview_split2.setVisibility(View.INVISIBLE);
                }
                else
                {
                    cardview_split2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                windowacdatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("WindowAC").getValue().toString();
                txtwindow.setText(""+windowacdatabaseQvalue);
                int intwindowacdatabaseQvalue = Integer.parseInt(windowacdatabaseQvalue);
                if(intwindowacdatabaseQvalue<=0)
                {
                    cardview_window2.setVisibility(View.INVISIBLE);
                }
                else
                {
                    cardview_window2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cassetteacdatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("CassetteAC").getValue().toString();
                txtcassette.setText(""+cassetteacdatabaseQvalue);
                int intcassetteacdatabaseQvalue = Integer.parseInt(cassetteacdatabaseQvalue);
                if(intcassetteacdatabaseQvalue<=0)
                {
                    cardview_cassette2.setVisibility(View.INVISIBLE);
                }
                else
                {
                    cardview_cassette2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                splitacprice = snapshot.child("Products").child(Category).child("Price").child("SplitAC").getValue().toString();
                txt_price_split.setText(""+splitacprice);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                windowacprice = snapshot.child("Products").child(Category).child("Price").child("WindowAC").getValue().toString();
                txt_price_window.setText(""+windowacprice);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cassetteacprice = snapshot.child("Products").child(Category).child("Price").child("CassetteAC").getValue().toString();
                txt_price_cassette.setText(""+cassetteacprice);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        // Auto footer for cart and fornt loading page

        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String stringfooteritems = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALQUANTITY").getValue().toString();
                String stringfooterprice = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALPRICE").getValue().toString();
                int footeritems = Integer.parseInt(stringfooteritems);
                int footerprice = Integer.parseInt(stringfooterprice);
                if(splitprice!=0&&windowprice!=0&&cassetteprice!=0) {
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

        txt_price_split=findViewById(R.id.textView8);
        txt_price_window=findViewById(R.id.txt_price_windowss_waterwash);
        txt_price_cassette=findViewById(R.id.txt_price_cassettess_waterwash);

        frontloadingpage = findViewById(R.id.frontloadingpage);

        cardview_split1 = findViewById(R.id.cardview_splitss1);
        cardview_split2 = findViewById(R.id.cardview_splitss2);

        cardview_window1 = findViewById(R.id.cardview_windowss1);
        cardview_window2 = findViewById(R.id.cardview_windowss2);

        cardview_cassette1 = findViewById(R.id.cardview_cassettess1);
        cardview_cassette2 = findViewById(R.id.cardview_cassettess2);

        splitbtn = findViewById(R.id.splitssbtn);
        windowbtn = findViewById(R.id.windowssbtn);
        cassettebtn = findViewById(R.id.cassettessbtn);

        addsplit = findViewById(R.id.addsplitss);
        addwindow = findViewById(R.id.addwindowss);
        addcassette = findViewById(R.id.addcassettess);

        subsplit = findViewById(R.id.subsplitss);
        subwindow = findViewById(R.id.subwindowss);
        subcassette = findViewById(R.id.subcassettess);

        txtsplit = findViewById(R.id.txtsplitss);
        txtwindow = findViewById(R.id.txtwindowss);
        txtcassette = findViewById(R.id.txtcassettess);

        txttotalprice = findViewById(R.id.txttotal);
        txttotalitems = findViewById(R.id.txtitems);

        splitacprogressbar = findViewById(R.id.splitssacprogressbar);
        windowacprogressbar = findViewById(R.id.windowssacprogressbar);
        cassetteacprogressbar = findViewById(R.id.cassettessacprogressbar);

        footer = findViewById(R.id.footer);


        splitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                cardview_split1.setVisibility(View.INVISIBLE);
                splitacprogressbar.setVisibility(View.VISIBLE);
                splitquantity("add");
                totalquantity("add");
                splitprice("add",splitprice);
                totalprice("add",splitprice,discountsplitprice,"SplitAC");
                btn("false");
            }
        });
        addsplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_split1.setVisibility(View.INVISIBLE);
                splitacprogressbar.setVisibility(View.VISIBLE);
                splitquantity("add");
                totalquantity("add");
                splitprice("add",splitprice-discountsplitprice);
                totalprice("add",splitprice-discountsplitprice,discountsplitprice,"SplitAC");
                totalsaved("add",discountsplitprice,"SplitAC");
                btn("false");
            }
        });
        subsplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_split1.setVisibility(View.INVISIBLE);
                splitacprogressbar.setVisibility(View.VISIBLE);
                splitquantity("sub");
                totalquantity("sub");
                splitprice("sub",splitprice-discountsplitprice);
                totalprice("sub",splitprice-discountsplitprice,discountsplitprice,"SplitAC");
                totalsaved("sub",discountsplitprice,"SplitAC");
                btn("false");
            }
        });

        windowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cardview_window1.setVisibility(View.INVISIBLE);
                windowacprogressbar.setVisibility(View.VISIBLE);
                totalquantity("add");
                windowquantity("add");
                windowprice("add",windowprice);
                totalprice("add",windowprice,discountwindowprice,"WindowAC");
                btn("false");
            }
        });
        addwindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cardview_window1.setVisibility(View.INVISIBLE);
                windowacprogressbar.setVisibility(View.VISIBLE);
                totalquantity("add");
                windowquantity("add");
                windowprice("add",windowprice-discountwindowprice);
                totalprice("add",windowprice-discountwindowprice,discountwindowprice,"WindowAC");
                totalsaved("add",discountwindowprice,"WindowAC");
                btn("false");

            }
        });
        subwindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_window1.setVisibility(View.INVISIBLE);
                windowacprogressbar.setVisibility(View.VISIBLE);
                totalquantity("sub");
                windowquantity("sub");
                windowprice("sub",windowprice-discountwindowprice);
                totalprice("sub",windowprice-discountwindowprice,discountwindowprice,"WindowAC");
                totalsaved("sub",discountwindowprice,"WindowAC");
                btn("false");
            }
        });

        cassettebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cardview_cassette1.setVisibility(View.INVISIBLE);
                cassetteacprogressbar.setVisibility(View.VISIBLE);
                totalquantity("add");
                cassettequantity("add");
                cassetteprice("add",cassetteprice);
                totalprice("add",cassetteprice,discountcassetteprice,"CassetteAC");
                btn("false");

            }
        });
        addcassette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cardview_cassette1.setVisibility(View.INVISIBLE);
                cassetteacprogressbar.setVisibility(View.VISIBLE);
                totalquantity("add");
                cassettequantity("add");
                cassetteprice("add",cassetteprice-discountcassetteprice);
                totalprice("add",cassetteprice-discountcassetteprice,discountcassetteprice,"CassetteAC");
                totalsaved("add",discountcassetteprice,"CassetteAC");
                btn("false");

            }
        });
        subcassette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cardview_cassette1.setVisibility(View.INVISIBLE);
                cassetteacprogressbar.setVisibility(View.VISIBLE);
                totalquantity("sub");
                cassettequantity("sub");
                cassetteprice("sub",cassetteprice-discountcassetteprice);
                totalprice("sub",cassetteprice-discountcassetteprice,discountcassetteprice,"CassetteAC");
                totalsaved("sub",discountcassetteprice,"CassetteAC");
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

    private void totalprice(String action,int price,int discount,String categoryname) {
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
                    String stringcartprice = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALPRICE").getValue().toString();
                    int cartprice = Integer.parseInt(stringcartprice);
                    int splitdiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child(categoryname).getValue().toString());
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

    // ACTIVATE BUTTON

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
                        footer.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        cartsaved = cartsaved - discount;
                        HashMap<String, Object> cartt = new HashMap<>();
                        cartt.put("TOTALSAVED", Integer.toString(cartsaved));
                        cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").updateChildren(cartt);
                        footer.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void btn(String action) {
        if(action=="false") {
            cardview_split1.setEnabled(false);
            addsplit.setEnabled(false);
            subsplit.setEnabled(false);
            cardview_window1.setEnabled(false);
            addwindow.setEnabled(false);
            subwindow.setEnabled(false);
            cardview_cassette1.setEnabled(false);
            addcassette.setEnabled(false);
            subcassette.setEnabled(false);
        }
        else if(action=="true") {
            cardview_split1.setEnabled(true);
            addsplit.setEnabled(true);
            subsplit.setEnabled(true);
            cardview_window1.setEnabled(true);
            addwindow.setEnabled(true);
            subwindow.setEnabled(true);
            cardview_cassette1.setEnabled(true);
            addcassette.setEnabled(true);
            subcassette.setEnabled(true);
        }
    }

    // SPLIT AC

    private void splitquantity(String action) {

        if(action=="add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringsplitcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("SplitAC").getValue().toString();
                            int splitcount = Integer.parseInt(stringsplitcount);
                            splitcount = splitcount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("SplitAC", Integer.toString(splitcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);
                            cardview_split2.setVisibility(View.VISIBLE);
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
                            String stringsplitcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("SplitAC").getValue().toString();
                            int splitcount = Integer.parseInt(stringsplitcount);
                            splitcount = splitcount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("SplitAC", Integer.toString(splitcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);
                            if(splitcount<=0) {
                                cardview_split2.setVisibility(View.INVISIBLE);
                                cardview_split1.setVisibility(View.VISIBLE);
                                splitacprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                            else
                            {
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

    private void splitprice(String action, int price) {
        if (action=="add") {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringsplitcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("SplitACTOTAL").getValue().toString();
                    int splitcount = Integer.parseInt(stringsplitcount);
                    splitcount = splitcount + price;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("SplitACTOTAL", Integer.toString(splitcount));
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
                    String stringsplitcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("SplitACTOTAL").getValue().toString();
                    int splitcount = Integer.parseInt(stringsplitcount);
                    int splitdiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("SplitAC").getValue().toString());
                    if(splitdiscount==1)
                    {
                        splitcount = splitcount - price - discountsplitprice;
                    }
                    else
                    {
                        splitcount = splitcount - price;
                    }
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("SplitACTOTAL", Integer.toString(splitcount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    // WINDOW AC

    private void windowquantity(String action) {

        if(action=="add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringwindowcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("WindowAC").getValue().toString();
                            int windowcount = Integer.parseInt(stringwindowcount);
                            windowcount = windowcount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("WindowAC", Integer.toString(windowcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);
                            cardview_split2.setVisibility(View.VISIBLE);
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
                            String stringwindowcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("WindowAC").getValue().toString();
                            int windowcount = Integer.parseInt(stringwindowcount);
                            windowcount = windowcount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("WindowAC", Integer.toString(windowcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);
                            if(windowcount<=0) {
                                cardview_window2.setVisibility(View.INVISIBLE);
                                cardview_window1.setVisibility(View.VISIBLE);
                                windowacprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                            else
                            {
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

    private void windowprice(String action, int price) {
        if (action=="add") {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringsplitcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("WindowACTOTAL").getValue().toString();
                    int splitcount = Integer.parseInt(stringsplitcount);
                    splitcount = splitcount + price;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("WindowACTOTAL", Integer.toString(splitcount));
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
                    String stringsplitcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("WindowACTOTAL").getValue().toString();
                    int splitcount = Integer.parseInt(stringsplitcount);
                    int splitdiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("WindowAC").getValue().toString());
                    if(splitdiscount==1) {
                        splitcount = splitcount - price - discountwindowprice;
                    }
                    else {
                        splitcount = splitcount - price;

                    }
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("WindowACTOTAL", Integer.toString(splitcount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    // Cassette AC

    private void cassettequantity(String action) {

        if(action=="add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringcassettecount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("CassetteAC").getValue().toString();
                            int cassettecount = Integer.parseInt(stringcassettecount);
                            cassettecount = cassettecount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("CassetteAC", Integer.toString(cassettecount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);
                            cardview_cassette2.setVisibility(View.VISIBLE);
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
                            String stringcassettecount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("CassetteAC").getValue().toString();
                            int cassettecount = Integer.parseInt(stringcassettecount);
                            cassettecount = cassettecount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("CassetteAC", Integer.toString(cassettecount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);
                            if(cassettecount<=0) {
                                cardview_cassette2.setVisibility(View.INVISIBLE);
                                cardview_cassette1.setVisibility(View.VISIBLE);
                                cassetteacprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                            else
                            {
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

    private void cassetteprice(String action, int price) {
        if (action=="add") {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringsplitcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("CassetteACTOTAL").getValue().toString();
                    int splitcount = Integer.parseInt(stringsplitcount);
                    splitcount = splitcount + price;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("CassetteACTOTAL", Integer.toString(splitcount));
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
                    String stringsplitcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("CassetteACTOTAL").getValue().toString();
                    int splitcount = Integer.parseInt(stringsplitcount);
                    int splitdiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).child("CassetteAC").getValue().toString());
                    if(splitdiscount==1) {
                        splitcount = splitcount - price - discountcassetteprice;
                    }
                    else {
                        splitcount = splitcount - price;

                    }
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("CassetteACTOTAL", Integer.toString(splitcount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Category).updateChildren(cartt);                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }






}