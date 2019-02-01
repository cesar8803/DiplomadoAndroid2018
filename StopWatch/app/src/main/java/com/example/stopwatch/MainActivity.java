package com.example.stopwatch;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends Activity {

    private int seconds = 0;
    private boolean running;
    private boolean v2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
            v2=savedInstanceState.getBoolean("v2");
        }
        runTimer();
    }

    public void onClickStart(View view){
        running=true;
    }

    public void onClickStop(View view){
        running=false;
    }

    public void onClickReset(View view){
        running=false;
        seconds = 0;
    }

    private void runTimer(){
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(),"%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running){
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (v2){
            running=true;
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
            v2=running;
            running=false;

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("v2",v2);
    }
}
