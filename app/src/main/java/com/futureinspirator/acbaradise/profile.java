package com.futureinspirator.acbaradise;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends Fragment {

    private RelativeLayout callnow , relative_rateus , order_summary , profileDetailsLayout;
    private String admin_number = "";
    private TextView logout , user_address , user_address_2,user_id;
    private int ttotal=0;

    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_profile, container, false);

        logout = v.findViewById(R.id.logout);

        profileDetailsLayout = v.findViewById(R.id.address);

        order_summary = v.findViewById(R.id.order_summary);

        relative_rateus = v.findViewById(R.id.relative_rateus);

        user_address_2 = v.findViewById(R.id.user_address_2);

        callnow = v.findViewById(R.id.callnow);

        user_id = v.findViewById(R.id.user_id);


        DatabaseReference root = FirebaseDatabase.getInstance().getReference();

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                admin_number = snapshot.child("Admin").child("Admin_number").getValue().toString();
                String address = snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).child("Address").getValue().toString();
                user_id.setText(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).getKey());
                user_address_2.setText(address);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        callnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+admin_number));
                startActivity(intent);


            }
        });

        relative_rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getRootView().getContext(), acb_blogs.class);
                startActivity(intent);

            }
        });

        order_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getRootView().getContext(), summary.class);
                startActivity(intent);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences prefernces = getActivity().getSharedPreferences("ACBARADISE.v3", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefernces.edit();
                editor.putString("remember.v3","false");
                editor.apply();
                getActivity().finish();
//                System.exit(0);
                Intent intent = new Intent(view.getRootView().getContext(),login.class);
                getActivity().startActivity(intent);
            }
        });

        profileDetailsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getRootView().getContext(), map.class);
                startActivity(intent);
            }
        });

        return v;
    }
}