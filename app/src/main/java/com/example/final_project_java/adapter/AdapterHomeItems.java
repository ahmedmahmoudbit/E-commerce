package com.example.final_project_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.search.ProductData;
import com.example.final_project_java.activity.interfaces.ClickProducts;
import com.example.final_project_java.databinding.RecyclerCategoryItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterHomeItems extends RecyclerView.Adapter<AdapterHomeItems.Holder> {
    List<ProductData> list;
    Context context;
    ClickProducts click_products;

    public AdapterHomeItems(List<ProductData> list, Context context, ClickProducts click_products) {
        this.list = list;
        this.context = context;
        this.click_products = click_products;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(DataBindingUtil.inflate(LayoutInflater.from(context) , R.layout.recycler_category_item , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.itemView.startAnimation(AnimationUtils.loadAnimation(context,R.anim.animations));

        holder.binding.setData(list.get(position));

        if (list.get(position).getImages().isEmpty()) {
            holder.binding.imageItem.setImageResource(R.drawable.ic_remove_image);

        } else {
            Picasso.get().load(list.get(position).getImages().get(0).getImage()).into(holder.binding.imageItem);
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
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        RecyclerCategoryItemBinding binding;
        public Holder(@NonNull RecyclerCategoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
