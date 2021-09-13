package com.example.final_project_java;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class TabLayout_fragment extends FragmentStateAdapter {
    public TabLayout_fragment(@NonNull Fragment fragment) {
        super(fragment);
    }

    NavController navController;
    public TabLayout_fragment(FragmentManager fragmentManager, Lifecycle lifecycle) {
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
