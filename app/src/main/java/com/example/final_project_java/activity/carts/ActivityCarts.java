package com.example.final_project_java.activity.carts;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.ProductActivity;
import com.example.final_project_java.databinding.ActivityCartsBinding;

public class ActivityCarts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carts);

        NavController navController = Navigation.findNavController(this, R.id.nav);
        navController.navigate(R.id.fragment_carts);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}