package com.example.final_project_java.activity.carts;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_project_java.R;

public class ActivityCarts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carts);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav , new Fragment_carts()).commit();
        
    }

}