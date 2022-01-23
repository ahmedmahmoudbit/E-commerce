package com.example.final_project_java.activity.carts.cart_page;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.final_project_java.R;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav , new FragmentCart()).commit();
    }
}