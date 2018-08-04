package com.example.mobilestudiolaptop008.firebchat.Actitities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mobilestudiolaptop008.firebchat.Fragments.LoginFragment;
import com.example.mobilestudiolaptop008.firebchat.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth =  FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null)
        {

            Intent intent = new Intent(this,DashBoardActivity.class);
            startActivity(intent);
            finish();

        }
        else
        {

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();

        }



    }
}
