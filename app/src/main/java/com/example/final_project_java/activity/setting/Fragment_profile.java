package com.example.final_project_java.activity.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.activities.MainActivity;
import com.example.final_project_java.activity.logout_more.LogoutResponse;
import com.example.final_project_java.activity.setting.profile.ProfileFragment;
import com.example.final_project_java.database.network.ApiRetrofit;
import com.example.final_project_java.database.network.RetrofitApis;
import com.example.final_project_java.database.shared.Constant;
import com.example.final_project_java.database.shared.PreferenceManager;
import com.example.final_project_java.databinding.FragmentSettingBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_profile extends Fragment {
    FragmentSettingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = DataBindingUtil.inflate(inflater , R.layout.fragment__setting , container , false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myProfile();

    }

    private void myProfile() {
        binding.myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().add(new ProfileFragment() , "dialog").commit();
            }
        });

    }
}