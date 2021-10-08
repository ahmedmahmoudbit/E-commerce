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
import com.example.final_project_java.activity.Result_activity;
import com.example.final_project_java.adapter.Adapter_search;
import com.example.final_project_java.databinding.FragmentSearchBinding;
import com.example.final_project_java.network.ApiRetrofit;
import com.example.final_project_java.network.RetrofitApis;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_search extends Fragment {
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

        ApiRetrofit.getapi().create(RetrofitApis.class).lasts().enqueue(new Callback<LastsResponse>() {
            @Override
            public void onResponse(@NotNull Call<LastsResponse> call, @NotNull Response<LastsResponse> response) {
                Toast.makeText(requireContext(), "Yes", Toast.LENGTH_SHORT).show();

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    dataLastProducts = response.body().getData();
                    adapter = new Adapter_search(dataLastProducts , requireContext());
                    binding.recyclerviewSearch.setLayoutManager(new LinearLayoutManager(requireContext()));
                    binding.recyclerviewSearch.setAdapter(adapter);
                } else {

                    Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<LastsResponse> call, Throwable t)
            {
                Toast.makeText(requireContext(), "No", Toast.LENGTH_SHORT).show();
            }
        });
    }

}