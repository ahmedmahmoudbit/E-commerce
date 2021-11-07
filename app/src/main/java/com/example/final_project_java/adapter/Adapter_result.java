package com.example.final_project_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.search.ProductData;
import com.example.final_project_java.data.Click_product_home;
import com.example.final_project_java.data.Data_result;
import com.example.final_project_java.databinding.RecyclerResultBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter_result extends RecyclerView.Adapter<Adapter_result.Holder> {
    List<ProductData> arrayList;
    Click_product_home click;
    Context context;

    public Adapter_result(List<ProductData> arrayList, Click_product_home click, Context context) {
        this.arrayList = arrayList;
        this.click = click;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(DataBindingUtil.inflate(LayoutInflater.from(context) , R.layout.recycler_result , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.binding.setData(arrayList.get(position));


        if (arrayList.get(position).getImages().isEmpty()) {
            holder.binding.img.setImageResource(R.drawable.ic_remove_image);

        } else {
            Picasso.get().load(arrayList.get(position).getImages().get(0).getImage()).into(holder.binding.img);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onclick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        RecyclerResultBinding binding;
        public Holder(@NonNull RecyclerResultBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }



}
