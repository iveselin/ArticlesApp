package com.example.cobeosijek.articlesapp.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by cobeosijek on 26/10/2017.
 */

public class BaseActivity extends AppCompatActivity {
    protected void replaceFragment(Fragment fragment, int targerLayout, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(targerLayout, fragment);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getTag());
        }
        fragmentTransaction.commit();
    }

    protected void addFragment(Fragment fragment, int targerLayout) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(targerLayout, fragment);

        fragmentTransaction.commit();
    }
}
