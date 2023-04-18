package com.arbutus.exerboost.activity.main.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.activity.auth.login.LoginActivity;
import com.arbutus.exerboost.activity.main.fragments.home.HomeFragment;
import com.arbutus.exerboost.activity.main.fragments.menu.MenuPackageFragment;
import com.arbutus.exerboost.activity.main.fragments.order.YourOrderFragment;
import com.arbutus.exerboost.databinding.ActivityMainBinding;
import com.arbutus.exerboost.repository.local.LocalController;
import com.arbutus.exerboost.repository.local.LocalSets;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;
import com.arbutus.exerboost.utilities.FragmentController;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;

    private MainActivityRepository repository = new MainActivityRepository();
    private Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentManager = getSupportFragmentManager();

        setUpToolbar();
        setUpBottomNavigation();


        FragmentController.addFragment(fragmentManager,R.id.fragmentContainer,new HomeFragment());
        initListener();
    }

    private void setUpToolbar() {

        setSupportActionBar(binding.header.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,binding.drawerLayout,binding.header.toolbar,R.string.open_navigation_drawer,R.string.close_navigation_drawer);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setUpBottomNavigation() {

        binding.bottomNavigation.navigationHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment homeFragment = HomeFragment.newInstance();
                FragmentController.replaceFragment(fragmentManager,R.id.fragmentContainer,homeFragment);

                binding.bottomNavigation.navigationHome.setBackgroundResource(R.drawable.navigation_home_img);
                binding.bottomNavigation.navigationMenuPackage.setBackgroundResource(R.drawable.navigation_menu_img);
                binding.bottomNavigation.navigationOrder.setBackgroundResource(R.drawable.navigation_order_img);
            }
        });

        binding.bottomNavigation.navigationMenuPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment menuPackageFragment = MenuPackageFragment.newInstance();
                FragmentController.replaceFragment(fragmentManager,R.id.fragmentContainer,menuPackageFragment);

                binding.bottomNavigation.navigationHome.setBackgroundResource(R.drawable.nav_home_img);
                binding.bottomNavigation.navigationMenuPackage.setBackgroundResource(R.drawable.navigation_menu_selected);
                binding.bottomNavigation.navigationOrder.setBackgroundResource(R.drawable.navigation_order_img);
            }
        });

        binding.bottomNavigation.navigationOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment orderFragment = YourOrderFragment.newInstance();
                FragmentController.replaceFragment(fragmentManager,R.id.fragmentContainer,orderFragment);

                binding.bottomNavigation.navigationHome.setBackgroundResource(R.drawable.nav_home_img);
                binding.bottomNavigation.navigationMenuPackage.setBackgroundResource(R.drawable.navigation_menu_img);
                binding.bottomNavigation.navigationOrder.setBackgroundResource(R.drawable.navigation_order_selected);
            }
        });

        binding.bottomNavigation.navigationProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //========================= NAVIGATION DRAWER ITEMS LISTENER =======================

        binding.navigationItems.logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogOutAlertDialog();
            }
        });
    }

    private void initListener() {

        binding.navigationItems.cancelImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });

    }

    private void showLogOutAlertDialog(){

        AlertDialog logOutDialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_exit)
                .setTitle("Log Out")
                .setMessage("Are you sure you want to Log Out?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        logOut();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

    private void logOut() {

        if(AppBoilerPlateCode.isInternetConnected(this)){

            progressDialog = AppBoilerPlateCode.setProgressDialog(MainActivity.this);

            repository.logOutUser();

            LiveData<String> successLiveData = repository.getSuccessResponseMutableData();
            LiveData<String> failureLiveData = repository.getFailureResponseMutableData();

            successLiveData.observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

                    LocalSets.clearLocalData(LocalController.getInstance(MainActivity.this).getPreferences());
                    AppBoilerPlateCode.navigateToActivityWithFinish(MainActivity.this,LoginActivity.class,null);
                    System.out.println("============= Failure ===============");
                }
            });

            failureLiveData.observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    System.out.println("============= Failure ===============");
                }
            });
        }
        else {
            AppBoilerPlateCode.showSnackBarForInternet(MainActivity.this,binding.drawerLayout);
        }
    }
}