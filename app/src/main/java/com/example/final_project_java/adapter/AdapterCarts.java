package com.example.final_project_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.carts.add_cart.showCartItems.DataItem;
import com.example.final_project_java.activity.interfaces.ClickValue;
import com.example.final_project_java.databinding.RecyclerCartsBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterCarts extends RecyclerView.Adapter<AdapterCarts.Holder> {
    public List<DataItem> list;
    Context context;
    ClickValue clickValue;


    public AdapterCarts(List<DataItem> list, Context context , ClickValue clickValue) {
        this.list = list;
        this.context = context;
        this.clickValue = clickValue;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.recycler_carts, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        // show string item from apis and set to all string to layout .
        holder.binding.setData(list.get(position));

        // show image from cart .
        Picasso.get().load(list.get(position).getProductId().getImages().get(0).getImage()).into(holder.binding.cartsImage);

        // remove + add + sub .
        holder.binding.addItemCartsImage.setOnClickListener(v -> {
            clickValue.onclickAdd(list.get(position).getProductId().getItemId(), holder.getAdapterPosition());
        });
        // holder.getAdapterPosition = holder.getAdapterPosition() but better
        holder.binding.minusItemCartsImage.setOnClickListener(v -> {
            clickValue.onclickSub(list.get(position).getProductId().getItemId(), holder.getAdapterPosition());
        });

        holder.binding.remove.setOnClickListener(v -> {
            clickValue.onclickRemove(list.get(position).getProductId().getItemId(), holder.getAdapterPosition());
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    static class Holder extends RecyclerView.ViewHolder {
        RecyclerCartsBinding binding;
        public Holder(@NonNull RecyclerCartsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
