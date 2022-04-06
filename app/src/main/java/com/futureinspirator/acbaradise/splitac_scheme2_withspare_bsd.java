package com.futureinspirator.acbaradise;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class splitac_scheme2_withspare_bsd extends BottomSheetDialogFragment {

    private String Maincategory = "Scheme2",Category = "Withspare";
    private CardView scheme2_withspare_totalspare_cardview1,scheme2_withspare_totalspare_cardview2,scheme2_withspare_limitedspare_cardview1,scheme2_withspare_limitedspare_cardview2;
    private CardView scheme2_withspare_totalspare_progressbar,scheme2_withspare_limitedspare_progressbar;
    private Button scheme2_withspare_totalspare_addbtn,scheme2_withspare_limitedspare_addbtn;
    private Button scheme2_withspare_totalspare_subbtn,scheme2_withspare_limitedspare_subbtn;
    private TextView scheme2_withspare_totalspare_counttxt,scheme2_withspare_limitedspare_counttxt;
    private Button scheme2_withspare_totalspare_plusbtn,scheme2_withspare_limitedspare_plusbtn;
    private RelativeLayout footer;
    private TextView txttotalitems,txttotalprice;
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());
    private int totalspare = 0, limitedspare = 0;
    private int distotalspare = 0, dislimitedspare = 0;


    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference cart = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference discount = FirebaseDatabase.getInstance().getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_splitac_scheme2_withspare_bsd, container, false);

        footer = v.findViewById(R.id.footer);

        scheme2_withspare_totalspare_cardview1 = v.findViewById(R.id.scheme2_withspare_totalspare_cardview1);
        scheme2_withspare_totalspare_cardview2 = v.findViewById(R.id.scheme2_withspare_totalspare_cardview2);
        scheme2_withspare_limitedspare_cardview1 = v.findViewById(R.id.scheme2_withspare_limitedspare_cardview1);
        scheme2_withspare_limitedspare_cardview2 = v.findViewById(R.id.scheme2_withspare_limitedspare_cardview2);

        scheme2_withspare_totalspare_addbtn  = v.findViewById(R.id.scheme2_withspare_totalspare_addbtn);
        scheme2_withspare_limitedspare_addbtn  = v.findViewById(R.id.scheme2_withspare_limitedspare_addbtn);

        scheme2_withspare_totalspare_subbtn = v.findViewById(R.id.scheme2_withspare_totalspare_subbtn);
        scheme2_withspare_limitedspare_subbtn = v.findViewById(R.id.scheme2_withspare_limitedspare_subbtn);

        scheme2_withspare_totalspare_counttxt = v.findViewById(R.id.scheme2_withspare_totalspare_counttxt);
        scheme2_withspare_limitedspare_counttxt = v.findViewById(R.id.scheme2_withspare_limitedspare_counttxt);

        scheme2_withspare_totalspare_plusbtn = v.findViewById(R.id.scheme2_withspare_totalspare_plusbtn);
        scheme2_withspare_limitedspare_plusbtn = v.findViewById(R.id.scheme2_withspare_limitedspare_plusbtn);

        scheme2_withspare_totalspare_progressbar = v.findViewById(R.id.scheme2_withspare_totalspare_progressbar);
        scheme2_withspare_limitedspare_progressbar = v.findViewById(R.id.scheme2_withspare_limitedspare_progressbar);

        txttotalprice = v.findViewById(R.id.txttotal);
        txttotalitems = v.findViewById(R.id.txtitems);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String stringtotalspare = snapshot.child("Products").child("AMC").child("SplitAC").child(Maincategory).child(Category).child("Price").child("Totalspare").getValue().toString();
                totalspare  = Integer.parseInt(stringtotalspare);
                String stringlimitedspare = snapshot.child("Products").child("AMC").child("SplitAC").child(Maincategory).child(Category).child("Price").child("Limitedspare").getValue().toString();
                limitedspare = Integer.parseInt(stringlimitedspare);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        discount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String stringtotalspare = snapshot.child("Products").child("AMC").child("SplitAC").child(Maincategory).child(Category).child("Discount").child("Totalspare").getValue().toString();
                distotalspare  = Integer.parseInt(stringtotalspare);
                String stringlimitedspare = snapshot.child("Products").child("AMC").child("SplitAC").child(Maincategory).child(Category).child("Discount").child("Limitedspare").getValue().toString();
                dislimitedspare = Integer.parseInt(stringlimitedspare);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


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

        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String totalsparedatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child(Maincategory).child(Category).child("Totalspare").getValue().toString();
                scheme2_withspare_totalspare_counttxt.setText("" + totalsparedatabaseQvalue);
                int inttotalsparedatabaseQvalue = Integer.parseInt(totalsparedatabaseQvalue);
                if (inttotalsparedatabaseQvalue <= 0) {
                    scheme2_withspare_totalspare_cardview2.setVisibility(View.INVISIBLE);
                } else {
                    scheme2_withspare_totalspare_cardview2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String limitedsparedatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child(Maincategory).child(Category).child("Limitedspare").getValue().toString();
                scheme2_withspare_limitedspare_counttxt.setText("" + limitedsparedatabaseQvalue);
                int intlimitedsparedatabaseQvalue = Integer.parseInt(limitedsparedatabaseQvalue);
                if (intlimitedsparedatabaseQvalue <= 0) {
                    scheme2_withspare_limitedspare_cardview2.setVisibility(View.INVISIBLE);
                } else {
                    scheme2_withspare_limitedspare_cardview2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        scheme2_withspare_totalspare_addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                scheme2_withspare_totalspare_cardview1.setVisibility(View.INVISIBLE);
                scheme2_withspare_totalspare_progressbar.setVisibility(View.VISIBLE);
                totalsparequantity("add");
                totalquantity("add");
                totalprice("add",totalspare,distotalspare,"Totalspare");
                totalspareprice("add",totalspare);

                btn("false");
            }
        });
        scheme2_withspare_totalspare_plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheme2_withspare_totalspare_cardview1.setVisibility(View.INVISIBLE);
                scheme2_withspare_totalspare_progressbar.setVisibility(View.VISIBLE);
                totalsparequantity("add");
                totalquantity("add");
                totalprice("add",totalspare-distotalspare,distotalspare,"Totalspare");
                totalspareprice("add",totalspare-distotalspare);
                totalsaved("add",distotalspare,"Totalspare");
                btn("false");
            }
        });
        scheme2_withspare_totalspare_subbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheme2_withspare_totalspare_cardview1.setVisibility(View.INVISIBLE);
                scheme2_withspare_totalspare_progressbar.setVisibility(View.VISIBLE);
                totalsparequantity("sub");
                totalquantity("sub");
                totalprice("sub",totalspare-distotalspare,distotalspare,"Totalspare");
                totalspareprice("sub",totalspare-distotalspare);
                totalsaved("sub",distotalspare,"Totalspare");
                btn("false");
            }
        });

        scheme2_withspare_limitedspare_addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                scheme2_withspare_limitedspare_cardview1.setVisibility(View.INVISIBLE);
                scheme2_withspare_limitedspare_progressbar.setVisibility(View.VISIBLE);
                limitedsparequantity("add");
                totalquantity("add");
                totalprice("add",limitedspare,dislimitedspare,"Limitedspare");
                limitedspareprice("add",limitedspare);

                btn("false");
            }
        });
        scheme2_withspare_limitedspare_plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheme2_withspare_limitedspare_cardview1.setVisibility(View.INVISIBLE);
                scheme2_withspare_limitedspare_progressbar.setVisibility(View.VISIBLE);
                limitedsparequantity("add");
                totalquantity("add");
                totalprice("add",limitedspare-dislimitedspare,dislimitedspare,"Limitedspare");
                limitedspareprice("add",limitedspare-dislimitedspare);
                totalsaved("add",dislimitedspare,"Limitedspare");
                btn("false");
            }
        });
        scheme2_withspare_limitedspare_subbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheme2_withspare_limitedspare_cardview1.setVisibility(View.INVISIBLE);
                scheme2_withspare_limitedspare_progressbar.setVisibility(View.VISIBLE);
                limitedsparequantity("sub");
                totalquantity("sub");
                totalprice("sub",limitedspare-dislimitedspare,dislimitedspare,"Limitedspare");
                limitedspareprice("sub",limitedspare-dislimitedspare);
                totalsaved("sub",dislimitedspare,"Limitedspare");
                btn("false");
            }
        });


        return  v;
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

    private void totalprice(String action,int price, int discount, String Categoryname) {
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
                    int totaldiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child(Maincategory).child(Category).child(Categoryname).getValue().toString());
                    if(totaldiscount==1)
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

    private void btn(String action) {
        if(action=="false") {
            scheme2_withspare_totalspare_cardview1.setEnabled(false);
            scheme2_withspare_totalspare_plusbtn.setEnabled(false);
            scheme2_withspare_totalspare_subbtn.setEnabled(false);
            scheme2_withspare_limitedspare_cardview1.setEnabled(false);
            scheme2_withspare_limitedspare_plusbtn.setEnabled(false);
            scheme2_withspare_limitedspare_subbtn.setEnabled(false);
        }
        else if(action=="true") {
            scheme2_withspare_totalspare_cardview1.setEnabled(true);
            scheme2_withspare_totalspare_plusbtn.setEnabled(true);
            scheme2_withspare_totalspare_subbtn.setEnabled(true);
            scheme2_withspare_limitedspare_cardview1.setEnabled(true);
            scheme2_withspare_limitedspare_plusbtn.setEnabled(true);
            scheme2_withspare_limitedspare_subbtn.setEnabled(true);
        }
    }

    // TOTALSPARE

    private void totalsparequantity(String action) {

        if(action=="add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringtotalsparecount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child(Maincategory).child(Category).child("Totalspare").getValue().toString();
                            int totalsparecount = Integer.parseInt(stringtotalsparecount);
                            totalsparecount =  totalsparecount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Totalspare", Integer.toString(totalsparecount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child(Maincategory).child(Category).updateChildren(cartt);
                            scheme2_withspare_totalspare_cardview2.setVisibility(View.VISIBLE);
                            scheme2_withspare_totalspare_progressbar.setVisibility(View.INVISIBLE);
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
                            String stringtotalsparecount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child(Maincategory).child(Category).child("Totalspare").getValue().toString();
                            int totalsparecount = Integer.parseInt(stringtotalsparecount);
                            totalsparecount =  totalsparecount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Totalspare", Integer.toString(totalsparecount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child(Maincategory).child(Category).updateChildren(cartt);
                            if(totalsparecount<=0) {
                                scheme2_withspare_totalspare_cardview2.setVisibility(View.INVISIBLE);
                                scheme2_withspare_totalspare_cardview1.setVisibility(View.VISIBLE);
                                scheme2_withspare_totalspare_progressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                            else
                            {
                                scheme2_withspare_totalspare_progressbar.setVisibility(View.INVISIBLE);
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
    private void totalspareprice(String action,int price) {

        if (action == "add") {
            cart.addListenerForSingleValueEvent((new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringtotalsparecount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child(Maincategory).child(Category).child("TotalspareTOTAL").getValue().toString();
                    int totalsparecount = Integer.parseInt(stringtotalsparecount);
                    totalsparecount = totalsparecount + price;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("TotalspareTOTAL", Integer.toString(totalsparecount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child(Maincategory).child(Category).updateChildren(cartt);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            }));
        }
        else if (action == "sub") {
            cart.addListenerForSingleValueEvent((new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringtotalsparecount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child(Maincategory).child(Category).child("TotalspareTOTAL").getValue().toString();
                    int totalsparecount = Integer.parseInt(stringtotalsparecount);
                    int totalsparediscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child(Maincategory).child(Category).child("Totalspare").getValue().toString());
                    if(totalsparediscount==1)
                    {
                        totalsparecount= totalsparecount - price - distotalspare;
                    }
                    else
                    {
                        totalsparecount = totalsparecount - price;
                    }

                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("TotalspareTOTAL", Integer.toString(totalsparecount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child(Maincategory).child(Category).updateChildren(cartt);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            }));
        }
    }


    // LIMITEDSPARE

    private void limitedsparequantity(String action) {

        if(action=="add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringlimitedsparecount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child(Maincategory).child(Category).child("Limitedspare").getValue().toString();
                            int limitedsparecount = Integer.parseInt(stringlimitedsparecount);
                            limitedsparecount =  limitedsparecount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Limitedspare", Integer.toString(limitedsparecount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child(Maincategory).child(Category).updateChildren(cartt);
                            scheme2_withspare_limitedspare_cardview2.setVisibility(View.VISIBLE);
                            scheme2_withspare_limitedspare_progressbar.setVisibility(View.INVISIBLE);
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
                            String stringlimitedsparecount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child(Maincategory).child(Category).child("Limitedspare").getValue().toString();
                            int limitedsparecount = Integer.parseInt(stringlimitedsparecount);
                            limitedsparecount =  limitedsparecount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Limitedspare", Integer.toString(limitedsparecount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child(Maincategory).child(Category).updateChildren(cartt);
                            if(limitedsparecount<=0) {
                                scheme2_withspare_limitedspare_cardview2.setVisibility(View.INVISIBLE);
                                scheme2_withspare_limitedspare_cardview1.setVisibility(View.VISIBLE);
                                scheme2_withspare_limitedspare_progressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                            else
                            {
                                scheme2_withspare_limitedspare_progressbar.setVisibility(View.INVISIBLE);
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
    private void limitedspareprice(String action,int price) {

        if (action == "add") {
            cart.addListenerForSingleValueEvent((new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringtotalsparecount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child(Maincategory).child(Category).child("LimitedspareTOTAL").getValue().toString();
                    int totalsparecount = Integer.parseInt(stringtotalsparecount);
                    totalsparecount = totalsparecount + price;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("LimitedspareTOTAL", Integer.toString(totalsparecount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child(Maincategory).child(Category).updateChildren(cartt);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            }));
        }
        else if (action == "sub") {
            cart.addListenerForSingleValueEvent((new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringtotalsparecount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child(Maincategory).child(Category).child("LimitedspareTOTAL").getValue().toString();
                    int totalsparecount = Integer.parseInt(stringtotalsparecount);
                    int totalsparediscount =Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child(Maincategory).child(Category).child("Limitedspare").getValue().toString());
                    if(totalsparediscount==1)
                    {
                        totalsparecount= totalsparecount - price - dislimitedspare;
                    }
                    else
                    {
                        totalsparecount = totalsparecount - price;
                    }
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("LimitedspareTOTAL", Integer.toString(totalsparecount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child(Maincategory).child(Category).updateChildren(cartt);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            }));
        }
    }

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
                    // footer.setVisibility(View.VISIBLE);
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
                    int totaldiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child(Maincategory).child(Category).child(categoryname).getValue().toString());
                    if(totaldiscount==1)
                    {
                        HashMap<String, Object> cartt = new HashMap<>();
                        cartt.put("TOTALSAVED", Integer.toString(cartsaved));
                        cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").updateChildren(cartt);
                        // footer.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        cartsaved = cartsaved - discount;
                        HashMap<String, Object> cartt = new HashMap<>();
                        cartt.put("TOTALSAVED", Integer.toString(cartsaved));
                        cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").updateChildren(cartt);
                        // footer.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }



}