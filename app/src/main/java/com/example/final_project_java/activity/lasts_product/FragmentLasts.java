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
import com.example.final_project_java.activity.activities.product.ProductActivity;
import com.example.final_project_java.activity.activities.product.data.ProductData;
import com.example.final_project_java.activity.search.SearchActivity;
import com.example.final_project_java.adapter.AdapterLasts;
import com.example.final_project_java.adapter.interfaces.ClickProducts;
import com.example.final_project_java.databinding.FragmentLastsBinding;
import com.example.final_project_java.database.network.ApiRetrofit;
import com.example.final_project_java.database.network.RetrofitApis;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentLasts extends Fragment implements ClickProducts {
    FragmentLastsBinding binding;
    NavController controller;
    List<ProductData> list;
    AdapterLasts adapter;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lasts, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        list = new ArrayList<>();
        lasts();

        binding.searchSearch.setOnClickListener(v -> startActivity(new Intent(requireActivity() , SearchActivity.class)));
    }

    private void lasts() {
        progressBar(true);
        ApiRetrofit.getapi().create(RetrofitApis.class).lasts().enqueue(new Callback<LastsResponse>() {
            @Override
            public void onResponse(@NotNull Call<LastsResponse> call, @NotNull Response<LastsResponse> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    list = response.body().getData();
                    adapter = new AdapterLasts(list , requireContext() , FragmentLasts.this);
                    binding.recyclerviewLasts.setLayoutManager(new LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false));
                    binding.recyclerviewLasts.setAdapter(adapter);

                } else {
                    Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show();
                }
                progressBar(false);
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
            binding.recyclerviewLasts.setVisibility(View.GONE);
        } else {
            binding.recyclerviewLasts.setVisibility(View.VISIBLE);
            binding.progress.setVisibility(View.GONE);
        }
    }

    @Override
    public void onclick(int position) {
        Intent intent = new Intent(requireContext() , ProductActivity.class);
        intent.putExtra("product", list.get(position));
        startActivity(intent);
    }
}