package com.example.final_project_java.adapter.tabs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.final_project_java.activity.carts.cart_page.FragmentCart;
import com.example.final_project_java.activity.home.FragmentHome;
import com.example.final_project_java.activity.setting.Fragment_profile;
import com.example.final_project_java.activity.lasts_product.FragmentLasts;
import com.example.final_project_java.activity.more.Fragment_more;


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
            case 1 : new FragmentLasts();
            case 2 : new FragmentCart();
            case 3 : new Fragment_profile();
            case 4 : new Fragment_more();
        }

        return new FragmentHome();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
