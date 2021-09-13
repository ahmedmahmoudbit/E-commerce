package com.example.final_project_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project_java.R;
import com.example.final_project_java.data.Data_category_item;
import com.example.final_project_java.databinding.RecyclerCategoryItemBinding;

import java.util.List;

public class Adapter_categories_item extends RecyclerView.Adapter<Adapter_categories_item.Holder> {
    List<Data_category_item> dataCategoryItems;
    Context context;

    public Adapter_categories_item(List<Data_category_item> dataCategoryItems, Context context) {
        this.dataCategoryItems = dataCategoryItems;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(DataBindingUtil.inflate(LayoutInflater.from(context) , R.layout.recycler_category_item , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final Data_category_item person = dataCategoryItems.get(position);
        holder.binding.nameOfItem.setText(person.getName());
        holder.binding.priceOfItem.setText(person.getPrice());
        holder.binding.imageItem.setImageResource(person.getImage());

    }

    @Override
    public int getItemCount() {
        return dataCategoryItems.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        RecyclerCategoryItemBinding binding;
        public Holder(@NonNull RecyclerCategoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
