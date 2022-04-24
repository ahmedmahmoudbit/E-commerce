package com.example.final_project_java.activity.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.example.final_project_java.activity.search.data.DataItem;
import com.example.final_project_java.activity.search.data.SearchResponses;
import com.example.final_project_java.activity.search.searchResult.SearchResultActivity;
import com.example.final_project_java.adapter.tabs.FragmentAdapter;
import com.example.final_project_java.R;
import com.example.final_project_java.database.network.ApiRetrofit;
import com.example.final_project_java.database.network.RetrofitApis;
import com.example.final_project_java.databinding.ActivitySearchResultBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    ActivitySearchResultBinding binding;
    FragmentAdapter adapter;
    ArrayList<DataItem> list;
    private static final String TAG = "SearchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this , R.layout.activity_search_result);
        buttons();


        binding.search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search(binding.search.getText().toString());
                }
                return false;
            }
        });

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Best mach"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("TOP RATED"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("PRICE LOW-HIGH"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("PRICE HIGH-LOW"));

//         binding.search.addTextChangedListener(new TextWatcher() {
//             @Override
//             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//             }
//
//             @Override
//             public void onTextChanged(CharSequence s, int start, int before, int count) {
////                 Handler handler = new Handler();
////                 handler.postDelayed(new Runnable() {
////                     @Override
////                     public void run() {
////                        search();
////                     }
////                 },500);
//             }
//
//             @Override
//             public void afterTextChanged(Editable s) {
//                 search();
//             }
//         });

        tabLayout();

    }

    // show items in viewPager .
    private void tabLayout() {
        // SearchResponse response
        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new FragmentAdapter(fragmentManager , getLifecycle());
        binding.viewpager2.setAdapter(adapter);

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
    private void search(String productName) {

        if (productName.isEmpty()) {
            productName = "";
        }
        if(!productName.isEmpty()) {
            ApiRetrofit.getapi().create(RetrofitApis.class).search(productName).enqueue(new Callback<SearchResponses>() {
                @Override
                public void onResponse(Call<SearchResponses> call, Response<SearchResponses> response) {
                    if(response.isSuccessful()) {
                        list = response.body().getData();
                        Intent i = new Intent(SearchActivity.this, SearchResultActivity.class);
                        i.putParcelableArrayListExtra("search", list);
                            startActivity(i);

                            Log.i(TAG, "onResponse: yes" + response.body().getData().size());

                        tabLayout();
                    }
                }

                @Override
                public void onFailure(Call<SearchResponses> call, Throwable t) {
                    Log.i(TAG, "onResponse: Failure");

                }
            });
        }
    }

}