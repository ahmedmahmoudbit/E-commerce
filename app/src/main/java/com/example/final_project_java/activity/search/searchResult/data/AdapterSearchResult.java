package com.example.final_project_java.activity.search.searchResult.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.search.data.DataItem;
import com.example.final_project_java.adapter.interfaces.ClickProducts;
import com.example.final_project_java.databinding.ListResultSearchBinding;
import com.example.final_project_java.databinding.RecyclerResultBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterSearchResult extends RecyclerView.Adapter<AdapterSearchResult.Holder> {
    ArrayList<DataItem> list;
    Context context;
    ClickProducts clickProducts;

    public AdapterSearchResult(ArrayList<DataItem> list, Context context, ClickProducts clickProducts) {
        this.list = list;
        this.context = context;
        this.clickProducts = clickProducts;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.list_result_search , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.tvProdactResult.setText(list.get(position).getItemName());
        holder.binding.salaryResult.setText(list.get(position).getPrice());

        if (list.get(position).getImages() != null) {
            if (list.get(position).getImages().isEmpty()) {
                holder.binding.img.setImageResource(R.drawable.ic_remove_image);
            } else {
                Picasso.get().load(list.get(position).getImages().get(0).getImage()).into(holder.binding.img);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickProducts.onclick(list.get(position).getItemId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ListResultSearchBinding binding;
        public Holder(@NonNull ListResultSearchBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
