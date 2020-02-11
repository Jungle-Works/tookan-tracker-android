package com.intertest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import tookan.tookantrack.Trackio;
import tookan.tookantrack.model.TrackerOptions;

public class MainActivity extends AppCompatActivity {

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
            trackerOptions.setPathUpdateTimer(15000);
            trackerOptions.setMarkerIcon(R.drawable.ic_agent_marker);

            Trackio.setSingletonInstance(new Trackio.Builder(this)
                    .appName(getString(R.string.app_name))
                    .userId("your_user_id")
                    .mapKey("your_google_api_key")
                    .trackerOptions(trackerOptions)
                    .build());
        }

        Trackio.get().setupTrackingFragment(MainActivity.this, Trackio.TrackerType.TASK
                , "task_id", R.id.frameLayout, getSupportFragmentManager());

    }

    private void stopTracking() {
        Trackio.get().stopTrackingLocation();
    }
}
