package com.example.adrian.practicaplacefindermaps2;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener,Response.ErrorListener {
    private Button boton1;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton1=findViewById(R.id.B1);
        boton1.setOnClickListener(this);
        editText=findViewById(R.id.editText);
    }

    @Override
    public void onClick(View view) {
        //Toast.makeText(this,"query",Toast.LENGTH_LONG).show();

        String query = editText.getText().toString();
        Toast.makeText(this,query,Toast.LENGTH_LONG).show();
        requestHTTP(query);
    }

    public void requestHTTP (String query){
        String location = "19.433997"+","+"-99.146006";
        RequestQueue queue=Volley.newRequestQueue(this);
        String loquesea =Uri.parse("https://api.foursquare.com/v2/venues/search").buildUpon()
                .appendQueryParameter("client_id","HOSIY11XMXHWFADXIPQTF5HRZA3YIWIFGRAOA5NIGXOY3CWI")
                .appendQueryParameter("client_secret","OGATJNY0E0JY15PRXYD5MQ2WW3EMFLRAWFHLAOQYSTMVKMHM")
                .appendQueryParameter("v","20130815")
                .appendQueryParameter("ll",location)
                .appendQueryParameter("query",query).build().toString();
        StringRequest stringRequest=new StringRequest(Request.Method.GET,loquesea,this,this);
        queue.add(stringRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object response) {

    }
}
