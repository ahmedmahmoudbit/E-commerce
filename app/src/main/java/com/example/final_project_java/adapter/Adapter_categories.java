package com.example.final_project_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project_java.R;
import com.example.final_project_java.data.Data_categories;
import com.example.final_project_java.databinding.RecyclerCategoryBinding;

import java.util.List;

public class Adapter_categories extends RecyclerView.Adapter<Adapter_categories.Holder> {
    List<Data_categories> adapter_categories ;
    Context context;


    public Adapter_categories(List<Data_categories> adapter_categories, Context context) {
        this.adapter_categories = adapter_categories;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(DataBindingUtil.inflate(LayoutInflater.from(context) , R.layout.recycler_category , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final Data_categories person = adapter_categories.get(position);
            holder.binding.categoryText.setText(person.getText());
            holder.binding.categoryImage.setImageResource(person.getImage());
    }

    @Override
    public int getItemCount() {
        return adapter_categories.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        RecyclerCategoryBinding binding;
        public Holder(@NonNull RecyclerCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
