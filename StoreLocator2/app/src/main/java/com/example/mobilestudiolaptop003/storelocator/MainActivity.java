package com.example.mobilestudiolaptop003.storelocator;


import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobilestudiolaptop003.storelocator.Adaptero.ResultList;
import com.example.mobilestudiolaptop003.storelocator.modelo.LiverAPI;
import com.example.mobilestudiolaptop003.storelocator.modelo.Store;
import com.example.mobilestudiolaptop004.placefinder.R;
import com.google.gson.Gson;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener, Response.ErrorListener {

    private Button bton1;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        bton1=findViewById(R.id.o1);
        bton1.setOnClickListener(this);

        recyclerView=(RecyclerView) findViewById(R.id.recycleresult);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View view) {
        callLiverpoolAPI();
    }


        public void callLiverpoolAPI(){
            RequestQueue queue = Volley.newRequestQueue(this);
            String URL=Uri.parse("https://api.myjson.com/bins/r882c").toString();
            StringRequest stringRequest=new StringRequest(Request.Method.GET,URL,this,this);
            queue.add(stringRequest);

        }





    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object response) {

        Gson gson = new Gson();
        LiverAPI liverAPI=gson.fromJson((String) response,LiverAPI.class);
        List<Store> stores = liverAPI.getStores();
        ResultList resultList = new ResultList(stores);
        recyclerView.setAdapter(resultList);
    }
}
