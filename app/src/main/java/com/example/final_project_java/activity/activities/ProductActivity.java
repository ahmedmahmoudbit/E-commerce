package com.example.final_project_java.activity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.denzcoskun.imageslider.models.SlideModel;
import com.example.final_project_java.R;
import com.example.final_project_java.activity.carts.ActivityCarts;
import com.example.final_project_java.activity.carts.add_cart.AddToCartResponse;
import com.example.final_project_java.databinding.ActivityProductBinding;
import com.example.final_project_java.network.ApiRetrofit;
import com.example.final_project_java.network.RetrofitApis;
import com.example.final_project_java.shared.Constant;
import com.example.final_project_java.shared.PreferenceManager;
import com.example.final_project_java.tabs.Tab_product;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {
    private static final String TAG = "ProductActivity";
    ActivityProductBinding binding;
    List<SlideModel> imageList = new ArrayList<>();
    Tab_product tab_adapter;
    PreferenceManager preferenceManager;
    String id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_product);
        preferenceManager = new PreferenceManager(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("name");
            String price = extras.getString("price");
            id = String.valueOf(extras.getInt("id"));
            binding.tvItem.setText(name);
            binding.price.setText(price);
        }

        // image slider
        imageList.add(new SlideModel(R.drawable.bage1, "Elephants and tigers may become extinct."));
        imageList.add(new SlideModel(R.drawable.bage2, "Elephants and tigers may become extinct."));
        imageList.add(new SlideModel(R.drawable.bage3, "Elephants and tigers may become extinct."));
        binding.imageslider.setImageList(imageList,true);

        tabLayout();
        intent_carts();
        intent_back();
        intent_add_to_cart();
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

    private void intent_add_to_cart() {
        String getToken =  "Bearer " + preferenceManager.getString(Constant.ACCESS_TOKEN);
        Log.i(TAG, "intent_add_to_cart: test");

        binding.imageadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiRetrofit.getapi().create(RetrofitApis.class).add_to_cart(getToken , id).enqueue(new Callback<AddToCartResponse>() {

                    @Override
                    public void onResponse(Call<AddToCartResponse> call, Response<AddToCartResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(ProductActivity.this, "Add Successful", Toast.LENGTH_SHORT).show();

                        } else {
                            try {
                                Log.i(TAG, "onResponse: " + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AddToCartResponse> call, Throwable t) {
                        Toast.makeText(ProductActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}