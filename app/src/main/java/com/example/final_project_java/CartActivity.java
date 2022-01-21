package com.example.final_project_java;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.final_project_java.activity.carts.add_cart.FragmentCart;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav , new FragmentCart()).commit();
    }
}