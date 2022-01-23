package com.example.final_project_java;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.final_project_java.activity.activities.product.ProductActivity;
import com.example.final_project_java.activity.activities.product.data.ProductData;
import com.example.final_project_java.adapter.interfaces.ClickProducts;
import com.example.final_project_java.databinding.FragmentHomeprofileBinding;

import java.util.ArrayList;

public class Fragment_homeprofile extends Fragment implements ClickProducts {
    FragmentHomeprofileBinding binding;
    ArrayList<ProductData> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_homeprofile, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        recycler_item();

    }

//    private void recycler_item() {
//        arrayList = new ArrayList<>();
//        AdapterHomeItems adapter;
//
//        arrayList.add(new Data_category_item("bage", "25.26$", R.drawable.jacket1));
//        arrayList.add(new Data_category_item("bage", "25.26$", R.drawable.jacket2));
//        arrayList.add(new Data_category_item("bage", "25.26$", R.drawable.jacket3));
//        arrayList.add(new Data_category_item("bage", "25.26$", R.drawable.jacket4));
//        arrayList.add(new Data_category_item("bage", "25.26$", R.drawable.jacket5));
//        arrayList.add(new Data_category_item("bage", "25.26$", R.drawable.bage1));
//        arrayList.add(new Data_category_item("bage", "25.26$", R.drawable.bage2));
//        arrayList.add(new Data_category_item("bage", "25.26$", R.drawable.bage3));
//        arrayList.add(new Data_category_item("bage", "25.26$", R.drawable.bage4));
//
//        adapter = new AdapterHomeItems(arrayList, requireContext(), this);
//        binding.items.setLayoutManager(new GridLayoutManager(requireContext(), 3));
//        binding.items.setAdapter(adapter);
//
//    }

    @Override
    public void onclick(int position) {
        startActivity(new Intent(requireActivity() , ProductActivity.class));
    }

}
