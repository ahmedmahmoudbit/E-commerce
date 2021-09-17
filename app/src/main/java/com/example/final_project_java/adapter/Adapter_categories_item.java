package com.example.final_project_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project_java.R;
import com.example.final_project_java.data.Click_product_home;
import com.example.final_project_java.data.Data_category_item;
import com.example.final_project_java.databinding.RecyclerCategoryItemBinding;

import java.util.List;

public class Adapter_categories_item extends RecyclerView.Adapter<Adapter_categories_item.Holder> {
    List<Data_category_item> dataCategoryItems;
    Context context;
    Click_product_home click_product_home;

    public Adapter_categories_item(List<Data_category_item> dataCategoryItems, Context context, Click_product_home click_product_home) {
        this.dataCategoryItems = dataCategoryItems;
        this.context = context;
        this.click_product_home = click_product_home;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(DataBindingUtil.inflate(LayoutInflater.from(context) , R.layout.recycler_category_item , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
          holder.binding.setData(dataCategoryItems.get(position));

          holder.binding.cardview.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  click_product_home.onclick(position);
              }
          });
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
