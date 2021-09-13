package com.example.final_project_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project_java.R;
import com.example.final_project_java.data.Data_carts;
import com.example.final_project_java.databinding.RecyclerCartsBinding;

import java.util.List;

public class Adapter_carts extends RecyclerView.Adapter<Adapter_carts.Holder> {
    List<Data_carts> data_carts;
    Context context;

    public Adapter_carts(List<Data_carts> data_carts, Context context) {
        this.data_carts = data_carts;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(DataBindingUtil.inflate(LayoutInflater.from(context) , R.layout.recycler_carts , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final  Data_carts person = data_carts.get(position);
        holder.binding.cartsImage.setImageResource(person.getImg());
        holder.binding.cartsTextTitle.setText(person.getName());
        holder.binding.cartsTextDescription.setText(person.getDescription());
        holder.binding.cartsPrice.setText(person.getPrice());

    }

    @Override
    public int getItemCount() {
        return data_carts.size();
    }
    class Holder extends RecyclerView.ViewHolder {
        RecyclerCartsBinding binding;
        public Holder(@NonNull RecyclerCartsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
