package com.example.final_project_java;

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

import com.example.final_project_java.activity.lasts_product.LastsResponse;
import com.example.final_project_java.adapter.Adapter_color;
import com.example.final_project_java.adapter.AdapterSizes;
import com.example.final_project_java.data.Data_color;
import com.example.final_project_java.data.Data_size;
import com.example.final_project_java.databinding.FragmentChangeproductBinding;
import com.example.final_project_java.network.ApiRetrofit;
import com.example.final_project_java.network.RetrofitApis;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FargmentChangeSizes extends Fragment {
    private static final String TAG = "Fragment_changeproduct";
    FragmentChangeproductBinding binding;
    List<Data_size> sizesItem;

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
        AdapterSizes adapter;

//        ApiRetrofit.getapi().create(RetrofitApis.class).lasts().enqueue(new Callback<LastsResponse>() {
//            @Override
//            public void onResponse(Call<LastsResponse> call, Response<LastsResponse> response) {
//                if (response.isSuccessful()) {
//                    sizesItem = response.body().getData();
//                    adapter = new AdapterSizes(sizesItem, requireContext());
//                    binding.rvSize.setLayoutManager(new LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false));
//                    binding.rvSize.setAdapter(adapter);
//
//                    Log.i(TAG, "onResponse: ");
//                } else {
//                    Log.i(TAG, "onResponse: ");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LastsResponse> call, Throwable t) {
//                Log.i(TAG, "onResponse: ");
//            }
//        });


    }

//    @Override
//    public void onclick(int position) {
//        Intent intent = new Intent(requireActivity() , ProductActivity.class);
//        intent.putExtra("size", dataLastProducts.get(position).getSizes());
//        intent.putExtra("color", dataLastProducts.get(position).getColor());
//        startActivity(intent);
//    }

}