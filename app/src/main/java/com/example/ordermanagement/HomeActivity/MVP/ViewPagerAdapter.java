package com.example.ordermanagement.HomeActivity.MVP;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.ordermanagement.HomeActivity.MVP.Cancelled.CancelledFragment;
import com.example.ordermanagement.HomeActivity.MVP.Delivered.DeliveredFragment;
import com.example.ordermanagement.HomeActivity.MVP.Dispatched.DispatchedFragment;
import com.example.ordermanagement.HomeActivity.MVP.Placed.PlacedFragment;
import com.example.ordermanagement.HomeActivity.MVP.Prepared.PreparedFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter
{


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position){
            case 0: return new PlacedFragment();
            case 1: return new PreparedFragment();
            case 2: return new DispatchedFragment();
            case 3: return new DeliveredFragment();
            case 4: return new CancelledFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0: return "Placed";
            case 1: return "Prepared";
            case 2: return "Dispatched";
            case 3: return "Delivered";
            case 4: return "Cancelled";

            default: return null;
        }
    }
}
