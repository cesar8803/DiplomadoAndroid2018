package mx.mobilestudio.androidlayoutbasics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView center_center_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        center_center_tv= (TextView) findViewById(R.id.centro);
        //center_center_tv.setText("animation");
        center_center_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Toast.makeText(this,"Se le dio Click gordo", Toast.LENGTH_LONG).show();
        center_center_tv.setText("YA DISTE CLICK");

        Intent intent_pantalla_secundaria = new Intent(this, SecondaryActivity.class);
        startActivity(intent_pantalla_secundaria);
    }
    //.activity_main cambiando eso cambio la ropita de la applicacion
    //TextView es una clase que llama ala objrto center_center_
    //centro lo tome de las palabras fruta etc en activity_main.xlm, es una id.
    //Toast es un metodo estatico el cual no necesita generar una clase para invocarlo.

    //center_center_tv= (TextView) findViewById(R.id.centro); "R." --> R. es una clase que tiene propiedades
    // es una clase estatica que guarda todos los id que tengo en mi clase, si hay nuevos id se cargar a
    // a clase R.

}
