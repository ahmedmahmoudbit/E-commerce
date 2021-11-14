package com.example.final_project_java.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.search.ProductColor;
import com.example.final_project_java.databinding.RecyclerColorselectorBinding;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class AdapterColor extends RecyclerView.Adapter<AdapterColor.Holder> {
    List<ProductColor> arrayList;
    Context context;
    int select = -1;

    // change select .
    private void selected(int adapterPosition) {
        if (adapterPosition == RecyclerView.NO_POSITION) return;
        notifyDataSetChanged();
        select = adapterPosition;
    }

    public AdapterColor(List<ProductColor> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(DataBindingUtil.inflate(LayoutInflater.from(context) , R.layout.recycler_colorselector , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.binding.imageColor.setBackgroundColor(Color.parseColor(arrayList.get(position).getColorid()));

        // select
        holder.binding.imageColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected(holder.getAdapterPosition());
            }
        });

        if (select == position) {
            holder.binding.selector.setVisibility(View.VISIBLE);
        } else {
            holder.binding.selector.setVisibility(View.GONE);
        }
        // select
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        RecyclerColorselectorBinding binding;
        public Holder(@NonNull RecyclerColorselectorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
