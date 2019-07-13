package com.example.adrian.tareagato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button Bo1, Bo2, Bo3, Bo4, Bo5, Bo6, Bo7, Bo8, Bo9, Rst;
    private int numerador, numerador1, numerador2, numerador3, numerador4, numerador5, numerador6,
    numerador7, numerador8, numerador9, numerador1b, numerador2b, numerador3b, numerador4b,
    numerador5b, numerador6b, numerador7b, numerador8b, numerador9b;

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

        Rst=findViewById(R.id.reset);
        Rst.setOnClickListener(this);


    }
    @Override
    public void onClick(View view){
        if (view.getId()==R.id.B1&& numerador==0 && numerador1 != 1 && numerador1b != 1) {
            numerador++;
            Bo1.setText("X");
            numerador1=1;
        } else if (view.getId()==R.id.B2&& numerador==0 && numerador2 !=1 && numerador2b != 1)  {
            numerador++;
            Bo2.setText("X");
            numerador2=1;
        } else if (view.getId()==R.id.B3 && numerador==0 && numerador3 !=1 && numerador3b != 1) {
            numerador++;
            Bo3.setText("X");
            numerador3=1;
        }else if (view.getId()==R.id.B4 && numerador==0 && numerador4 !=1 && numerador4b != 1){
            numerador++;
            Bo4.setText("X");
            numerador4=1;
        }else if (view.getId()==R.id.B5 && numerador==0 && numerador5 !=1 && numerador5b != 1){
            numerador++;
            Bo5.setText("X");
            numerador5=1;
        }else if (view.getId()==R.id.B6 && numerador==0 && numerador6 !=1 && numerador6b != 1){
            numerador++;
            Bo6.setText("X");
            numerador6=1;
        }else if (view.getId()==R.id.B7 && numerador==0 && numerador7 !=1 && numerador7b != 1){
            numerador++;
            Bo7.setText("X");
            numerador7=1;
        }else if (view.getId()==R.id.B8 && numerador==0 && numerador8 !=1 && numerador8b != 1){
            Bo8.setText("X");
            numerador++;
            numerador8=1;
        }else if (view.getId()==R.id.B9 && numerador==0 && numerador9 !=1 && numerador9b != 1){
            Bo9.setText("X");
            numerador++;
            numerador9=1;
        }else if(view.getId()==R.id.B1 && numerador==1 && numerador1 != 1 && numerador1b !=1){
            Bo1.setText("O");
            --numerador;
            numerador1b=1;
        }else if (view.getId()==R.id.B2 && numerador==1 && numerador2 != 1 && numerador2b!=1 ){
            Bo2.setText("O");
            --numerador;
            numerador2b=1;
        }else if (view.getId()==R.id.B3 && numerador==1 && numerador3 !=1 && numerador3b!=1){
            Bo3.setText("O");
            --numerador;
            numerador3b=1;
        }else if (view.getId()==R.id.B4 && numerador==1 && numerador4 !=1 && numerador4b!=1){
            Bo4.setText("O");
            --numerador;
            numerador4b=1;
        }else if (view.getId()==R.id.B5 && numerador==1 && numerador5 !=1 && numerador5b!=1){
            Bo5.setText("O");
            --numerador;
            numerador5b=1;
        }else if (view.getId()==R.id.B6 && numerador==1 && numerador6 !=1 && numerador6b!=1){
            Bo6.setText("O");
            --numerador;
            numerador6b=1;
        }else if (view.getId()==R.id.B7 && numerador==1 && numerador7 !=1 && numerador7b!=1){
            Bo7.setText("O");
            --numerador;
            numerador7b=1;
        }else if (view.getId()==R.id.B8 && numerador==1 && numerador8 !=1 && numerador8b!=1){
            Bo8.setText("O");
            --numerador;
            numerador8b=1;
        }else if (view.getId()==R.id.B9 && numerador==1 && numerador9!=1 && numerador9b!=1){
            Bo9.setText("O");
            --numerador;
            numerador9b=1;
        }
        System.out.println(numerador);


        if (view.getId()==R.id.B1 && numerador==1 && numerador1==1 && numerador2==1 && numerador3==1){
            Bo1.setText("WINNER");
            Bo2.setText("WINNER");
            Bo3.setText("WINNER");

            Intent segu = new Intent(MainActivity.this,Second.class);
            startActivity(segu);

        }  else if (view.getId()==R.id.B1 && numerador==1 && numerador1==1 && numerador4==1 && numerador7==1){
            Bo1.setText("WINNER");
            Bo4.setText("WINNER");
            Bo7.setText("WINNER");
        }else if (view.getId()==R.id.B1 && numerador==1 && numerador1==1 && numerador5==1 && numerador9==1){
            Bo1.setText("WINNER");
            Bo5.setText("WINNER");
            Bo9.setText("WINNER");
        }else if (view.getId()==R.id.B7 && numerador==1 && numerador7==1 && numerador4==1 && numerador1==1){
            Bo7.setText("WINNER");
            Bo4.setText("WINNER");
            Bo1.setText("WINNER");
        }  else if (view.getId()==R.id.B7 && numerador==1 && numerador7==1 && numerador8==1 && numerador9==1){
            Bo7.setText("WINNER");
            Bo8.setText("WINNER");
            Bo9.setText("WINNER");
        }else if (view.getId()==R.id.B7 && numerador==1 && numerador7==1 && numerador5==1 && numerador3==1){
            Bo7.setText("WINNER");
            Bo5.setText("WINNER");
            Bo3.setText("WINNER");
        }else if (view.getId()==R.id.B3 && numerador==1 && numerador3==1 && numerador6==1 && numerador9==1){
            Bo3.setText("WINNER");
            Bo6.setText("WINNER");
            Bo9.setText("WINNER");
        } else if (view.getId()==R.id.B3 && numerador==1 && numerador3==1 && numerador2==1 && numerador1==1){
            Bo3.setText("WINNER");
            Bo2.setText("WINNER");
            Bo1.setText("WINNER");
        }else if (view.getId()==R.id.B3 && numerador==1 && numerador3==1 && numerador5==1 && numerador7==1) {
            Bo3.setText("WINNER");
            Bo5.setText("WINNER");
            Bo7.setText("WINNER");
        }else if (view.getId()==R.id.B9 && numerador==1 && numerador9==1 && numerador8==1 && numerador7==1){
            Bo9.setText("WINNER");
            Bo8.setText("WINNER");
            Bo7.setText("WINNER");
        } else if (view.getId()==R.id.B9 && numerador==1 && numerador9==1 && numerador5==1 && numerador1==1){
            Bo9.setText("WINNER");
            Bo5.setText("WINNER");
            Bo1.setText("WINNER");
        }else if (view.getId()==R.id.B9 && numerador==1 && numerador9==1 && numerador6==1 && numerador3==1) {
            Bo9.setText("WINNER");
            Bo6.setText("WINNER");
            Bo3.setText("WINNER");
        }else if (view.getId()==R.id.B4 && numerador==1 && numerador4==1 && numerador1==1 && numerador7==1){
            Bo4.setText("WINNER");
            Bo1.setText("WINNER");
            Bo7.setText("WINNER");
        } else if (view.getId()==R.id.B4 && numerador==1 && numerador4==1 && numerador5==1 && numerador6==1){
            Bo4.setText("WINNER");
            Bo5.setText("WINNER");
            Bo6.setText("WINNER");
        }else if (view.getId()==R.id.B2 && numerador==1 && numerador1==1 && numerador2==1 && numerador3==1){
            Bo1.setText("WINNER");
            Bo2.setText("WINNER");
            Bo3.setText("WINNER");
        } else if (view.getId()==R.id.B2 && numerador==1 && numerador2==1 && numerador5==1 && numerador8==1){
            Bo2.setText("WINNER");
            Bo5.setText("WINNER");
            Bo8.setText("WINNER");
        }else if (view.getId()==R.id.B6 && numerador==1 && numerador6==1 && numerador3==1 && numerador9==1){
            Bo6.setText("WINNER");
            Bo3.setText("WINNER");
            Bo9.setText("WINNER");
        } else if (view.getId()==R.id.B6 && numerador==1 && numerador4==1 && numerador5==1 && numerador6==1){
            Bo4.setText("WINNER");
            Bo5.setText("WINNER");
            Bo6.setText("WINNER");
        }else if (view.getId()==R.id.B8 && numerador==1 && numerador7==1 && numerador8==1 && numerador9==1){
            Bo7.setText("WINNER");
            Bo8.setText("WINNER");
            Bo9.setText("WINNER");
        } else if (view.getId()==R.id.B8 && numerador==1 && numerador8==1 && numerador5==1 && numerador2==1){
            Bo8.setText("WINNER");
            Bo5.setText("WINNER");
            Bo2.setText("WINNER");
        }else if (view.getId()==R.id.B5 && numerador==1 && numerador4==1 && numerador5==1 && numerador6==1){
            Bo4.setText("WINNER");
            Bo5.setText("WINNER");
            Bo6.setText("WINNER");
        } else if (view.getId()==R.id.B5 && numerador==1 && numerador2==1 && numerador5==1 && numerador8==1){
            Bo2.setText("WINNER");
            Bo5.setText("WINNER");
            Bo8.setText("WINNER");
        }else if (view.getId()==R.id.B5 && numerador==1 && numerador1==1 && numerador5==1 && numerador9==1){
            Bo1.setText("WINNER");
            Bo5.setText("WINNER");
            Bo9.setText("WINNER");
        } else if (view.getId()==R.id.B5 && numerador==1 && numerador3==1 && numerador5==1 && numerador7==1) {
            Bo3.setText("WINNER");
            Bo5.setText("WINNER");
            Bo7.setText("WINNER");
        }


        if (view.getId()==R.id.B1 && numerador==0 && numerador1b==1 && numerador2b==1 && numerador3b==1){
            Bo1.setText("WINNER");
            Bo2.setText("WINNER");
            Bo3.setText("WINNER");
        }  else if (view.getId()==R.id.B1 && numerador==0 && numerador1b==1 && numerador4b==1 && numerador7b==1){
            Bo1.setText("WINNER");
            Bo4.setText("WINNER");
            Bo7.setText("WINNER");
        }else if (view.getId()==R.id.B1 && numerador==0 && numerador1b==1 && numerador5b==1 && numerador9b==1){
            Bo1.setText("WINNER");
            Bo5.setText("WINNER");
            Bo9.setText("WINNER");
        }else if (view.getId()==R.id.B7 && numerador==0 && numerador7b==1 && numerador4b==1 && numerador1b==1){
            Bo7.setText("WINNER");
            Bo4.setText("WINNER");
            Bo1.setText("WINNER");
        }  else if (view.getId()==R.id.B7 && numerador==0 && numerador7b==1 && numerador8b==1 && numerador9b==1){
            Bo7.setText("WINNER");
            Bo8.setText("WINNER");
            Bo9.setText("WINNER");
        }else if (view.getId()==R.id.B7 && numerador==0 && numerador7b==1 && numerador5b==1 && numerador3b==1){
            Bo7.setText("WINNER");
            Bo5.setText("WINNER");
            Bo3.setText("WINNER");
        }else if (view.getId()==R.id.B3 && numerador==0 && numerador3b==1 && numerador6b==1 && numerador9b==1){
            Bo3.setText("WINNER");
            Bo6.setText("WINNER");
            Bo9.setText("WINNER");
        } else if (view.getId()==R.id.B3 && numerador==0 && numerador3b==1 && numerador2b==1 && numerador1b==1){
            Bo3.setText("WINNER");
            Bo2.setText("WINNER");
            Bo1.setText("WINNER");
        }else if (view.getId()==R.id.B3 && numerador==0 && numerador3b==1 && numerador5b==1 && numerador7b==1) {
            Bo3.setText("WINNER");
            Bo5.setText("WINNER");
            Bo7.setText("WINNER");
        }else if (view.getId()==R.id.B9 && numerador==0 && numerador9b==1 && numerador8b==1 && numerador7b==1){
            Bo9.setText("WINNER");
            Bo8.setText("WINNER");
            Bo7.setText("WINNER");
        } else if (view.getId()==R.id.B9 && numerador==0 && numerador9b==1 && numerador5b==1 && numerador1b==1){
            Bo9.setText("WINNER");
            Bo5.setText("WINNER");
            Bo1.setText("WINNER");
        }else if (view.getId()==R.id.B9 && numerador==0 && numerador9b==1 && numerador6b==1 && numerador3b==1) {
            Bo9.setText("WINNER");
            Bo6.setText("WINNER");
            Bo3.setText("WINNER");
        }else if (view.getId()==R.id.B4 && numerador==0 && numerador4b==1 && numerador1b==1 && numerador7b==1){
            Bo4.setText("WINNER");
            Bo1.setText("WINNER");
            Bo7.setText("WINNER");
        } else if (view.getId()==R.id.B4 && numerador==0 && numerador4b==1 && numerador5b==1 && numerador6b==1){
            Bo4.setText("WINNER");
            Bo5.setText("WINNER");
            Bo6.setText("WINNER");
        }else if (view.getId()==R.id.B2 && numerador==0 && numerador1b==1 && numerador2b==1 && numerador3b==1){
            Bo1.setText("WINNER");
            Bo2.setText("WINNER");
            Bo3.setText("WINNER");
        } else if (view.getId()==R.id.B2 && numerador==0 && numerador2b==1 && numerador5b==1 && numerador8b==1){
            Bo2.setText("WINNER");
            Bo5.setText("WINNER");
            Bo8.setText("WINNER");
        }else if (view.getId()==R.id.B6 && numerador==0 && numerador6b==1 && numerador3b==1 && numerador9b==1){
            Bo6.setText("WINNER");
            Bo3.setText("WINNER");
            Bo9.setText("WINNER");
        } else if (view.getId()==R.id.B6 && numerador==0 && numerador4b==1 && numerador5b==1 && numerador6b==1){
            Bo4.setText("WINNER");
            Bo5.setText("WINNER");
            Bo6.setText("WINNER");
        }else if (view.getId()==R.id.B8 && numerador==0 && numerador7b==1 && numerador8b==1 && numerador9b==1){
            Bo7.setText("WINNER");
            Bo8.setText("WINNER");
            Bo9.setText("WINNER");
        } else if (view.getId()==R.id.B8 && numerador==0 && numerador8b==1 && numerador5b==1 && numerador2b==1){
            Bo8.setText("WINNER");
            Bo5.setText("WINNER");
            Bo2.setText("WINNER");
        }else if (view.getId()==R.id.B5 && numerador==0 && numerador4b==1 && numerador5b==1 && numerador6b==1){
            Bo4.setText("WINNER");
            Bo5.setText("WINNER");
            Bo6.setText("WINNER");
        } else if (view.getId()==R.id.B5 && numerador==0 && numerador2b==1 && numerador5b==1 && numerador8b==1){
            Bo2.setText("WINNER");
            Bo5.setText("WINNER");
            Bo8.setText("WINNER");
        }else if (view.getId()==R.id.B5 && numerador==0 && numerador1b==1 && numerador5b==1 && numerador9b==1){
            Bo1.setText("WINNER");
            Bo5.setText("WINNER");
            Bo9.setText("WINNER");
        } else if (view.getId()==R.id.B5 && numerador==0 && numerador3b==1 && numerador5b==1 && numerador7b==1) {
            Bo3.setText("WINNER");
            Bo5.setText("WINNER");
            Bo7.setText("WINNER");
            }

            if (view.getId()==R.id.reset){
                Bo1.setText("Play again.");
                Bo2.setText("Play again.");
                Bo3.setText("Play again.");
                Bo4.setText("Play again.");
                Bo5.setText("Play again.");
                Bo6.setText("Play again.");
                Bo7.setText("Play again.");
                Bo8.setText("Play again.");
                Bo9.setText("Play again.");
                numerador=0;
                numerador1=0;
                numerador2=0;
                numerador3=0;
                numerador4=0;
                numerador5=0;
                numerador6=0;
                numerador7=0;
                numerador8=0;
                numerador9=0;
                numerador1b=0;
                numerador2b=0;
                numerador3b=0;
                numerador4b=0;
                numerador5b=0;
                numerador6b=0;
                numerador7b=0;
                numerador8b=0;
                numerador9b=0;
            }

        }
}
