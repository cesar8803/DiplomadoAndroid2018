package mx.mobilestudio.androidlayoutbasics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView centro_centro_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        centro_centro_tv = (TextView) findViewById(R.id.centro_centro);
        centro_centro_tv.setOnClickListener(this);
    }

    @Override
    public void onClick (View view){
        Toast.makeText(this,"Se le dio Click",Toast.LENGTH_LONG).show();
        centro_centro_tv.setText("asdasdasdasdasdasdasd");

        Intent intent_pantalla_secundaria = new Intent(this,SecondaryActivity.class);
        startActivity(intent_pantalla_secundaria);

    }

}
