package mx.mobilestudio.promohunters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton imageButtonAddNewPromo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButtonAddNewPromo = findViewById(R.id.button_add_new);
        imageButtonAddNewPromo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
