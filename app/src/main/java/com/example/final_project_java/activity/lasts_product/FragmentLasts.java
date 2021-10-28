package com.example.final_project_java.activity.lasts_product;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.activities.ProductActivity;
import com.example.final_project_java.activity.activities.Result_activity;
import com.example.final_project_java.adapter.Adapter_search;
import com.example.final_project_java.data.Click_product_home;
import com.example.final_project_java.databinding.FragmentSearchBinding;
import com.example.final_project_java.network.ApiRetrofit;
import com.example.final_project_java.network.RetrofitApis;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentLasts extends Fragment implements Click_product_home {
    FragmentSearchBinding binding;
    NavController controller;
    List<DataLastProduct> dataLastProducts = new ArrayList<>();
    Adapter_search adapter;

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
        lasts();

        binding.searchSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext() , Result_activity.class));
            }
        });
    }

    private void lasts() {
        progressBar(true);
        ApiRetrofit.getapi().create(RetrofitApis.class).lasts().enqueue(new Callback<LastsResponse>() {
            @Override
            public void onResponse(@NotNull Call<LastsResponse> call, @NotNull Response<LastsResponse> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    dataLastProducts = response.body().getData();
                    adapter = new Adapter_search(dataLastProducts , requireContext() , FragmentLasts.this::onclick);
                    binding.recyclerviewSearch.setLayoutManager(new LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false));
                    binding.recyclerviewSearch.setAdapter(adapter);
                    progressBar(false);

                } else {
                    Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show();
                    progressBar(false);
                }
            }

            @Override
            public void onFailure(Call<LastsResponse> call, Throwable t)
            {
                Toast.makeText(requireContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                progressBar(false);
            }
        });
    }

    private void progressBar(Boolean loading) {
        if (loading) {
            binding.progress.setVisibility(View.VISIBLE);
            binding.recyclerviewSearch.setVisibility(View.GONE);
        } else {
            binding.recyclerviewSearch.setVisibility(View.VISIBLE);
            binding.progress.setVisibility(View.GONE);
        }
    }

    @Override
    public void onclick(int position) {
        Intent intent = new Intent(requireActivity() , ProductActivity.class);
        intent.putExtra("name", dataLastProducts.get(position).getItemName());
        intent.putExtra("price", dataLastProducts.get(position).getItemPrice());
        intent.putExtra("id", dataLastProducts.get(position).getItemId());
        startActivity(intent);
    }
}