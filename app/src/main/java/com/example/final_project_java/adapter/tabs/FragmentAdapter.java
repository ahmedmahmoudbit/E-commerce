package com.example.final_project_java.adapter.tabs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.final_project_java.activity.carts.cart_page.FragmentCart;
import com.example.final_project_java.activity.lasts_product.FragmentLasts;
import com.example.final_project_java.activity.more.Fragment_more;
import com.example.final_project_java.activity.search.FragmentProducts;
import com.example.final_project_java.activity.setting.Fragment_profile;

public class FragmentAdapter extends FragmentStateAdapter {
//    SearchResponse response;
    public FragmentAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public FragmentAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager , lifecycle);
//          SearchResponse response
//        this.response = response;
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

        return new FragmentProducts();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
