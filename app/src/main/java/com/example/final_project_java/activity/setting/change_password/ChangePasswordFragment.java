package com.example.final_project_java.activity.setting.change_password;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.setting.change_password.data.ChangePasswordRequest;
import com.example.final_project_java.activity.setting.change_password.data.ChangePasswordResponse;
import com.example.final_project_java.database.network.ApiRetrofit;
import com.example.final_project_java.database.network.RetrofitApis;
import com.example.final_project_java.database.shared.Constant;
import com.example.final_project_java.database.shared.PreferenceManager;
import com.example.final_project_java.databinding.FragmentChangePasswordBinding;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordFragment extends DialogFragment {
    FragmentChangePasswordBinding binding;
    PreferenceManager preferenceManager;
    private static final String TAG = "ChangePasswordFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_change_password, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferenceManager = new PreferenceManager(requireContext());

        binding.btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change();
            }
        });

    }

    private void change() {
        // notation get String text
        String new_password = binding.password.getEditText().getText().toString();
        String confirm_password = binding.confirmPassword.getEditText().getText().toString();
        String old_password = binding.oldPassword.getEditText().getText().toString();

        if (!new_password.equals(confirm_password)) {
            Toast.makeText(requireContext(), "password not matching with confirm password", Toast.LENGTH_SHORT).show();
        } else if (new_password.isEmpty()) {
            Toast.makeText(requireContext(), "password is Empty", Toast.LENGTH_SHORT).show();
        }  else if (new_password.equals(old_password)){
            Toast.makeText(requireContext(), "old password match with new password", Toast.LENGTH_SHORT).show();
        }   else if (new_password.length() > 6) {
            Toast.makeText(requireContext(), "Password is too small", Toast.LENGTH_SHORT).show();
        }

        // get token
        String token = "Bearer " + preferenceManager.getString(Constant.ACCESS_TOKEN);
        // send new_password and confirm_password.

        ChangePasswordRequest request = new ChangePasswordRequest(new_password , confirm_password , old_password);

        // change password from api server
        ApiRetrofit.getapi().create(RetrofitApis.class).change_password(token , request).enqueue(new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(@NotNull Call<ChangePasswordResponse> call, @NotNull Response<ChangePasswordResponse> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Toast.makeText(getContext(), "Changed successfully", Toast.LENGTH_SHORT).show();
                    dismiss();
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                Toast.makeText(requireContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onResponse: False " + t.getLocalizedMessage());
            }
        });

    }

}