package com.example.fcmfakesessionstart;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MyActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    boolean isClosed;
    Intent intent;
    JobScheduler scheduler;
    JobInfo info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        ComponentName componentName = new ComponentName(MyActivity.this, MyJobService.class);
        info = new JobInfo.Builder(1, componentName)
                .setPeriodic(10000)
                .build();
        scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.schedule(info);
        int resultCode = scheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "JOb Scheduled");
        } else {
            Log.d(TAG, "Job Scheduling failed");
        }

        intent = getIntent();
        isClosed = intent.getBooleanExtra("isClosed", false);

        if (isClosed == false) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    finish();
                }
            }, 5000);
        }
    }
}