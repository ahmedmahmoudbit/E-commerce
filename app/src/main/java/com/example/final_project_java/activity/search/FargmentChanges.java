package com.example.final_project_java.activity.search;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.activities.product.data.ProductData;
import com.example.final_project_java.adapter.AdapterColor;
import com.example.final_project_java.adapter.AdapterSizes;
import com.example.final_project_java.databinding.FragmentChangeproductBinding;

public class FargmentChanges extends Fragment {
    private static final String TAG = "Fragment_changeproduct";
    FragmentChangeproductBinding binding;
    ProductData productData;

    public FargmentChanges(ProductData productData) {
        this.productData = productData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_changeproduct, container, false);
        return binding.getRoot();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        change_color();
        change_size();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void change_color() {
        AdapterColor adapter;
        adapter = new AdapterColor(productData.getColor(), requireContext());
        binding.rvColor.setLayoutManager(new LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false));
        binding.rvColor.setAdapter(adapter);

    }

    private void change_size() {
        AdapterSizes adapter;
        adapter = new AdapterSizes(productData.getSizes(), requireContext());
        binding.rvSize.setLayoutManager(new LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false));
        binding.rvSize.setAdapter(adapter);

    }

}