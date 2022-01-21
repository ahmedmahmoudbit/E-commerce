package com.example.final_project_java.activity.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.denzcoskun.imageslider.models.SlideModel;
import com.example.final_project_java.AddToCartSuccessfullyActivity;
import com.example.final_project_java.CartActivity;
import com.example.final_project_java.R;
import com.example.final_project_java.activity.carts.add_cart.addCartItem.AddToCartItemsResponse;
import com.example.final_project_java.activity.carts.add_cart.addCartItem.AddToCartRequest;
import com.example.final_project_java.activity.search.ProductData;
import com.example.final_project_java.adapter.AdapterColor;
import com.example.final_project_java.adapter.AdapterSizes;
import com.example.final_project_java.databinding.ActivityProductBinding;
import com.example.final_project_java.network.ApiRetrofit;
import com.example.final_project_java.network.RetrofitApis;
import com.example.final_project_java.shared.Constant;
import com.example.final_project_java.shared.PreferenceManager;
import com.example.final_project_java.tabs.TabProductLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {
    private static final String TAG = "ProductActivity";
    ActivityProductBinding binding;
    List<SlideModel> imageList = new ArrayList<>();
    TabProductLayout tab_adapter;
    PreferenceManager preferenceManager;
    ProductData productData;
    AddToCartRequest addToCartRequest;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_product);
        preferenceManager = new PreferenceManager(this);
        productData = (ProductData) getIntent().getSerializableExtra("product");
        binding.tvItem.setText(productData.getItemName());
        binding.price.setText(productData.getPrice());
        id = String.valueOf(productData.getItemId());

        tabLayout();
        intent();
        showImageSlider();
        transferDataToCarts();

    }


    private void tabLayout() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        tab_adapter = new TabProductLayout(fragmentManager , getLifecycle() , productData);
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

    private void intent() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        binding.imgCarts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductActivity.this, CartActivity.class));
                finish();
            }
        });
        binding.share.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductActivity.this, AdapterSizes.size, Toast.LENGTH_SHORT).show();
                Toast.makeText(ProductActivity.this, AdapterColor.color, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showImageSlider(){
        for (int i = 0; i < productData.getImages().size(); i++) {
            imageList.add(new SlideModel(productData.getImages().get(i).getImage()));
        }
        binding.imageslider.setImageList(imageList,true);
    }

    private void transferDataToCarts() {

        addToCartRequest = new AddToCartRequest(AdapterSizes.size,AdapterColor.color,id);

        String getToken =  "Bearer " + preferenceManager.getString(Constant.ACCESS_TOKEN);

        Log.i(TAG, "transferDataToCarts: click ");
        binding.imageadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "transferDataToCarts: click 2");
                ApiRetrofit.getapi().create(RetrofitApis.class).add_to_cart(getToken , addToCartRequest).enqueue(new Callback<AddToCartItemsResponse>() {

                    @Override
                    public void onResponse(Call<AddToCartItemsResponse> call, Response<AddToCartItemsResponse> response) {
                        if (response.isSuccessful()) {
                            Log.i(TAG, "transferDataToCarts: click 3 ");
                            startActivity(new Intent(ProductActivity.this , AddToCartSuccessfullyActivity.class));

                        } else {
                            Log.i(TAG, "onResponse: click 4" + response.message());
                        }
                        Log.i(TAG, "onResponse: click 4" + response.message());
                    }

                    @Override
                    public void onFailure(Call<AddToCartItemsResponse> call, Throwable t) {
                        Log.i(TAG, "transferDataToCarts: click 5 ");
                        Toast.makeText(ProductActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

}