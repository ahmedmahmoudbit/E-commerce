package com.example.final_project_java;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.final_project_java.activity.activities.ProductActivity;
import com.example.final_project_java.adapter.Adapter_categories_item;
import com.example.final_project_java.data.Click_product_home;
import com.example.final_project_java.data.Data_category_item;
import com.example.final_project_java.databinding.FragmentProductsprofileBinding;
import com.example.final_project_java.shared.Constant;
import com.example.final_project_java.shared.PreferenceManager;

import java.util.ArrayList;

public class Fragment_productsprofile extends Fragment implements Click_product_home {
   FragmentProductsprofileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_productsprofile , container , false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerbag();
        recycler_jacket();
    }

    private void recyclerbag() {

        ArrayList<Data_category_item> arrayList = new ArrayList<>();
        Adapter_categories_item adapter;

        arrayList.add(new Data_category_item("bag 1" , "25.99$" , R.drawable.bage3));
        arrayList.add(new Data_category_item("bag 1" , "25.99$" , R.drawable.bage4));
        arrayList.add(new Data_category_item("bag 1" , "25.99$" , R.drawable.bage5));
        arrayList.add(new Data_category_item("bag 1" , "25.99$" , R.drawable.bage1));

        adapter = new Adapter_categories_item(arrayList , requireContext() , this);
        binding.recyclerBags.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL , false));
        binding.recyclerBags.setAdapter(adapter);

    }
    private void recycler_jacket() {

        ArrayList<Data_category_item> arrayList = new ArrayList<>();
        Adapter_categories_item adapter;

        arrayList.add(new Data_category_item("bag 1" , "25.99$" , R.drawable.jacket1));
        arrayList.add(new Data_category_item("bag 1" , "25.99$" , R.drawable.jacket3));
        arrayList.add(new Data_category_item("bag 1" , "25.99$" , R.drawable.jacket2));
        arrayList.add(new Data_category_item("bag 1" , "25.99$" , R.drawable.jacket4));

        adapter = new Adapter_categories_item(arrayList , requireContext() , this);
        binding.recyclerJacket.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL , false));
        binding.recyclerJacket.setAdapter(adapter);

    }

    @Override
    public void onclick(int position) {
        startActivity(new Intent(requireActivity() , ProductActivity.class));
    }

}