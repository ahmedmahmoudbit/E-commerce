package com.example.final_project_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.activities.product.data.ProductSize;
import com.example.final_project_java.databinding.RecyclerSizeBinding;

import java.util.List;

public class AdapterSizes extends RecyclerView.Adapter<AdapterSizes.Holder> {
    List<ProductSize> arrayList;
    Context context;
    public static int select = -1;
    public static String size;

    private void selected(int adapterPosition) {
        if (adapterPosition == RecyclerView.NO_POSITION) return;
        notifyDataSetChanged();
        select = adapterPosition;
    }

    public AdapterSizes(List<ProductSize> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(DataBindingUtil.inflate(LayoutInflater.from(context) , R.layout.recycler_size , parent ,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.binding.setData(arrayList.get(position));

        holder.binding.tvSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected(holder.getAdapterPosition());
            }
        });

        if (select == position) {
            holder.binding.tvSize.setTextColor(context.getResources().getColor(R.color.red_based));
            size = arrayList.get(position).getName();
        } else {
            holder.binding.tvSize.setTextColor(context.getResources().getColor(R.color.singup));
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        RecyclerSizeBinding binding;
        public Holder(@NonNull RecyclerSizeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }
    }
}
