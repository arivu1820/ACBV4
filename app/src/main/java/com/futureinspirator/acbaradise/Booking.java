package com.futureinspirator.acbaradise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Booking extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());
    private TextView totalitem,yousaved,charge,grandtotal;
    private FrameLayout confirm;
    private String payment = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        recyclerView = findViewById(R.id.recyclerView);
        confirm  = findViewById(R.id.confirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Booking.this, checkout.class);
                intent.putExtra("payment",payment);
                startActivity(intent);
                finish();
            }
        });

        totalitem = findViewById(R.id.totalitem);
        yousaved = findViewById(R.id.yousaved);
        charge = findViewById(R.id.charge);
        grandtotal = findViewById(R.id.grandtotal);

        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").removeValue();


        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("GeneralService").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> hashvalue = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String Stringvalue = dataSnapshot.getValue().toString();
                    int value = Integer.parseInt(Stringvalue);
                    if(value != 0)
                    {
                        hashvalue.put("GeneralService > "+dataSnapshot.getKey().toString(),dataSnapshot.getValue().toString());
                    }
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").updateChildren(hashvalue);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("WaterWash").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> hashvalue = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String Stringvalue = dataSnapshot.getValue().toString();
                    int value = Integer.parseInt(Stringvalue);
                    if(value != 0)
                    {
                        hashvalue.put("WaterWash > "+dataSnapshot.getKey().toString(),dataSnapshot.getValue().toString());

                    }
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").updateChildren(hashvalue);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Spares").child("SplitAC").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("Spares > SplitAC > "+dataSnapshot.getKey().toString(),quantity);
                    }
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").updateChildren(products);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Spares").child("WindowAC").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("Spares > WindowAC > "+dataSnapshot.getKey().toString(),quantity);
                    }
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").updateChildren(products);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Spares").child("CassetteAC").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("Spares > CassetteAC > "+dataSnapshot.getKey().toString(),quantity);
                    }
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").updateChildren(products);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Faults").child("SplitAC").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("Faults > SplitAC > "+dataSnapshot.getKey().toString(),quantity);

                    }
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").updateChildren(products);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Faults").child("WindowAC").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("Faults > WindowAC > "+dataSnapshot.getKey().toString(),quantity);
                    }
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").updateChildren(products);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Faults").child("CassetteAC").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("Faults > CassetteAC > "+dataSnapshot.getKey().toString(),quantity);
                    }
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").updateChildren(products);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child("SplitAC").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {

                        products.put("Installuninstall > SplitAC > "+dataSnapshot.getKey().toString(),quantity);
                    }
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").updateChildren(products);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child("WindowAC").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {

                        products.put("Installuninstall > WindowAC > "+dataSnapshot.getKey().toString(),quantity);
                    }
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").updateChildren(products);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("Installuninstall").child("CassetteAC").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("Installuninstall > CassetteAC > "+dataSnapshot.getKey().toString(),quantity);
                    }
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").updateChildren(products);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme1").child("Withspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    String stringkey = dataSnapshot.getKey().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > SplitAC > Scheme1 > Withspare > "+dataSnapshot.getKey().toString(),quantity);
                    }
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").updateChildren(products);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme2").child("Withspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    String stringkey = dataSnapshot.getKey().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > SplitAC > Scheme2 > Withspare > "+dataSnapshot.getKey().toString(),quantity);
                    }
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").updateChildren(products);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme3").child("Withspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    String stringkey = dataSnapshot.getKey().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > SplitAC > Scheme3 > Withspare > "+dataSnapshot.getKey().toString(),quantity);
                    }
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").updateChildren(products);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme1").child("Withoutspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > SplitAC > Scheme1 > Withoutspare > "+dataSnapshot.getKey().toString(),quantity);
                    }
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").updateChildren(products);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme2").child("Withoutspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > SplitAC > Scheme2 > Withoutspare > "+dataSnapshot.getKey().toString(),quantity);
                    }
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").updateChildren(products);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("SplitAC").child("Scheme3").child("Withoutspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > SplitAC > Scheme3 > Withoutspare > "+dataSnapshot.getKey().toString(),quantity);

                    }
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").updateChildren(products);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child("Scheme1").child("Withspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    String stringkey = dataSnapshot.getKey().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > WindowAC > Scheme1 > Withspare > "+dataSnapshot.getKey().toString(),quantity);

                    }
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").updateChildren(products);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child("Scheme2").child("Withspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    String stringkey = dataSnapshot.getKey().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > WindowAC > Scheme2 > Withspare > "+dataSnapshot.getKey().toString(),quantity);
                    }
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").updateChildren(products);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child("Scheme1").child("Withoutspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > WindowAC > Scheme1 > Withoutspare > "+dataSnapshot.getKey().toString(),quantity);

                    }
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").updateChildren(products);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("WindowAC").child("Scheme2").child("Withoutspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > WindowAC > Scheme2 > Withoutspare > "+dataSnapshot.getKey().toString(),quantity);
                    }
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").updateChildren(products);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("CassetteAC").child("Scheme1").child("Withspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    String stringkey = dataSnapshot.getKey().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > CassetteAC > Scheme1 > Withspare > "+dataSnapshot.getKey().toString(),quantity);
                    }
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").updateChildren(products);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("AMC").child("CassetteAC").child("Scheme1").child("Withoutspare").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> products = new HashMap<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String stringquantity = dataSnapshot.getValue().toString();
                    int quantity = Integer.parseInt(stringquantity);
                    if(quantity!=0)
                    {
                        products.put("AMC > CassetteAC > Scheme1 > Withoutspare > "+dataSnapshot.getKey().toString(),quantity);
                    }
                    root.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").updateChildren(products);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                ArrayList<parentbooking> list = new ArrayList<>();

                if(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").exists()) {
                    for (DataSnapshot data : snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").getChildren()) {

                        String price = "", totalprice = "";
                        String quantity = data.getValue().toString();
                        String quantitykey = data.getKey();
                        int inttotalprice = 0;

                        for (int i = 0; 10 < quantitykey.length(); i++) {

                            StringBuffer sb = new StringBuffer(quantitykey);
                            sb.deleteCharAt(sb.length() - 1);
                            sb.deleteCharAt(sb.length() - 1);
                            sb.deleteCharAt(sb.length() - 1);
                            sb.deleteCharAt(sb.length() - 1);
                            sb.deleteCharAt(sb.length() - 1);

                            char c = quantitykey.charAt(i);

                            if (c == '>') {
                                if (!(sb + "TOTAL").equals(quantitykey)) {
                                    for (DataSnapshot data2 : snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Preview").getChildren()) {
                                        if ((data2.getKey().toString()).equals(quantitykey + "TOTAL")) {
                                            totalprice = data2.getValue().toString();
                                            inttotalprice = Integer.parseInt(totalprice);
                                        }
                                    }
                                    int intquantity = Integer.parseInt(quantity);
                                    int intprice = inttotalprice / intquantity;
                                    price = Integer.toString(intprice);
                                    list.add(new parentbooking(quantitykey, quantity,totalprice));
                                }
                                break;
                            }
                        }

                        bookingAdapter adapter = new bookingAdapter();
                        adapter.setbookings(list);

                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Booking.this));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String quantity = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALQUANTITY").getValue().toString();
                totalitem.setText(quantity);
                String totalprice = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALPRICE").getValue().toString();
                grandtotal.setText(totalprice);
                String yousave = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Addcart").child("TOTALSAVED").getValue().toString();
                yousaved.setText(yousave);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.cod:
                if (checked) {
                    payment = "1";
                    break;
                }
            case R.id.online:
                if (checked) {
                    payment  = "0";
                    break;
                }
        }

    }

}