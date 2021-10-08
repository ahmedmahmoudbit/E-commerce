package com.example.final_project_java.activity.carts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.final_project_java.R;
import com.example.final_project_java.adapter.Adapter_carts;
import com.example.final_project_java.data.Data_carts;
import com.example.final_project_java.databinding.FragmentCartsBinding;

import java.util.ArrayList;

public class Fragment_carts extends Fragment {
    FragmentCartsBinding binding;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_carts, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        carts();
    }

    private void carts() {
        Adapter_carts adapter;
        ArrayList<Data_carts> categories = new ArrayList<>();

        categories.add(new Data_carts("item1","...","25.99$",R.drawable.ttshirt));
        categories.add(new Data_carts("item2","...","25.99$",R.drawable.ttshirt));
        categories.add(new Data_carts("item1","...","25.99$",R.drawable.ttshirt));
        categories.add(new Data_carts("item1","...","25.99$",R.drawable.ttshirt));
        categories.add(new Data_carts("item1","...","25.99$",R.drawable.ttshirt));

        adapter = new Adapter_carts(categories,requireContext());
        binding.recyclerViewCarts.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerViewCarts.setAdapter(adapter);

        binding.buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_fragment_carts_to_fragment_checkout);
            }
        });

    }

}