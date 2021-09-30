package com.example.final_project_java.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.final_project_java.ChatActivity;
import com.example.final_project_java.R;
import com.example.final_project_java.databinding.ActivityMainHomeActivityBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class Home_activity extends AppCompatActivity {
    private static final String TAG = "Home_activity";
    ActivityMainHomeActivityBinding binding;
    public static String ACCESS_TOKEN ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_home_activity);
        btn_notification();
        btn_message();

        NavController navController = Navigation.findNavController(this,R.id.navfragment);
        NavigationUI.setupWithNavController(binding.buttonNav , navController);

        Log.i(TAG, "onCreate: " + ACCESS_TOKEN);

    }

    private void btn_message() {
        binding.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home_activity.this , ChatActivity.class));
            }
        });
    }

    // intent notification button to notification activity
    private void btn_notification(){
        binding.notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home_activity.this , Notification_activity.class));
            }
        });
    }


}