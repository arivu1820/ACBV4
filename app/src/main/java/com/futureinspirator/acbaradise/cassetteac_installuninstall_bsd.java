package com.futureinspirator.acbaradise;

import android.graphics.Paint;
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

public class cassetteac_installuninstall_bsd extends BottomSheetDialogFragment {

    private String Category = "CassetteAC";

    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());
    private Button cassette_install_addbutton;
    private Button cassette_install_minusbtn;
    private Button cassette_install_plusbtn;
    private TextView cassette_install_txt;
    private CardView cassette_install_pmt_cardview2;
    private CardView cassette_install_addbtn_cardview1;
    private CardView cassette_install_progressbar;

    private Button cassette_uninstall_addbutton;
    private Button cassette_uninstall_minusbtn;
    private Button cassette_uninstall_plusbtn;
    private TextView cassette_uninstall_txt;
    private CardView cassette_uninstall_addbtn_cardview1;
    private CardView cassette_uninstall_pmt_cardview2;
    private CardView cassette_uninstall_progressbar;

    private Button cassette_combo_addbutton;
    private Button cassette_combo_minusbtn;
    private Button cassette_combo_plusbtn;
    private TextView cassette_combo_txt;
    private CardView cassette_combo_addbtn_cardview1;
    private CardView cassette_combo_pmt_cardview2;
    private CardView cassette_combo_progressbar;

    private TextView txttotalitems;
    private RelativeLayout footer;
    private TextView  txttotalprice;





    private int installprice = 0;
    private int uninstallprice = 0;
    private int comboprice=0;

    private int disinstallprice = 0;
    private int disuninstallprice = 0;
    private int discomboprice=0;



    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();

    private DatabaseReference cart = FirebaseDatabase.getInstance().getReference();

    private DatabaseReference discount = FirebaseDatabase.getInstance().getReference().child("Products").child("Installuninstall").child(Category).child("Discount");
    private TextView txt_originalprice_install_cassetteac,txt_originalprice_uninstall,txt_originalprice_combo;
private TextView   txt_discount_price_install_cassetteac,txt_discount_price_uninstall,txt_discount_price_combo;
private int disstring_install_price,disstring_uninstall_price,disstring_combo_price;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_cassetteac_installuninstall_bsd, container, false);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String string_install_price = snapshot.child("Products").child("Installuninstall").child(Category).child("Price").child("Install").getValue().toString();
                installprice = Integer.parseInt(string_install_price);
                txt_originalprice_install_cassetteac.setText(""+string_install_price);

                disstring_install_price=installprice + 100;
                txt_discount_price_install_cassetteac.setText(""+disstring_install_price);
                txt_discount_price_install_cassetteac.setPaintFlags(txt_discount_price_install_cassetteac.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String installdatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).child("Install").getValue().toString();
                cassette_install_txt.setText("" + installdatabaseQvalue);
                int intinstalldatabaseQvalue = Integer.parseInt(installdatabaseQvalue);

                if (intinstalldatabaseQvalue <= 0) {
                    cassette_install_pmt_cardview2.setVisibility(View.INVISIBLE);
                } else {
                    cassette_install_pmt_cardview2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String string_uninstall_price = snapshot.child("Products").child("Installuninstall").child(Category).child("Price").child("Uninstall").getValue().toString();
                uninstallprice = Integer.parseInt(string_uninstall_price);
                txt_originalprice_uninstall.setText(""+string_uninstall_price);

                disstring_uninstall_price=uninstallprice + 100;
                txt_discount_price_uninstall.setText(""+disstring_uninstall_price);
                txt_discount_price_uninstall.setPaintFlags(txt_discount_price_uninstall.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String uninstalldatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).child("Uninstall").getValue().toString();
                cassette_uninstall_txt.setText("" + uninstalldatabaseQvalue);
                int intuninstalldatabaseQvalue = Integer.parseInt(uninstalldatabaseQvalue);
                if (intuninstalldatabaseQvalue <= 0) {
                    cassette_uninstall_pmt_cardview2.setVisibility(View.INVISIBLE);
                } else {
                    cassette_uninstall_pmt_cardview2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String string_combo_price = snapshot.child("Products").child("Installuninstall").child(Category).child("Price").child("Combo").getValue().toString();
                comboprice = Integer.parseInt(string_combo_price);
                txt_originalprice_combo.setText(""+string_combo_price);

                disstring_combo_price=comboprice + 100;
                txt_discount_price_combo.setText(""+disstring_combo_price);
                txt_discount_price_combo.setPaintFlags(txt_discount_price_combo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String combodatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).child("Combo").getValue().toString();
                cassette_combo_txt.setText("" + combodatabaseQvalue);
                int intcombodatabaseQvalue = Integer.parseInt(combodatabaseQvalue);
                if (intcombodatabaseQvalue <= 0) {
                    cassette_combo_pmt_cardview2.setVisibility(View.INVISIBLE);
                } else {
                    cassette_combo_pmt_cardview2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        discount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String disstring_install_price = snapshot.child("Install").getValue().toString();
                String disstring_uninstall_price = snapshot.child("Uninstall").getValue().toString();
                String disstring_combo_price = snapshot.child("Combo").getValue().toString();
                disinstallprice = Integer.parseInt(disstring_install_price);
                disuninstallprice = Integer.parseInt(disstring_uninstall_price);
                discomboprice = Integer.parseInt(disstring_combo_price);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        txt_discount_price_install_cassetteac=v.findViewById(R.id.txt_discount_price_install_cassetteac);
        txt_discount_price_uninstall=v.findViewById(R.id.txt_discount_price_uninstall);
        txt_discount_price_combo=v.findViewById(R.id.txt_discount_price_combo);

        txt_originalprice_install_cassetteac=v.findViewById(R.id.txt_originalprice_install_cassetteac);
        txt_originalprice_uninstall=v.findViewById(R.id.txt_originalprice_uninstall);
        txt_originalprice_combo=v.findViewById(R.id.txt_originalprice_capacitor);

        cassette_install_addbutton = v.findViewById(R.id.cassette_install_addbutton);
        cassette_install_minusbtn = v.findViewById(R.id.cassette_install_minusbtn);
        cassette_install_plusbtn = v.findViewById(R.id.cassette_install_plusbtn);
        cassette_install_txt = v.findViewById(R.id.cassette_install_txt);
        cassette_install_addbtn_cardview1 = v.findViewById(R.id.cassette_install_addbtn_cardview1);
        cassette_install_pmt_cardview2 = v.findViewById(R.id.cassette_install_pmt_cardview2);
        cassette_install_progressbar = v.findViewById(R.id.cassette_install_progressbar);


        cassette_uninstall_addbtn_cardview1 = v.findViewById(R.id.cassette_uninstall_addbtn_cardview1);
        cassette_uninstall_pmt_cardview2 = v.findViewById(R.id.cassette_uninstall_pmt_cardview2);
        cassette_uninstall_addbutton = v.findViewById(R.id.cassette_uninstall_addbutton);
        cassette_uninstall_plusbtn = v.findViewById(R.id.cassette_uninstall_plusbtn);
        cassette_uninstall_minusbtn = v.findViewById(R.id.cassette_uninstall_minusbtn);
        cassette_uninstall_txt = v.findViewById(R.id.cassette_uninstall_txt);
        cassette_uninstall_progressbar = v.findViewById(R.id.cassette_uninstall_progressbar);


        cassette_combo_addbtn_cardview1 = v.findViewById(R.id.cassette_combo_addbtn_cardview1);
        cassette_combo_pmt_cardview2 = v.findViewById(R.id.cassette_combo_pmt_cardview2);
        cassette_combo_addbutton = v.findViewById(R.id.cassette_combo_addbutton);
        cassette_combo_plusbtn = v.findViewById(R.id.cassette_combo_plusbtn);
        cassette_combo_minusbtn = v.findViewById(R.id.cassette_combo_minusbtn);
        cassette_combo_txt = v.findViewById(R.id.cassette_combo_txt);
        cassette_combo_progressbar = v.findViewById(R.id.cassette_combo_progressbar);



        txttotalitems = v.findViewById(R.id.txtitems);
        txttotalprice=v.findViewById(R.id.txttotal);
        footer = v.findViewById(R.id.footer);


        cassette_install_addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cassette_install_addbtn_cardview1.setVisibility(View.INVISIBLE);
                cassette_install_progressbar.setVisibility(View.VISIBLE);
                cassette_install_quantity("add");
                totalquantity("add");
                totalprice("add", installprice,disinstallprice,"Install");
                cassette_install_price("add",installprice);
                btn("false");
            }
        });
        cassette_install_plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cassette_install_addbtn_cardview1.setVisibility(View.INVISIBLE);
                cassette_install_progressbar.setVisibility(View.VISIBLE);
                cassette_install_quantity("add");
                totalquantity("add");
                totalprice("add", installprice-disinstallprice,disinstallprice,"Install");
                cassette_install_price("add",installprice-disinstallprice);
                totalsaved("add",disinstallprice,"Install");
                btn("false");
            }
        });
        cassette_install_minusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cassette_install_addbtn_cardview1.setVisibility(View.INVISIBLE);
                cassette_install_progressbar.setVisibility(View.VISIBLE);
                cassette_install_quantity("sub");
                totalquantity("sub");
                totalprice("sub", installprice-disinstallprice,disinstallprice,"Install");
                cassette_install_price("sub",installprice-disinstallprice);
                totalsaved("sub",disinstallprice,"Install");
                btn("false");
            }
        });



        cassette_uninstall_addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cassette_uninstall_addbtn_cardview1.setVisibility(View.INVISIBLE);
                cassette_uninstall_progressbar.setVisibility(View.VISIBLE);
                cassette_uninstall_quantity("add");
                totalquantity("add");
                totalprice("add", uninstallprice,disuninstallprice,"Uninstall");
                cassette_uninstall_price("add",uninstallprice);
                btn("false");
            }
        });
        cassette_uninstall_plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cassette_uninstall_addbtn_cardview1.setVisibility(View.INVISIBLE);
                cassette_uninstall_progressbar.setVisibility(View.VISIBLE);
                cassette_uninstall_quantity("add");
                totalquantity("add");
                totalprice("add", uninstallprice-disuninstallprice,disuninstallprice,"Uninstall");
                cassette_uninstall_price("add",uninstallprice-disuninstallprice);
                totalsaved("add",disuninstallprice,"Uninstall");
                btn("false");
            }
        });
        cassette_uninstall_minusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cassette_uninstall_addbtn_cardview1.setVisibility(View.INVISIBLE);
                cassette_uninstall_progressbar.setVisibility(View.VISIBLE);
                cassette_uninstall_quantity("sub");
                totalquantity("sub");
                totalprice("sub", uninstallprice-disuninstallprice,disuninstallprice,"Uninstall");
                cassette_uninstall_price("sub",uninstallprice-disuninstallprice);
                totalsaved("sub",disuninstallprice,"Uninstall");
                btn("false");
            }
        });


        cassette_combo_addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cassette_combo_addbtn_cardview1.setVisibility(View.INVISIBLE);
                cassette_combo_progressbar.setVisibility(View.VISIBLE);
                cassette_combo_quantity("add");
                totalquantity("add");
                totalprice("add", comboprice,discomboprice,"Combo");
                cassette_combo_price("add",comboprice);
                btn("false");
            }
        });
        cassette_combo_plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cassette_combo_addbtn_cardview1.setVisibility(View.INVISIBLE);
                cassette_combo_progressbar.setVisibility(View.VISIBLE);
                cassette_combo_quantity("add");
                totalquantity("add");
                totalprice("add", comboprice-discomboprice,discomboprice,"Combo");
                cassette_combo_price("add",comboprice-discomboprice);
                totalsaved("add",discomboprice,"Combo");
                btn("false");
            }
        });
        cassette_combo_minusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cassette_combo_addbtn_cardview1.setVisibility(View.INVISIBLE);
                cassette_combo_progressbar.setVisibility(View.VISIBLE);
                cassette_combo_quantity("sub");
                totalquantity("sub");
                totalprice("sub", comboprice-discomboprice,discomboprice,"Combo");
                cassette_combo_price("sub",comboprice-discomboprice);
                totalsaved("sub",discomboprice,"Combo");
                btn("false");
            }
        });


        return v;
    }

    // TOTAL QUANTITY

    private void totalquantity(String action) {

        if (action == "add") {
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
        } else if (action == "sub") {
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

    private void totalprice(String action, int price ,int discount, String Categoryname) {
        if (action == "add") {
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
        } else if (action == "sub") {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringcartprice = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALPRICE").getValue().toString();
                    int cartprice = Integer.parseInt(stringcartprice);

                    int totaldiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).child(Categoryname).getValue().toString());
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
        if (action == "false") {
            cassette_install_addbutton.setEnabled(false);
            cassette_install_minusbtn.setEnabled(false);
            cassette_install_plusbtn.setEnabled(false);
            cassette_uninstall_addbutton.setEnabled(false);
            cassette_uninstall_minusbtn.setEnabled(false);
            cassette_uninstall_plusbtn.setEnabled(false);
            cassette_combo_addbutton.setEnabled(false);
            cassette_combo_minusbtn.setEnabled(false);
            cassette_combo_plusbtn.setEnabled(false);
        } else if (action == "true") {
            cassette_install_addbutton.setEnabled(true);
            cassette_install_minusbtn.setEnabled(true);
            cassette_install_plusbtn.setEnabled(true);
            cassette_uninstall_addbutton.setEnabled(true);
            cassette_uninstall_minusbtn.setEnabled(true);
            cassette_uninstall_plusbtn.setEnabled(true);
            cassette_combo_addbutton.setEnabled(true);
            cassette_combo_minusbtn.setEnabled(true);
            cassette_combo_plusbtn.setEnabled(true);
        }
    }

    // INSTALL

    private void cassette_install_quantity(String action) {

        if (action == "add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringinstallcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).child("Install").getValue().toString();
                            int installcount = Integer.parseInt(stringinstallcount);
                            installcount = installcount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Install", Integer.toString(installcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).updateChildren(cartt);
                            cassette_install_pmt_cardview2.setVisibility(View.VISIBLE);
                            cassette_install_progressbar.setVisibility(View.INVISIBLE);
                            btn("true");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            };
            delay.postDelayed(run, 500);
        }
        else if (action == "sub") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringinstallcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).child("Install").getValue().toString();
                            int installcount = Integer.parseInt(stringinstallcount);
                            installcount = installcount- 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Install", Integer.toString(installcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).updateChildren(cartt);
                            if (installcount <= 0) {
                                cassette_install_addbtn_cardview1.setVisibility(View.VISIBLE);
                                cassette_install_pmt_cardview2.setVisibility(View.INVISIBLE);
                                cassette_install_progressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                            else {
                                cassette_install_progressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            };
            delay.postDelayed(run, 500);


        }
    }
    private void cassette_install_price(String action,int price) {
        if(action=="add")
        {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringinstallcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).child("InstallTOTAL").getValue().toString();
                    int installcount = Integer.parseInt(stringinstallcount);
                    installcount = installcount + price;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("InstallTOTAL", Integer.toString(installcount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).updateChildren(cartt);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else if(action=="sub")
        {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringinstallcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).child("InstallTOTAL").getValue().toString();
                    int installcount = Integer.parseInt(stringinstallcount);

                    int installdiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).child("Install").getValue().toString());
                    if(installdiscount==1)
                    {
                        installcount = installcount - price - disinstallprice;
                    }
                    else
                    {
                        installcount = installcount - price;
                    }

                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("InstallTOTAL", Integer.toString(installcount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).updateChildren(cartt);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    //UNINSTALL
    private void cassette_uninstall_quantity(String action) {

        if (action == "add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringuninstallcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).child("Uninstall").getValue().toString();
                            int uninstallcount = Integer.parseInt(stringuninstallcount);
                            uninstallcount = uninstallcount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Uninstall", Integer.toString(uninstallcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).updateChildren(cartt);
                            cassette_uninstall_pmt_cardview2.setVisibility(View.VISIBLE);
                            cassette_uninstall_progressbar.setVisibility(View.INVISIBLE);
                            btn("true");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            };
            delay.postDelayed(run, 500);
        } else if (action == "sub") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringuninstallcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).child("Uninstall").getValue().toString();
                            int uninstallcount = Integer.parseInt(stringuninstallcount);
                            uninstallcount = uninstallcount- 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Uninstall", Integer.toString(uninstallcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).updateChildren(cartt);
                            if (uninstallcount <= 0) {
                                cassette_uninstall_addbtn_cardview1.setVisibility(View.VISIBLE);
                                cassette_uninstall_pmt_cardview2.setVisibility(View.INVISIBLE);
                                cassette_uninstall_progressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                            else {
                                cassette_uninstall_progressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            };
            delay.postDelayed(run, 500);


        }
    }
    private void cassette_uninstall_price(String action,int price) {
        if(action=="add")
        {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringuninstallcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).child("UninstallTOTAL").getValue().toString();
                    int uninstallcount = Integer.parseInt(stringuninstallcount);
                    uninstallcount = uninstallcount + price;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("UninstallTOTAL", Integer.toString(uninstallcount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).updateChildren(cartt);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else if(action=="sub")
        {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringuninstallcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).child("UninstallTOTAL").getValue().toString();
                    int uninstallcount = Integer.parseInt(stringuninstallcount);

                    int uninstalldiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).child("Uninstall").getValue().toString());
                    if(uninstalldiscount==1)
                    {
                        uninstallcount = uninstallcount - price - disuninstallprice;
                    }
                    else
                    {
                        uninstallcount = uninstallcount - price;
                    }

                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("UninstallTOTAL", Integer.toString(uninstallcount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).updateChildren(cartt);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    //COMBO

    private void cassette_combo_quantity(String action) {

        if (action == "add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringcombocount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).child("Combo").getValue().toString();
                            int combocount = Integer.parseInt(stringcombocount);
                            combocount = combocount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Combo", Integer.toString(combocount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).updateChildren(cartt);
                            cassette_combo_pmt_cardview2.setVisibility(View.VISIBLE);
                            cassette_combo_progressbar.setVisibility(View.INVISIBLE);
                            btn("true");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            };
            delay.postDelayed(run, 500);
        }
        else if (action == "sub") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringcombocount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).child("Combo").getValue().toString();
                            int combocount = Integer.parseInt(stringcombocount);
                            combocount = combocount- 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Combo", Integer.toString(combocount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).updateChildren(cartt);
                            if (combocount <= 0) {
                                cassette_combo_addbtn_cardview1.setVisibility(View.VISIBLE);
                                cassette_combo_pmt_cardview2.setVisibility(View.INVISIBLE);
                                cassette_combo_progressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                            else {
                                cassette_combo_progressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            };
            delay.postDelayed(run, 500);


        }
    }
    private void cassette_combo_price(String action,int price) {
        if(action=="add")
        {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringcombocount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).child("ComboTOTAL").getValue().toString();
                    int combocount = Integer.parseInt(stringcombocount);
                    combocount = combocount + price;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("ComboTOTAL", Integer.toString(combocount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).updateChildren(cartt);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else if(action=="sub")
        {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringcombocount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).child("ComboTOTAL").getValue().toString();
                    int combocount = Integer.parseInt(stringcombocount);

                    int combodiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).child("Combo").getValue().toString());
                    if(combodiscount==1)
                    {
                        combocount= combocount - price - discomboprice;
                    }
                    else
                    {
                        combocount = combocount - price;
                    }

                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("ComboTOTAL", Integer.toString(combocount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).updateChildren(cartt);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    //TOTALSAVED

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
                    String stringcartsaved = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALSAVED").getValue().toString();
                    int cartsaved = Integer.parseInt(stringcartsaved);
                    int totaldiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).child(categoryname).getValue().toString());
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