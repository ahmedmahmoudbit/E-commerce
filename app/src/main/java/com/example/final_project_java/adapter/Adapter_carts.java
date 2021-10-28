package com.example.final_project_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.carts.show_cart.DataItem;
import com.example.final_project_java.activity.carts.show_cart.ProductId;
import com.example.final_project_java.data.Data_carts;
import com.example.final_project_java.databinding.RecyclerCartsBinding;

import java.util.List;

public class Adapter_carts extends RecyclerView.Adapter<Adapter_carts.Holder> {
    List<DataItem> productId;
    Context context;

    public Adapter_carts(List<DataItem> productId, Context context) {
        this.productId = productId;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(DataBindingUtil.inflate(LayoutInflater.from(context) , R.layout.recycler_carts , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.binding.setData(productId.get(position));

    }

    @Override
    public int getItemCount() {
        return productId.size();
    }
    class Holder extends RecyclerView.ViewHolder {
        RecyclerCartsBinding binding;
        public Holder(@NonNull RecyclerCartsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
