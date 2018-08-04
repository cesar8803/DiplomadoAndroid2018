package com.example.mobilestudiolaptop004.placefinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//Volley --> libreria para trabajar http, esta es de google

    private Button centro;
    private Button boton2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        centro= findViewById(R.id.B1);
        centro.setOnClickListener(this);

        boton2= findViewById(R.id.B2);
        boton2.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.B1 && centro.getText().toString() !="x") {
            centro.setText("x");
            Toast.makeText(this, "Se le dio Click gordo", Toast.LENGTH_LONG).show();
        }
    }
}
