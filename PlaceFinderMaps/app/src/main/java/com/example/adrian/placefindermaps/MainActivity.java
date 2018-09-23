package com.example.adrian.placefindermaps;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener, Response.ErrorListener  {

    private Button boton1;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton1= findViewById(R.id.B1);
        boton1.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.B1){
            String query = editText.getText().toString();
            if (query.isEmpty()==false){
                callFourSquareApi(query);
            }else{
                Toast.makeText(this,"Ingresa una palabra.", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void callFourSquareApi(String query){
        String location = "19.433997"+","+"-99.146006";
        RequestQueue queue = Volley.newRequestQueue(this);
            String URL = Uri.parse("https://api.foursquare.com/v2/venues/search").buildUpon()
                    .appendQueryParameter("client_id","HOSIY11XMXHWFADXIPQTF5HRZA3YIWIFGRAOA5NIGXOY3CWI")
                    .appendQueryParameter("client_secret","OGATJNY0E0JY15PRXYD5MQ2WW3EMFLRAWFHLAOQYSTMVKMHM")
                    .appendQueryParameter("v","20130815")
                    .appendQueryParameter("ll",location)
                    .appendQueryParameter("query",query).build().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL,this,this);
        queue.add(stringRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object response) {

    }
}
