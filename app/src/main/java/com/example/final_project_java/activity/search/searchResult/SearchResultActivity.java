package com.example.final_project_java.activity.search.searchResult;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.details_data_by_id.DetailsDataByIdActivity;
import com.example.final_project_java.activity.search.data.DataItem;
import com.example.final_project_java.activity.search.searchResult.data.AdapterSearchResult;
import com.example.final_project_java.adapter.AdapterProducts;
import com.example.final_project_java.adapter.interfaces.ClickProducts;
import com.example.final_project_java.databinding.ActivitySearchResult2Binding;

import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity implements ClickProducts {
    ActivitySearchResult2Binding binding;
    private static final String TAG = "SearchResultActivity";
    ArrayList<DataItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_search_result2);

        list = getIntent().getParcelableArrayListExtra("search");
        binding.recycler.setAdapter(new AdapterSearchResult(list , this,this));
        binding.recycler.setLayoutManager(new GridLayoutManager(this,2));


    }

    @Override
    public void onclick(int position) {
        Intent i = new Intent(SearchResultActivity.this , DetailsDataByIdActivity.class);
        i.putExtra("id",position);
        startActivity(i);
    }
}