package com.example.final_project_java.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.activities.product.data.ProductData;
import com.example.final_project_java.adapter.interfaces.ClickProducts;
import com.example.final_project_java.databinding.RecyclerLastsBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterLasts extends RecyclerView.Adapter<AdapterLasts.Holder> {
    List<ProductData> arrayList;
    Context context;
    ClickProducts click_products;

    public AdapterLasts(List<ProductData> arrayList, Context context, ClickProducts click_products) {
        this.arrayList = arrayList;
        this.context = context;
        this.click_products = click_products;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(DataBindingUtil.inflate(LayoutInflater.from(context) , R.layout.recycler_lasts, parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.setData(arrayList.get(position));

        if (arrayList.get(position).getImages().isEmpty()) {
            holder.binding.imgLasts.setImageResource(R.drawable.ic_remove_image);

        } else {
            holder.binding.imgLasts.setImageResource(R.drawable.jacket1);
            // Picasso.get().load(arrayList.get(position).getImages().get(0).getImage()).into(holder.binding.imgLasts);
        }

        holder.binding.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_products.onclick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        RecyclerLastsBinding binding;
        public Holder(@NonNull RecyclerLastsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
