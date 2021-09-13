package com.example.final_project_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project_java.R;
import com.example.final_project_java.data.Data_search;
import com.example.final_project_java.databinding.RecyclerSearchLastBinding;

import java.util.List;

public class Adapter_search extends RecyclerView.Adapter<Adapter_search.Holder> {
    List<Data_search> data_searches;
    Context context;

    public Adapter_search(List<Data_search> data_searches, Context context) {
        this.data_searches = data_searches;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(DataBindingUtil.inflate(LayoutInflater.from(context) , R.layout.recycler_search_last , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
      final Data_search person = data_searches.get(position);
      holder.binding.textName.setText(person.getName());
      holder.binding.textPrice.setText(person.getPrice());
      holder.binding.searchImage.setImageResource(person.getImg());

    }


    @Override
    public int getItemCount() {
        return data_searches.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        RecyclerSearchLastBinding binding;
        public Holder(@NonNull RecyclerSearchLastBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
