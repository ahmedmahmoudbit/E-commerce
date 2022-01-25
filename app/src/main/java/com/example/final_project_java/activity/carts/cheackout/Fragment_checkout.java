package com.example.final_project_java.activity.carts.cheackout;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.final_project_java.R;
import com.example.final_project_java.activity.activities.MainActivity;
import com.example.final_project_java.activity.carts.cheackout.data.PlaceOrderRequest;
import com.example.final_project_java.activity.carts.cheackout.data.PlaceOrderResponse;
import com.example.final_project_java.adapter.Adapter_checkout;
import com.example.final_project_java.database.data.Data_checkout;
import com.example.final_project_java.database.network.ApiRetrofit;
import com.example.final_project_java.database.network.RetrofitApis;
import com.example.final_project_java.database.shared.Constant;
import com.example.final_project_java.database.shared.PreferenceManager;
import com.example.final_project_java.databinding.FragmentCheckoutBinding;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_checkout extends Fragment {
    private final int LOCATION_REQUEST_CODE = 1001;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private LocationManager manager;
    private final int LOCATION_REQUEST_SETTING = 1002;
    FragmentCheckoutBinding binding;
    PreferenceManager preferenceManager;
    NavController navController;
    PlaceOrderRequest request;
    private static final String TAG = "Fragment_checkout";

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
        preferenceManager = new PreferenceManager(requireContext());
        manager = (LocationManager) getActivity().getSystemService(MainActivity.LOCATION_SERVICE);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(4000);
        locationRequest.setFastestInterval(2000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        askForPermission();
        clicks();
        recycler_item();



    }

    private void clicks() {
        binding.placeOrder.setOnClickListener(v -> getDataFromApi());
        binding.closePage.setOnClickListener(v -> navController.navigateUp());
        binding.changePlace.setOnClickListener(v -> getting_your_data_location());

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

    @Override
    public void onStart() {
        super.onStart();

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLastLocation();
            cheackSetSettingAndStartLocation();
        } else {
            askForPermission();
        }

        // chek on or off wifi

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            check_Gps_ON_or_OFF();
            binding.changePlace.setVisibility(View.GONE);
        }
        else {
            binding.checkGps.setVisibility(View.GONE);
        }

    }

    private void getting_your_data_location() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {

                Location location = task.getResult();
                if (location != null) {
                    try {
                        // if you change language data use ( Locale.ENGLISH )
                        Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());
                        List<Address> address = geocoder.getFromLocation(
                                location.getLatitude(),location.getLongitude(),1
                        );
                        Log.i(TAG, "getLatitude: "+address.get(0).getLatitude());
                        Log.i(TAG, "getLongitude: "+address.get(0).getLongitude());
                        preferenceManager.putString(Constant.LATITUDE , (Double.toString(address.get(0).getLatitude())));
                        preferenceManager.putString(Constant.LONGITUDE , (Double.toString(address.get(0).getLongitude())));

//                        binding.location.setText(Html.fromHtml("<font color ='#57837B'><b>Longitude :</b><br></font>" +address.get(0).getLongitude()));
//                        binding.code.setText(Html.fromHtml("<font color ='#57837B'><b>CountryName :</b><br></font>" +address.get(0).getCountryCode()));
//                        binding.country.setText(Html.fromHtml("<font color ='#57837B'><b>Locality :</b><br></font>" +address.get(0).getLocality()));

                        binding.country.setText(Html.fromHtml("<font color ='#57837B'><b>CountryCode :</b><br></font>" +address.get(0).getCountryName()));
                        binding.location.setText(Html.fromHtml("<font color ='#57837B'><b>AddressLine :</b><br></font>" +address.get(0).getAddressLine(0)));
                        binding.code.setText(Html.fromHtml("<font color ='#57837B'><b>PostalCode :</b><br></font>" +address.get(0).getPostalCode()));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }

    private void check_Gps_ON_or_OFF() {
        binding.checkGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                        .addLocationRequest(locationRequest);
                builder.setAlwaysShow(true);

                Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getActivity().getApplicationContext())
                        .checkLocationSettings(builder.build());

                result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                        try {
                            LocationSettingsResponse response = task.getResult(ApiException.class);
                            binding.checkGps.setVisibility(View.GONE);
                            Toast.makeText(requireContext(), "GPS in ON", Toast.LENGTH_SHORT).show();


                        } catch (ApiException e) {

                            switch (e.getStatusCode()) {

                                case LocationSettingsStatusCodes
                                        .RESOLUTION_REQUIRED:
                                    try {
                                        ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                        resolvableApiException.startResolutionForResult(requireActivity(), LOCATION_REQUEST_SETTING);

                                    } catch (IntentSender.SendIntentException ex) {

                                    }
                                    break;
                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                    break;
                            }

                        }

                    }
                });
            }
        });
    }

    // يتبع للخطوه السابق وهي التاكد من عمل gps على موقع المستخدم
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LOCATION_REQUEST_SETTING) {

            switch (resultCode) {
                case Activity.RESULT_OK:
                    Toast.makeText(requireContext(), "GPS is True", Toast.LENGTH_SHORT).show();
                    break;
                case Activity.RESULT_CANCELED:
                    Toast.makeText(requireContext(), "GPS is required to be turned on", Toast.LENGTH_SHORT).show();
                    return;
            }

        }

    }

    // اعطاء صلاحيات الموقع للتطبيق
    private void askForPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
            } else {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);

            }
        }
    }

    // نتيجة البيرميشن
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                cheackSetSettingAndStartLocation();
            }
        }
    }

    // الحصول على اخر موقع للمستخدم
    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    Log.i(TAG, "onSuccess: " + location.toString());
                    Log.i(TAG, "onSuccess: " + location.getLatitude());
                    Log.i(TAG, "onSuccess: " + location.getLongitude());
                } else {
                    Log.i(TAG, "onSuccess: Location was null ");
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String Error = e.getMessage();
                Log.i(TAG, "onFailure: " + Error);
                Toast.makeText(requireContext(), Error, Toast.LENGTH_LONG).show();

            }
        });
    }

    // التاكد من اعدادات بداية التطبيق وحصول التطبيق على البيرميشن
    private void cheackSetSettingAndStartLocation() {
        LocationSettingsRequest locationSettingsRequest = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest).build();

        SettingsClient settingsClient = LocationServices.getSettingsClient(requireActivity());
        Task<LocationSettingsResponse> locationSettingsRequestTask = settingsClient.checkLocationSettings(locationSettingsRequest);

        locationSettingsRequestTask.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String error = e.getMessage();
                Log.i(TAG, "onFailure 2: " + error);
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataFromApi() {
        
        String latitude = preferenceManager.getString(Constant.LATITUDE);
        String longitude = preferenceManager.getString(Constant.LONGITUDE);
        double _longitude = Double.parseDouble(longitude);
        double _latitude = Double.parseDouble(latitude);
        String token = "Bearer " + preferenceManager.getString(Constant.ACCESS_TOKEN);

        Log.i(TAG, "getDataFromApi: "+latitude+longitude);
        Log.i(TAG, "getDataFromApi: "+token);
        
        request = new PlaceOrderRequest(_latitude , _longitude);
        ApiRetrofit.getapi().create(RetrofitApis.class).addPlaceOrder(request,token).enqueue(new Callback<PlaceOrderResponse>() {
            @Override
            public void onResponse(Call<PlaceOrderResponse> call, Response<PlaceOrderResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(requireContext(), "The request has been submitted", Toast.LENGTH_SHORT).show();
                    navController.navigate(R.id.action_fragment_checkout_to_order_done);
                } else {

                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PlaceOrderResponse> call, Throwable t) {
                Toast.makeText(requireContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    }
