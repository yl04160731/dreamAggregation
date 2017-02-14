package com.dream.aggregation.activity;

import android.content.Intent;
import android.os.Bundle;

import com.dream.aggregation.DreamAggApplication;
import com.dream.aggregation.MainActivity;
import com.dream.aggregation.R;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ((DreamAggApplication)DreamAggApplication.getContext()).addActivity(this);
        final Intent it = new Intent(this, MainActivity.class);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(it);
            }
        };
        timer.schedule(task, 1000);
    }
}
