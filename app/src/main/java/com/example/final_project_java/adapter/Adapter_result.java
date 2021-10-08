package com.example.final_project_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project_java.R;
import com.example.final_project_java.data.Data_result;
import com.example.final_project_java.databinding.RecyclerResultBinding;

import java.util.ArrayList;

public class Adapter_result extends RecyclerView.Adapter<Adapter_result.Holder> {
    ArrayList<Data_result> arrayList;
    Context context;

    public Adapter_result(ArrayList<Data_result> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(DataBindingUtil.inflate(LayoutInflater.from(context) , R.layout.recycler_result , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final Data_result person = arrayList.get(position);
            holder.binding.jacketResult.setImageResource(person.getImg());
            holder.binding.ratingResult.setText(person.getRating());
            holder.binding.salaryResult.setText(person.getSalary());
            holder.binding.tvProdactResult.setText(person.getName());
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
