package com.futureinspirator.acbaradise;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class about_a_c_b extends Fragment {



      TextView callnow;
      private String admin_number;
      private ScrollView scroll,loadingscroll;
      private CardView progressbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_about_a_c_b, container, false);

        requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        callnow = v.findViewById(R.id.callnow);

        scroll = v.findViewById(R.id.scroll);

        loadingscroll = v.findViewById(R.id.loadingscroll);

        progressbar = v.findViewById(R.id.progressbar);


        DatabaseReference root = FirebaseDatabase.getInstance().getReference();

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                admin_number = snapshot.child("Admin").child("Admin_number").getValue().toString();

                if(!(admin_number.isEmpty()))
                {
                    progressbar.setVisibility(View.GONE);
                    loadingscroll.setVisibility(View.GONE);
                    scroll.setVisibility(View.VISIBLE);
                    requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                }
                else
                {
                    progressbar.setVisibility(View.VISIBLE);
                    loadingscroll.setVisibility(View.VISIBLE);
                    scroll.setVisibility(View.GONE);
                    requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                }

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

        return v;
    }
}