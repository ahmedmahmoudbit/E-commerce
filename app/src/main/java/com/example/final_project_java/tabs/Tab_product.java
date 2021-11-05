package com.example.final_project_java.tabs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.final_project_java.FargmentChangeSizes;
import com.example.final_project_java.Fragment_reviewsprofile;
import com.example.final_project_java.data.Fragment_product_details;

public class Tab_product extends FragmentStateAdapter {
    public Tab_product(@NonNull Fragment fragment) {
        super(fragment);
    }

    public Tab_product(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 1 : return new Fragment_product_details() ;
            case 2 : return new Fragment_reviewsprofile() ;
        }

        return new FargmentChangeSizes();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
