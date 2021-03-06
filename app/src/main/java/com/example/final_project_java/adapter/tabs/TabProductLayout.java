package com.example.final_project_java.adapter.tabs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.final_project_java.activity.search.FargmentChanges;
import com.example.final_project_java.activity.activities.product.FragmentReviews;
import com.example.final_project_java.activity.activities.product.data.ProductData;
import com.example.final_project_java.database.data.Fragment_product_details;

public class TabProductLayout extends FragmentStateAdapter {
    ProductData productData;

    public TabProductLayout(@NonNull Fragment fragment) {
        super(fragment);
    }

    public TabProductLayout(FragmentManager fragmentManager, Lifecycle lifecycle , ProductData productData) {
        super(fragmentManager, lifecycle);
        this.productData = productData;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 1 : return new Fragment_product_details() ;
            case 2 : return new FragmentReviews() ;
        }

        return new FargmentChanges(productData);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
