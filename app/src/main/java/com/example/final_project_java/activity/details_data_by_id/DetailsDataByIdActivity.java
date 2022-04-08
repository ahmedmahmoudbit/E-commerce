package com.example.final_project_java.activity.details_data_by_id;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.final_project_java.R;

public class DetailsDataByIdActivity extends AppCompatActivity {
    private static final String TAG = "DetailsDataByIdActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detiles_data_by_id);
        int id = (int) getIntent().getSerializableExtra("product");
        Log.i(TAG, "onCreate: " + id);
    }
}