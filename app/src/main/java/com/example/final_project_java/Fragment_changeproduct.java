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

import com.example.final_project_java.adapter.Adapter_color;
import com.example.final_project_java.adapter.Adapter_size;
import com.example.final_project_java.data.Data_color;
import com.example.final_project_java.data.Data_size;
import com.example.final_project_java.databinding.FragmentChangeproductBinding;

import java.util.ArrayList;

public class Fragment_changeproduct extends Fragment {
    FragmentChangeproductBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_changeproduct, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        change_color();
        change_size();
    }

    private void change_color() {
        ArrayList<Data_color> arrayList = new ArrayList<>();
        Adapter_color adapter;

        arrayList.add(new Data_color(R.color.red_based));
        arrayList.add(new Data_color(R.color.teal_200));
        arrayList.add(new Data_color(R.color.design_default_color_secondary_variant));

        adapter = new Adapter_color(arrayList, requireContext());
        binding.rvColor.setLayoutManager(new LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false));
        binding.rvColor.setAdapter(adapter);

    }

    private void change_size() {
        ArrayList<Data_size> arrayList = new ArrayList<>();
        Adapter_size adapter;

        arrayList.add(new Data_size("9.5"));
        arrayList.add(new Data_size("6.7"));
        arrayList.add(new Data_size("5.6"));
        arrayList.add(new Data_size("8.6"));
        arrayList.add(new Data_size("9.6"));

        adapter = new Adapter_size(arrayList, requireContext());
        binding.rvSize.setLayoutManager(new LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false));
        binding.rvSize.setAdapter(adapter);

    }
}