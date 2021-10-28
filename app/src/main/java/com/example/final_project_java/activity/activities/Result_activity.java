package com.example.final_project_java.activity.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.example.final_project_java.Dialog_Fragment;
import com.example.final_project_java.FragmentAdapter;
import com.example.final_project_java.R;
import com.example.final_project_java.databinding.ActivitySearchResultBinding;
import com.google.android.material.tabs.TabLayout;

public class Result_activity extends AppCompatActivity {
    ActivitySearchResultBinding binding;
    FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this , R.layout.activity_search_result);
         tabLayout();
         buttons();

         // binding.searchSearch.Add

    }

    private void tabLayout() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new FragmentAdapter(fragmentManager , getLifecycle());
        binding.viewpager2.setAdapter(adapter);

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Best mach"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("TOP RATED"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("PRICE LOW-HIGH"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("PRICE HIGH-LOW"));

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewpager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        binding.viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position));
            }
        });
    }

    private void buttons() {

        binding.filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().add(new Dialog_Fragment() , "dialog").commit();

            }
        });

        binding.searchResultBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

//    private void filter(String text) {
//        for ()
//    }

}