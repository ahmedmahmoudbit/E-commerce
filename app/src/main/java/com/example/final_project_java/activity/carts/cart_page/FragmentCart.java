package com.example.final_project_java.activity.carts.cart_page;

import android.os.Bundle;
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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.carts.cart_page.operations.add.AddResponse;
import com.example.final_project_java.activity.carts.cart_page.operations.remov.RemoveResponse;
import com.example.final_project_java.activity.carts.cart_page.operations.sub.SubResponse;
import com.example.final_project_java.activity.carts.cart_page.showCartItems.DataItem;
import com.example.final_project_java.activity.carts.cart_page.showCartItems.ShowCartItemsResponse;
import com.example.final_project_java.adapter.AdapterCarts;
import com.example.final_project_java.adapter.interfaces.ClickValue;
import com.example.final_project_java.database.network.ApiRetrofit;
import com.example.final_project_java.database.network.RetrofitApis;
import com.example.final_project_java.database.shared.Constant;
import com.example.final_project_java.database.shared.PreferenceManager;
import com.example.final_project_java.databinding.FragmentCartBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCart extends Fragment implements ClickValue {
    private static final String TAG = "Fragment_carts";
    FragmentCartBinding binding;
    NavController navController;
    AdapterCarts adapter;
    List<DataItem> list;
    PreferenceManager preferenceManager;
    double totalAmount = 0.0;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferenceManager = new PreferenceManager(requireContext());
        navController = Navigation.findNavController(view);
        intent();
        carts();

    }

    // show item in carts.
    private void carts() {
        String token = "Bearer " + preferenceManager.getString(Constant.ACCESS_TOKEN);
        ApiRetrofit.getapi().create(RetrofitApis.class).carts_show(token).enqueue(new Callback<ShowCartItemsResponse>() {
            @Override
            public void onResponse(@NotNull Call<ShowCartItemsResponse> call, @NotNull Response<ShowCartItemsResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    try {
                        list = response.body().getData();
                        adapter = new AdapterCarts(list, requireContext() , FragmentCart.this);
                        binding.recyclerViewCarts.setLayoutManager(new LinearLayoutManager(requireContext()));
                        binding.recyclerViewCarts.setAdapter(adapter);
                        progressBar(false);
                        countAmount();
                    } catch (Exception e) {
                        Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar(false);
                    }

                } else {
                    Toast.makeText(requireContext(), response.message(), Toast.LENGTH_SHORT).show();
                    progressBar(false);
                }
            }

            @Override
            public void onFailure(Call<ShowCartItemsResponse> call, Throwable t) {
                Toast.makeText(requireContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                progressBar(false);
            }
        });

    }

    // intent to checkout Screen .
    private void intent() {
        binding.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_fragmentCart_to_fragment_checkout);
            }
        });
    }

    // count price items .
    public void countAmount() {
        totalAmount = 0.0;
        for (int i = 0; i < list.size(); i++) {
            double amount = Double.parseDouble(list.get(i).getQuantity())
                    * Double.parseDouble(String.format(list.get(i).getProductId().getPrice() , 3 ));
            totalAmount = totalAmount + amount;

        }
        binding.salary.setText(String.valueOf(totalAmount) + "$");

    }

    // add quantity item from api and change price .
    @Override
    public void onclickAdd(int productId , int position) {
        Log.i(TAG, "onclick: "+ preferenceManager.getString(Constant.ACCESS_TOKEN));
        String token = "Bearer " + preferenceManager.getString(Constant.ACCESS_TOKEN);
        ApiRetrofit.getapi().create(RetrofitApis.class).
                addItem(productId,token).enqueue(new Callback<AddResponse>() {
            @Override
            public void onResponse(@NotNull Call<AddResponse> call, @NotNull Response<AddResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(requireContext(), "Add is success", Toast.LENGTH_SHORT).show();
                    assert response.body() != null;

                    // change Quantity item .
                    int quantity = response.body().getData().getQuantity();
                     String newQuantity = Integer.toString(quantity);
                     Log.i(TAG, "onResponse: " + newQuantity);
                     adapter.list.get(position).setQuantity(newQuantity);
                     adapter.notifyItemChanged(position);

                    countAmount();

                }

                else{
                    Toast.makeText(requireContext(), response.message(), Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(@NotNull Call<AddResponse> call, @NotNull Throwable t) {
                Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());

            }
        });
    }

    // sub quantity item from api and change price .
    @Override
    public void onclickSub(int productId , int position) {
        String token = "Bearer " + preferenceManager.getString(Constant.ACCESS_TOKEN);
        ApiRetrofit.getapi().create(RetrofitApis.class).subItem(productId ,token).enqueue(new Callback<SubResponse>() {
            @Override
            public void onResponse(@NotNull Call<SubResponse> call, @NotNull Response<SubResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(requireContext(), "Add is success", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "onSuccess: " + response.message());

                    assert response.body() != null;
                    int quantity = response.body().getData().getQuantity();
                    String newQuantity = Integer.toString(quantity);
                    Log.i(TAG, "onResponse: " + newQuantity);
                    adapter.list.get(position).setQuantity(newQuantity);
                    adapter.notifyItemChanged(position);
                    countAmount();


                }
                Toast.makeText(requireContext(), response.message(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "no onSuccess: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<SubResponse> call, @NotNull Throwable t) {
                Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    // remove quantity item from api and change price .
    @Override
    public void onclickRemove(int productId , int position) {
        // take token .
        String token = "Bearer " + preferenceManager.getString(Constant.ACCESS_TOKEN);
        Log.i(TAG, "onclickRemove: " + token);
        ApiRetrofit.getapi().create(RetrofitApis.class).removeItem(productId , token).enqueue(new Callback<RemoveResponse>() {
            @Override
            public void onResponse(@NotNull Call<RemoveResponse> call, @NotNull Response<RemoveResponse> response) {
                Toast.makeText(requireContext(), "Removed", Toast.LENGTH_SHORT).show();
                // remove item from list .
                list.remove(position);
                // remove item from api  + update .
                adapter.notifyItemRemoved(position);
                countAmount();
            }

            @Override
            public void onFailure(@NotNull Call<RemoveResponse> call, @NotNull Throwable t) {

            }
        });

    }

    // progressBar
    private void progressBar(Boolean loading) {
        if (loading) {
            binding.progress.setVisibility(View.VISIBLE);
            binding.recyclerViewCarts.setVisibility(View.GONE);
        } else {
            binding.recyclerViewCarts.setVisibility(View.VISIBLE);
            binding.progress.setVisibility(View.GONE);
        }
    }

}