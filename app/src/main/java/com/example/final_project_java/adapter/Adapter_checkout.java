package com.example.final_project_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project_java.R;
import com.example.final_project_java.data.Data_checkout;
import com.example.final_project_java.databinding.RecyclerCheckoutBinding;

import java.util.ArrayList;

public class Adapter_checkout extends RecyclerView.Adapter<Adapter_checkout.Holder> {
    ArrayList<Data_checkout> arrayList;
    Context context;

    public Adapter_checkout(ArrayList<Data_checkout> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(DataBindingUtil.inflate(LayoutInflater.from(context) , R.layout.recycler_checkout , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final Data_checkout person = arrayList.get(position);
        holder.binding.nameOfProdact.setText(person.getProduct());
        holder.binding.detailsOfProdact.setText(person.getDetails());
        holder.binding.price.setText(person.getPrice());
        holder.binding.counterAddItem.setText(person.getCount());
        holder.binding.imageItem.setImageResource(person.getImg());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        RecyclerCheckoutBinding binding;
        public Holder(@NonNull RecyclerCheckoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
