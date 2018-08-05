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
    private int numerador;
    private int numerador1;
    private int numerador2;
    private int numerador3;
    private int numerador4;
    private int numerador5;
    private int numerador6;
    private int numerador7;
    private int numerador8;
    private int numerador9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bo1= findViewById(R.id.B1);
        Bo1.setOnClickListener(this);

        Bo2= findViewById(R.id.B2);
        Bo2.setOnClickListener(this);

        Bo3= findViewById(R.id.B3);
        Bo3.setOnClickListener(this);

        Bo4= findViewById(R.id.B4);
        Bo4.setOnClickListener(this);

        Bo5= findViewById(R.id.B5);
        Bo5.setOnClickListener(this);

        Bo6=findViewById(R.id.B6);
        Bo6.setOnClickListener(this);

        Bo7=findViewById(R.id.B7);
        Bo7.setOnClickListener(this);

        Bo8=findViewById(R.id.B8);
        Bo8.setOnClickListener(this);

        Bo9=findViewById(R.id.B9);
        Bo9.setOnClickListener(this);


    }
    @Override
    public void onClick(View view){
        if (view.getId()==R.id.B1 && numerador==0 && numerador1 != 1) {
            Bo1.setText("X");
            numerador++;
            numerador1=1;
        } else if (view.getId()==R.id.B2 && numerador==0 && numerador2 !=1)  {
            Bo2.setText("X");
            numerador++;
            numerador2=1;
        } else if (view.getId()==R.id.B3 && numerador==0 && numerador3 !=1) {
            Bo3.setText("X");
            numerador++;
            numerador3=1;
        }else if (view.getId()==R.id.B4 && numerador==0 && numerador4 !=1){
            Bo4.setText("X");
            numerador++;
            numerador4=1;
        }else if (view.getId()==R.id.B5 && numerador==0 && numerador5 !=1){
            Bo5.setText("X");
            numerador++;
            numerador5=1;
        }else if (view.getId()==R.id.B6 && numerador==0 && numerador6 !=1){
            Bo6.setText("X");
            numerador++;
            numerador6=1;
        }else if (view.getId()==R.id.B7 && numerador==0 && numerador7 !=1){
            Bo7.setText("X");
            numerador++;
            numerador7=1;
        }else if (view.getId()==R.id.B8 && numerador==0 && numerador8 !=1){
            Bo8.setText("X");
            numerador++;
            numerador8=1;
        }else if (view.getId()==R.id.B9 && numerador==0 && numerador9 !=1){
            Bo9.setText("X");
            numerador++;
            numerador9=1;
        }else if(view.getId()==R.id.B1 && numerador==1 && numerador1 != 1){
            Bo1.setText("O");
            --numerador;
            numerador1=1;
        }else if (view.getId()==R.id.B2 && numerador==1 && numerador2 != 1 ){
            Bo2.setText("O");
            --numerador;
            numerador2=1;
        }else if (view.getId()==R.id.B3 && numerador==1 && numerador3 !=1){
            Bo3.setText("O");
            --numerador;
            numerador3=1;
        }else if (view.getId()==R.id.B4 && numerador==1 && numerador4 !=1){
            Bo4.setText("O");
            --numerador;
            numerador4=1;
        }else if (view.getId()==R.id.B5 && numerador==1 && numerador5 !=1){
            Bo5.setText("O");
            --numerador;
            numerador5=1;
        }else if (view.getId()==R.id.B6 && numerador==1 && numerador6 !=1){
            Bo6.setText("O");
            --numerador;
            numerador6=1;
        }else if (view.getId()==R.id.B7 && numerador==1 && numerador7 !=1){
            Bo7.setText("O");
            --numerador;
            numerador7=1;
        }else if (view.getId()==R.id.B8 && numerador==1 && numerador8 !=1){
            Bo8.setText("O");
            --numerador;
            numerador8=1;
        }else if (view.getId()==R.id.B9 && numerador==1 && numerador9!=1){
            Bo9.setText("O");
            --numerador;
            numerador9=1;
        }
        System.out.println(numerador);

    }
}
