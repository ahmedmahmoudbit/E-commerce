package com.example.final_project_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.final_project_java.adapter.Adapter_chat1;
import com.example.final_project_java.adapter.Adapter_chat2;
import com.example.final_project_java.data.Data_chat1;
import com.example.final_project_java.data.Data_chat2;
import com.example.final_project_java.databinding.ActivityChatBinding;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    ActivityChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);

        chat2();
        chat1();


    }

    private void chat2() {
        ArrayList<Data_chat2> arrayList = new ArrayList<>();
        Adapter_chat2 adapter;

        arrayList.add(new Data_chat2("hhrhtrhfghfbnrt"));
        arrayList.add(new Data_chat2("hhrhtrhfghfbnrt"));
        arrayList.add(new Data_chat2("hhrhtrhfghfbnrt"));

        adapter = new Adapter_chat2(arrayList , ChatActivity.this);
        binding.rvChat1.setLayoutManager(new LinearLayoutManager(this));
        binding.rvChat1.setAdapter(adapter);

    }

    private void chat1() {
        ArrayList<Data_chat1> list = new ArrayList<>();
        Adapter_chat1 adapter;

        list.add(new Data_chat1("hhrhtrhfghfbnrt"));
        list.add(new Data_chat1("hhrhtrhfghfbnrt"));
        list.add(new Data_chat1("hhrhtrhfghfbnrt"));

        adapter = new Adapter_chat1(list , ChatActivity.this);
        binding.rvChat1.setLayoutManager(new LinearLayoutManager(this));
        binding.rvChat1.setAdapter(adapter);

    }
}