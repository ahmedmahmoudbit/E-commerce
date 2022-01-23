package com.example.final_project_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project_java.R;
import com.example.final_project_java.database.data.Data_chat1;
import com.example.final_project_java.databinding.RecyclerChat1Binding;

import java.util.List;

public class Adapter_chat1 extends RecyclerView.Adapter<Adapter_chat1.Holder> {
    List<Data_chat1> array ;
    Context context;

    public Adapter_chat1(List<Data_chat1> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(DataBindingUtil.inflate(LayoutInflater.from(context) , R.layout.recycler_chat1 , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.binding.setData(array.get(position));
    }

    @Override
    public int getItemCount() {
        return array.size();
    }
    static class Holder extends RecyclerView.ViewHolder {
        RecyclerChat1Binding binding;
        public Holder(@NonNull RecyclerChat1Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
