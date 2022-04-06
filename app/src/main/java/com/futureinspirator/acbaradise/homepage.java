package com.futureinspirator.acbaradise;

import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.futureinspirator.acbaradise.databinding.ActivityHomepageBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class homepage extends AppCompatActivity {


    ActivityHomepageBinding binding;

    private BottomNavigationView bottomNavigationView;
    private RelativeLayout relative;

    private DatabaseReference root  = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);




        binding = ActivityHomepageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replacefragment(new home());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.home:
                    replacefragment(new home());
                    break;
                case R.id.myprofile:
                    replacefragment(new profile());
                    break;
                case R.id.aboutACB:
                    replacefragment(new about_a_c_b());
                    break;
                case R.id.myorders:
                    replacefragment(new myorders());
                    break;
                case R.id.amc_subscription:
                    replacefragment(new Fragment_amc_subscription());
                    break;
            }

            return true;
        });


    }

    private void replacefragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();
//
    }




}