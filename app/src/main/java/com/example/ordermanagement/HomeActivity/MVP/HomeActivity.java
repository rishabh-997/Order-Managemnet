package com.example.ordermanagement.HomeActivity.MVP;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.example.ordermanagement.HomeActivity.MVP.Cancelled.CancelledFragment;
import com.example.ordermanagement.HomeActivity.MVP.Delivered.DeliveredFragment;
import com.example.ordermanagement.HomeActivity.MVP.Dispatched.DispatchedFragment;
import com.example.ordermanagement.HomeActivity.MVP.Placed.PlacedFragment;
import com.example.ordermanagement.HomeActivity.MVP.Prepared.PreparedFragment;
import com.example.ordermanagement.R;

public class HomeActivity extends AppCompatActivity implements PlacedFragment.OnFragmentInteractionListener, PreparedFragment.OnFragmentInteractionListener, DispatchedFragment.OnFragmentInteractionListener, DeliveredFragment.OnFragmentInteractionListener, CancelledFragment.OnFragmentInteractionListener
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewPager viewPager = findViewById(R.id.pager);
        ViewPagerAdapter myPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(myPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
