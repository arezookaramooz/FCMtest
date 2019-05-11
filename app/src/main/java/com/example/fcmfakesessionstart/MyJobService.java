package com.example.fcmfakesessionstart;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.content.SharedPreferences;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MyJobService extends JobService {
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private static final String TAG = "MyJobService";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int sessionsNumber = prefs.getInt("sessions number", 0);
        if (sessionsNumber != 0) {
            Intent intent = new Intent(this, MyActivity.class).putExtra("isClosed", false).setFlags(FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putInt("sessions number", sessionsNumber--);
            editor.apply();
        }
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}