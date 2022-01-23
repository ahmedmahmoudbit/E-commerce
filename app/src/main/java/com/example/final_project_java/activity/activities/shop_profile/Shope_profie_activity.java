package com.example.final_project_java.activity.activities.shop_profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

import com.example.final_project_java.Fragment_homeprofile;
import com.example.final_project_java.R;
import com.example.final_project_java.activity.activities.product.FragmentReviews;
import com.example.final_project_java.databinding.ActivityShopeProfieActivityBinding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class Shope_profie_activity extends AppCompatActivity {
    ActivityShopeProfieActivityBinding binding;
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =
                DataBindingUtil.setContentView(this, R.layout.activity_shope_profie_activity);
        back();

        // Shop profile activity .
        select_fragment();
        if (savedInstanceState==null) {
            binding.animatedbottombar.selectTabById(R.id.home_prfileshop , true);
            fragmentManager = getSupportFragmentManager();
            Fragment_homeprofile fragment_homeprofile = new Fragment_homeprofile();
            fragmentManager.beginTransaction().replace(R.id.fragment_container , fragment_homeprofile).commit();
        }

    }

    private void back() {
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void select_fragment() {

        binding.animatedbottombar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int lastindex, @Nullable AnimatedBottomBar.Tab lastTab, int newIndex, @NotNull AnimatedBottomBar.Tab newTab) {
                Fragment fragment = null;
                switch (newTab.getId()) {
                    case R.id.home_prfileshop:
                        fragment = new Fragment_homeprofile();
                        break;
                    case R.id.products_prfileshop:
                        fragment = new Fragment_productsprofile();
                        break;
                    case R.id.reviews_prfileshop:
                        fragment = new FragmentReviews();
                        break;
                }

                if (fragment != null) {
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
                }

            }

            @Override
            public void onTabReselected(int i, @NotNull AnimatedBottomBar.Tab tab) {

            }
        });

    }

}