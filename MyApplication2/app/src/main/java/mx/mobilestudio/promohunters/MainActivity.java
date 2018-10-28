package mx.mobilestudio.promohunters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton= (ImageButton) findViewById(R.id.Bmas);
        imageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this,"funciona", Toast.LENGTH_LONG).show();

    }
}
