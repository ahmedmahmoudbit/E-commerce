package com.example.final_project_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.lasts_product.DataItem;
import com.example.final_project_java.data.Click_product_home;
import com.example.final_project_java.databinding.RecyclerSearchLastBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.Holder> {
    List<DataItem> data_searches;
    Context context;
    Click_product_home click_product_home;

    public AdapterSearch(List<DataItem> data_searches, Context context, Click_product_home click_product_home) {
        this.data_searches = data_searches;
        this.context = context;
        this.click_product_home = click_product_home;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(DataBindingUtil.inflate(LayoutInflater.from(context) , R.layout.recycler_search_last , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.binding.setData(data_searches.get(position));
        Picasso.get().load(data_searches.get(position).getImages().get(0).getImage()).placeholder(R.drawable.ic_remove_image).into(holder.binding.imgLasts);

        holder.binding.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_product_home.onclick(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return data_searches.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        RecyclerSearchLastBinding binding;
        public Holder(@NonNull RecyclerSearchLastBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
