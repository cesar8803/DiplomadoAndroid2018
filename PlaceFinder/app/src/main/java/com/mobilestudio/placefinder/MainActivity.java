package com.mobilestudio.placefinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button centrado;
    private Button centrado2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        centrado = (Button) findViewById(R.id.center);
        centrado2 = (Button) findViewById(R.id.center2);

        centrado.setOnClickListener(this);
        centrado2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        //mensaje al dar clic
        //Toast.makeText(this,"Se le dio click en FORSQUARE",Toast.LENGTH_LONG).show();


        if(view.getId()==R.id.center){
            Toast.makeText(this,"Se le dio click en FORSQUARE",Toast.LENGTH_LONG).show();
        }else if (view.getId()==R.id.center2){
            Toast.makeText(this,"Se le dio click en FORSQUARE_2",Toast.LENGTH_LONG).show();
        }
    }
}