package com.example.mymessenger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RecievedMessageActivity extends Activity {
    public static final String EXTRA_MESSAGE="message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieved_message);
        Intent intent = getIntent();
        String texto3 = intent.getStringExtra(EXTRA_MESSAGE);
        TextView texto4 = (TextView)findViewById(R.id.message2);
        texto4.setText(texto3);
    }
}
