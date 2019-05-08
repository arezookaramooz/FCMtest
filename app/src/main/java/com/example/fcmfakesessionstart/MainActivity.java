package com.example.fcmfakesessionstart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    Button startButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, MyActivity.class);
                startActivity(myIntent);
                finish();
            }
        });


        FirebaseAnalytics.getInstance(this).logEvent("SessionStart", null);
        FirebaseAnalytics.getInstance(this).setMinimumSessionDuration(1);
        FirebaseAnalytics.getInstance(this).setSessionTimeoutDuration(1);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy is called");
        super.onDestroy();
    }
}
