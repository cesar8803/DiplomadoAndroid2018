package mx.mobilestudio.androidlayoutbasics;

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

        //cambia valor de texto desde java
        center_center_tv.setText("Alejandro");

        center_center_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        //mensaje al dar clic
        Toast.makeText(this,"Se le dio click",Toast.LENGTH_LONG).show();

    }
}
