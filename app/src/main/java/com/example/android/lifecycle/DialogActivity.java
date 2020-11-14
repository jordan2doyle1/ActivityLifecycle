package com.example.android.lifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class DialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_dialog);

        Button close_dialog = findViewById(R.id.btn_close_dialog);
        close_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(R.id.btn_close_dialog);
                finishDialog();
            }
        });
    }

    public void finishDialog() {
        DialogActivity.this.finish();
    }

    public boolean onTouchEvent(MotionEvent event) {

        // If the user has touched outside the app, finish the activity.
        if (MotionEvent.ACTION_OUTSIDE == event.getAction()) {
            DialogActivity.this.finish();
            return true;
        }

        // Delegate everything else to Activity.
        return super.onTouchEvent(event);
    }
}
