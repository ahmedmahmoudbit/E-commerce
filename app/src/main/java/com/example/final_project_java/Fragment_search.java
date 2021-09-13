package com.example.final_project_java;

import android.content.Intent;
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

import com.example.final_project_java.activity.Result_activity;
import com.example.final_project_java.adapter.Adapter_search;
import com.example.final_project_java.data.Data_search;
import com.example.final_project_java.databinding.FragmentSearchBinding;

import java.util.ArrayList;

public class Fragment_search extends Fragment {
    FragmentSearchBinding binding;
    NavController controller;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        search_last();

        binding.searchRecently.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext() , Result_activity.class));
            }
        });

    }

    private void search_last() {
        Adapter_search adapter;
        ArrayList<Data_search> arrayList = new ArrayList<>();

        arrayList.add(new Data_search("short", "58.99$", R.drawable.ttshirt));
        arrayList.add(new Data_search("backpack", "58.99$", R.drawable.backpack));
        arrayList.add(new Data_search("t-shirt", "58.99$", R.drawable.ttshirt));

        adapter = new Adapter_search(arrayList , requireContext());
        binding.recyclerviewSearch.setLayoutManager(new LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false) );
        binding.recyclerviewSearch.setAdapter(adapter);


    }

}