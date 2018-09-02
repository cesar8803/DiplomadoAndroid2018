package com.example.adrian.placefind3;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adrian.placefind3.Adaptr.AdaptrList;
import com.example.adrian.placefind3.Mode.Liveapi;
import com.example.adrian.placefind3.Mode.Store;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener, Response.ErrorListener{
    private Button bton1;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bton1=findViewById(R.id.bu1);
        bton1.setOnClickListener(this);

        recyclerView=(RecyclerView) findViewById(R.id.rview);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this,"Yeah Budy!!!",Toast.LENGTH_LONG).show();
        callLiverpoolAPI();
    }

    public void callLiverpoolAPI(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = Uri.parse("https://api.myjson.com/bins/r882c").toString();
        StringRequest stringRequest=new StringRequest(Request.Method.GET,URL,this,this);
        queue.add(stringRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object response) {
        Gson gson = new Gson();
        Liveapi liveapi=gson.fromJson((String) response, Liveapi.class);
        List<Store> stores = liveapi.getStores();
        AdaptrList adaptrList = new AdaptrList(stores);
        recyclerView.setAdapter(adaptrList);
    }
}
