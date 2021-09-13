package com.example.final_project_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project_java.R;
import com.example.final_project_java.data.Data_notification;
import com.example.final_project_java.databinding.RecyclerNotificationsBinding;

import java.util.ArrayList;

public class Adapter_notification extends RecyclerView.Adapter<Adapter_notification.Holder> {
    ArrayList<Data_notification> arrayList;
    Context context;

    public Adapter_notification(ArrayList<Data_notification> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.recycler_notifications, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final Data_notification person = arrayList.get(position);
        holder.binding.imgNotification.setImageResource(person.getImg());
        holder.binding.tvIdOrder.setText(person.getOrder());
        holder.binding.stateOrder.setText(person.getState());
        holder.binding.descripction.setText(person.getDescripction());
        holder.binding.tvTime.setText(person.getTime());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        RecyclerNotificationsBinding binding;

        public Holder(@NonNull RecyclerNotificationsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
