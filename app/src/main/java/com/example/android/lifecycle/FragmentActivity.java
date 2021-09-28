package com.example.android.lifecycle;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, FragmentA.class, null)
                    .commit();
        }
    }
}