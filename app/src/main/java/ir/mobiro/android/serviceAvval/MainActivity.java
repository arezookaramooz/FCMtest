package ir.mobiro.android.serviceAvval;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {
    public static final String MY_PREFS_NAME = "Prefs";
    public static final String SESSIONS_NUMBER = "sessions number";
    public static final String TAG = "MainActivity";
    private Button startButton;
    private EditText sessionsNumberBox;
    private int sessionsNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setAnalytics();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionsNumber = Integer.parseInt(sessionsNumberBox.getText().toString());
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putInt(SESSIONS_NUMBER, sessionsNumber - 2);
                editor.apply();
                Intent myIntent = new Intent(MainActivity.this, MyActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
    }

    private void findViews() {
        sessionsNumberBox = findViewById(R.id.sessions_number);
        startButton = findViewById(R.id.start_button);
    }

    private void setAnalytics() {
        FirebaseAnalytics.getInstance(this).setMinimumSessionDuration(1);
        FirebaseAnalytics.getInstance(this).setSessionTimeoutDuration(1);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy is called");
        super.onDestroy();
    }
}
