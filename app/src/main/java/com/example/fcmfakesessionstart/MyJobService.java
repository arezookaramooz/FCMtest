package com.example.fcmfakesessionstart;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MyJobService extends JobService {

    private static final String TAG = "MyJobService";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Intent intent = new Intent(this, MyActivity.class).putExtra("isClosed", false).setFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}