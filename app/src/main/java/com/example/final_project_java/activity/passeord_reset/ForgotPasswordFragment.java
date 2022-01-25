package com.example.final_project_java.activity.passeord_reset;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.passeord_reset.data.ForgotPasswordResponse;
import com.example.final_project_java.database.network.ApiRetrofit;
import com.example.final_project_java.database.network.RetrofitApis;
import com.example.final_project_java.databinding.FragmentForgotPasswordFragmentBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordFragment extends Fragment {
    NavController navController;
    FragmentForgotPasswordFragmentBinding binding;
    private static final String TAG = "ForgotPasswordFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgot_password_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        binding.forgottToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_forgot_password_fragment_to_fragment_Login2);
            }
        });
        binding.forgottSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgot_password();
            }
        });


    }
    private void forgot_password() {
        String email = binding.forgotPass.getEditText().getText().toString().trim();
        ApiRetrofit.getapi().create(RetrofitApis.class).forgot_password(email).enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(requireContext(), "password reset has been sent to your email", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(requireContext(), "Invalid mail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
                Log.i(TAG, "ForgotPasswordFragment: Failed");
                Toast.makeText(requireContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}