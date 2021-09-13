package com.example.final_project_java.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.final_project_java.R;
import com.example.final_project_java.adapter.Adapter_notification;
import com.example.final_project_java.data.Data_notification;
import com.example.final_project_java.databinding.ActivityNotificationActivityBinding;

import java.util.ArrayList;

public class Notification_activity extends AppCompatActivity {
    ActivityNotificationActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this , R.layout.activity_notification_activity);
        recyclerview();
        close_btn();

    }

    private void recyclerview() {
        ArrayList<Data_notification> arrayList = new ArrayList<>();
        Adapter_notification adapter;

        arrayList.add(new Data_notification("Package from your order","#1982345 ", "arrived to destination country","16:03",R.drawable.icon));
        arrayList.add(new Data_notification("Package from your order","#1982345 ", "arrived to destination country","16:03",R.drawable.icon_1));
        arrayList.add(new Data_notification("50% OFF of everything",R.drawable.icon_2));
        arrayList.add(new Data_notification("BOGO Sale starting ",R.drawable.icon_3));

        adapter = new Adapter_notification(arrayList , this);
        binding.rvNotification.setLayoutManager(new LinearLayoutManager(this));
        binding.rvNotification.setAdapter(adapter);

    }
    private void close_btn() {
        binding.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //startActivity(new Intent(Notification_activity.this, Home_activity.class));
            }
        });
    }

}