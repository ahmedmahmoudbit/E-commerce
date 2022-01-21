package com.example.final_project_java;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AddToCartSuccessfullyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart_successfully);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(4500);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();



    }
}