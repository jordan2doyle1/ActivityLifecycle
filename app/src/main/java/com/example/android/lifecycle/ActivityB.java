package com.example.android.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.lifecycle.util.StatusTracker;
import com.example.android.lifecycle.util.Utils;

public class ActivityB extends AppCompatActivity {

    private final StatusTracker mStatusTracker = StatusTracker.getInstance();
    private String mActivityName;
    private TextView mStatusView;
    private TextView mStatusAllView;

    @SuppressWarnings("Convert2Lambda")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        mActivityName = getString(R.string.activity_b_label);
        mStatusView = findViewById(R.id.status_view_b);
        mStatusAllView = findViewById(R.id.status_view_all_b);
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_create));
        Utils.printStatus(mStatusView, mStatusAllView);

        Button start_A = findViewById(R.id.btn_start_a);
        start_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityA();
            }
        });
        Button start_C = findViewById(R.id.btn_start_c);
        start_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityC();
            }
        });
        Button start_fragment = findViewById(R.id.btn_start_fragment);
        start_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment();
            }
        });
        Button finish_B = findViewById(R.id.btn_finish_b);
        finish_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivityB();
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_destroy));
    }

    public void startFragment() {
        Intent intent = new Intent(ActivityB.this, FragmentActivity.class);
        startActivity(intent);
    }

    public void startActivityA() {
        Intent intent = new Intent(ActivityB.this, ActivityA.class);
        startActivity(intent);
    }

    public void startActivityC() {
        Intent intent = new Intent(ActivityB.this, ActivityC.class);
        startActivity(intent);
    }

    public void finishActivityB() {
        ActivityB.this.finish();
    }
}
