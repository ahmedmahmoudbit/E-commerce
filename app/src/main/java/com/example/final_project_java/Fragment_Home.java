package com.example.final_project_java;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.final_project_java.adapter.Adapter_categories;
import com.example.final_project_java.adapter.Adapter_categories_item;
import com.example.final_project_java.data.Click_product_home;
import com.example.final_project_java.data.Data_categories;
import com.example.final_project_java.data.Data_category_item;
import com.example.final_project_java.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class Fragment_Home extends Fragment implements Click_product_home {
    NavController navController;
    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment__home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        category();
        item();
        intent();

    }

    private void category() {
        Adapter_categories adapter;
        ArrayList<Data_categories> categories = new ArrayList<>();

        categories.add(new Data_categories("Apparel" , R.drawable.apparel));
        categories.add(new Data_categories("Beauty" , R.drawable.beauty));
        categories.add(new Data_categories("Shoes" , R.drawable.shoes));
        categories.add(new Data_categories("Electronics" , R.drawable.electronics));
        categories.add(new Data_categories("Furniture" , R.drawable.furniture));
        categories.add(new Data_categories("Stationary" , R.drawable.stationary));
        categories.add(new Data_categories("Home" , R.drawable.home));

        adapter = new Adapter_categories(categories,requireContext());
        binding.recyclerViewCategory.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL , false));
        binding.recyclerViewCategory.setAdapter(adapter);
    }

    private void item() {
        Adapter_categories_item adapter;
        ArrayList<Data_category_item> categories = new ArrayList<>();

        categories.add(new Data_category_item("t-shirt","26.99$",R.drawable.ttshirt));
        categories.add(new Data_category_item("t-shirt","28.99$",R.drawable.ttshirt));
        categories.add(new Data_category_item("t-shirt","59.99$",R.drawable.ttshirt));
        categories.add(new Data_category_item("t-shirt","78.99$",R.drawable.ttshirt));

        adapter = new Adapter_categories_item(categories,requireContext(),this);
        binding.recyclerViewItem.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL , false));
        binding.recyclerViewItem.setAdapter(adapter);

    }

    private void intent() {
        binding.shopProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity() , Shope_profie_activity.class));
            }
        });
    }

    @Override
    public void onclick(int position) {
        startActivity(new Intent(requireActivity() , ProductActivity.class));
    }
}