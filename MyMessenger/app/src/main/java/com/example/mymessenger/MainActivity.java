package com.example.mymessenger;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSendMessage(View view){
        EditText texto = (EditText) findViewById(R.id.message);
        String texto2 = texto.getText().toString();
        //Intent intent = new Intent(this, RecievedMessageActivity.class);
        //intent.putExtra(RecievedMessageActivity.EXTRA_MESSAGE, texto2);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, texto2);
        String texto5 = getString(R.string.chooser);
        Intent chosenintent = Intent.createChooser(intent,texto5);
        startActivity(chosenintent);
    }
}
