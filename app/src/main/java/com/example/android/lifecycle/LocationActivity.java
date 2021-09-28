package com.example.android.lifecycle;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
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

import com.example.android.lifecycle.util.StatusTracker;
import com.example.android.lifecycle.util.Utils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnSuccessListener;

public class LocationActivity extends AppCompatActivity {

    private final StatusTracker mStatusTracker = StatusTracker.getInstance();
    boolean locationPermissionGranted;
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
    private String mActivityName;
    private TextView mStatusView;
    private TextView mStatusAllView;
    private FusedLocationProviderClient fusedLocationClient;
    private Location lastKnownLocation;
    private CancellationTokenSource cancellationSource;

    @SuppressWarnings("Convert2Lambda")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        mActivityName = getString(R.string.activity_location_label);
        mStatusView = findViewById(R.id.status_view_a);
        mStatusAllView = findViewById(R.id.status_view_all_a);
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_create));
        Utils.printStatus(mStatusView, mStatusAllView);

        Button start_A = findViewById(R.id.btn_start_a);
        start_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("btn_start_a:" + R.id.btn_start_a);
                startActivityA();
            }
        });
        Button start_B = findViewById(R.id.btn_start_b);
        start_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("btn_start_b:" + R.id.btn_start_b);
                startActivityB();
            }
        });
        Button start_C = findViewById(R.id.btn_start_c);
        start_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("btn_start_c:" + R.id.btn_start_c);
                startActivityC();
            }
        });
        Button finish_Location = findViewById(R.id.btn_finish_location);
        finish_Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("btn_finish_location:" + R.id.btn_finish_location);
                finishActivityLocation();
            }
        });

        locationPermissionGranted = false;
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        cancellationSource = new CancellationTokenSource();

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
                                        lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude()));
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

                if (locationPermissionGranted) {

                    fusedLocationClient.getCurrentLocation(
                            LocationRequest.PRIORITY_HIGH_ACCURACY, cancellationSource.getToken())
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

    @Override
    protected void onStart() {
        super.onStart();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_start));
        Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_restart));
        Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(uiOptions);

        mStatusTracker.setStatus(mActivityName, getString(R.string.on_resume));
        Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_pause));
        Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_stop));
        cancellationSource.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_destroy));
        mStatusTracker.clear();
    }

    public void startActivityA() {
        Intent intent = new Intent(LocationActivity.this, ActivityA.class);
        startActivity(intent);
    }

    public void startActivityB() {
        Intent intent = new Intent(LocationActivity.this, ActivityB.class);
        startActivity(intent);
    }

    public void startActivityC() {
        Intent intent = new Intent(LocationActivity.this, ActivityC.class);
        startActivity(intent);
    }

    public void finishActivityLocation() {
        LocationActivity.this.finish();
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
}