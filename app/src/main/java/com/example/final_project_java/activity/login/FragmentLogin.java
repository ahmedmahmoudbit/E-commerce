package com.example.final_project_java.activity.login;

import android.content.Intent;
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
import com.example.final_project_java.activity.activities.Home_activity;
import com.example.final_project_java.databinding.FragmentLoginBinding;
import com.example.final_project_java.network.RetrofitApis;
import com.example.final_project_java.shared.Constant;
import com.example.final_project_java.shared.PreferenceManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentLogin extends Fragment {
    FragmentLoginBinding binding;
    private static final String TAG = "Fragment_Login";
    NavController navController;
    RetrofitApis apis;
    PreferenceManager preferenceManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment__login, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        preferenceManager = new PreferenceManager(requireContext());

        token_device();
        listener();
        shared();
    }

    private void listener() {

        binding.loginToSignUp.setOnClickListener(v -> navController.navigate(R.id.action_fragment_Login2_to_sign_up_fragment));
        binding.loginToForgott.setOnClickListener(v -> navController.navigate(R.id.action_fragment_Login2_to_forgot_password_fragment));
        binding.loginToHome.setOnClickListener(v -> {

            progressBar(true);
            retrofit();
        });

    }

    private void shared() {
        if (preferenceManager.getBoolean(Constant.KEY_PREFERENCE_NAME) ) {
            startActivity(new Intent(requireContext() , Home_activity.class));
            requireActivity().finish();

        }
    }

    private void token_device() {
        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                Log.i(TAG, "onSuccess: Token device "+ s);
                preferenceManager.putString(Constant.TOKEN_DEVICE , s);
            }
        });
    }

    private void retrofit() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://ecommerce-api.senior-consultingco.com")
                .addConverterFactory(GsonConverterFactory.create()).build();

        apis = retrofit.create(RetrofitApis.class);

        String email = binding.loginEmail.getEditText().getText().toString().trim();
        String password = binding.loginPassword.getEditText().getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "Empty Data", Toast.LENGTH_SHORT).show();
        }

        // String token = "Bearer" + Constant.ACCESS_TOKEN ;
        String token = preferenceManager.getString(Constant.TOKEN_DEVICE);
        LoginRequest request = new LoginRequest(email, password , token);
        login(apis, request);

    }

    private void login(RetrofitApis apis, LoginRequest request) {
        apis.login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(!response.isSuccessful()) {
                    assert response.errorBody() != null;
                    LoginResponseError message = new Gson().fromJson(response.errorBody().charStream() , LoginResponseError.class);
                    Toast.makeText(getContext(), message.getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar(false);
                    return;

                } if (response.isSuccessful()) {

                    preferenceManager.putBoolean(Constant.KEY_PREFERENCE_NAME , true);
                    preferenceManager.putInteger(Constant.Key_LOGIN ,response.body().getData().getId());
                    preferenceManager.putString(Constant.ACCESS_TOKEN ,response.body().getData().getAccessToken());

                    Toast.makeText(getContext(), "Welcome", Toast.LENGTH_SHORT).show();

                    startActivity((new Intent(requireActivity() , Home_activity.class)));
                    requireActivity().finish();
                    progressBar(false);
                }


            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressBar(false);
                Log.i(TAG, "Error: " + t.getLocalizedMessage());
                Toast.makeText(requireContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void progressBar(Boolean loading) {
        if (loading) {
            binding.progress.setVisibility(View.VISIBLE);
            binding.login.setVisibility(View.GONE);
        } else {
            binding.login.setVisibility(View.VISIBLE);
            binding.progress.setVisibility(View.GONE);
        }
    }



}