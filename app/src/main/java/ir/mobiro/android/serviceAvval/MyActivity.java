package ir.mobiro.android.serviceAvval;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Random;

public class MyActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    private boolean shouldClose;
    private Intent intent;
    private JobScheduler scheduler;
    private JobInfo info;
    Random random;
    private int randomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        createRandomNumber();
        scheduleJob();

        intent = getIntent();
        shouldClose = intent.getBooleanExtra(MyJobService.SHOULD_CLOSE, true);

        if (shouldClose == true) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    finish();
                }
            }, 5000);
        }
    }

    private void createRandomNumber() {
        random = new Random();
        randomNumber = random.nextInt((15 - 10) + 1) + 10;
    }

    private void scheduleJob() {
        ComponentName componentName = new ComponentName(MyActivity.this, MyJobService.class);
        info = new JobInfo.Builder(1, componentName)
                .setPeriodic(randomNumber * 60 * 1000)
                .build();
        scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.schedule(info);

        int resultCode = scheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "JOb Scheduled");
        } else {
            Log.d(TAG, "Job Scheduling failed");
        }
    }
}