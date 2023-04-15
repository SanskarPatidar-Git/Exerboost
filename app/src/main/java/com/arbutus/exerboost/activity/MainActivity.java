package com.arbutus.exerboost.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.activity.main.fragments.home.HomeFragment;
import com.arbutus.exerboost.databinding.ActivityMainBinding;
import com.arbutus.exerboost.utilities.FragmentController;
import com.library.center.circle.bottomnavigationview.SpaceItem;
import com.library.center.circle.bottomnavigationview.SpaceOnClickListener;

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
        setUpBottomNavigation();




        FragmentController.replaceFragment(fragmentManager,R.id.fragmentContainer,new HomeFragment());
        initListener();
    }

    private void setUpToolbar() {

        setSupportActionBar(binding.header.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,binding.drawerLayout,binding.header.toolbar,R.string.open_navigation_drawer,R.string.close_navigation_drawer);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setUpBottomNavigation() {


        binding.spaceBottomNavigationView.addSpaceItem(new SpaceItem("", R.drawable.navigation_home_img));
        binding.spaceBottomNavigationView.addSpaceItem(new SpaceItem("", R.drawable.navigation_more_img));
        binding.spaceBottomNavigationView.addSpaceItem(new SpaceItem("", R.drawable.navigation_buy_img));
        binding.spaceBottomNavigationView.addSpaceItem(new SpaceItem("", R.drawable.person_cartoon_img));

        binding.spaceBottomNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {

            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {

                switch (itemIndex){


                    case 0 : FragmentController.replaceFragment(fragmentManager,R.id.fragmentContainer,new HomeFragment());
                        break;
                    case 1 : break;
                    case 2 : break;
                    case 3 : break;
                    default:
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {

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
}