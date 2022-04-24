package com.example.final_project_java.activity.details_data_by_id;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.denzcoskun.imageslider.models.SlideModel;
import com.example.final_project_java.R;
import com.example.final_project_java.activity.activities.product.AddToCartSuccessfullyActivity;
import com.example.final_project_java.activity.activities.product.ProductActivity;
import com.example.final_project_java.activity.activities.product.data.ProductData;
import com.example.final_project_java.activity.activities.product.data.ProductImages;
import com.example.final_project_java.activity.carts.cart_page.CartActivity;
import com.example.final_project_java.activity.carts.cart_page.addCartItem.AddToCartRequest;
import com.example.final_project_java.activity.carts.cart_page.addCartItem.data.AddToCartItemsResponse;
import com.example.final_project_java.activity.details_data_by_id.data.DataDetailes;
import com.example.final_project_java.activity.details_data_by_id.data.ResultDataByIdResponses;
import com.example.final_project_java.adapter.AdapterColor;
import com.example.final_project_java.adapter.AdapterSizes;
import com.example.final_project_java.adapter.tabs.TabProductLayout;
import com.example.final_project_java.database.network.ApiRetrofit;
import com.example.final_project_java.database.network.RetrofitApis;
import com.example.final_project_java.database.shared.Constant;
import com.example.final_project_java.database.shared.PreferenceManager;
import com.example.final_project_java.databinding.ActivityDetilesDataByIdBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsDataByIdActivity extends AppCompatActivity {
    private static final String TAG = "DetailsDataByIdActivity";
    ActivityDetilesDataByIdBinding binding;
    PreferenceManager preferenceManager;
    List<SlideModel> imageList;
    TabProductLayout tab_adapter;
    ProductData list;
    AddToCartRequest addToCartRequest;
    int id;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_detiles_data_by_id);
        preferenceManager = new PreferenceManager(DetailsDataByIdActivity.this);
        id = (int) getIntent().getSerializableExtra("id");
        imageList = new ArrayList<>();

        ApiRetrofit.getapi().create(RetrofitApis.class).getItemById(id).enqueue(new Callback<ResultDataByIdResponses>() {
            @Override
            public void onResponse(Call<ResultDataByIdResponses> call, Response<ResultDataByIdResponses> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    list = response.body().getData();
                    binding.tvItem.setText(list.getItemName());
                    binding.price.setText(list.getPrice());
                    Log.i(TAG, "onResponse: " + list);

                } else {
                    Log.i(TAG, "onResponse: no");
                }
            }

            @Override
            public void onFailure(Call<ResultDataByIdResponses> call, Throwable t) {
                Log.i(TAG, "onResponse: error");
            }
        });


        binding.imageadd.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                transferDataToCarts();
            }
        });
        addToCartRequest = new AddToCartRequest(AdapterSizes.size,AdapterColor.color,String.valueOf(id));

        // tabLayout();
        showImageSlider();
        intent();
    }

    private void tabLayout() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        tab_adapter = new TabProductLayout(fragmentManager , getLifecycle() , list);
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
                startActivity(new Intent(DetailsDataByIdActivity.this, CartActivity.class));
                finish();
            }
        });
        binding.share.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailsDataByIdActivity.this, AdapterSizes.size, Toast.LENGTH_SHORT).show();
                Toast.makeText(DetailsDataByIdActivity.this, AdapterColor.color, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showImageSlider(){
        imageList.add(new SlideModel(R.drawable.jacket1));
        imageList.add(new SlideModel(R.drawable.jacket2));
        imageList.add(new SlideModel(R.drawable.jacket3));
//        if (list.getImages() != null) {
//            for (int i = 0; i < list.getImages().size(); i++) {
//                // holder.binding.img.setImageResource(R.drawable.jacket1);
////                imageList.add(new SlideModel(list.getImages().get(i).getImage()));
//                imageList.add(new SlideModel(R.drawable.jacket1));
//            }
//            binding.imageslider.setImageList(imageList,true);
//        } else {
//            imageList.add(new SlideModel(R.drawable.error));
//        }
    }

    private void transferDataToCarts() {
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
                            startActivity(new Intent(DetailsDataByIdActivity.this , AddToCartSuccessfullyActivity.class));

                        } else {
                            Log.i(TAG, "onResponse: click 4" + response.message());
                        }
                        Log.i(TAG, "onResponse: click 4" + response.message());
                    }

                    @Override
                    public void onFailure(Call<AddToCartItemsResponse> call, Throwable t) {
                        Log.i(TAG, "transferDataToCarts: click 5 ");
                        Toast.makeText(DetailsDataByIdActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

}