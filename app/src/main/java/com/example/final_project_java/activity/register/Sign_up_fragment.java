package com.example.final_project_java.activity.register;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.final_project_java.R;
import com.example.final_project_java.network.RetrofitApis;
import com.example.final_project_java.databinding.FragmentSignUpFragmentBinding;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Sign_up_fragment extends Fragment {
    FragmentSignUpFragmentBinding binding;
    NavController navController;
    RetrofitApis apis;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        listener();
    }

    private void listener() {

        binding.sigupToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               navController.navigate(R.id.action_sign_up_fragment_to_fragment_Login2);
            }
        });

        binding.clickSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressbar(true);
                createApis();
            }
        });

    }

    private void createApis() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://ecommerce-api.senior-consultingco.com/").
                addConverterFactory(GsonConverterFactory.create()).build();

        apis = retrofit.create(RetrofitApis.class);

        String name = binding.registerName.getEditText().getText().toString().trim();
        String email = binding.registerEmail.getEditText().getText().toString().trim();
        String password = binding.registerPassword.getEditText().getText().toString().trim();
        String re_password = binding.registerRepassword.getEditText().getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || re_password.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill in the data", Toast.LENGTH_SHORT).show();
            progressbar(false);
            return;
        }
        if (!password.equals(re_password)) {
            Toast.makeText(requireContext(), "the password not mach", Toast.LENGTH_SHORT).show();
            progressbar(false);
            return;
        }

        progressbar(true);
        RegisterRequest registerRequest = new RegisterRequest(name, email, password, re_password);
        register(apis, registerRequest);

    }

    private void register(RetrofitApis apis, RegisterRequest registerRequest) {
        apis.register(registerRequest).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if (!response.isSuccessful()) {
                    RegisterResponseError message = new Gson().fromJson(response.errorBody().charStream() , RegisterResponseError.class);
                    Toast.makeText(requireContext(), message.getData().toString(), Toast.LENGTH_SHORT).show();
                    progressbar(false);
                    return;
                } else {
                    Toast.makeText(getContext(), "Account Created", Toast.LENGTH_SHORT).show();
                    navController.navigate(R.id.action_sign_up_fragment_to_fragment_Login2);
                    progressbar(false);
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(requireContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void progressbar(Boolean progress) {
        if (progress) {
            binding.progress.setVisibility(View.VISIBLE);
            binding.clickSignUp.setVisibility(View.GONE);
        } else {
            binding.clickSignUp.setVisibility(View.VISIBLE);
            binding.progress.setVisibility(View.GONE);
        }
    }

}