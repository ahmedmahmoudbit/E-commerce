package com.example.final_project_java.tabs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.final_project_java.Fragment_Home;
import com.example.final_project_java.activity.carts.Fragment_carts;
import com.example.final_project_java.Fragment_profile;
import com.example.final_project_java.activity.lasts_product.Fragment_search;
import com.example.final_project_java.activity.logoutAndmore.Fragment_more;


public class tablayout_result extends FragmentStateAdapter {
    public tablayout_result(@NonNull Fragment fragment) {
        super(fragment);
    }

    NavController navController;
    public tablayout_result(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager , lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {


        switch (position) {
            case 1 : new Fragment_search();
            case 2 : new Fragment_carts();
            case 3 : new Fragment_profile();
            case 4 : new Fragment_more();
        }

        return new Fragment_Home();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
