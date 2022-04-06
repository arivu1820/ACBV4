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


public class windowac_scheme2_withoutspare_bsd extends BottomSheetDialogFragment {

    private String Maincategory = "Scheme2",Category = "Withoutspare";
    private CardView scheme2_withoutspare_cardview1,scheme2_withoutspare_cardview2;
    private CardView scheme2_withoutspare_progressbar;
    private Button scheme2_withoutspare_addbtn;
    private Button scheme2_withoutspare_subbtn;
    private TextView scheme2_withoutspare_counttxt;
    private Button scheme2_withoutspare_plusbtn;
    private RelativeLayout footer;
    private TextView txttotalitems,txttotalprice;
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());
    private int withoutspare = 0;
    private int diswithoutspare = 0;

    private DatabaseReference discount = FirebaseDatabase.getInstance().getReference();

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference cart = FirebaseDatabase.getInstance().getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_windowac_scheme2_withoutspare_bsd, container, false);

        footer = v.findViewById(R.id.footer);

        scheme2_withoutspare_cardview1 = v.findViewById(R.id.scheme2_withoutspare_cardview1);
        scheme2_withoutspare_cardview2 = v.findViewById(R.id.scheme2_withoutspare_cardview2);

        scheme2_withoutspare_addbtn  = v.findViewById(R.id.scheme2_withoutspare_addbtn);

        scheme2_withoutspare_subbtn = v.findViewById(R.id.scheme2_withoutspare_subbtn);

        scheme2_withoutspare_counttxt = v.findViewById(R.id.scheme2_withoutspare_counttxt);

        scheme2_withoutspare_plusbtn = v.findViewById(R.id.scheme2_withoutspare_plusbtn);

        scheme2_withoutspare_progressbar = v.findViewById(R.id.scheme2_withoutspare_progressbar);

        txttotalprice = v.findViewById(R.id.txttotal);
        txttotalitems = v.findViewById(R.id.txtitems);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String stringtotalspare = snapshot.child("Products").child("AMC").child("WindowAC").child(Maincategory).child(Category).child("Price").child("Nospare").getValue().toString();
                withoutspare  = Integer.parseInt(stringtotalspare);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        discount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String disstringtotalspare = snapshot.child("Products").child("AMC").child("WindowAC").child(Maincategory).child(Category).child("Discount").child("Nospare").getValue().toString();
                diswithoutspare  = Integer.parseInt(disstringtotalspare);
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
                String nosparedatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child(Maincategory).child(Category).child("Nospare").getValue().toString();
                scheme2_withoutspare_counttxt.setText("" + nosparedatabaseQvalue);
                int intnosparedatabaseQvalue = Integer.parseInt(nosparedatabaseQvalue);
                if (intnosparedatabaseQvalue <= 0) {
                    scheme2_withoutspare_cardview2.setVisibility(View.INVISIBLE);
                } else {
                    scheme2_withoutspare_cardview2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        scheme2_withoutspare_addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                scheme2_withoutspare_cardview1.setVisibility(View.INVISIBLE);
                scheme2_withoutspare_progressbar.setVisibility(View.VISIBLE);
                withoutsparequantity("add");
                totalquantity("add");
                totalprice("add",withoutspare,diswithoutspare,"Nospare");
                withoutspareprice("add",withoutspare);

                btn("false");
            }
        });
        scheme2_withoutspare_plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheme2_withoutspare_cardview1.setVisibility(View.INVISIBLE);
                scheme2_withoutspare_progressbar.setVisibility(View.VISIBLE);
                withoutsparequantity("add");
                totalquantity("add");
                totalprice("add",withoutspare-diswithoutspare,diswithoutspare,"Nospare");
                withoutspareprice("add",withoutspare-diswithoutspare);
                totalsaved("add",diswithoutspare,"Nospare");
                btn("false");
            }
        });
        scheme2_withoutspare_subbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheme2_withoutspare_cardview1.setVisibility(View.INVISIBLE);
                scheme2_withoutspare_progressbar.setVisibility(View.VISIBLE);
                withoutsparequantity("sub");
                totalquantity("sub");
                totalprice("sub",withoutspare-diswithoutspare,diswithoutspare,"Nospare");
                withoutspareprice("sub",withoutspare-diswithoutspare);
                totalsaved("sub",diswithoutspare,"Nospare");
                btn("false");
            }
        });

        return v;
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

    private void totalprice(String action,int price,int discount,String Categoryname) {
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
                    int totaldiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child(Maincategory).child(Category).child(Categoryname).getValue().toString());
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
            scheme2_withoutspare_cardview1.setEnabled(false);
            scheme2_withoutspare_plusbtn.setEnabled(false);
            scheme2_withoutspare_subbtn.setEnabled(false);
        }
        else if(action=="true") {
            scheme2_withoutspare_cardview1.setEnabled(true);
            scheme2_withoutspare_plusbtn.setEnabled(true);
            scheme2_withoutspare_subbtn.setEnabled(true);
        }
    }

    // TOTALSPARE

    private void withoutsparequantity(String action) {

        if(action=="add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringwithoutsparecount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child(Maincategory).child(Category).child("Nospare").getValue().toString();
                            int withoutsparecount = Integer.parseInt(stringwithoutsparecount);
                            withoutsparecount =  withoutsparecount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Nospare", Integer.toString(withoutsparecount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child(Maincategory).child(Category).updateChildren(cartt);
                            scheme2_withoutspare_cardview2.setVisibility(View.VISIBLE);
                            scheme2_withoutspare_progressbar.setVisibility(View.INVISIBLE);
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
                            String stringwithoutsparecount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child(Maincategory).child(Category).child("Nospare").getValue().toString();
                            int withoutsparecount = Integer.parseInt(stringwithoutsparecount);
                            withoutsparecount =  withoutsparecount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Nospare", Integer.toString(withoutsparecount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child(Maincategory).child(Category).updateChildren(cartt);
                            if(withoutsparecount<=0) {
                                scheme2_withoutspare_cardview2.setVisibility(View.INVISIBLE);
                                scheme2_withoutspare_cardview1.setVisibility(View.VISIBLE);
                                scheme2_withoutspare_progressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                            else
                            {
                                scheme2_withoutspare_progressbar.setVisibility(View.INVISIBLE);
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
    private void withoutspareprice(String action,int price) {

        if (action == "add") {
            cart.addListenerForSingleValueEvent((new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringtotalsparecount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child(Maincategory).child(Category).child("NospareTOTAL").getValue().toString();
                    int totalsparecount = Integer.parseInt(stringtotalsparecount);
                    totalsparecount = totalsparecount + price;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("NospareTOTAL", Integer.toString(totalsparecount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child(Maincategory).child(Category).updateChildren(cartt);

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
                    String stringtotalsparecount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child(Maincategory).child(Category).child("NospareTOTAL").getValue().toString();
                    int totalsparecount = Integer.parseInt(stringtotalsparecount);
                    int totalsparediscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child(Maincategory).child(Category).child("Nospare").getValue().toString());
                    if(totalsparediscount==1)
                    {
                        totalsparecount= totalsparecount - price - diswithoutspare;
                    }
                    else
                    {
                        totalsparecount = totalsparecount - price;
                    }

                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("NospareTOTAL", Integer.toString(totalsparecount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child(Maincategory).child(Category).updateChildren(cartt);

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
                    int totaldiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child(Maincategory).child(Category).child(categoryname).getValue().toString());
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