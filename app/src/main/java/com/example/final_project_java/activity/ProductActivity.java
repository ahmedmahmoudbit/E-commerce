package com.example.final_project_java.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.final_project_java.R;
import com.example.final_project_java.activity.carts.ActivityCarts;
import com.example.final_project_java.activity.carts.Fragment_carts;
import com.example.final_project_java.databinding.ActivityProductBinding;
import com.example.final_project_java.tabs.Tab_product;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    ActivityProductBinding binding;
    List<SlideModel> imageList = new ArrayList<>();
    Tab_product tab_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_product);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("book_2");
            binding.tvItem.setText(value);
        }

        // image slider
        imageList.add(new SlideModel(R.drawable.bage1, "Elephants and tigers may become extinct."));
        imageList.add(new SlideModel(R.drawable.bage2, "Elephants and tigers may become extinct."));
        imageList.add(new SlideModel(R.drawable.bage3, "Elephants and tigers may become extinct."));
        binding.imageslider.setImageList(imageList,true);

        tabLayout();
        intent_carts();
        intent_back();

    }

    private void tabLayout() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        tab_adapter = new Tab_product(fragmentManager , getLifecycle());
        binding.viewpager2.setAdapter(tab_adapter);

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Product"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Details"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Reviews"));

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

    private void intent_carts() {
        binding.imgCarts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductActivity.this, ActivityCarts.class));
                finish();
            }
        });
    }

    private void intent_back() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductActivity.this , Home_activity.class));
                finish();
            }
        });
    }

}