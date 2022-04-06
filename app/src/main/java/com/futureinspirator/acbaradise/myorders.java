package com.futureinspirator.acbaradise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class myorders extends Fragment {

    DatabaseReference root = FirebaseDatabase.getInstance().getReference();

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());

    List<ChildItem> childItemList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v  = inflater.inflate(R.layout.fragment_myorders, container, false);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<ParentItem> ChildItemList = new ArrayList<>();

                for (DataSnapshot bookingkey : snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").getChildren()) {

                    String key = bookingkey.getKey().toString();
                    String totalitem = "", yousaved = "", charge = "", grandtotal = "", discount = "", payment = "", date = "", number = "", address = "";
                    List<ChildItem> List = new ArrayList<>();

                    int count = (int) bookingkey.getChildrenCount();

                    for (DataSnapshot data : snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(key).getChildren()) {

                        String price="", totalprice = "";
                        String quantity = data.getValue().toString();
                        String quantitykey = data.getKey();
                        int inttotalprice=0;

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
                                    for (DataSnapshot data2 : snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Booking").child(key).getChildren()) {
                                        if ((data2.getKey().toString()).equals(quantitykey + "TOTAL")) {
                                            totalprice = data2.getValue().toString();
                                            inttotalprice = Integer.parseInt(totalprice);
                                        }
                                    }
                                    int intquantity = Integer.parseInt(quantity);
                                    int intprice = inttotalprice / intquantity;
                                    price = Integer.toString(intprice);
                                    List.add(new ChildItem(quantitykey, quantity, price, totalprice));
                                }
                                break;
                            }
                        }
                        if((data.getKey().toString()).equals("Payment"))
                        {
                            payment = data.getValue().toString();
                        }
                        else if((data.getKey().toString()).equals("Address"))
                        {
                            address = data.getValue().toString();
                        }
                        else if((data.getKey().toString()).equals("Totalsaved"))
                        {
                            yousaved = data.getValue().toString();
                        }
                        else if((data.getKey().toString()).equals("Discount"))
                        {
                            discount = data.getValue().toString();
                        }
                        else if((data.getKey().toString()).equals("Totalitems"))
                        {
                            totalitem = data.getValue().toString();
                        }
                        else if((data.getKey().toString()).equals("Charge"))
                        {
                            charge = data.getValue().toString();
                        }
                        else if((data.getKey().toString()).equals("Grandtotal"))
                        {
                            grandtotal = data.getValue().toString();

                        }
                        else if((data.getKey().toString()).equals("Date"))
                        {
                            date = data.getValue().toString();

                        }
                        else if((data.getKey().toString()).equals("Number"))
                        {
                            number = data.getValue().toString();

                        }


                    }

                    ChildItemList.add(new ParentItem(totalitem,yousaved,charge,grandtotal,"Your Total Discount          "+discount,key,payment,date,number,address,List));


                }

                RecyclerView ParentRecyclerViewItem = v.findViewById(R.id.orderlist);

                LinearLayoutManager layoutManager = new LinearLayoutManager(v.getRootView().getContext());

                ParentItemAdapter parentItemAdapter = new ParentItemAdapter(ChildItemList);

                ParentRecyclerViewItem.setAdapter(parentItemAdapter);
                ParentRecyclerViewItem.setLayoutManager(layoutManager);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return  v;
    }
}