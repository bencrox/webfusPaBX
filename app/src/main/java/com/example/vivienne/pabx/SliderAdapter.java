package com.example.vivienne.pabx;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.util.Log;


/**
 * Created by Vivienne on 12/21/2016.
 */
public class SliderAdapter {

    FragmentManager fm;
    public int siteID;
    Context context;
//    MySite mysite;

    public SliderAdapter(FragmentManager _fm, Context c) {
        fm = _fm;
        context = c;
        siteID = 0;
    }

    public void init () {
        HomeFragment home=new HomeFragment();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.pager, home, "home");
        ft.addToBackStack("addHome");
//        System.out.println("reach sideFrag");
        ft.commit();
    }


}
