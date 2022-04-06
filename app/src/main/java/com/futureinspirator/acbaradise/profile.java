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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends Fragment {

    private RelativeLayout callnow;
    private String admin_number = "";
    private TextView logout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_profile, container, false);

        logout = v.findViewById(R.id.logout);



        callnow = v.findViewById(R.id.callnow);


        DatabaseReference root = FirebaseDatabase.getInstance().getReference();

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                admin_number = snapshot.child("Admin").child("Admin_number").getValue().toString();
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

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences prefernces = getActivity().getSharedPreferences("ACBARADISE.v3", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefernces.edit();
                editor.putString("remember.v3","false");
                editor.apply();
//                System.exit(0);
            }
        });

        return v;
    }
}