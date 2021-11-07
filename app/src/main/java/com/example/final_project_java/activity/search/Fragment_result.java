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
import com.example.final_project_java.activity.activities.ProductActivity;
import com.example.final_project_java.adapter.Adapter_result;
import com.example.final_project_java.data.Click_product_home;
import com.example.final_project_java.data.Data_result;
import com.example.final_project_java.databinding.FragmentResultBinding;
import com.example.final_project_java.network.ApiRetrofit;
import com.example.final_project_java.network.RetrofitApis;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_result extends Fragment implements Click_product_home{
    FragmentResultBinding binding;
    List<ProductData> list;
    Adapter_result adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    list = response.body().getData();
                    adapter = new Adapter_result(list, Fragment_result.this,requireContext());
                    binding.recyclerResult.setLayoutManager(new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL));
                    binding.recyclerResult.setAdapter(adapter);

                } else {
                    Toast.makeText(requireContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(requireContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onclick(int position) {
        Intent intent = new Intent(requireActivity() , ProductActivity.class);
        intent.putExtra("name", list.get(position).getItemName());
        intent.putExtra("price", list.get(position).getPrice());
        intent.putExtra("id", list.get(position).getItemId());
        startActivity(intent);
    }
}