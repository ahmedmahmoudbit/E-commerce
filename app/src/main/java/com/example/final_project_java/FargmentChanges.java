package com.example.final_project_java;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.final_project_java.activity.search.ProductData;
import com.example.final_project_java.activity.search.ProductSize;
import com.example.final_project_java.adapter.AdapterColor;
import com.example.final_project_java.adapter.AdapterSizes;
import com.example.final_project_java.data.Data_color;
import com.example.final_project_java.databinding.FragmentChangeproductBinding;

import java.util.ArrayList;
import java.util.List;

public class FargmentChanges extends Fragment {
    private static final String TAG = "Fragment_changeproduct";
    FragmentChangeproductBinding binding;
    ArrayList<ProductSize> sizesItem;
    AdapterSizes adapter;
    String size , color , id;
    ProductData productData;

    public FargmentChanges(ProductData productData) {
        this.productData = productData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_changeproduct, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sizesItem = new ArrayList<>();
        change_color();
        change_size();

    }

    private void change_color() {
        AdapterColor adapter;

//        arrayList.add(new Data_color(R.color.red_based));
//        arrayList.add(new Data_color(R.color.teal_200));
//        arrayList.add(new Data_color(R.color.purple_500));

        adapter = new AdapterColor(productData.getColor(), requireContext());
        binding.rvColor.setLayoutManager(new LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false));
        binding.rvColor.setAdapter(adapter);

    }

    private void change_size() {


    }

}