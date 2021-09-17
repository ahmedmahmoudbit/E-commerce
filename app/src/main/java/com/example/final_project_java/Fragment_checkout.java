package com.example.final_project_java;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.final_project_java.adapter.Adapter_checkout;
import com.example.final_project_java.data.Data_checkout;
import com.example.final_project_java.databinding.FragmentCheckoutBinding;

import java.util.ArrayList;

public class Fragment_checkout extends Fragment {
    FragmentCheckoutBinding binding;

    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_checkout, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        check_out_btn();
        recycler_item();
        close_page_btn();

    }

    private void check_out_btn() {
        binding.btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_fragment_checkout_to_order_done);
            }
        });
    }

    private void close_page_btn(){
        binding.closePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigateUp();
            }
        });
    }

    private void recycler_item() {
        ArrayList<Data_checkout> arrayList = new ArrayList<>();
        Adapter_checkout adapter ;

        arrayList.add(new Data_checkout("tshirt","the best material","25$","1",R.drawable.jacket7));
        arrayList.add(new Data_checkout("tshirt","the best material","25$","1",R.drawable.jacket5));
        arrayList.add(new Data_checkout("tshirt","the best material","25$","1",R.drawable.jacket4));

        adapter = new Adapter_checkout(arrayList , requireContext());
        binding.rvItem.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvItem.setAdapter(adapter);


    }

}