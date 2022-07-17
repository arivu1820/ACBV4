package com.futureinspirator.acbaradise;

import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class fragment_split_faults extends Fragment {

    private String Maincategory = "Faults", Category = "SplitAC";
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());
    private CardView cardview_waterleak1, cardview_waterleak2;
    private Button waterleakbtn;
    private Button addwaterleak;
    private Button subwaterleak;
    private TextView txtwaterleak;
    private CardView waterleakprogressbar;

    private CardView cardview_gasleak1, cardview_gasleak2;
    private Button gasleakbtn;
    private Button addgasleak;
    private Button subgasleak;
    private TextView txtgasleak;
    private CardView gasleakprogressbar;

    private CardView cardview_pcboard1, cardview_pcboard2;
    private Button pcboardbtn;
    private Button addpcboard;
    private Button subpcboard;
    private TextView txtpcboard;
    private CardView pcboardprogressbar;

    private CardView cardview_indoorcoil1, cardview_indoorcoil2;
    private Button indoorcoilbtn;
    private Button addindoorcoil;
    private Button subindoorcoil;
    private TextView txtindoorcoil;
    private CardView indoorcoilprogressbar;

    private CardView cardview_outdoorcondenser1, cardview_outdoorcondenser2;
    private Button outdoorcondenserbtn;
    private Button addoutdoorcondenser;
    private Button suboutdoorcondenser;
    private TextView txtoutdoorcondenser;
    private CardView outdoorcondenserprogressbar;

    private CardView cardview_remote1, cardview_remote2;
    private Button remotebtn;
    private Button addremote;
    private Button subremote;
    private TextView txtremote;
    private CardView remoteprogressbar;

    private CardView cardview_stabilizer1, cardview_stabilizer2;
    private Button stabilizerbtn;
    private Button addstabilizer;
    private Button substabilizer;
    private TextView txtstabilizer;
    private CardView stabilizerprogressbar;

    private int waterleakprice = 0,gasleakprice = 0,pcboardprice = 0,indoorcoilprice = 0,outdoorcondenserprice = 0,remoteprice = 0,stabilizerprice = 0;
    private int diswaterleakprice = 0,disgasleakprice = 0,dispcboardprice = 0,disindoorcoilprice = 0,disoutdoorcondenserprice = 0,disremoteprice = 0,disstabilizerprice = 0;
    private DatabaseReference discount = FirebaseDatabase.getInstance().getReference().child("Products").child(Maincategory).child(Category).child("Discount");

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference cart = FirebaseDatabase.getInstance().getReference();

    private TextView txt_price_waterleak,txt_price_gasleak,txt_price_pcboard,txt_price_indoorcoil,txt_price_outdoorcomdenser, txt_price_remote,txt_price_stablizer ;
    private  int disstringwaterleakprice,disstringgasleakprice,disstringpcboardprice,disstringindoorcoilprice,disstringoutdoorcondenserprice,disstringremoteprice,disstringstabilizerprice;
    private TextView textView7,textView6,txt_discount_price_stablizer,txt_discount_price_pcboard,txt_discount_price_indoorcoil,txt_discount_price_outdoorcomdenser,txt_discount_price_remote;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//
        View v = inflater.inflate(R.layout.fragment_split_faults, container, false);


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String stringwaterleakprice = snapshot.child("Products").child(Maincategory).child(Category).child("Price").child("Waterleak").getValue().toString();
                String stringgasleakprice = snapshot.child("Products").child(Maincategory).child(Category).child("Price").child("Gasleak").getValue().toString();
                String stringpcboardprice = snapshot.child("Products").child(Maincategory).child(Category).child("Price").child("Pcboard").getValue().toString();
                String stringindoorcoilprice = snapshot.child("Products").child(Maincategory).child(Category).child("Price").child("Indoorcoil").getValue().toString();
                String stringoutdoorcondenserprice = snapshot.child("Products").child(Maincategory).child(Category).child("Price").child("Outdoorcondenser").getValue().toString();
                String stringremoteprice = snapshot.child("Products").child(Maincategory).child(Category).child("Price").child("Remote").getValue().toString();
                String stringstabilizerprice = snapshot.child("Products").child(Maincategory).child(Category).child("Price").child("Stabilizer").getValue().toString();



                txt_price_waterleak.setText(""+stringwaterleakprice);
                txt_price_gasleak.setText(""+stringgasleakprice);
                txt_price_pcboard .setText(""+stringpcboardprice);
                txt_price_indoorcoil.setText(""+stringindoorcoilprice);
                txt_price_outdoorcomdenser.setText(""+stringoutdoorcondenserprice);
                txt_price_remote.setText(""+stringremoteprice);
                txt_price_stablizer.setText(""+stringstabilizerprice);

                waterleakprice = Integer.parseInt(stringwaterleakprice);
                gasleakprice = Integer.parseInt(stringgasleakprice);
                pcboardprice = Integer.parseInt(stringpcboardprice);
                indoorcoilprice = Integer.parseInt(stringindoorcoilprice);
                outdoorcondenserprice = Integer.parseInt(stringoutdoorcondenserprice);
                remoteprice = Integer.parseInt(stringremoteprice);
                stabilizerprice = Integer.parseInt(stringstabilizerprice);

                disstringwaterleakprice = waterleakprice + 100;
                disstringwaterleakprice = waterleakprice + 100;
                disstringgasleakprice = gasleakprice + 100;
                disstringpcboardprice = pcboardprice + 100;
                disstringindoorcoilprice = indoorcoilprice + 100;
                disstringoutdoorcondenserprice = outdoorcondenserprice + 100;
                disstringremoteprice = remoteprice + 100;
                disstringstabilizerprice = stabilizerprice + 100;



                textView7.setText(""+disstringwaterleakprice);
                textView7.setPaintFlags(textView7.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                textView6.setText(""+disstringgasleakprice);
                textView6.setPaintFlags(textView6.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                txt_discount_price_stablizer.setText(""+disstringstabilizerprice);
                txt_discount_price_stablizer.setPaintFlags(txt_discount_price_stablizer.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                txt_discount_price_pcboard.setText(""+disstringpcboardprice);
                txt_discount_price_pcboard.setPaintFlags(txt_discount_price_pcboard.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


                txt_discount_price_indoorcoil.setText(""+disstringindoorcoilprice);
                txt_discount_price_indoorcoil.setPaintFlags(txt_discount_price_indoorcoil.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                txt_discount_price_outdoorcomdenser.setText(""+disstringoutdoorcondenserprice);
                txt_discount_price_outdoorcomdenser.setPaintFlags(txt_discount_price_outdoorcomdenser.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                txt_discount_price_remote.setText(""+disstringremoteprice);
                txt_discount_price_remote.setPaintFlags(txt_discount_price_remote.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String waterleakdatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Waterleak").getValue().toString();
                txtwaterleak.setText("" + waterleakdatabaseQvalue);
                int intwaterleakdatabaseQvalue = Integer.parseInt(waterleakdatabaseQvalue);
                if (intwaterleakdatabaseQvalue <= 0) {
                    cardview_waterleak2.setVisibility(View.INVISIBLE);
                } else {
                    cardview_waterleak2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String gasleakdatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Gasleak").getValue().toString();
                txtgasleak.setText("" + gasleakdatabaseQvalue);
                int intgasleakdatabaseQvalue = Integer.parseInt(gasleakdatabaseQvalue);
                if (intgasleakdatabaseQvalue <= 0) {
                    cardview_gasleak2.setVisibility(View.INVISIBLE);
                } else {
                    cardview_gasleak2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String pcboarddatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Pcboard").getValue().toString();
                txtpcboard.setText("" + pcboarddatabaseQvalue);
                int intpcboarddatabaseQvalue = Integer.parseInt(pcboarddatabaseQvalue);
                if (intpcboarddatabaseQvalue <= 0) {
                    cardview_pcboard2.setVisibility(View.INVISIBLE);
                } else {
                    cardview_pcboard2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String indoorcoildatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Indoorcoil").getValue().toString();
                txtindoorcoil.setText("" + indoorcoildatabaseQvalue);
                int intindoorcoildatabaseQvalue = Integer.parseInt(indoorcoildatabaseQvalue);
                if (intindoorcoildatabaseQvalue <= 0) {
                    cardview_indoorcoil2.setVisibility(View.INVISIBLE);
                } else {
                    cardview_indoorcoil2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String outdoorcondenserdatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Outdoorcondenser").getValue().toString();
                txtoutdoorcondenser.setText("" + outdoorcondenserdatabaseQvalue);
                int intoutdoorcondenserdatabaseQvalue = Integer.parseInt(outdoorcondenserdatabaseQvalue);
                if (intoutdoorcondenserdatabaseQvalue <= 0) {
                    cardview_outdoorcondenser2.setVisibility(View.INVISIBLE);
                } else {
                    cardview_outdoorcondenser2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String remotedatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Remote").getValue().toString();
                txtremote.setText("" + remotedatabaseQvalue);
                int intremotedatabaseQvalue = Integer.parseInt(remotedatabaseQvalue);
                if (intremotedatabaseQvalue <= 0) {
                    cardview_remote2.setVisibility(View.INVISIBLE);
                } else {
                    cardview_remote2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String stabilizerdatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Stabilizer").getValue().toString();
                txtstabilizer.setText("" + stabilizerdatabaseQvalue);
                int intstabilizerdatabaseQvalue = Integer.parseInt(stabilizerdatabaseQvalue);
                if (intstabilizerdatabaseQvalue <= 0) {
                    cardview_stabilizer2.setVisibility(View.INVISIBLE);
                } else {
                    cardview_stabilizer2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        discount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String disstringwaterleakprice = snapshot.child("Waterleak").getValue().toString();
                String disstringgasleakprice = snapshot.child("Gasleak").getValue().toString();
                String disstringpcboardprice = snapshot.child("Pcboard").getValue().toString();
                String disstringindoorcoilprice = snapshot.child("Indoorcoil").getValue().toString();
                String disstringoutdoorcondenserprice = snapshot.child("Outdoorcondenser").getValue().toString();
                String disstringremoteprice = snapshot.child("Remote").getValue().toString();
                String disstringstabilizerprice = snapshot.child("Stabilizer").getValue().toString();


                diswaterleakprice = Integer.parseInt(disstringwaterleakprice);
                disgasleakprice = Integer.parseInt(disstringgasleakprice);
                dispcboardprice = Integer.parseInt(disstringpcboardprice);
                disindoorcoilprice = Integer.parseInt(disstringindoorcoilprice);
                disoutdoorcondenserprice = Integer.parseInt(disstringoutdoorcondenserprice);
                disremoteprice = Integer.parseInt(disstringremoteprice);
                disstabilizerprice = Integer.parseInt(disstringstabilizerprice);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        textView7=v.findViewById(R.id.textView7);
        textView6=v.findViewById(R.id.textView6);
        txt_discount_price_stablizer=v.findViewById(R.id.txt_discount_price_stablizer);
        txt_discount_price_pcboard=v.findViewById(R.id.txt_discount_price_pcboard);
        txt_discount_price_indoorcoil=v.findViewById(R.id.txt_discount_price_indoorcoil);
        txt_discount_price_outdoorcomdenser=v.findViewById(R.id.txt_discount_price_outdoorcomdenser);
        txt_discount_price_remote=v.findViewById(R.id.txt_discount_price_remote);

        txt_price_waterleak=v.findViewById(R.id.textView8);
        txt_price_gasleak=v.findViewById(R.id.textView5);
        txt_price_pcboard=v.findViewById(R.id.txt_price_pcboard);
        txt_price_indoorcoil=v.findViewById(R.id.txt_price_indoorcoil);
        txt_price_outdoorcomdenser=v.findViewById(R.id.txt_price_outdoorcomdenser);
        txt_price_remote=v.findViewById(R.id.txt_price_remote);
        txt_price_stablizer=v.findViewById(R.id.txt_price_stablizer);

        cardview_waterleak1 = v.findViewById(R.id.cardview_waterleak1);
        cardview_waterleak2 = v.findViewById(R.id.cardview_waterleak2);
        waterleakbtn = v.findViewById(R.id.waterleakbtn);
        addwaterleak = v.findViewById(R.id.addwaterleak);
        subwaterleak = v.findViewById(R.id.subwaterleak);
        txtwaterleak = v.findViewById(R.id.txtwaterleak);
        waterleakprogressbar = v.findViewById(R.id.waterleakprogressbar);

        cardview_gasleak1 = v.findViewById(R.id.cardview_gasleak1);
        cardview_gasleak2 = v.findViewById(R.id.cardview_gasleak2);
        gasleakbtn = v.findViewById(R.id.gasleakbtn);
        addgasleak = v.findViewById(R.id.addgasleak);
        subgasleak = v.findViewById(R.id.subgasleak);
        txtgasleak = v.findViewById(R.id.txtgasleak);
        gasleakprogressbar = v.findViewById(R.id.gasleakprogressbar);

        cardview_pcboard1 = v.findViewById(R.id.cardview_pcboard1);
        cardview_pcboard2 = v.findViewById(R.id.cardview_pcboard2);
        pcboardbtn = v.findViewById(R.id.pcboardbtn);
        addpcboard = v.findViewById(R.id.addpcboard);
        subpcboard = v.findViewById(R.id.subpcboard);
        txtpcboard = v.findViewById(R.id.txtpcboard);
        pcboardprogressbar = v.findViewById(R.id.pcboardprogressbar);

        cardview_indoorcoil1 = v.findViewById(R.id.cardview_indoorcoil1);
        cardview_indoorcoil2 = v.findViewById(R.id.cardview_indoorcoil2);
        indoorcoilbtn = v.findViewById(R.id.indoorcoilbtn);
        addindoorcoil = v.findViewById(R.id.addindoorcoil);
        subindoorcoil = v.findViewById(R.id.subindoorcoil);
        txtindoorcoil = v.findViewById(R.id.txtindoorcoil);
        indoorcoilprogressbar = v.findViewById(R.id.indoorcoilprogressbar);

        cardview_outdoorcondenser1 = v.findViewById(R.id.cardview_outdoorcondenser1);
        cardview_outdoorcondenser2 = v.findViewById(R.id.cardview_outdoorcondenser2);
        outdoorcondenserbtn = v.findViewById(R.id.outdoorcondenserbtn);
        addoutdoorcondenser = v.findViewById(R.id.addoutdoorcondenser);
        suboutdoorcondenser = v.findViewById(R.id.suboutdoorcondenser);
        txtoutdoorcondenser = v.findViewById(R.id.txtoutdoorcondenser);
        outdoorcondenserprogressbar = v.findViewById(R.id.outdoorcondenserprogressbar);

        cardview_remote1 = v.findViewById(R.id.cardview_remote1);
        cardview_remote2 = v.findViewById(R.id.cardview_remote2);
        remotebtn = v.findViewById(R.id.remotebtn);
        addremote = v.findViewById(R.id.addremote);
        subremote = v.findViewById(R.id.subremote);
        txtremote = v.findViewById(R.id.txtremote);
        remoteprogressbar = v.findViewById(R.id.remoteprogressbar);

        cardview_stabilizer1 = v.findViewById(R.id.cardview_stabilizer1);
        cardview_stabilizer2 = v.findViewById(R.id.cardview_stabilizer2);
        stabilizerbtn = v.findViewById(R.id.stabilizerbtn);
        addstabilizer = v.findViewById(R.id.addstabilizer);
        substabilizer = v.findViewById(R.id.substabilizer);
        txtstabilizer = v.findViewById(R.id.txtstabilizer);
        stabilizerprogressbar = v.findViewById(R.id.stabilizerprogressbar);


        waterleakbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_waterleak1.setVisibility(View.INVISIBLE);
                waterleakprogressbar.setVisibility(View.VISIBLE);
                waterleakquantity("add");
                totalquantity("add");
                totalprice("add", waterleakprice,diswaterleakprice,"Waterleak");
                waterleakprice("add", waterleakprice);
                btn("false");
            }
        });
        addwaterleak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_waterleak1.setVisibility(View.INVISIBLE);
                waterleakprogressbar.setVisibility(View.VISIBLE);
                waterleakquantity("add");
                totalquantity("add");
                totalprice("add", waterleakprice-diswaterleakprice,diswaterleakprice,"Waterleak");
                waterleakprice("add", waterleakprice-diswaterleakprice);
                totalsaved("add",diswaterleakprice,"Waterleak");
                btn("false");
            }
        });
        subwaterleak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_waterleak1.setVisibility(View.INVISIBLE);
                waterleakprogressbar.setVisibility(View.VISIBLE);
                waterleakquantity("sub");
                totalquantity("sub");
                totalprice("sub", waterleakprice-diswaterleakprice,diswaterleakprice,"Waterleak");
                waterleakprice("sub", waterleakprice-diswaterleakprice);
                totalsaved("sub",diswaterleakprice,"Waterleak");
                btn("false");
            }
        });

        gasleakbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_gasleak1.setVisibility(View.INVISIBLE);
                gasleakprogressbar.setVisibility(View.VISIBLE);
                gasleakquantity("add");
                totalquantity("add");
                totalprice("add", gasleakprice,disgasleakprice,"Gasleak");
                gasleakprice("add", gasleakprice);
                btn("false");
            }
        });
        addgasleak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_gasleak1.setVisibility(View.INVISIBLE);
                gasleakprogressbar.setVisibility(View.VISIBLE);
                gasleakquantity("add");
                totalquantity("add");
                totalprice("add", gasleakprice-disgasleakprice,disgasleakprice,"Gasleak");
                gasleakprice("add", gasleakprice-disgasleakprice);
                totalsaved("add",disgasleakprice,"Gasleak");
                btn("false");
            }
        });
        subgasleak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_gasleak1.setVisibility(View.INVISIBLE);
                gasleakprogressbar.setVisibility(View.VISIBLE);
                gasleakquantity("sub");
                totalquantity("sub");
                totalprice("sub", gasleakprice-disgasleakprice,disgasleakprice,"Gasleak");
                gasleakprice("sub", gasleakprice-disgasleakprice);
                totalsaved("sub",disgasleakprice,"Gasleak");
                btn("false");
            }
        });

        pcboardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_pcboard1.setVisibility(View.INVISIBLE);
                pcboardprogressbar.setVisibility(View.VISIBLE);
                pcboardquantity("add");
                totalquantity("add");
                totalprice("add", pcboardprice,dispcboardprice,"Pcboard");
                pcboardprice("add", pcboardprice);
                btn("false");
            }
        });
        addpcboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_pcboard1.setVisibility(View.INVISIBLE);
                pcboardprogressbar.setVisibility(View.VISIBLE);
                pcboardquantity("add");
                totalquantity("add");
                totalprice("add", pcboardprice-dispcboardprice,dispcboardprice,"Pcboard");
                pcboardprice("add", pcboardprice-dispcboardprice);
                totalsaved("add",dispcboardprice,"Pcboard");
                btn("false");
            }
        });
        subpcboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_pcboard1.setVisibility(View.INVISIBLE);
                pcboardprogressbar.setVisibility(View.VISIBLE);
                pcboardquantity("sub");
                totalquantity("sub");
                totalprice("sub", pcboardprice-dispcboardprice,dispcboardprice,"Pcboard");
                pcboardprice("sub", pcboardprice-dispcboardprice);
                totalsaved("sub",dispcboardprice,"Pcboard");
                btn("false");
            }
        });

        indoorcoilbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_indoorcoil1.setVisibility(View.INVISIBLE);
                indoorcoilprogressbar.setVisibility(View.VISIBLE);
                indoorcoilquantity("add");
                totalquantity("add");
                totalprice("add", indoorcoilprice,disindoorcoilprice,"Indoorcoil");
                indoorcoilprice("add", indoorcoilprice);
                btn("false");
            }
        });
        addindoorcoil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_indoorcoil1.setVisibility(View.INVISIBLE);
                indoorcoilprogressbar.setVisibility(View.VISIBLE);
                indoorcoilquantity("add");
                totalquantity("add");
                totalprice("add", indoorcoilprice-disindoorcoilprice,disindoorcoilprice,"Indoorcoil");
                indoorcoilprice("add", indoorcoilprice-disindoorcoilprice);
                totalsaved("add",disindoorcoilprice,"Indoorcoil");
                btn("false");
            }
        });
        subindoorcoil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_indoorcoil1.setVisibility(View.INVISIBLE);
                indoorcoilprogressbar.setVisibility(View.VISIBLE);
                indoorcoilquantity("sub");
                totalquantity("sub");
                totalprice("sub", indoorcoilprice-disindoorcoilprice,disindoorcoilprice,"Indoorcoil");
                indoorcoilprice("sub", indoorcoilprice-disindoorcoilprice);
                totalsaved("sub",disindoorcoilprice,"Indoorcoil");
                btn("false");
            }
        });

        outdoorcondenserbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_outdoorcondenser1.setVisibility(View.INVISIBLE);
                outdoorcondenserprogressbar.setVisibility(View.VISIBLE);
                outdoorcondenserquantity("add");
                totalquantity("add");
                totalprice("add", outdoorcondenserprice,disoutdoorcondenserprice,"Outdoorcondenser");
                outdoorcondenserprice("add", outdoorcondenserprice);
                btn("false");
            }
        });
        addoutdoorcondenser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_outdoorcondenser1.setVisibility(View.INVISIBLE);
                outdoorcondenserprogressbar.setVisibility(View.VISIBLE);
                outdoorcondenserquantity("add");
                totalquantity("add");
                totalprice("add", outdoorcondenserprice-disoutdoorcondenserprice,disoutdoorcondenserprice,"Outdoorcondenser");
                outdoorcondenserprice("add", outdoorcondenserprice-disoutdoorcondenserprice);
                totalsaved("add",disoutdoorcondenserprice,"Outdoorcondenser");

                btn("false");
            }
        });
        suboutdoorcondenser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_outdoorcondenser1.setVisibility(View.INVISIBLE);
                outdoorcondenserprogressbar.setVisibility(View.VISIBLE);
                outdoorcondenserquantity("sub");
                totalquantity("sub");
                totalprice("sub", outdoorcondenserprice-disoutdoorcondenserprice,disoutdoorcondenserprice,"Outdoorcondenser");
                outdoorcondenserprice("sub", outdoorcondenserprice-disoutdoorcondenserprice);
                totalsaved("sub",disoutdoorcondenserprice,"Outdoorcondenser");
                btn("false");
            }
        });

        remotebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_remote1.setVisibility(View.INVISIBLE);
                remoteprogressbar.setVisibility(View.VISIBLE);
                remotequantity("add");
                totalquantity("add");
                totalprice("add", remoteprice,disremoteprice,"Remote");
                remoteprice("add", remoteprice);
                btn("false");
            }
        });
        addremote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_remote1.setVisibility(View.INVISIBLE);
                remoteprogressbar.setVisibility(View.VISIBLE);
                remotequantity("add");
                totalquantity("add");
                totalprice("add", remoteprice-disremoteprice,disremoteprice,"Remote");
                remoteprice("add", remoteprice-disremoteprice);
                totalsaved("add",disremoteprice,"Remote");
                btn("false");
            }
        });
        subremote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_remote1.setVisibility(View.INVISIBLE);
                remoteprogressbar.setVisibility(View.VISIBLE);
                remotequantity("sub");
                totalquantity("sub");
                totalprice("sub", remoteprice-disremoteprice,disremoteprice,"Remote");
                remoteprice("sub", remoteprice-disremoteprice);
                totalsaved("sub",disremoteprice,"Remote");
                btn("false");
            }
        });

        stabilizerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_stabilizer1.setVisibility(View.INVISIBLE);
                stabilizerprogressbar.setVisibility(View.VISIBLE);
                stabilizerquantity("add");
                totalquantity("add");
                totalprice("add", stabilizerprice,disstabilizerprice,"Stabilizer");
                stabilizerprice("add", stabilizerprice);
                btn("false");
            }
        });
        addstabilizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_stabilizer1.setVisibility(View.INVISIBLE);
                stabilizerprogressbar.setVisibility(View.VISIBLE);
                stabilizerquantity("add");
                totalquantity("add");
                totalprice("add", stabilizerprice-disstabilizerprice,disstabilizerprice,"Stabilizer");
                stabilizerprice("add", stabilizerprice-disstabilizerprice);
                totalsaved("add",disstabilizerprice,"Stabilizer");
                btn("false");
            }
        });
        substabilizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_stabilizer1.setVisibility(View.INVISIBLE);
                stabilizerprogressbar.setVisibility(View.VISIBLE);
                stabilizerquantity("sub");
                totalquantity("sub");
                totalprice("sub", stabilizerprice-disstabilizerprice,disstabilizerprice,"Stabilizer");
                stabilizerprice("sub", stabilizerprice-disstabilizerprice);
                totalsaved("sub",disstabilizerprice,"Stabilizer");
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
                    String stringcartprice = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALPRICE").getValue().toString();
                    int cartprice = Integer.parseInt(stringcartprice);

                    int totaldiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child(Categoryname).getValue().toString());
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
    //
//    // ACTIVATE BUTTON
//
    private void btn(String action) {
        if (action == "false") {
            waterleakbtn.setEnabled(false);
            addwaterleak.setEnabled(false);
            subwaterleak.setEnabled(false);

            gasleakbtn.setEnabled(false);
            addgasleak.setEnabled(false);
            subgasleak.setEnabled(false);

            pcboardbtn.setEnabled(false);
            addpcboard.setEnabled(false);
            subpcboard.setEnabled(false);

            indoorcoilbtn.setEnabled(false);
            addindoorcoil.setEnabled(false);
            subindoorcoil.setEnabled(false);

            outdoorcondenserbtn.setEnabled(false);
            addoutdoorcondenser.setEnabled(false);
            suboutdoorcondenser.setEnabled(false);

            remotebtn.setEnabled(false);
            addremote.setEnabled(false);
            subremote.setEnabled(false);

            stabilizerbtn.setEnabled(false);
            addstabilizer.setEnabled(false);
            substabilizer.setEnabled(false);
        }
        else if (action == "true") {
            waterleakbtn.setEnabled(true);
            addwaterleak.setEnabled(true);
            subwaterleak.setEnabled(true);

            gasleakbtn.setEnabled(true);
            addgasleak.setEnabled(true);
            subgasleak.setEnabled(true);

            pcboardbtn.setEnabled(true);
            addpcboard.setEnabled(true);
            subpcboard.setEnabled(true);

            indoorcoilbtn.setEnabled(true);
            addindoorcoil.setEnabled(true);
            subindoorcoil.setEnabled(true);

            outdoorcondenserbtn.setEnabled(true);
            addoutdoorcondenser.setEnabled(true);
            suboutdoorcondenser.setEnabled(true);

            remotebtn.setEnabled(true);
            addremote.setEnabled(true);
            subremote.setEnabled(true);

            stabilizerbtn.setEnabled(true);
            addstabilizer.setEnabled(true);
            substabilizer.setEnabled(true);
        }
    }

//    // waterleak

    private void waterleakquantity(String action) {

        if (action == "add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringwaterleakcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Waterleak").getValue().toString();
                            int waterleakcount = Integer.parseInt(stringwaterleakcount);
                            waterleakcount = waterleakcount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Waterleak", Integer.toString(waterleakcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            cardview_waterleak2.setVisibility(View.VISIBLE);
                            waterleakprogressbar.setVisibility(View.INVISIBLE);
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
                            String stringwaterleakcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Waterleak").getValue().toString();
                            int waterleakcount = Integer.parseInt(stringwaterleakcount);
                            waterleakcount = waterleakcount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Waterleak", Integer.toString(waterleakcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            if (waterleakcount <= 0) {
                                cardview_waterleak2.setVisibility(View.INVISIBLE);
                                cardview_waterleak1.setVisibility(View.VISIBLE);
                                waterleakprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            } else {
                                waterleakprogressbar.setVisibility(View.INVISIBLE);
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
    private void waterleakprice(String action,int price) {

        if (action == "add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringwaterleakcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("WaterleakTOTAL").getValue().toString();
                            int waterleakcount = Integer.parseInt(stringwaterleakcount);
                            waterleakcount = waterleakcount + price;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("WaterleakTOTAL", Integer.toString(waterleakcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
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
                            String stringwaterleakcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("WaterleakTOTAL").getValue().toString();
                            int waterleakcount = Integer.parseInt(stringwaterleakcount);

                            int waterleakdiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Waterleak").getValue().toString());
                            if(waterleakdiscount==1)
                            {
                                waterleakcount= waterleakcount - price - diswaterleakprice;
                            }
                            else
                            {
                                waterleakcount = waterleakcount - price;
                            }


                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("WaterleakTOTAL", Integer.toString(waterleakcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
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

    private void gasleakquantity(String action) {

        if (action == "add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringgasleakcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Gasleak").getValue().toString();
                            int gasleakcount = Integer.parseInt(stringgasleakcount);
                            gasleakcount = gasleakcount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Gasleak", Integer.toString(gasleakcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            cardview_gasleak2.setVisibility(View.VISIBLE);
                            gasleakprogressbar.setVisibility(View.INVISIBLE);
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
                            String stringgasleakcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Gasleak").getValue().toString();
                            int gasleakcount = Integer.parseInt(stringgasleakcount);
                            gasleakcount = gasleakcount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Gasleak", Integer.toString(gasleakcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            if (gasleakcount <= 0) {
                                cardview_gasleak2.setVisibility(View.INVISIBLE);
                                cardview_gasleak1.setVisibility(View.VISIBLE);
                                gasleakprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            } else {
                                gasleakprogressbar.setVisibility(View.INVISIBLE);
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
    private void gasleakprice(String action,int price) {

        if (action == "add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringgasleakcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("GasleakTOTAL").getValue().toString();
                            int gasleakcount = Integer.parseInt(stringgasleakcount);
                            gasleakcount = gasleakcount + price;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("GasleakTOTAL", Integer.toString(gasleakcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
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
                            String stringgasleakcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("GasleakTOTAL").getValue().toString();
                            int gasleakcount = Integer.parseInt(stringgasleakcount);

                            int gasleakdiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Gasleak").getValue().toString());
                            if(gasleakdiscount==1)
                            {
                                gasleakcount = gasleakcount - price - disgasleakprice;
                            }
                            else
                            {
                                gasleakcount = gasleakcount - price;
                            }
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("GasleakTOTAL", Integer.toString(gasleakcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
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

    private void pcboardquantity(String action) {

        if (action == "add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringpcboardcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Pcboard").getValue().toString();
                            int pcboardcount = Integer.parseInt(stringpcboardcount);
                            pcboardcount = pcboardcount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Pcboard", Integer.toString(pcboardcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            cardview_pcboard2.setVisibility(View.VISIBLE);
                            pcboardprogressbar.setVisibility(View.INVISIBLE);
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
                            String stringpcboardcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Pcboard").getValue().toString();
                            int pcboardcount = Integer.parseInt(stringpcboardcount);
                            pcboardcount = pcboardcount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Pcboard", Integer.toString(pcboardcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            if (pcboardcount <= 0) {
                                cardview_pcboard2.setVisibility(View.INVISIBLE);
                                cardview_pcboard1.setVisibility(View.VISIBLE);
                                pcboardprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            } else {
                                pcboardprogressbar.setVisibility(View.INVISIBLE);
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
    private void pcboardprice(String action,int price) {

        if (action == "add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringpcboardcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("PcboardTOTAL").getValue().toString();
                            int pcboardcount = Integer.parseInt(stringpcboardcount);
                            pcboardcount = pcboardcount + price;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("PcboardTOTAL", Integer.toString(pcboardcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
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
                            String stringpcboardcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("PcboardTOTAL").getValue().toString();
                            int pcboardcount = Integer.parseInt(stringpcboardcount);

                            int pcboarddiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Pcboard").getValue().toString());
                            if(pcboarddiscount==1)
                            {
                                pcboardcount = pcboardcount - price - dispcboardprice;
                            }
                            else
                            {
                                pcboardcount = pcboardcount - price;
                            }
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("PcboardTOTAL", Integer.toString(pcboardcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
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

    private void indoorcoilquantity(String action) {

        if (action == "add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringindoorcoilcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Indoorcoil").getValue().toString();
                            int indoorcoilcount = Integer.parseInt(stringindoorcoilcount);
                            indoorcoilcount = indoorcoilcount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Indoorcoil", Integer.toString(indoorcoilcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            cardview_indoorcoil2.setVisibility(View.VISIBLE);
                            indoorcoilprogressbar.setVisibility(View.INVISIBLE);
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
                            String stringindoorcoilcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Indoorcoil").getValue().toString();
                            int indoorcoilcount = Integer.parseInt(stringindoorcoilcount);
                            indoorcoilcount = indoorcoilcount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Indoorcoil", Integer.toString(indoorcoilcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            if (indoorcoilcount <= 0) {
                                cardview_indoorcoil2.setVisibility(View.INVISIBLE);
                                cardview_indoorcoil1.setVisibility(View.VISIBLE);
                                indoorcoilprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            } else {
                                indoorcoilprogressbar.setVisibility(View.INVISIBLE);
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
    private void indoorcoilprice(String action,int price) {

        if (action == "add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringindoorcoilcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("IndoorcoilTOTAL").getValue().toString();
                            int indoorcoilcount = Integer.parseInt(stringindoorcoilcount);
                            indoorcoilcount = indoorcoilcount + price;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("IndoorcoilTOTAL", Integer.toString(indoorcoilcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
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
                            String stringindoorcoilcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("IndoorcoilTOTAL").getValue().toString();
                            int indoorcoilcount = Integer.parseInt(stringindoorcoilcount);
                            int indoorcoildiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Indoorcoil").getValue().toString());
                            if(indoorcoildiscount==1)
                            {
                                indoorcoilcount = indoorcoilcount - price - disindoorcoilprice;
                            }
                            else
                            {
                                indoorcoilcount = indoorcoilcount - price;
                            }
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("IndoorcoilTOTAL", Integer.toString(indoorcoilcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
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

    private void outdoorcondenserquantity(String action) {

        if (action == "add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringoutdoorcondensercount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Outdoorcondenser").getValue().toString();
                            int outdoorcondensercount = Integer.parseInt(stringoutdoorcondensercount);
                            outdoorcondensercount = outdoorcondensercount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Outdoorcondenser", Integer.toString(outdoorcondensercount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            cardview_outdoorcondenser2.setVisibility(View.VISIBLE);
                            outdoorcondenserprogressbar.setVisibility(View.INVISIBLE);
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
                            String stringoutdoorcondensercount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Outdoorcondenser").getValue().toString();
                            int outdoorcondensercount = Integer.parseInt(stringoutdoorcondensercount);
                            outdoorcondensercount = outdoorcondensercount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Outdoorcondenser", Integer.toString(outdoorcondensercount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            if (outdoorcondensercount <= 0) {
                                cardview_outdoorcondenser2.setVisibility(View.INVISIBLE);
                                cardview_outdoorcondenser1.setVisibility(View.VISIBLE);
                                outdoorcondenserprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            } else {
                                outdoorcondenserprogressbar.setVisibility(View.INVISIBLE);
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
    private void outdoorcondenserprice(String action,int price) {

        if (action == "add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringoutdoorcondensercount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("OutdoorcondenserTOTAL").getValue().toString();
                            int outdoorcondensercount = Integer.parseInt(stringoutdoorcondensercount);
                            outdoorcondensercount = outdoorcondensercount + price;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("OutdoorcondenserTOTAL", Integer.toString(outdoorcondensercount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
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
                            String stringoutdoorcondensercount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("OutdoorcondenserTOTAL").getValue().toString();
                            int outdoorcondensercount = Integer.parseInt(stringoutdoorcondensercount);
                            int outdoorcondenserdiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Outdoorcondenser").getValue().toString());
                            if(outdoorcondenserdiscount==1)
                            {
                                outdoorcondensercount = outdoorcondensercount - price - disoutdoorcondenserprice;
                            }
                            else
                            {
                                outdoorcondensercount = outdoorcondensercount - price;
                            }
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("OutdoorcondenserTOTAL", Integer.toString(outdoorcondensercount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
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

    private void remotequantity(String action) {

        if (action == "add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringremotecount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Remote").getValue().toString();
                            int remotecount = Integer.parseInt(stringremotecount);
                            remotecount = remotecount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Remote", Integer.toString(remotecount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            cardview_remote2.setVisibility(View.VISIBLE);
                            remoteprogressbar.setVisibility(View.INVISIBLE);
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
                            String stringremotecount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Remote").getValue().toString();
                            int remotecount = Integer.parseInt(stringremotecount);
                            remotecount = remotecount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Remote", Integer.toString(remotecount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            if (remotecount <= 0) {
                                cardview_remote2.setVisibility(View.INVISIBLE);
                                cardview_remote1.setVisibility(View.VISIBLE);
                                remoteprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            } else {
                                remoteprogressbar.setVisibility(View.INVISIBLE);
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
    private void remoteprice(String action,int price) {

        if (action == "add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringremotecount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("RemoteTOTAL").getValue().toString();
                            int remotecount = Integer.parseInt(stringremotecount);
                            remotecount = remotecount + price;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("RemoteTOTAL", Integer.toString(remotecount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
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
                            String stringremotecount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("RemoteTOTAL").getValue().toString();
                            int remotecount = Integer.parseInt(stringremotecount);
                            int remotediscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Remote").getValue().toString());
                            if(remotediscount==1)
                            {
                                remotecount = remotecount - price - disremoteprice;
                            }
                            else
                            {
                                remotecount = remotecount - price;
                            }
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("RemoteTOTAL", Integer.toString(remotecount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
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

    private void stabilizerquantity(String action) {

        if (action == "add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringstabilizercount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Stabilizer").getValue().toString();
                            int stabilizercount = Integer.parseInt(stringstabilizercount);
                            stabilizercount = stabilizercount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Stabilizer", Integer.toString(stabilizercount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            cardview_stabilizer2.setVisibility(View.VISIBLE);
                            stabilizerprogressbar.setVisibility(View.INVISIBLE);
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
                            String stringstabilizercount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Stabilizer").getValue().toString();
                            int stabilizercount = Integer.parseInt(stringstabilizercount);
                            stabilizercount = stabilizercount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Stabilizer", Integer.toString(stabilizercount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            if (stabilizercount <= 0) {
                                cardview_stabilizer2.setVisibility(View.INVISIBLE);
                                cardview_stabilizer1.setVisibility(View.VISIBLE);
                                stabilizerprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            } else {
                                stabilizerprogressbar.setVisibility(View.INVISIBLE);
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
    private void stabilizerprice(String action,int price) {

        if (action == "add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringstabilizercount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("StabilizerTOTAL").getValue().toString();
                            int stabilizercount = Integer.parseInt(stringstabilizercount);
                            stabilizercount = stabilizercount + price;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("StabilizerTOTAL", Integer.toString(stabilizercount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
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
                            String stringstabilizercount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("StabilizerTOTAL").getValue().toString();
                            int stabilizercount = Integer.parseInt(stringstabilizercount);
                            int stabilizerdiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Stabilizer").getValue().toString());
                            if(stabilizerdiscount==1)
                            {
                                stabilizercount = stabilizercount - price - disstabilizerprice;
                            }
                            else
                            {
                                stabilizercount = stabilizercount - price;
                            }
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("StabilizerTOTAL", Integer.toString(stabilizercount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
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
                    int totaldiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child(categoryname).getValue().toString());
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