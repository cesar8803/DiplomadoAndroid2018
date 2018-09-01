package mx.mobilestudio.gatoandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button miBoton;
    private Button miBoton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miBoton = (Button) findViewById(R.id.button);
        miBoton2 = (Button) findViewById(R.id.button2);

        miBoton.setOnClickListener(this);
        miBoton2.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {


        if( view.getId()==R.id.button && miBoton.getText().toString() != "X" ){
            miBoton.setText("X");
            Toast.makeText(this, "Se dio click al boton 1", Toast.LENGTH_LONG).show();

        }else if(view.getId()==R.id.button2){
            Toast.makeText(this, "Se dio click al boton 2", Toast.LENGTH_LONG).show();

        }

    }
}
