package com.example.locationapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class LocationActivity extends AppCompatActivity {

    boolean locationPermissionGranted;
    private FusedLocationProviderClient fusedLocationClient;
    private Location lastKnownLocation;

    @SuppressWarnings("Convert2Lambda")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        locationPermissionGranted = false;
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        Button lastKnownLocationUpdate = findViewById(R.id.last_known_location_update);
        lastKnownLocationUpdate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                getPermissions();

                if (locationPermissionGranted) {

                    fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                lastKnownLocation = location;
                                TextView lastKnownLocationText = findViewById(R.id.last_known_location_text);
                                lastKnownLocationText.setText(getString(R.string.latitude_and_longitude,
                                        location.getLatitude(), location.getLongitude()));
                            }
                        }
                    });
                }
            }
        });

        Button currentLocationUpdate = findViewById(R.id.current_location_update);
        currentLocationUpdate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                getPermissions();

                if(locationPermissionGranted) {

                    fusedLocationClient.getCurrentLocation(
                            LocationRequest.PRIORITY_HIGH_ACCURACY, null)
                            .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                TextView currentLocationText = findViewById(R.id.current_location_text);
                                currentLocationText.setText(getString(R.string.latitude_and_longitude,
                                        location.getLatitude(), location.getLongitude()));
                            }
                        }
                    });
                }
            }
        });
    }

    private void getPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_DENIED) {
            // The registered ActivityResultCallback gets the result of this request.
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
        } else {
            locationPermissionGranted = true;
            Toast.makeText(this, "Location Permission Already Granted", Toast.LENGTH_LONG).show();
        }
    }

    // Register the permissions callback, which handles the user's response to the system permissions dialog. Save
    // the return value, an instance of ActivityResultLauncher, as an instance variable.
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    locationPermissionGranted = true;
                    Toast.makeText(this, R.string.permission_status_granted, Toast.LENGTH_LONG).show();
                } else {
                    // Explain to the user that the feature is unavailable because the features requires a permission
                    // that the user has denied. At the same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their decision.
                    locationPermissionGranted = false;
                    Toast.makeText(this, R.string.permission_status_denied, Toast.LENGTH_LONG).show();
                }
            });
}