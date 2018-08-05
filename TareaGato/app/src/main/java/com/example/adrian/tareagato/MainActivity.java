package com.example.adrian.tareagato;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button Bo1;
    private Button Bo2;
    private Button Bo3;
    private Button Bo4;
    private Button Bo5;
    private Button Bo6;
    private Button Bo7;
    private Button Bo8;
    private Button Bo9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bo1= findViewById(R.id.B1);
        Bo1.setOnClickListener(this);
    }
    @Override
    public void OnClick(View view){
        if (view.getId()==R.id.B1){
            Toast.makeText(this, "Se le dio Click gordo", Toast.LENGTH_LONG).show();

        }
    }
}
