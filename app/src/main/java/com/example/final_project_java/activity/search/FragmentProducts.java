package com.example.final_project_java.activity.search;

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
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.activities.product.ProductActivity;
import com.example.final_project_java.activity.activities.product.data.ProductData;
import com.example.final_project_java.activity.activities.product.data.ProductResponse;
import com.example.final_project_java.activity.search.data.SearchResponse;
import com.example.final_project_java.adapter.AdapterProducts;
import com.example.final_project_java.adapter.interfaces.ClickProducts;
import com.example.final_project_java.databinding.FragmentResultBinding;
import com.example.final_project_java.database.network.ApiRetrofit;
import com.example.final_project_java.database.network.RetrofitApis;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentProducts extends Fragment implements ClickProducts {
    FragmentResultBinding binding;
    List<ProductData> list;
    AdapterProducts adapter;

//    String text;
//    SearchResponse response;
//
//    public FragmentProducts(SearchResponse response) {
//        this.response = response;
//    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_result , container , false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         list = new ArrayList<>();
        products();
    }

    private void products() {
        ApiRetrofit.getapi().create(RetrofitApis.class).product().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(@NotNull Call<ProductResponse> call, @NotNull Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    list = response.body().getData();
                    adapter = new AdapterProducts(list, FragmentProducts.this,requireContext());
                    binding.recyclerResult.setLayoutManager(new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL));
                    binding.recyclerResult.setAdapter(adapter);

                } else {
                    Toast.makeText(requireContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<ProductResponse> call, @NotNull Throwable t) {
                Toast.makeText(requireContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onclick(int position) {
        Intent intent = new Intent(requireContext() , ProductActivity.class);
        intent.putExtra("product", list.get(position));
        startActivity(intent);
    }
}