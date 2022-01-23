package com.example.final_project_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project_java.R;
import com.example.final_project_java.database.data.Data_chat2;
import com.example.final_project_java.databinding.RecyclerChat2Binding;

import java.util.List;

public class Adapter_chat2 extends RecyclerView.Adapter<Adapter_chat2.Holder> {

    List<Data_chat2> array ;
    Context context;

    public Adapter_chat2(List<Data_chat2> array, Context context) {
        this.array = array;
        this.context = context;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(DataBindingUtil.inflate(LayoutInflater.from(context) , R.layout.recycler_chat2 , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.binding.setData(array.get(position));
    }

    @Override
    public int getItemCount() {
        return array.size();
    }
    class Holder extends RecyclerView.ViewHolder {
        RecyclerChat2Binding binding;
        public Holder(@NonNull RecyclerChat2Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
