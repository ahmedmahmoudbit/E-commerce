package com.example.final_project_java.activity.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.final_project_java.adapter.tabs.FragmentAdapter;
import com.example.final_project_java.R;
import com.example.final_project_java.activity.search.data.SearchResponse;
import com.example.final_project_java.database.network.ApiRetrofit;
import com.example.final_project_java.database.network.RetrofitApis;
import com.example.final_project_java.databinding.ActivitySearchResultBinding;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    ActivitySearchResultBinding binding;
    FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this , R.layout.activity_search_result);
         buttons();

         binding.search.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
                 Handler handler = new Handler();
                 handler.postDelayed(new Runnable() {
                     @Override
                     public void run() {
                        search();
                     }
                 },500);
             }

             @Override
             public void afterTextChanged(Editable s) {
             }
         });

        tabLayout();

    }

    // show items in viewPager .
    private void tabLayout() {
//        SearchResponse response
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
        binding.filter.setOnClickListener(v -> {
            // get Custom Dialog from Dialog_Fragment
            getSupportFragmentManager().beginTransaction().add(new FilterDialog() , "dialog").commit();

        });
        // finish activity and close .
        binding.back.setOnClickListener(v -> finish());
    }

    // search in api product
    private void search() {
        String search = binding.search.getText().toString().trim();

        if(!search.isEmpty()) {
            ApiRetrofit.getapi().create(RetrofitApis.class).search(search).enqueue(new Callback<SearchResponse>() {
                @Override
                public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                    if(response.body().isStatus()) {
                        tabLayout();
                    }
                }

                @Override
                public void onFailure(Call<SearchResponse> call, Throwable t) {

                }
            });
        }
    }

}