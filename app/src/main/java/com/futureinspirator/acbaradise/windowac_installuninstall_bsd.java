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

public class windowac_installuninstall_bsd extends BottomSheetDialogFragment {

    private String Category = "WindowAC";

    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());
    private Button window_install_addbutton;
    private Button window_install_minusbtn;
    private Button window_install_plusbtn;
    private TextView window_install_txt;
    private CardView window_install_pmt_cardview2;
    private CardView window_install_addbtn_cardview1;
    private CardView window_install_progressbar;

    private Button window_uninstall_addbutton;
    private Button window_uninstall_minusbtn;
    private Button window_uninstall_plusbtn;
    private TextView window_uninstall_txt;
    private CardView window_uninstall_addbtn_cardview1;
    private CardView window_uninstall_pmt_cardview2;
    private CardView window_uninstall_progressbar;

    private Button window_combo_addbutton;
    private Button window_combo_minusbtn;
    private Button window_combo_plusbtn;
    private TextView window_combo_txt;
    private CardView window_combo_addbtn_cardview1;
    private CardView window_combo_pmt_cardview2;
    private CardView window_combo_progressbar;

    private TextView txttotalitems;
    private RelativeLayout footer;
    private TextView  txttotalprice;





    private int installprice = 0;
    private int uninstallprice = 0;
    private int comboprice=0;


    private int disinstallprice = 0;
    private int disuninstallprice = 0;
    private int discomboprice=0;
    private DatabaseReference discount = FirebaseDatabase.getInstance().getReference().child("Products").child("Installuninstall").child(Category).child("Discount");
     private TextView txt_originalprice_install_windowac, txt_originalprice_uninstall_windowac,txt_originalprice_windowac_combo;
private TextView txt_discount_price_install_windowac,txt_discount_price_uninstall_windowac,txt_discount_price_install_windowac_combo;
private int disstring_install_price,disstring_uninstall_price,disstring_combo_price;

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();

    private DatabaseReference cart = FirebaseDatabase.getInstance().getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_windowac_installuninstall_bsd, container, false);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String string_install_price = snapshot.child("Products").child("Installuninstall").child(Category).child("Price").child("Install").getValue().toString();
                installprice = Integer.parseInt(string_install_price);
                txt_originalprice_install_windowac.setText(""+string_install_price);

                disstring_install_price=installprice + 100;
                txt_discount_price_install_windowac.setText(""+disstring_install_price);
                txt_discount_price_install_windowac.setPaintFlags(txt_discount_price_install_windowac.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String installdatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).child("Install").getValue().toString();
                window_install_txt.setText("" + installdatabaseQvalue);
                int intinstalldatabaseQvalue = Integer.parseInt(installdatabaseQvalue);

                if (intinstalldatabaseQvalue <= 0) {
                    window_install_pmt_cardview2.setVisibility(View.INVISIBLE);
                } else {
                    window_install_pmt_cardview2.setVisibility(View.VISIBLE);
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
                txt_originalprice_uninstall_windowac.setText(""+string_uninstall_price);

                disstring_uninstall_price=uninstallprice + 100;
                txt_discount_price_uninstall_windowac.setText(""+disstring_uninstall_price);
                txt_discount_price_uninstall_windowac.setPaintFlags(txt_discount_price_uninstall_windowac.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String uninstalldatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).child("Uninstall").getValue().toString();
                window_uninstall_txt.setText("" + uninstalldatabaseQvalue);
                int intuninstalldatabaseQvalue = Integer.parseInt(uninstalldatabaseQvalue);
                if (intuninstalldatabaseQvalue <= 0) {
                    window_uninstall_pmt_cardview2.setVisibility(View.INVISIBLE);
                } else {
                    window_uninstall_pmt_cardview2.setVisibility(View.VISIBLE);
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
                txt_originalprice_windowac_combo.setText(""+string_combo_price);


                disstring_combo_price=comboprice + 100;
                txt_discount_price_install_windowac_combo.setText(""+disstring_combo_price);
                txt_discount_price_install_windowac_combo.setPaintFlags(txt_discount_price_install_windowac_combo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String combodatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child(Category).child("Combo").getValue().toString();
                window_combo_txt.setText("" + combodatabaseQvalue);
                int intcombodatabaseQvalue = Integer.parseInt(combodatabaseQvalue);
                if (intcombodatabaseQvalue <= 0) {
                    window_combo_pmt_cardview2.setVisibility(View.INVISIBLE);
                } else {
                    window_combo_pmt_cardview2.setVisibility(View.VISIBLE);
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



        txt_discount_price_install_windowac=v.findViewById(R.id.txt_discount_price_install_windowac);
                txt_discount_price_uninstall_windowac=v.findViewById(R.id.txt_discount_price_uninstall_windowac);
        txt_discount_price_install_windowac_combo=v.findViewById(R.id.txt_discount_price_install_windowac_combo);

        txt_originalprice_install_windowac=v.findViewById(R.id.txt_originalprice_install_windowac);
        txt_originalprice_uninstall_windowac=v.findViewById(R.id.txt_originalprice_uninstall_windowac);
        txt_originalprice_windowac_combo=v.findViewById(R.id.txt_originalprice_install_windowac_combo);

        window_install_addbutton = v.findViewById(R.id.window_install_addbutton);
        window_install_minusbtn = v.findViewById(R.id.window_install_minusbtn);
        window_install_plusbtn = v.findViewById(R.id.window_install_plusbtn);
        window_install_txt = v.findViewById(R.id.window_install_txt);
        window_install_addbtn_cardview1 = v.findViewById(R.id.window_install_addbtn_cardview1);
        window_install_pmt_cardview2 = v.findViewById(R.id.window_install_pmt_cardview2);
        window_install_progressbar = v.findViewById(R.id.window_install_progressbar);


        window_uninstall_addbtn_cardview1 = v.findViewById(R.id.window_uninstall_addbtn_cardview1);
        window_uninstall_pmt_cardview2 = v.findViewById(R.id.window_uninstall_pmt_cardview2);
        window_uninstall_addbutton = v.findViewById(R.id.window_uninstall_addbutton);
        window_uninstall_plusbtn = v.findViewById(R.id.window_uninstall_plusbtn);
        window_uninstall_minusbtn = v.findViewById(R.id.window_uninstall_minusbtn);
        window_uninstall_txt = v.findViewById(R.id.window_uninstall_txt);
        window_uninstall_progressbar = v.findViewById(R.id.window_uninstall_progressbar);


        window_combo_addbtn_cardview1 = v.findViewById(R.id.window_combo_addbtn_cardview1);
        window_combo_pmt_cardview2 = v.findViewById(R.id.window_combo_pmt_cardview2);
        window_combo_addbutton = v.findViewById(R.id.window_combo_addbutton);
        window_combo_plusbtn = v.findViewById(R.id.window_combo_plusbtn);
        window_combo_minusbtn = v.findViewById(R.id.window_combo_minusbtn);
        window_combo_txt = v.findViewById(R.id.window_combo_txt);
        window_combo_progressbar = v.findViewById(R.id.window_combo_progressbar);



        txttotalitems = v.findViewById(R.id.txtitems);
        txttotalprice=v.findViewById(R.id.txttotal);
        footer = v.findViewById(R.id.footer);


        window_install_addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window_install_addbtn_cardview1.setVisibility(View.INVISIBLE);
                window_install_progressbar.setVisibility(View.VISIBLE);
                window_install_quantity("add");
                totalquantity("add");
                totalprice("add", installprice,disinstallprice,"Install");
                window_install_price("add",installprice);
                btn("false");
            }
        });
        window_install_plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window_install_addbtn_cardview1.setVisibility(View.INVISIBLE);
                window_install_progressbar.setVisibility(View.VISIBLE);
                window_install_quantity("add");
                totalquantity("add");
                totalprice("add", installprice-disinstallprice,disinstallprice,"Install");
                window_install_price("add",installprice-disinstallprice);
                totalsaved("add",disinstallprice,"Install");
                btn("false");
            }
        });
        window_install_minusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window_install_addbtn_cardview1.setVisibility(View.INVISIBLE);
                window_install_progressbar.setVisibility(View.VISIBLE);
                window_install_quantity("sub");
                totalquantity("sub");
                totalprice("sub", installprice-disinstallprice,disinstallprice,"Install");
                window_install_price("sub",installprice-disinstallprice);
                totalsaved("sub",disinstallprice,"Install");
                btn("false");
            }
        });



        window_uninstall_addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window_uninstall_addbtn_cardview1.setVisibility(View.INVISIBLE);
                window_uninstall_progressbar.setVisibility(View.VISIBLE);
                window_uninstall_quantity("add");
                totalquantity("add");
                totalprice("add", uninstallprice,disuninstallprice,"Uninstall");
                window_uninstall_price("add",uninstallprice);
                btn("false");
            }
        });
        window_uninstall_plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window_uninstall_addbtn_cardview1.setVisibility(View.INVISIBLE);
                window_uninstall_progressbar.setVisibility(View.VISIBLE);
                window_uninstall_quantity("add");
                totalquantity("add");
                totalprice("add", uninstallprice-disuninstallprice,disuninstallprice,"Uninstall");
                window_uninstall_price("add",uninstallprice-disuninstallprice);
                totalsaved("add",disuninstallprice,"Uninstall");
                btn("false");
            }
        });
        window_uninstall_minusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window_uninstall_addbtn_cardview1.setVisibility(View.INVISIBLE);
                window_uninstall_progressbar.setVisibility(View.VISIBLE);
                window_uninstall_quantity("sub");
                totalquantity("sub");
                totalprice("sub", uninstallprice-disuninstallprice,disuninstallprice,"Uninstall");
                window_uninstall_price("sub",uninstallprice-disuninstallprice);
                totalsaved("sub",disuninstallprice,"Uninstall");
                btn("false");
            }
        });


        window_combo_addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window_combo_addbtn_cardview1.setVisibility(View.INVISIBLE);
                window_combo_progressbar.setVisibility(View.VISIBLE);
                window_combo_quantity("add");
                totalquantity("add");
                totalprice("add", comboprice,discomboprice,"Combo");
                window_combo_price("add",comboprice);
                btn("false");
            }
        });
        window_combo_plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window_combo_addbtn_cardview1.setVisibility(View.INVISIBLE);
                window_combo_progressbar.setVisibility(View.VISIBLE);
                window_combo_quantity("add");
                totalquantity("add");
                totalprice("add", comboprice-discomboprice,discomboprice,"Combo");
                window_combo_price("add",comboprice-discomboprice);
                totalsaved("add",discomboprice,"Combo");
                btn("false");
            }
        });
        window_combo_minusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window_combo_addbtn_cardview1.setVisibility(View.INVISIBLE);
                window_combo_progressbar.setVisibility(View.VISIBLE);
                window_combo_quantity("sub");
                totalquantity("sub");
                totalprice("sub", comboprice-discomboprice,discomboprice,"Combo");
                window_combo_price("sub",comboprice-discomboprice);
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

    private void totalprice(String action, int price,int discount,String Categoryname) {
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
                    };
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
            window_install_addbutton.setEnabled(false);
            window_install_minusbtn.setEnabled(false);
            window_install_plusbtn.setEnabled(false);
            window_uninstall_addbutton.setEnabled(false);
            window_uninstall_minusbtn.setEnabled(false);
            window_uninstall_plusbtn.setEnabled(false);
            window_combo_addbutton.setEnabled(false);
            window_combo_minusbtn.setEnabled(false);
            window_combo_plusbtn.setEnabled(false);
        } else if (action == "true") {
            window_install_addbutton.setEnabled(true);
            window_install_minusbtn.setEnabled(true);
            window_install_plusbtn.setEnabled(true);
            window_uninstall_addbutton.setEnabled(true);
            window_uninstall_minusbtn.setEnabled(true);
            window_uninstall_plusbtn.setEnabled(true);
            window_combo_addbutton.setEnabled(true);
            window_combo_minusbtn.setEnabled(true);
            window_combo_plusbtn.setEnabled(true);
        }
    }

    // INSTALL

    private void window_install_quantity(String action) {

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
                            window_install_pmt_cardview2.setVisibility(View.VISIBLE);
                            window_install_progressbar.setVisibility(View.INVISIBLE);
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
                                window_install_addbtn_cardview1.setVisibility(View.VISIBLE);
                                window_install_pmt_cardview2.setVisibility(View.INVISIBLE);
                                window_install_progressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                            else {
                                window_install_progressbar.setVisibility(View.INVISIBLE);
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
    private void window_install_price(String action,int price) {

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
    private void window_uninstall_quantity(String action) {

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
                            window_uninstall_pmt_cardview2.setVisibility(View.VISIBLE);
                            window_uninstall_progressbar.setVisibility(View.INVISIBLE);
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
                                window_uninstall_addbtn_cardview1.setVisibility(View.VISIBLE);
                                window_uninstall_pmt_cardview2.setVisibility(View.INVISIBLE);
                                window_uninstall_progressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                            else {
                                window_uninstall_progressbar.setVisibility(View.INVISIBLE);
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
    private void window_uninstall_price(String action,int price) {
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

    private void window_combo_quantity(String action) {

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
                            window_combo_pmt_cardview2.setVisibility(View.VISIBLE);
                            window_combo_progressbar.setVisibility(View.INVISIBLE);
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
                                window_combo_addbtn_cardview1.setVisibility(View.VISIBLE);
                                window_combo_pmt_cardview2.setVisibility(View.INVISIBLE);
                                window_combo_progressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                            else {
                                window_combo_progressbar.setVisibility(View.INVISIBLE);
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
    private void window_combo_price(String action,int price) {

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