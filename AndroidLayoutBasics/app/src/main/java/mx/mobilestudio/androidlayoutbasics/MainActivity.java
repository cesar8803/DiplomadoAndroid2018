package mx.mobilestudio.androidlayoutbasics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //enlazar el xml con java, se crea una variable
    private TextView center_center_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //método para buscar una vista con id = center_center dentro del xml
        center_center_tv = (TextView) findViewById(R.id.center_center);


        center_center_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        //mensaje al dar clic
        Toast.makeText(this,"Se le dio click, cambío de Activity y de texto",Toast.LENGTH_LONG).show();
        //cambia valor de texto desde java
        center_center_tv.setText("Alejandro");


        //Decimos en donde se encuentra y hacia donde queremos ir
        Intent intent_pantalla_secundaria = new Intent(this, SecondaryActivity.class);


        //método de la clase activity que tiene como parametro un intent
        startActivity(intent_pantalla_secundaria);

    }
}
