package com.intertest;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import tookan.tookantrack.Trackio;
import tookan.tookantrack.model.TrackerOptions;

public class MainActivity extends AppCompatActivity {
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupTrackingLink();
    }

    private void setupTrackingLink() {
        if (!Trackio.isInitialized()) {
            TrackerOptions trackerOptions = new TrackerOptions();
            trackerOptions.setEtaTextColor(R.color.colorPrimary);
            trackerOptions.setPolylineColor(R.color.polyline_color);

            Trackio.setSingletonInstance(new Trackio.Builder(this)
                    .appName(getString(R.string.app_name))
                    .userId("tookan_user_id")
                    .mapKey("your_google_api_key")
                    .trackerOptions(trackerOptions)
                    .build());
        }

        Trackio.get().setupTrackingFragment(MainActivity.this, Trackio.TrackerType.TASK
                , "tookan_task_id", R.id.frameLayout, getSupportFragmentManager());

    }

    private void stopTracking() {
        Trackio.get().stopTrackingLocation();
    }
}
