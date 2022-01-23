package com.example.final_project_java.activity.setting.profile;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.setting.profile.data.ProfileResponse;
import com.example.final_project_java.database.network.ApiRetrofit;
import com.example.final_project_java.database.network.RetrofitApis;
import com.example.final_project_java.database.shared.Constant;
import com.example.final_project_java.database.shared.PreferenceManager;
import com.example.final_project_java.databinding.FragmentProfileBinding;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends DialogFragment {
    FragmentProfileBinding binding;
    PreferenceManager preferenceManager;
    private static final String TAG = "ProfileFragment";

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // convert design to dialog
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // define preferenceManager
        preferenceManager = new PreferenceManager(requireContext());

        // get token and id from local database ( SharedPreferences )
        String token = "Bearer " + preferenceManager.getString(Constant.ACCESS_TOKEN);
        int id =preferenceManager.getInteger(Constant.ACCESS_ID);

        ApiRetrofit.getapi().create(RetrofitApis.class).profile(token , id).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(@NotNull Call<ProfileResponse> call, @NotNull Response<ProfileResponse> response) {
                // get name and email from api and set data to design.
                if (response.body().isStatus()) {
                    binding.email.setText(response.body().getData().getEmail());
                    binding.name.setText(response.body().getData().getName());
                }
            }

            @Override
            public void onFailure(@NotNull Call<ProfileResponse> call, @NotNull Throwable t) {
                // on failure data .
                Toast.makeText(requireContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        clickButton();

    }

    private void clickButton() {
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}