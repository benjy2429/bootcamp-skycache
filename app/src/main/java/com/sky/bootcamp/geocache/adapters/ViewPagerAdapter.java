package com.sky.bootcamp.geocache.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sky.bootcamp.geocache.R;
import com.sky.bootcamp.geocache.tabs.PackTab;
import com.sky.bootcamp.geocache.tabs.PickTab;

/**
 * Created by bca23 on 25/07/15.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    Context context;


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, Context context, int mNumbOfTabsumb) {
        super(fm);

        this.context = context;
        this.numOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if (position == 0) // if the position is 0 we are returning the First tab
        {
            PickTab tab1 = new PickTab();
            return tab1;
        } else             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            PackTab tab2 = new PackTab();
            return tab2;
        }

    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) {
            return context.getString(R.string.title_section1);
        } else {
            return context.getString(R.string.title_section2);
        }
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
