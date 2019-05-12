package ir.mobiro.android.serviceAvval;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.content.SharedPreferences;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MyJobService extends JobService {

    public static final String SHOULD_CLOSE = "should close";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        SharedPreferences prefs = getSharedPreferences(MainActivity.MY_PREFS_NAME, MODE_PRIVATE);
        int sessionsNumber = prefs.getInt(MainActivity.SESSIONS_NUMBER, 0);

        if (sessionsNumber != 0) {

            SharedPreferences.Editor editor = getSharedPreferences(MainActivity.MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putInt(MainActivity.SESSIONS_NUMBER, sessionsNumber - 1);
            editor.apply();

            Intent intent = new Intent(this, MyActivity.class).putExtra(SHOULD_CLOSE, true).setFlags(FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}