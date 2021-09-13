package com.example.final_project_java;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.final_project_java.adapter.Adapter_result;
import com.example.final_project_java.data.Data_result;
import com.example.final_project_java.databinding.FragmentResultBinding;

import java.util.ArrayList;

public class Fragment_result extends Fragment {

    FragmentResultBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_result , container , false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        products();


    }

    private void products() {
        Adapter_result adapter;
        ArrayList<Data_result> arrayList = new ArrayList<>();

        arrayList.add(new Data_result("jatet","58.65$" , "     4.8",R.drawable.jacket4));
        arrayList.add(new Data_result("jatet","58.65$" , "     4.8",R.drawable.jacket2));
        arrayList.add(new Data_result("jatet","58.65$" , "     4.8",R.drawable.jacket5));
        arrayList.add(new Data_result("jatet","58.65$" , "     4.8",R.drawable.jacket1));
        arrayList.add(new Data_result("jatet","58.65$" , "     4.8",R.drawable.jacket5));
        arrayList.add(new Data_result("jatet","58.65$" , "     4.8",R.drawable.jacket4));
        arrayList.add(new Data_result("jatet","58.65$" , "     4.8",R.drawable.jacket7));
        arrayList.add(new Data_result("jatet","58.65$" , "     4.8",R.drawable.jacket6));

        adapter = new Adapter_result(arrayList , requireContext());
        binding.recyclerResult.setLayoutManager(new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL));
        binding.recyclerResult.setAdapter(adapter);


    }

}