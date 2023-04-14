package com.arbutus.exerboost.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.activity.main.fragments.home.HomeFragment;
import com.arbutus.exerboost.databinding.ActivityMainBinding;
import com.arbutus.exerboost.utilities.FragmentController;
import com.library.center.circle.bottomnavigationview.SpaceItem;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentManager = getSupportFragmentManager();

        setUpToolbar();

        binding.spaceBottomNavigationView.initWithSaveInstanceState(savedInstanceState);
        binding.spaceBottomNavigationView.addSpaceItem(new SpaceItem("", R.drawable.navigation_home_img));
        binding.spaceBottomNavigationView.addSpaceItem(new SpaceItem("", R.drawable.navigation_more_img));
        binding.spaceBottomNavigationView.addSpaceItem(new SpaceItem("", R.drawable.navigation_buy_img));
        binding.spaceBottomNavigationView.addSpaceItem(new SpaceItem("", R.drawable.navigation_profile_img));

        FragmentController.replaceFragment(fragmentManager,R.id.fragmentContainer,new HomeFragment());
        initListener();
    }

    private void setUpToolbar() {

        setSupportActionBar(binding.header.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,binding.drawerLayout,binding.header.toolbar,R.string.open_navigation_drawer,R.string.close_navigation_drawer);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initListener() {


    }
}