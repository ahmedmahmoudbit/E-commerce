package com.example.final_project_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project_java.R;
import com.example.final_project_java.data.Data_comments;
import com.example.final_project_java.databinding.RecyclerCommentsBinding;

import java.util.ArrayList;

public class Adapter_comment extends RecyclerView.Adapter<Adapter_comment.Holder> {
    ArrayList<Data_comments> arrayList ;
    Context context;

    public Adapter_comment(ArrayList<Data_comments> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(DataBindingUtil.inflate(LayoutInflater.from(context) , R.layout.recycler_comments , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final Data_comments person = arrayList.get(position);
        holder.binding.ratingComment.setImageResource(person.getRatting());
        holder.binding.photoComent.setImageResource(person.getPhoto());
        holder.binding.ratingComment.setImageResource(person.getRatting());
        holder.binding.ratingComment.setImageResource(person.getRatting());
        holder.binding.ratingComment.setImageResource(person.getRatting());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    class Holder extends RecyclerView.ViewHolder {
        RecyclerCommentsBinding binding;
        public Holder(@NonNull RecyclerCommentsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
