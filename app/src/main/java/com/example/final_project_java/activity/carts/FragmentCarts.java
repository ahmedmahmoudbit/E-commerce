package com.example.final_project_java.activity.carts;

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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.carts.show_cart.DataItem;
import com.example.final_project_java.activity.carts.show_cart.ShowItemCardResponse;
import com.example.final_project_java.adapter.AdapterCarts;
import com.example.final_project_java.databinding.FragmentCartsBinding;
import com.example.final_project_java.network.ApiRetrofit;
import com.example.final_project_java.network.RetrofitApis;
import com.example.final_project_java.shared.Constant;
import com.example.final_project_java.shared.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCarts extends Fragment {
    private static final String TAG = "Fragment_carts";
    FragmentCartsBinding binding;
    NavController navController;
    AdapterCarts adapter;
    List<DataItem> categories;
    PreferenceManager preferenceManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_carts, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferenceManager = new PreferenceManager(requireContext());
        categories = new ArrayList<>();
        carts();
    }

    private void carts() {

        String token = "Bearer " + preferenceManager.getString(Constant.ACCESS_TOKEN);
        ApiRetrofit.getapi().create(RetrofitApis.class).carts_show(token).enqueue(new Callback<ShowItemCardResponse>() {
            @Override
            public void onResponse(Call<ShowItemCardResponse> call, Response<ShowItemCardResponse> response) {
                if(response.isSuccessful()) {
                    categories = response.body().getData();
                    adapter = new AdapterCarts(categories,requireContext());
                    binding.recyclerViewCarts.setLayoutManager(new LinearLayoutManager(requireContext()));
                    binding.recyclerViewCarts.setAdapter(adapter);

                } else {
                    Toast.makeText(requireContext(), response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ShowItemCardResponse> call, Throwable t) {
                Toast.makeText(requireContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        binding.buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_fragment_carts_to_fragment_checkout);
            }
        });

    }

}