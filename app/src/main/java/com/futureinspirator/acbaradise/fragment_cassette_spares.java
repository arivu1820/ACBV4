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

public class fragment_cassette_spares extends Fragment {

    private String Maincategory = "Spares", Category = "CassetteAC";
    private CardView cardview_capacitor1, cardview_capacitor2, cardview_blower1, cardview_blower2,
            cardview_indoormotor1, cardview_indoormotor2, cardview_outdoorfan1, cardView_outdoorfan2,
            cardview_outdoormotor1, cardview_outdoormotor2, cardview_swingmotor1, cardview_swingmotor2,
            cardView_indoorcoil1,cardView_indoorcoil2, cardView_outdoorcondenser1, cardView_outdoorcondenser2,
            cardView_swingflap1, cardView_swingflap2, cardView_remote1, cardView_remote2;
    private Button capacitorbtn, blowerbtn, indoormotorbtn, outdoorfanbtn, outdoormotorbtn, swingmotorbtn, indoorcoilbtn, outdoorcondenserbtn, swingflapbtn, remotebtn;
    private Button addcapacitor, addblower, addindoormotor, addoutdoorfan, addoutdoormotor, addswingmotor, addindoorcoil, addoutdoorcondenser, addswingflap, addremote;
    private Button subcapacitor, subblower, subindoormotor, suboutdoorfan, suboutdoormotor, subswingmotor, subindoorcoil, suboutdoorcondenser, subswingflap, subremote;
    private TextView txtcapacitor, txtblower, txtindoormotor, txtoutdoorfan, txtoutdoormotor, txtswingmotor, txtindoorcoil, txtoutdoorcondenser, txtswingflap, txtremote;
    private int capacitorprice = 0, blowerprice = 0, indoormotorprice = 0, outdoorfanprice = 0, outdoormotorprice = 0, swingmotorprice = 0,
            indoorcoilprice = 0, outdoorcondenserprice = 0, swingflapprice = 0, remoteprice = 0;
    private CardView capacitorprogressbar, blowerprogressbar, indoormotorprogressbar, outdoorfanprogressbar, outdoormotorprogressbar, swingmotorprogressbar,
            indoorcoilprogressbar, outdoorcondenserprogressbar, swingflapprogressbar, remoteprogressbar;

    private int discapacitorprice = 0, disblowerprice = 0, disindoormotorprice = 0, disoutdoorfanprice = 0, disoutdoormotorprice = 0, disswingmotorprice = 0,
            disindoorcoilprice = 0, disoutdoorcondenserprice = 0, disswingflapprice = 0, disremoteprice = 0;
    private DatabaseReference discount = FirebaseDatabase.getInstance().getReference().child("Products").child(Maincategory).child(Category).child("Discount");

    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference cart = FirebaseDatabase.getInstance().getReference();
    private TextView txt_originalprice_capacitor,txt_originalprice_blower,txt_originalprice_outdoorfan,txt_originalprice_indoormotor,txt_originalprice_outdoormotor,txt_originalprice_indoorcoil,txt_originalprice_outdoorcondenser ,txt_originalprice_swingflap ,txt_originalprice_remote,txt_originalprice_swingmotor;
    private TextView txt_discount_price_capacitor,txt_discount_price_blower,txt_discount_price_outdoorfan,txt_discount_price_indoormotor,txt_discount_price_outdoormotor,txt_discount_price_swingmotor,txt_discount_price_indoorcoil,txt_discount_price_outdoorcondenser,txt_discount_price_swingflap,txt_discount_price_remote;

    private int disstringcapacitorprice,disstringblowerprice,disstringindoormotorprice,disstringoutdoorfanprice,disstringoutdoormotorprice,disstringswingmotorprice,disstringindoorcoilprice,disstringoutdoorcondenserprice,disstringswingflapprice,distringremoteprice;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_cassette_spares, container, false);


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String stringcapacitorprice = snapshot.child("Products").child(Maincategory).child(Category).child("Price").child("Capacitor").getValue().toString();
                String stringblowerprice = snapshot.child("Products").child(Maincategory).child(Category).child("Price").child("Blower").getValue().toString();
                String stringindoormotorprice = snapshot.child("Products").child(Maincategory).child(Category).child("Price").child("Indoormotor").getValue().toString();
                String stringoutdoorfanprice = snapshot.child("Products").child(Maincategory).child(Category).child("Price").child("Outdoorfan").getValue().toString();
                String stringoutdoormotorprice = snapshot.child("Products").child(Maincategory).child(Category).child("Price").child("Outdoormotor").getValue().toString();
                String stringswingmotorprice = snapshot.child("Products").child(Maincategory).child(Category).child("Price").child("Swingmotor").getValue().toString();
                String stringindoorcoilprice = snapshot.child("Products").child(Maincategory).child(Category).child("Price").child("Indoorcoil").getValue().toString();
                String stringoutdoorcondenserprice = snapshot.child("Products").child(Maincategory).child(Category).child("Price").child("Outdoorcondenser").getValue().toString();
                String stringswingflapprice = snapshot.child("Products").child(Maincategory).child(Category).child("Price").child("Swingflap").getValue().toString();
                String stringremoteprice = snapshot.child("Products").child(Maincategory).child(Category).child("Price").child("Remote").getValue().toString();



                txt_originalprice_capacitor.setText(""+stringcapacitorprice);
                txt_originalprice_blower.setText(""+stringblowerprice);
                txt_originalprice_indoormotor.setText(""+stringindoormotorprice);
                txt_originalprice_outdoorfan.setText(""+stringoutdoorfanprice);
                txt_originalprice_outdoormotor.setText(""+stringoutdoormotorprice);
                txt_originalprice_swingmotor.setText(""+stringswingmotorprice);
                txt_originalprice_indoorcoil.setText(""+stringindoorcoilprice);
                txt_originalprice_outdoorcondenser.setText(""+stringoutdoorcondenserprice);
                txt_originalprice_swingflap.setText(""+stringswingflapprice);
                txt_originalprice_remote.setText(""+stringremoteprice);



                capacitorprice = Integer.parseInt(stringcapacitorprice);
                blowerprice = Integer.parseInt(stringblowerprice);
                indoormotorprice = Integer.parseInt(stringindoormotorprice);
                outdoorfanprice = Integer.parseInt(stringoutdoorfanprice);
                outdoormotorprice = Integer.parseInt(stringoutdoormotorprice);
                swingmotorprice = Integer.parseInt(stringswingmotorprice);
                indoorcoilprice = Integer.parseInt(stringindoorcoilprice);
                outdoorcondenserprice = Integer.parseInt(stringoutdoorcondenserprice);
                swingflapprice = Integer.parseInt(stringswingflapprice);
                remoteprice = Integer.parseInt(stringremoteprice);



                disstringcapacitorprice = capacitorprice  + 100;
                disstringblowerprice =  blowerprice + 100;
                disstringindoormotorprice = indoormotorprice + 100;
                disstringoutdoorfanprice  =  outdoorfanprice + 100;
                disstringoutdoormotorprice=  outdoormotorprice + 100;
                disstringswingmotorprice   =  swingmotorprice + 100;
                disstringindoorcoilprice  = indoorcoilprice + 100;
                disstringoutdoorcondenserprice = outdoorcondenserprice + 100;
                disstringswingflapprice  = swingflapprice + 100;
                distringremoteprice     =      remoteprice + 100;


                txt_discount_price_capacitor.setText(""+disstringcapacitorprice);
                txt_discount_price_capacitor.setPaintFlags(txt_discount_price_capacitor.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


                txt_discount_price_blower.setText(""+disstringblowerprice);
                txt_discount_price_blower.setPaintFlags(txt_discount_price_blower.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                txt_discount_price_outdoorfan.setText(""+disstringindoormotorprice);
                txt_discount_price_outdoorfan.setPaintFlags(txt_discount_price_outdoorfan.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                txt_discount_price_indoormotor.setText(""+disstringoutdoorfanprice);
                txt_discount_price_indoormotor.setPaintFlags(txt_discount_price_indoormotor.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                txt_discount_price_outdoormotor.setText(""+disstringoutdoormotorprice);
                txt_discount_price_outdoormotor.setPaintFlags(txt_discount_price_outdoormotor.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                txt_discount_price_swingmotor.setText(""+disstringswingmotorprice);
                txt_discount_price_swingmotor.setPaintFlags(txt_discount_price_swingmotor.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                txt_discount_price_indoorcoil.setText(""+disstringindoorcoilprice);
                txt_discount_price_indoorcoil.setPaintFlags(txt_discount_price_indoorcoil.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                txt_discount_price_outdoorcondenser.setText(""+disstringoutdoorcondenserprice);
                txt_discount_price_outdoorcondenser.setPaintFlags(txt_discount_price_outdoorcondenser.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                txt_discount_price_swingflap.setText(""+disstringswingflapprice);
                txt_discount_price_swingflap.setPaintFlags(txt_discount_price_swingflap.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                txt_discount_price_remote.setText(""+distringremoteprice);
                txt_discount_price_remote.setPaintFlags(txt_discount_price_remote.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);






            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String capacitordatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Capacitor").getValue().toString();
                txtcapacitor.setText("" + capacitordatabaseQvalue);
                int intcapacitordatabaseQvalue = Integer.parseInt(capacitordatabaseQvalue);
                if (intcapacitordatabaseQvalue <= 0) {
                    cardview_capacitor2.setVisibility(View.INVISIBLE);
                } else {
                    cardview_capacitor2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String blowerdatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Blower").getValue().toString();
                txtblower.setText("" + blowerdatabaseQvalue);
                int intblowerdatabaseQvalue = Integer.parseInt(blowerdatabaseQvalue);
                if (intblowerdatabaseQvalue <= 0) {
                    cardview_blower2.setVisibility(View.INVISIBLE);
                } else {
                    cardview_blower2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String outdoorfandatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Outdoorfan").getValue().toString();
                txtoutdoorfan.setText("" + outdoorfandatabaseQvalue);
                int intoutdoorfandatabaseQvalue = Integer.parseInt(outdoorfandatabaseQvalue);
                if (intoutdoorfandatabaseQvalue <= 0) {
                    cardView_outdoorfan2.setVisibility(View.INVISIBLE);
                } else {
                    cardView_outdoorfan2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String indoormotordatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Indoormotor").getValue().toString();
                txtindoormotor.setText("" + indoormotordatabaseQvalue);
                int intindoormotordatabaseQvalue = Integer.parseInt(indoormotordatabaseQvalue);
                if (intindoormotordatabaseQvalue <= 0) {
                    cardview_indoormotor2.setVisibility(View.INVISIBLE);
                } else {
                    cardview_indoormotor2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String outdoormotordatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Outdoormotor").getValue().toString();
                txtoutdoormotor.setText("" + outdoormotordatabaseQvalue);
                int intoutdoormotordatabaseQvalue = Integer.parseInt(outdoormotordatabaseQvalue);
                if (intoutdoormotordatabaseQvalue <= 0) {
                    cardview_outdoormotor2.setVisibility(View.INVISIBLE);
                } else {
                    cardview_outdoormotor2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String swingmotordatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Swingmotor").getValue().toString();
                txtswingmotor.setText("" + swingmotordatabaseQvalue);
                int intswingmotordatabaseQvalue = Integer.parseInt(swingmotordatabaseQvalue);
                if (intswingmotordatabaseQvalue <= 0) {
                    cardview_swingmotor2.setVisibility(View.INVISIBLE);
                } else {
                    cardview_swingmotor2.setVisibility(View.VISIBLE);
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
                    cardView_indoorcoil2.setVisibility(View.INVISIBLE);
                } else {
                    cardView_indoorcoil2.setVisibility(View.VISIBLE);
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
                    cardView_outdoorcondenser2.setVisibility(View.INVISIBLE);
                } else {
                    cardView_outdoorcondenser2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String swingflapdatabaseQvalue = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Swingflap").getValue().toString();
                txtswingflap.setText("" + swingflapdatabaseQvalue);
                int intswingflapdatabaseQvalue = Integer.parseInt(swingflapdatabaseQvalue);
                if (intswingflapdatabaseQvalue <= 0) {
                    cardView_swingflap2.setVisibility(View.INVISIBLE);
                } else {
                    cardView_swingflap2.setVisibility(View.VISIBLE);
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
                    cardView_remote2.setVisibility(View.INVISIBLE);
                } else {
                    cardView_remote2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        discount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String disstringcapacitorprice = snapshot.child("Capacitor").getValue().toString();
                String disstringblowerprice = snapshot.child("Blower").getValue().toString();
                String disstringindoormotorprice = snapshot.child("Indoormotor").getValue().toString();
                String disstringoutdoorfanprice = snapshot.child("Outdoorfan").getValue().toString();
                String disstringoutdoormotorprice = snapshot.child("Outdoormotor").getValue().toString();
                String disstringswingmotorprice = snapshot.child("Swingmotor").getValue().toString();
                String disstringindoorcoilprice = snapshot.child("Indoorcoil").getValue().toString();
                String disstringoutdoorcondenserprice = snapshot.child("Outdoorcondenser").getValue().toString();
                String disstringswingflapprice = snapshot.child("Swingflap").getValue().toString();
                String disstringremoteprice = snapshot.child("Remote").getValue().toString();



                discapacitorprice = Integer.parseInt(disstringcapacitorprice);
                disblowerprice = Integer.parseInt(disstringblowerprice);
                disindoormotorprice = Integer.parseInt(disstringindoormotorprice);
                disoutdoorfanprice = Integer.parseInt(disstringoutdoorfanprice);
                disoutdoormotorprice = Integer.parseInt(disstringoutdoormotorprice);
                disswingmotorprice = Integer.parseInt(disstringswingmotorprice);
                disindoorcoilprice = Integer.parseInt(disstringindoorcoilprice);
                disoutdoorcondenserprice = Integer.parseInt(disstringoutdoorcondenserprice);
                disswingflapprice = Integer.parseInt(disstringswingflapprice);
                disremoteprice = Integer.parseInt(disstringremoteprice);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        txt_discount_price_capacitor=v.findViewById(R.id.txt_discount_price_capacitor);
        txt_discount_price_blower=v.findViewById(R.id.txt_discount_price_blower);
        txt_discount_price_outdoorfan=v.findViewById(R.id.txt_discount_price_outdoorfan);
        txt_discount_price_indoormotor=v.findViewById(R.id.txt_discount_price_indoormotor);
        txt_discount_price_outdoormotor=v.findViewById(R.id.txt_discount_price_outdoormotor);
        txt_discount_price_swingmotor=v.findViewById(R.id.txt_discount_price_swingmotor);
        txt_discount_price_indoorcoil=v.findViewById(R.id.txt_discount_price_indoorcoil);
        txt_discount_price_outdoorcondenser=v.findViewById(R.id.txt_discount_price_outdoorcondenser);
        txt_discount_price_swingflap=v.findViewById(R.id.txt_discount_price_swingflap);
        txt_discount_price_remote=v.findViewById(R.id.txt_discount_price_remote);


        txt_originalprice_capacitor =v.findViewById(R.id.txt_originalprice_capacitor);
        txt_originalprice_blower=v.findViewById(R.id.txt_originalprice_blower);
        txt_originalprice_outdoorfan=v.findViewById(R.id.txt_originalprice_outdoorfan);
        txt_originalprice_indoormotor=v.findViewById(R.id.txt_originalprice_indoormotor);
        txt_originalprice_outdoormotor=v.findViewById(R.id.txt_originalprice_outdoormotor);
        txt_originalprice_indoorcoil=v.findViewById(R.id.txt_originalprice_indoorcoil);
        txt_originalprice_outdoorcondenser=v.findViewById(R.id.txt_originalprice_outdoorcondenser);
        txt_originalprice_swingflap=v.findViewById(R.id.txt_originalprice_swingflap);
        txt_originalprice_remote=v.findViewById(R.id.txt_originalprice_remote);
        txt_originalprice_swingmotor=v.findViewById(R.id.txt_originalprice_swingmotor);


        cardview_capacitor1 = v.findViewById(R.id.cardview_capacitor1);
        cardview_capacitor2 = v.findViewById(R.id.cardview_capacitor2);
        cardview_blower1 = v.findViewById(R.id.cardview_blower1);
        cardview_blower2 = v.findViewById(R.id.cardview_blower2);
        cardview_outdoorfan1 = v.findViewById(R.id.cardview_outdoorfan1);
        cardView_outdoorfan2 = v.findViewById(R.id.cardview_outdoorfan2);
        cardview_indoormotor1 = v.findViewById(R.id.cardview_indoormotor1);
        cardview_indoormotor2 = v.findViewById(R.id.cardview_indoormotor2);
        cardview_outdoormotor1 = v.findViewById(R.id.cardview_outdoormotor1);
        cardview_outdoormotor2 = v.findViewById(R.id.cardview_outdoormotor2);
        cardview_swingmotor1 = v.findViewById(R.id.cardview_swingmotor1);
        cardview_swingmotor2 = v.findViewById(R.id.cardview_swingmotor2);
        cardView_indoorcoil1 = v.findViewById(R.id.cardview_indoorcoil1);
        cardView_indoorcoil2 = v.findViewById(R.id.cardview_indoorcoil2);
        cardView_outdoorcondenser1 = v.findViewById(R.id.cardview_outdoorcondenser1);
        cardView_outdoorcondenser2 = v.findViewById(R.id.cardview_outdoorcondenser2);
        cardView_swingflap1 = v.findViewById(R.id.cardview_swingflap1);
        cardView_swingflap2 = v.findViewById(R.id.cardview_swingflap2);
        cardView_remote1 = v.findViewById(R.id.cardview_remote1);
        cardView_remote2 = v.findViewById(R.id.cardview_remote2);



        capacitorbtn = v.findViewById(R.id.capacitorbtn);
        blowerbtn = v.findViewById(R.id.blowerbtn);
        outdoorfanbtn = v.findViewById(R.id.outdoorfanbtn);
        indoormotorbtn = v.findViewById(R.id.indoormotorbtn);
        outdoormotorbtn = v.findViewById(R.id.outdoormotorbtn);
        swingmotorbtn = v.findViewById(R.id.swingmotorbtn);
        indoorcoilbtn = v.findViewById(R.id.indoorcoilbtn);
        outdoorcondenserbtn = v.findViewById(R.id.outdoorcondenserbtn);
        swingflapbtn = v.findViewById(R.id.swingflapbtn);
        remotebtn = v.findViewById(R.id.remotebtn);


        addcapacitor = v.findViewById(R.id.addcapacitor);
        addblower = v.findViewById(R.id.addblower);
        addoutdoorfan = v.findViewById(R.id.addoutdoorfan);
        addindoormotor = v.findViewById(R.id.addindoormotor);
        addoutdoormotor = v.findViewById(R.id.addoutdoormotor);
        addswingmotor = v.findViewById(R.id.addswingmotor);
        addindoorcoil = v.findViewById(R.id.addindoorcoil);
        addoutdoorcondenser = v.findViewById(R.id.addoutdoorcondenser);
        addswingflap = v.findViewById(R.id.addswingflap);
        addremote = v.findViewById(R.id.addremote);



        subcapacitor = v.findViewById(R.id.subcapacitor);
        subblower = v.findViewById(R.id.subblower);
        suboutdoorfan = v.findViewById(R.id.suboutdoorfan);
        subindoormotor = v.findViewById(R.id.subindoormotor);
        suboutdoormotor = v.findViewById(R.id.suboutdoormotor);
        subswingmotor = v.findViewById(R.id.subswingmotor);
        subindoorcoil = v.findViewById(R.id.subindoorcoil);
        suboutdoorcondenser = v.findViewById(R.id.suboutdoorcondenser);
        subswingflap = v.findViewById(R.id.subswingflap);
        subremote = v.findViewById(R.id.subremote);



        txtcapacitor = v.findViewById(R.id.txtcapacitor);
        txtblower = v.findViewById(R.id.txtblower);
        txtoutdoorfan = v.findViewById(R.id.txtoutdoorfan);
        txtindoormotor = v.findViewById(R.id.txtindoormotor);
        txtoutdoormotor = v.findViewById(R.id.txtoutdoormotor);
        txtswingmotor = v.findViewById(R.id.txtswingmotor);
        txtindoorcoil = v.findViewById(R.id.txtindoorcoil);
        txtoutdoorcondenser = v.findViewById(R.id.txtoutdoorcondenser);
        txtswingflap = v.findViewById(R.id.txtswingflap);
        txtremote = v.findViewById(R.id.txtremote);



        capacitorprogressbar = v.findViewById(R.id.capacitorprogressbar);
        blowerprogressbar = v.findViewById(R.id.blowerprogressbar);
        outdoorfanprogressbar = v.findViewById(R.id.outdoorfanprogressbar);
        indoormotorprogressbar = v.findViewById(R.id.indoormotorprogressbar);
        outdoormotorprogressbar = v.findViewById(R.id.outdoormotorprogressbar);
        swingmotorprogressbar = v.findViewById(R.id.swingmotorprogressbar);
        indoorcoilprogressbar = v.findViewById(R.id.indoorcoilprogressbar);
        outdoorcondenserprogressbar = v.findViewById(R.id.outdoorcondenserprogressbar);
        swingflapprogressbar = v.findViewById(R.id.swingflapprogressbar);
        remoteprogressbar = v.findViewById(R.id.remoteprogressbar);



        capacitorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_capacitor1.setVisibility(View.INVISIBLE);
                capacitorprogressbar.setVisibility(View.VISIBLE);
                capacitorquantity("add");
                totalquantity("add");
                totalprice("add", capacitorprice,discapacitorprice,"Capacitor");
                capacitorprice("add",capacitorprice);

                btn("false");
            }
        });
        addcapacitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_capacitor1.setVisibility(View.INVISIBLE);
                capacitorprogressbar.setVisibility(View.VISIBLE);
                capacitorquantity("add");
                totalquantity("add");
                totalprice("add", capacitorprice-discapacitorprice,discapacitorprice,"Capacitor");
                capacitorprice("add",capacitorprice-discapacitorprice);
                totalsaved("add",discapacitorprice,"Capacitor");
                btn("false");
            }
        });
        subcapacitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_capacitor1.setVisibility(View.INVISIBLE);
                capacitorprogressbar.setVisibility(View.VISIBLE);
                capacitorquantity("sub");
                totalquantity("sub");
                totalprice("sub", capacitorprice-discapacitorprice,discapacitorprice,"Capacitor");
                capacitorprice("sub",capacitorprice-discapacitorprice);
                totalsaved("sub",discapacitorprice,"Capacitor");
                btn("false");
            }
        });

        blowerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_blower1.setVisibility(View.INVISIBLE);
                blowerprogressbar.setVisibility(View.VISIBLE);
                blowerquantity("add");
                totalquantity("add");
                totalprice("add", blowerprice,disblowerprice,"Blower");
                blowerprice("add",blowerprice);
                btn("false");
            }
        });
        addblower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_blower1.setVisibility(View.INVISIBLE);
                blowerprogressbar.setVisibility(View.VISIBLE);
                blowerquantity("add");
                totalquantity("add");
                totalprice("add", blowerprice-disblowerprice,disblowerprice,"Blower");
                blowerprice("add",blowerprice-disblowerprice);
                totalsaved("add",disblowerprice,"Blower");
                btn("false");
            }
        });
        subblower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_blower1.setVisibility(View.INVISIBLE);
                blowerprogressbar.setVisibility(View.VISIBLE);
                blowerquantity("sub");
                totalquantity("sub");
                totalprice("sub", blowerprice-disblowerprice,disblowerprice,"Blower");
                blowerprice("sub",blowerprice-disblowerprice);
                totalsaved("sub",disblowerprice,"Blower");
                btn("false");
            }
        });


        indoormotorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_indoormotor1.setVisibility(View.INVISIBLE);
                indoormotorprogressbar.setVisibility(View.VISIBLE);
                indoormotorquantity("add");
                totalquantity("add");
                totalprice("add", indoormotorprice,disindoormotorprice,"Indoormotor");
                indoormotorprice("add",indoormotorprice);

                btn("false");
            }
        });
        addindoormotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_indoormotor1.setVisibility(View.INVISIBLE);
                indoormotorprogressbar.setVisibility(View.VISIBLE);
                indoormotorquantity("add");
                totalquantity("add");
                totalprice("add", indoormotorprice-disindoormotorprice,disindoormotorprice,"Indoormotor");
                indoormotorprice("add",indoormotorprice-disindoormotorprice);
                totalsaved("add",disindoormotorprice,"Indoormotor");
                btn("false");
            }
        });
        subindoormotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_indoormotor1.setVisibility(View.INVISIBLE);
                indoormotorprogressbar.setVisibility(View.VISIBLE);
                indoormotorquantity("sub");
                totalquantity("sub");
                totalprice("sub", indoormotorprice-disindoormotorprice,disindoormotorprice,"Indoormotor");
                indoormotorprice("sub",indoormotorprice-disindoormotorprice);
                totalsaved("sub",disindoormotorprice,"Indoormotor");
                btn("false");
            }
        });

        outdoorfanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_outdoorfan1.setVisibility(View.INVISIBLE);
                outdoorfanprogressbar.setVisibility(View.VISIBLE);
                outdoorfanquantity("add");
                totalquantity("add");
                totalprice("add", outdoorfanprice,disoutdoorfanprice,"Outdoorfan");
                outdoorfanprice("add",outdoorfanprice);

                btn("false");
            }
        });
        addoutdoorfan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_outdoorfan1.setVisibility(View.INVISIBLE);
                outdoorfanprogressbar.setVisibility(View.VISIBLE);
                outdoorfanquantity("add");
                totalquantity("add");
                totalprice("add", outdoorfanprice-disoutdoorfanprice,disoutdoorfanprice,"Outdoorfan");
                outdoorfanprice("add",outdoorfanprice-disoutdoorfanprice);
                totalsaved("add",disoutdoorfanprice,"Outdoorfan");
                btn("false");
            }
        });
        suboutdoorfan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_outdoorfan1.setVisibility(View.INVISIBLE);
                outdoorfanprogressbar.setVisibility(View.VISIBLE);
                outdoorfanquantity("sub");
                totalquantity("sub");
                totalprice("sub", outdoorfanprice-disoutdoorfanprice,disoutdoorfanprice,"Outdoorfan");
                outdoorfanprice("sub",outdoorfanprice-disoutdoorfanprice);
                totalsaved("sub",disoutdoorfanprice,"Outdoorfan");
                btn("false");
            }
        });

        outdoormotorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_outdoormotor1.setVisibility(View.INVISIBLE);
                outdoormotorprogressbar.setVisibility(View.VISIBLE);
                outdoormotorquantity("add");
                totalquantity("add");
                totalprice("add", outdoormotorprice,disoutdoormotorprice,"Outdoormotor");
                outdoormotorprice("add",outdoormotorprice);

                btn("false");
            }
        });
        addoutdoormotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_outdoormotor1.setVisibility(View.INVISIBLE);
                outdoormotorprogressbar.setVisibility(View.VISIBLE);
                outdoormotorquantity("add");
                totalquantity("add");
                totalprice("add", outdoormotorprice-disoutdoormotorprice,disoutdoormotorprice,"Outdoormotor");
                outdoormotorprice("add",outdoormotorprice-disoutdoormotorprice);
                totalsaved("add",disoutdoorfanprice,"Outdoormotor");
                btn("false");
            }
        });
        suboutdoormotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_outdoormotor1.setVisibility(View.INVISIBLE);
                outdoormotorprogressbar.setVisibility(View.VISIBLE);
                outdoormotorquantity("sub");
                totalquantity("sub");
                totalprice("sub", outdoormotorprice-disoutdoormotorprice,disoutdoormotorprice,"Outdoormotor");
                outdoormotorprice("sub",outdoormotorprice-disoutdoormotorprice);
                totalsaved("sub",disoutdoorfanprice,"Outdoormotor");
                btn("false");
            }
        });

        swingmotorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_swingmotor1.setVisibility(View.INVISIBLE);
                swingmotorprogressbar.setVisibility(View.VISIBLE);
                swingmotorquantity("add");
                totalquantity("add");
                totalprice("add", swingmotorprice,disswingmotorprice,"Swingmotor");
                swingmotorprice("add",swingmotorprice);

                btn("false");
            }
        });
        addswingmotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_swingmotor1.setVisibility(View.INVISIBLE);
                swingmotorprogressbar.setVisibility(View.VISIBLE);
                swingmotorquantity("add");
                totalquantity("add");
                totalprice("add", swingmotorprice-disswingmotorprice,disswingmotorprice,"Swingmotor");
                swingmotorprice("add",swingmotorprice-disswingmotorprice);
                totalsaved("add",disswingmotorprice,"Swingmotor");
                btn("false");
            }
        });
        subswingmotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardview_swingmotor1.setVisibility(View.INVISIBLE);
                swingmotorprogressbar.setVisibility(View.VISIBLE);
                swingmotorquantity("sub");
                totalquantity("sub");
                totalprice("sub", swingmotorprice-disswingmotorprice,disswingmotorprice,"Swingmotor");
                swingmotorprice("sub",swingmotorprice-disswingmotorprice);
                totalsaved("sub",disswingmotorprice,"Swingmotor");
                btn("false");
            }
        });

        indoorcoilbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardView_indoorcoil1.setVisibility(View.INVISIBLE);
                indoorcoilprogressbar.setVisibility(View.VISIBLE);
                indoorcoilquantity("add");
                totalquantity("add");
                totalprice("add", indoorcoilprice,disindoorcoilprice,"Indoorcoil");
                indoorcoilprice("add",indoorcoilprice);

                btn("false");
            }
        });
        addindoorcoil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardView_indoorcoil1.setVisibility(View.INVISIBLE);
                indoorcoilprogressbar.setVisibility(View.VISIBLE);
                indoorcoilquantity("add");
                totalquantity("add");
                totalprice("add", indoorcoilprice-disindoorcoilprice,disindoorcoilprice,"Indoorcoil");
                indoorcoilprice("add",indoorcoilprice-disindoorcoilprice);
                totalsaved("add",disindoorcoilprice,"Indoorcoil");
                btn("false");
            }
        });
        subindoorcoil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardView_indoorcoil1.setVisibility(View.INVISIBLE);
                indoorcoilprogressbar.setVisibility(View.VISIBLE);
                indoorcoilquantity("sub");
                totalquantity("sub");
                totalprice("sub", indoorcoilprice-disindoorcoilprice,disindoorcoilprice,"Indoorcoil");
                indoorcoilprice("sub",indoorcoilprice-disindoorcoilprice);
                totalsaved("sub",disindoorcoilprice,"Indoorcoil");
                btn("false");
            }
        });

        outdoorcondenserbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardView_outdoorcondenser1.setVisibility(View.INVISIBLE);
                outdoorcondenserprogressbar.setVisibility(View.VISIBLE);
                outdoorcondenserquantity("add");
                totalquantity("add");
                totalprice("add", outdoorcondenserprice,disoutdoorcondenserprice,"Outdoorcondenser");
                outdoorcondenserprice("add",outdoorcondenserprice);

                btn("false");
            }
        });
        addoutdoorcondenser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardView_outdoorcondenser1.setVisibility(View.INVISIBLE);
                outdoorcondenserprogressbar.setVisibility(View.VISIBLE);
                outdoorcondenserquantity("add");
                totalquantity("add");
                totalprice("add", outdoorcondenserprice-disoutdoorcondenserprice,disoutdoorcondenserprice,"Outdoorcondenser");
                outdoorcondenserprice("add",outdoorcondenserprice-disoutdoorcondenserprice);
                totalsaved("add",disoutdoorcondenserprice,"Outdoorcondenser");
                btn("false");
            }
        });
        suboutdoorcondenser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardView_outdoorcondenser1.setVisibility(View.INVISIBLE);
                outdoorcondenserprogressbar.setVisibility(View.VISIBLE);
                outdoorcondenserquantity("sub");
                totalquantity("sub");
                totalprice("sub", outdoorcondenserprice-disoutdoorcondenserprice,disoutdoorcondenserprice,"Outdoorcondenser");
                outdoorcondenserprice("sub",outdoorcondenserprice-disoutdoorcondenserprice);
                totalsaved("sub",disoutdoorcondenserprice,"Outdoorcondenser");
                btn("false");
            }
        });

        swingflapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardView_swingflap1.setVisibility(View.INVISIBLE);
                swingflapprogressbar.setVisibility(View.VISIBLE);
                swingflapquantity("add");
                totalquantity("add");
                totalprice("add", swingflapprice,disswingflapprice,"Swingflap");
                swingflapprice("add",swingflapprice);

                btn("false");
            }
        });
        addswingflap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardView_swingflap1.setVisibility(View.INVISIBLE);
                swingflapprogressbar.setVisibility(View.VISIBLE);
                swingflapquantity("add");
                totalquantity("add");
                totalprice("add", swingflapprice-disswingflapprice,disswingflapprice,"Swingflap");
                swingflapprice("add",swingflapprice-disswingflapprice);
                totalsaved("add",disswingflapprice,"Swingflap");
                btn("false");
            }
        });
        subswingflap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardView_swingflap1.setVisibility(View.INVISIBLE);
                swingflapprogressbar.setVisibility(View.VISIBLE);
                swingflapquantity("sub");
                totalquantity("sub");
                totalprice("sub", swingflapprice-disswingflapprice,disswingflapprice,"Swingflap");
                swingflapprice("sub",swingflapprice-disswingflapprice);
                totalsaved("sub",disswingflapprice,"Swingflap");
                btn("false");
            }
        });

        remotebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardView_remote1.setVisibility(View.INVISIBLE);
                remoteprogressbar.setVisibility(View.VISIBLE);
                remotequantity("add");
                totalquantity("add");
                totalprice("add", remoteprice,disremoteprice,"Remote");
                remoteprice("add",remoteprice);

                btn("false");
            }
        });
        addremote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardView_remote1.setVisibility(View.INVISIBLE);
                remoteprogressbar.setVisibility(View.VISIBLE);
                remotequantity("add");
                totalquantity("add");
                totalprice("add", remoteprice-disremoteprice,disremoteprice,"Remote");
                remoteprice("add",remoteprice-disremoteprice);
                totalsaved("add",disremoteprice,"Remote");
                btn("false");
            }
        });
        subremote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardView_remote1.setVisibility(View.INVISIBLE);
                remoteprogressbar.setVisibility(View.VISIBLE);
                remotequantity("sub");
                totalquantity("sub");
                totalprice("sub", remoteprice-disremoteprice,disremoteprice,"Remote");
                remoteprice("sub",remoteprice-disremoteprice);
                totalsaved("sub",disremoteprice,"Remote");
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

    private void totalprice(String action, int price,int discount, String Categoryname) {
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

    // ACTIVATE BUTTON

    private void btn(String action) {
        if (action == "false") {
            capacitorbtn.setEnabled(false);
            addcapacitor.setEnabled(false);
            subcapacitor.setEnabled(false);
            blowerbtn.setEnabled(false);
            addblower.setEnabled(false);
            subblower.setEnabled(false);
            indoormotorbtn.setEnabled(false);
            addindoormotor.setEnabled(false);
            subindoormotor.setEnabled(false);
            outdoorfanbtn.setEnabled(false);
            addoutdoorfan.setEnabled(false);
            suboutdoorfan.setEnabled(false);
            outdoormotorbtn.setEnabled(false);
            addoutdoormotor.setEnabled(false);
            suboutdoormotor.setEnabled(false);
            swingmotorbtn.setEnabled(false);
            addswingmotor.setEnabled(false);
            subswingmotor.setEnabled(false);
            indoorcoilbtn.setEnabled(false);
            addindoorcoil.setEnabled(false);
            subindoorcoil.setEnabled(false);
            outdoorcondenserbtn.setEnabled(false);
            addoutdoorcondenser.setEnabled(false);
            suboutdoorcondenser.setEnabled(false);
            swingflapbtn.setEnabled(false);
            addswingflap.setEnabled(false);
            subswingflap.setEnabled(false);
            remotebtn.setEnabled(false);
            addremote.setEnabled(false);
            subremote.setEnabled(false);


        }
        else if (action == "true") {
            capacitorbtn.setEnabled(true);
            addcapacitor.setEnabled(true);
            subcapacitor.setEnabled(true);
            blowerbtn.setEnabled(true);
            addblower.setEnabled(true);
            subblower.setEnabled(true);
            indoormotorbtn.setEnabled(true);
            addindoormotor.setEnabled(true);
            subindoormotor.setEnabled(true);
            outdoorfanbtn.setEnabled(true);
            addoutdoorfan.setEnabled(true);
            suboutdoorfan.setEnabled(true);
            outdoormotorbtn.setEnabled(true);
            addoutdoormotor.setEnabled(true);
            suboutdoormotor.setEnabled(true);
            swingmotorbtn.setEnabled(true);
            addswingmotor.setEnabled(true);
            subswingmotor.setEnabled(true);
            indoorcoilbtn.setEnabled(true);
            addindoorcoil.setEnabled(true);
            subindoorcoil.setEnabled(true);
            outdoorcondenserbtn.setEnabled(true);
            addoutdoorcondenser.setEnabled(true);
            suboutdoorcondenser.setEnabled(true);
            swingflapbtn.setEnabled(true);
            addswingflap.setEnabled(true);
            subswingflap.setEnabled(true);
            remotebtn.setEnabled(true);
            addremote.setEnabled(true);
            subremote.setEnabled(true);
        }
    }

    // CAPACITOR

    private void capacitorquantity(String action) {

        if (action == "add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringcapacitorcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Capacitor").getValue().toString();
                            int capacitorcount = Integer.parseInt(stringcapacitorcount);
                            capacitorcount = capacitorcount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Capacitor", Integer.toString(capacitorcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            cardview_capacitor2.setVisibility(View.VISIBLE);
                            capacitorprogressbar.setVisibility(View.INVISIBLE);
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
                            String stringcapacitorcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Capacitor").getValue().toString();
                            int capacitorcount = Integer.parseInt(stringcapacitorcount);
                            capacitorcount = capacitorcount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Capacitor", Integer.toString(capacitorcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            if (capacitorcount <= 0) {
                                cardview_capacitor2.setVisibility(View.INVISIBLE);
                                cardview_capacitor1.setVisibility(View.VISIBLE);
                                capacitorprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            } else {
                                capacitorprogressbar.setVisibility(View.INVISIBLE);
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
    private void capacitorprice(String action, int price) {
        if(action=="add")
        {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringcapacitorcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("CapacitorTOTAL").getValue().toString();
                    int capacitorcount = Integer.parseInt(stringcapacitorcount);
                    capacitorcount = capacitorcount + price;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("CapacitorTOTAL", Integer.toString(capacitorcount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
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
                    String stringcapacitorcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("CapacitorTOTAL").getValue().toString();
                    int capacitorcount = Integer.parseInt(stringcapacitorcount);
                    int capacitordiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Capacitor").getValue().toString());

                    if(capacitordiscount==1)
                    {
                        capacitorcount= capacitorcount - price - discapacitorprice;
                    }
                    else
                    {
                        capacitorcount = capacitorcount - price;
                    }
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("CapacitorTOTAL", Integer.toString(capacitorcount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }

    // BLOWER

    private void blowerquantity(String action) {

        if (action == "add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringblowercount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Blower").getValue().toString();
                            int blowercount = Integer.parseInt(stringblowercount);
                            blowercount = blowercount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Blower", Integer.toString(blowercount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            cardview_blower2.setVisibility(View.VISIBLE);
                            blowerprogressbar.setVisibility(View.INVISIBLE);
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
                            String stringblowercount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Blower").getValue().toString();
                            int blowercount = Integer.parseInt(stringblowercount);
                            blowercount = blowercount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Blower", Integer.toString(blowercount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            if (blowercount <= 0) {
                                cardview_blower2.setVisibility(View.INVISIBLE);
                                cardview_blower1.setVisibility(View.VISIBLE);
                                blowerprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            } else {
                                blowerprogressbar.setVisibility(View.INVISIBLE);
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
    private void blowerprice(String action, int price) {
        if(action=="add")
        {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringblowercount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("BlowerTOTAL").getValue().toString();
                    int blowercount = Integer.parseInt(stringblowercount);
                    blowercount = blowercount + price;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("BlowerTOTAL", Integer.toString(blowercount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
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
                    String stringblowercount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("BlowerTOTAL").getValue().toString();
                    int blowercount = Integer.parseInt(stringblowercount);
                    int blowerdiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Blower").getValue().toString());
                    if(blowerdiscount==1)
                    {
                        blowercount = blowercount - price - disblowerprice;
                    }
                    else
                    {
                        blowercount = blowercount - price;
                    }
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("BlowerTOTAL", Integer.toString(blowercount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }


    //OUTDOORFAN

    private void outdoorfanquantity(String action) {

        if (action == "add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringoutdoorfancount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Outdoorfan").getValue().toString();
                            int outdoorfancount = Integer.parseInt(stringoutdoorfancount);
                            outdoorfancount = outdoorfancount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Outdoorfan", Integer.toString(outdoorfancount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            cardView_outdoorfan2.setVisibility(View.VISIBLE);
                            outdoorfanprogressbar.setVisibility(View.INVISIBLE);
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
                            String stringoutdoorfancount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Outdoorfan").getValue().toString();
                            int outdoorfancount = Integer.parseInt(stringoutdoorfancount);
                            outdoorfancount = outdoorfancount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Outdoorfan", Integer.toString(outdoorfancount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            if (outdoorfancount <= 0) {
                                cardView_outdoorfan2.setVisibility(View.INVISIBLE);
                                cardview_outdoorfan1.setVisibility(View.VISIBLE);
                                outdoorfanprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            } else {
                                outdoorfanprogressbar.setVisibility(View.INVISIBLE);
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
    private void outdoorfanprice(String action, int price) {
        if(action=="add")
        {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringoutdoorfancount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("OutdoorfanTOTAL").getValue().toString();
                    int outdoorfancount = Integer.parseInt(stringoutdoorfancount);
                    outdoorfancount = outdoorfancount + price;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("OutdoorfanTOTAL", Integer.toString(outdoorfancount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
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
                    String stringoutdoorfancount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("OutdoorfanTOTAL").getValue().toString();
                    int outdoorfancount = Integer.parseInt(stringoutdoorfancount);
                    int outdoorfandiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Outdoorfan").getValue().toString());
                    if(outdoorfandiscount==1)
                    {
                        outdoorfancount = outdoorfancount - price - disoutdoorfanprice;
                    }
                    else
                    {
                        outdoorfancount = outdoorfancount - price;
                    }
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("OutdoorfanTOTAL", Integer.toString(outdoorfancount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }


    // INDOOR MOTOR

    private void indoormotorquantity(String action) {

        if (action == "add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringindoormotorcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Indoormotor").getValue().toString();
                            int indoormotorcount = Integer.parseInt(stringindoormotorcount);
                            indoormotorcount = indoormotorcount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Indoormotor", Integer.toString(indoormotorcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            cardview_indoormotor2.setVisibility(View.VISIBLE);
                            indoormotorprogressbar.setVisibility(View.INVISIBLE);
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
                            String stringindoormotorcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Indoormotor").getValue().toString();
                            int indoormotorcount = Integer.parseInt(stringindoormotorcount);
                            indoormotorcount = indoormotorcount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Indoormotor", Integer.toString(indoormotorcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            if (indoormotorcount <= 0) {
                                cardview_indoormotor2.setVisibility(View.INVISIBLE);
                                cardview_indoormotor1.setVisibility(View.VISIBLE);
                                indoormotorprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            } else {
                                indoormotorprogressbar.setVisibility(View.INVISIBLE);
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
    private void indoormotorprice(String action, int price) {
        if(action=="add")
        {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringindoormotorcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("IndoormotorTOTAL").getValue().toString();
                    int indoormotorcount = Integer.parseInt(stringindoormotorcount);
                    indoormotorcount = indoormotorcount + price;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("IndoormotorTOTAL", Integer.toString(indoormotorcount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
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
                    String stringindoormotorcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("IndoormotorTOTAL").getValue().toString();
                    int indoormotorcount = Integer.parseInt(stringindoormotorcount);
                    int indoormotordiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Indoormotor").getValue().toString());
                    if(indoormotordiscount==1)
                    {
                        indoormotorcount = indoormotorcount - price - disindoormotorprice;
                    }
                    else
                    {
                        indoormotorcount = indoormotorcount - price;
                    }
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("IndoormotorTOTAL", Integer.toString(indoormotorcount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }


    //OUTDOORMOTOR

    private void outdoormotorquantity(String action) {

        if (action == "add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringoutdoormotorcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Outdoormotor").getValue().toString();
                            int outdoormotorcount = Integer.parseInt(stringoutdoormotorcount);
                            outdoormotorcount = outdoormotorcount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Outdoormotor", Integer.toString(outdoormotorcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            cardview_outdoormotor2.setVisibility(View.VISIBLE);
                            outdoormotorprogressbar.setVisibility(View.INVISIBLE);
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
                            String stringoutdoormotorcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Outdoormotor").getValue().toString();
                            int outdoormotorcount = Integer.parseInt(stringoutdoormotorcount);
                            outdoormotorcount = outdoormotorcount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Outdoormotor", Integer.toString(outdoormotorcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            if (outdoormotorcount <= 0) {
                                cardview_outdoormotor2.setVisibility(View.INVISIBLE);
                                cardview_outdoormotor1.setVisibility(View.VISIBLE);
                                outdoormotorprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            } else {
                                outdoormotorprogressbar.setVisibility(View.INVISIBLE);
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
    private void outdoormotorprice(String action, int price) {
        if(action=="add")
        {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringoutdoormotorcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("OutdoormotorTOTAL").getValue().toString();
                    int outdoormotorcount = Integer.parseInt(stringoutdoormotorcount);
                    outdoormotorcount = outdoormotorcount + price;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("OutdoormotorTOTAL", Integer.toString(outdoormotorcount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
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
                    String stringoutdoormotorcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("OutdoormotorTOTAL").getValue().toString();
                    int outdoormotorcount = Integer.parseInt(stringoutdoormotorcount);
                    int outdoordiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Outdoormotor").getValue().toString());
                    if(outdoordiscount==1)
                    {
                        outdoormotorcount = outdoormotorcount - price - disoutdoormotorprice;
                    }
                    else
                    {
                        outdoormotorcount = outdoormotorcount - price;
                    }
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("OutdoormotorTOTAL", Integer.toString(outdoormotorcount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }


    //SWINGMOTOR

    private void swingmotorquantity(String action) {

        if(action=="add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringswingmotorcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Swingmotor").getValue().toString();
                            int swingmotorcount = Integer.parseInt(stringswingmotorcount);
                            swingmotorcount = swingmotorcount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Swingmotor", Integer.toString(swingmotorcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            cardview_swingmotor2.setVisibility(View.VISIBLE);
                            swingmotorprogressbar.setVisibility(View.INVISIBLE);
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
                            String stringswingmotorcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Swingmotor").getValue().toString();
                            int swingmotorcount = Integer.parseInt(stringswingmotorcount);
                            swingmotorcount = swingmotorcount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Swingmotor", Integer.toString(swingmotorcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            if(swingmotorcount<=0) {
                                cardview_swingmotor2.setVisibility(View.INVISIBLE);
                                cardview_swingmotor1.setVisibility(View.VISIBLE);
                                swingmotorprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                            else
                            {
                                swingmotorprogressbar.setVisibility(View.INVISIBLE);
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
    private void swingmotorprice(String action, int price) {
        if(action=="add")
        {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringswingmotorcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("SwingmotorTOTAL").getValue().toString();
                    int swingmotorcount = Integer.parseInt(stringswingmotorcount);
                    swingmotorcount = swingmotorcount + price;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("SwingmotorTOTAL", Integer.toString(swingmotorcount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
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
                    String stringswingmotorcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("SwingmotorTOTAL").getValue().toString();
                    int swingmotorcount = Integer.parseInt(stringswingmotorcount);
                    int swingmotordiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Swingmotor").getValue().toString());
                    if(swingmotordiscount==1)
                    {
                        swingmotorcount = swingmotorcount - price - disswingmotorprice;
                    }
                    else
                    {
                        swingmotorcount = swingmotorcount - price;
                    }
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("SwingmotorTOTAL", Integer.toString(swingmotorcount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }


    //INDOORCOIL


    private void indoorcoilquantity(String action) {

        if(action=="add") {
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
                            cardView_indoorcoil2.setVisibility(View.VISIBLE);
                            indoorcoilprogressbar.setVisibility(View.INVISIBLE);
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
                            String stringindoorcoilcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Indoorcoil").getValue().toString();
                            int indoorcoilcount = Integer.parseInt(stringindoorcoilcount);
                            indoorcoilcount = indoorcoilcount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Indoorcoil", Integer.toString(indoorcoilcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            if(indoorcoilcount<=0) {
                                cardView_indoorcoil2.setVisibility(View.INVISIBLE);
                                cardView_indoorcoil1.setVisibility(View.VISIBLE);
                                indoorcoilprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                            else
                            {
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
            delay.postDelayed(run,500);
        }

    }
    private void indoorcoilprice(String action, int price) {
        if(action=="add")
        {
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
        else if(action=="sub")
        {
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

    }


    //OUTDOORCONDENSER

    private void outdoorcondenserquantity(String action) {

        if(action=="add") {
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
                            cardView_outdoorcondenser2.setVisibility(View.VISIBLE);
                            outdoorcondenserprogressbar.setVisibility(View.INVISIBLE);
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
                            String stringoutdoorcondensercount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Outdoorcondenser").getValue().toString();
                            int outdoorcondensercount = Integer.parseInt(stringoutdoorcondensercount);
                            outdoorcondensercount = outdoorcondensercount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Outdoorcondenser", Integer.toString(outdoorcondensercount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            if(outdoorcondensercount<=0) {
                                cardView_outdoorcondenser2.setVisibility(View.INVISIBLE);
                                cardView_outdoorcondenser1.setVisibility(View.VISIBLE);
                                outdoorcondenserprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                            else
                            {
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
            delay.postDelayed(run,500);
        }

    }
    private void outdoorcondenserprice(String action, int price) {
        if(action=="add")
        {
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
        else if(action=="sub")
        {
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

    }


    //SWINGFLAP

    private void swingflapquantity(String action) {

        if(action=="add") {
            Handler delay = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    cart.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringswingflapcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Swingflap").getValue().toString();
                            int swingflapcount = Integer.parseInt(stringswingflapcount);
                            swingflapcount = swingflapcount + 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Swingflap", Integer.toString(swingflapcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            cardView_swingflap2.setVisibility(View.VISIBLE);
                            swingflapprogressbar.setVisibility(View.INVISIBLE);
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
                            String stringswingflapcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Swingflap").getValue().toString();
                            int swingflapcount = Integer.parseInt(stringswingflapcount);
                            swingflapcount = swingflapcount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Swingflap", Integer.toString(swingflapcount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            if(swingflapcount<=0) {
                                cardView_swingflap2.setVisibility(View.INVISIBLE);
                                cardView_swingflap1.setVisibility(View.VISIBLE);
                                swingflapprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                            else
                            {
                                swingflapprogressbar.setVisibility(View.INVISIBLE);
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
    private void swingflapprice(String action, int price) {
        if(action=="add")
        {
            cart.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String stringswingflapcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("SwingflapTOTAL").getValue().toString();
                    int swingflapcount = Integer.parseInt(stringswingflapcount);
                    swingflapcount = swingflapcount + price;
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("SwingflapTOTAL", Integer.toString(swingflapcount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
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
                    String stringswingflapcount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("SwingflapTOTAL").getValue().toString();
                    int swingflapcount = Integer.parseInt(stringswingflapcount);
                    int swingflapdiscount = Integer.parseInt(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Swingflap").getValue().toString());
                    if(swingflapdiscount==1)
                    {
                        swingflapcount = swingflapcount - price - disswingflapprice;
                    }
                    else
                    {
                        swingflapcount = swingflapcount - price;
                    }
                    HashMap<String, Object> cartt = new HashMap<>();
                    cartt.put("SwingflapTOTAL", Integer.toString(swingflapcount));
                    cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }


    //REMOTE

    private void remotequantity(String action) {

        if(action=="add") {
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
                            cardView_remote2.setVisibility(View.VISIBLE);
                            remoteprogressbar.setVisibility(View.INVISIBLE);
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
                            String stringremotecount = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).child("Remote").getValue().toString();
                            int remotecount = Integer.parseInt(stringremotecount);
                            remotecount = remotecount - 1;
                            HashMap<String, Object> cartt = new HashMap<>();
                            cartt.put("Remote", Integer.toString(remotecount));
                            cart.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child(Maincategory).child(Category).updateChildren(cartt);
                            if(remotecount<=0) {
                                cardView_remote2.setVisibility(View.INVISIBLE);
                                cardView_remote1.setVisibility(View.VISIBLE);
                                remoteprogressbar.setVisibility(View.INVISIBLE);
                                btn("true");
                            }
                            else
                            {
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
            delay.postDelayed(run,500);
        }

    }
    private void remoteprice(String action, int price) {
        if(action=="add")
        {
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
        else if(action=="sub")
        {
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