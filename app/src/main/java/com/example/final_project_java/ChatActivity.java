package com.example.final_project_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

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
        listener();

    }

    private void listener() {
        binding.btnBack.setOnClickListener(v -> onBackPressed());
    }


}