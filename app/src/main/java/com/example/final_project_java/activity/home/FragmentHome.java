package com.example.final_project_java.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.PerformanceHintManager;
import android.util.Log;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.activities.product.data.ProductData;
import com.example.final_project_java.activity.activities.product.data.ProductResponse;
import com.example.final_project_java.activity.activities.shop_profile.Shope_profie_activity;
import com.example.final_project_java.activity.activities.product.ProductActivity;
import com.example.final_project_java.adapter.AdapterHomeItems;
import com.example.final_project_java.adapter.Adapter_categories;
import com.example.final_project_java.adapter.interfaces.ClickProducts;
import com.example.final_project_java.database.data.Data_categories;
import com.example.final_project_java.database.shared.Constant;
import com.example.final_project_java.database.shared.PreferenceManager;
import com.example.final_project_java.databinding.FragmentHomeBinding;
import com.example.final_project_java.database.network.ApiRetrofit;
import com.example.final_project_java.database.network.RetrofitApis;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment implements ClickProducts {
    NavController navController;
    FragmentHomeBinding binding;
    List<ProductData> list;
    AdapterHomeItems adapter;
    private static final String TAG = "FragmentHome";
    PreferenceManager preferenceManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment__home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        preferenceManager = new PreferenceManager(requireContext());

        category();
        items();
        intent();

    }

    private void category() {
        Adapter_categories adapter;
        ArrayList<Data_categories> categories = new ArrayList<>();

        categories.add(new Data_categories("Apparel" , R.drawable.apparel));
        categories.add(new Data_categories("Beauty" , R.drawable.beauty));
        categories.add(new Data_categories("Shoes" , R.drawable.shoes));
        categories.add(new Data_categories("Electronics" , R.drawable.electronics));
        categories.add(new Data_categories("Furniture" , R.drawable.furniture));
        categories.add(new Data_categories("Stationary" , R.drawable.stationary));
        categories.add(new Data_categories("Home" , R.drawable.home));

        adapter = new Adapter_categories(categories,requireContext());
        binding.recyclerViewCategory.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL , false));
        binding.recyclerViewCategory.setAdapter(adapter);
    }

    private void items() {
        ApiRetrofit.getapi().create(RetrofitApis.class).product().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(@NotNull Call<ProductResponse> call, @NotNull Response<ProductResponse> response) {
                assert response.body() != null;
                if (response.isSuccessful()) {
                    list = response.body().getData();
                    adapter = new AdapterHomeItems(list,requireContext(),FragmentHome.this);
                    binding.recyclerViewItem.setLayoutManager(new LinearLayoutManager(requireContext() ,
                            GridLayoutManager.HORIZONTAL , false));
                    binding.recyclerViewItem.setAdapter(adapter);
                    progressBar(false);

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

    private void intent() {
        binding.img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity() , Shope_profie_activity.class));
            }
        });
    }

    private void progressBar(Boolean loading) {
        if (loading) {
            binding.progress.setVisibility(View.VISIBLE);
            binding.recyclerViewItem.setVisibility(View.GONE);
        } else {
            binding.recyclerViewItem.setVisibility(View.VISIBLE);
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