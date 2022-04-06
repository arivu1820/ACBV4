package com.futureinspirator.acbaradise;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

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

public class Fragment_amc_subscription extends Fragment {

    private RecyclerView amcrecyclerview;

    TextView callnow;
    private String admin_number;

    String first,second,third,fourth,title,key;

    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_amc_subscription, container, false);

        callnow = v.findViewById(R.id.callnow);

        callnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+admin_number));
                startActivity(intent);


            }
        });

        DatabaseReference root = FirebaseDatabase.getInstance().getReference();

        amcrecyclerview = v.findViewById(R.id.amcrecyclerview);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                admin_number = snapshot.child("Admin").child("Admin_number").getValue().toString();

                if(!(admin_number.isEmpty()))
                {
                    requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                }
                else
                {
                    requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                }
                if (snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("amc_subscriber").exists()) {
                    ArrayList<parent_amc_subscriber> list = new ArrayList<>();

                    for (DataSnapshot data : snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("amc_subscriber").getChildren()) {

                        for (DataSnapshot datasnapshot : data.getChildren()) {
                            if (datasnapshot.getKey().equals("FirstService")) {
                                first = datasnapshot.getValue().toString();
                            }
                            if (datasnapshot.getKey().equals("SecondService")) {
                                second = datasnapshot.getValue().toString();
                            }
                            if (datasnapshot.getKey().equals("ThirdService")) {
                                third = datasnapshot.getValue().toString();
                            }
                            if (datasnapshot.getKey().equals("FourthService")) {
                                fourth = datasnapshot.getValue().toString();
                            }
                            if (datasnapshot.getKey().equals("Title")) {
                                title = datasnapshot.getValue().toString();
                            }
                            if (datasnapshot.getKey().equals("Key")) {
                                key = datasnapshot.getValue().toString();
                            }
                        }
                        list.add(new parent_amc_subscriber("#"+key,title, first, second, third, fourth));
                    }

                    amcAdapter adapter = new amcAdapter();
                    adapter.setParentamcs(list);

                    amcrecyclerview.setAdapter(adapter);
                    amcrecyclerview.setLayoutManager(new LinearLayoutManager(v.getRootView().getContext()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return v;
    }
}