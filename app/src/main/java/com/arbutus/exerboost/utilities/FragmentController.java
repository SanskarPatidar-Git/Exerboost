package com.arbutus.exerboost.utilities;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentController {

    public static void addFragment(FragmentManager manager , int container , Fragment fragment){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(container, fragment);
        transaction.commit();
    }

    public static void replaceFragment(FragmentManager manager , int container , Fragment fragment){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(container, fragment);
        transaction.commit();
    }

    public static void addFragmentWithBackStack(FragmentManager manager , int container , Fragment fragment){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
