package com.example.final_project_java.activity.logoutAndmore;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.activities.MainActivity;
import com.example.final_project_java.network.RetrofitApis;
import com.example.final_project_java.databinding.FragmentMoreBinding;
import com.example.final_project_java.network.ApiRetrofit;
import com.example.final_project_java.shared.Constant;
import com.example.final_project_java.shared.PreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_more extends Fragment {
    FragmentMoreBinding binding;
    PreferenceManager preferenceManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(requireContext()) , R.layout.fragment_more , container , false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferenceManager = new PreferenceManager(requireContext());
        listener();
    }

    private void listener() {
        binding.btnLogout.setOnClickListener(v -> apicreate());

    }

    private void apicreate() {
        String token = "Bearer " + Constant.ACCESS_TOKEN;
        ApiRetrofit.getapi().create(RetrofitApis.class).logout(token).enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                if (response.isSuccessful()) {
                    startActivity(new Intent(requireActivity() , MainActivity.class));
                    preferenceManager.clear();
                    requireActivity().finish();
                } else {
                    Toast.makeText(requireContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                Toast.makeText(requireContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}