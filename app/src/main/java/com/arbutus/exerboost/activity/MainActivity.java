package com.arbutus.exerboost.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.activity.main.fragments.home.HomeFragment;
import com.arbutus.exerboost.databinding.ActivityMainBinding;
import com.arbutus.exerboost.utilities.FragmentController;
import com.google.android.material.navigation.NavigationBarView;
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


        binding.spaceBottomNavigationView.initWithSaveInstanceState(savedInstanceState);
        binding.spaceBottomNavigationView.addSpaceItem(new SpaceItem("HOME", R.drawable.ic_search));
        binding.spaceBottomNavigationView.addSpaceItem(new SpaceItem("SEARCH", R.drawable.ic_search));

        FragmentController.replaceFragment(fragmentManager,R.id.fragmentContainer,new HomeFragment());
        initListener();
    }

    private void initListener() {

//        binding..setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                return true;
//            }
//        });
    }
}