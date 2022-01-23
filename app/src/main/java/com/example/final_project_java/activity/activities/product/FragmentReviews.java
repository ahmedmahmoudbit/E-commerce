package com.example.final_project_java.activity.activities.product;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.final_project_java.R;
import com.example.final_project_java.adapter.Adapter_comment;
import com.example.final_project_java.database.data.Data_comments;
import com.example.final_project_java.databinding.FragmentReviewsprofileBinding;

import java.util.ArrayList;

public class FragmentReviews extends Fragment {
    FragmentReviewsprofileBinding binding;
    ArrayList<Data_comments> arrayList = new ArrayList<>();
    Adapter_comment adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_reviewsprofile,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        arrayList.add(new Data_comments("Khaled Maher","it's very very good","12:52",R.raw.person_comment,R.drawable.rating_4));
        arrayList.add(new Data_comments("ahmed mahmoud"," very good","13:48",R.raw.person_comment,R.drawable.rating_3));
        arrayList.add(new Data_comments("Khaled Maher","Exceptional Quality, Comfort and Appearance For Less than $8 a Shirt, Great Deal!","12:52",R.raw.person_comment,R.drawable.rating_4));
        arrayList.add(new Data_comments("Khaled Maher","it's very very good","12:52",R.raw.person_comment,R.drawable.rating_4));
        arrayList.add(new Data_comments("hamed hamoda","it's very very good","12:52",R.raw.person_comment,R.drawable.rating_4));
        arrayList.add(new Data_comments("shady kareem","it's very very good","12:52",R.raw.person_comment,R.drawable.rating_3));
        arrayList.add(new Data_comments("Khaled Maher","it's very very good","12:52",R.raw.person_comment,R.drawable.rating_5));

        adapter = new Adapter_comment (arrayList , requireContext());
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerview.setAdapter(adapter);

    }
}