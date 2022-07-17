package com.futureinspirator.acbaradise;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class homepage extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);


        HomeAdapter homeAdapter = new HomeAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        homeAdapter.addfragment(new home(),"");
        homeAdapter.addfragment(new myorders(),"");
        homeAdapter.addfragment(new about_a_c_b(),"");
        homeAdapter.addfragment(new Fragment_amc_subscription(),"");
        homeAdapter.addfragment(new profile(),"");
        viewPager.setAdapter(homeAdapter);

        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.booking);
        tabLayout.getTabAt(2).setIcon(R.drawable.splashimage1);
        tabLayout.getTabAt(3).setIcon(R.drawable.splashimage1);
        tabLayout.getTabAt(4).setIcon(R.drawable.splashimage1);

    }
}