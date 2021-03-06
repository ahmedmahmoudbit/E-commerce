package com.example.final_project_java.activity.activities.shop_profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.activities.product.ProductActivity;
import com.example.final_project_java.adapter.interfaces.ClickProducts;
import com.example.final_project_java.databinding.FragmentProductsprofileBinding;

public class Fragment_productsprofile extends Fragment implements ClickProducts {
   FragmentProductsprofileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_productsprofile , container , false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//         recyclerbag();
//         recycler_jacket();
    }

//    private void recyclerbag() {
//
//        ArrayList<Data_category_item> arrayList = new ArrayList<>();
//        AdapterHomeItems adapter;
//
//        arrayList.add(new Data_category_item("bag 1" , "25.99$" , R.drawable.bage3));
//        arrayList.add(new Data_category_item("bag 1" , "25.99$" , R.drawable.bage4));
//        arrayList.add(new Data_category_item("bag 1" , "25.99$" , R.drawable.bage5));
//        arrayList.add(new Data_category_item("bag 1" , "25.99$" , R.drawable.bage1));
//
//        adapter = new AdapterHomeItems(arrayList , requireContext() , this);
//        binding.recyclerBags.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL , false));
//        binding.recyclerBags.setAdapter(adapter);
//
//    }

//    private void recycler_jacket() {
//
//        ArrayList<Data_category_item> arrayList = new ArrayList<>();
//        AdapterHomeItems adapter;
//
//        arrayList.add(new Data_category_item("bag 1" , "25.99$" , R.drawable.jacket1));
//        arrayList.add(new Data_category_item("bag 1" , "25.99$" , R.drawable.jacket3));
//        arrayList.add(new Data_category_item("bag 1" , "25.99$" , R.drawable.jacket2));
//        arrayList.add(new Data_category_item("bag 1" , "25.99$" , R.drawable.jacket4));
//
//        adapter = new AdapterHomeItems(arrayList , requireContext() , this);
//        binding.recyclerJacket.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL , false));
//        binding.recyclerJacket.setAdapter(adapter);
//
//    }

    @Override
    public void onclick(int position) {
        startActivity(new Intent(requireActivity() , ProductActivity.class));
    }

}