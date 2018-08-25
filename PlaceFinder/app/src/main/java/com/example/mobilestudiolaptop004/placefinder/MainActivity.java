package com.example.mobilestudiolaptop004.placefinder;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.Adapter.ListResultsAdapter;
import com.example.mobilestudiolaptop004.placefinder.model.ApiFourSquareResponse;
import com.example.mobilestudiolaptop004.placefinder.model.Venue;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener, Response.ErrorListener {
//Volley --> libreria para trabajar http, esta es de google

    private Button boton1;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton1= findViewById(R.id.B1);
        boton1.setOnClickListener(this);

        recyclerView=(RecyclerView) findViewById(R.id.recycler_view_results);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void onClick(View view) {
        callFourSquareApi("gasolinera");
    }

    //Metodo para ejecutar el request HTTP mediante Volley
    public void callFourSquareApi(String query){
        String location = "19.433997"+","+"-99.146006"; //HARDCORE
        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = Uri.parse("https://api.foursquare.com/v2/venues/search").buildUpon()
                .appendQueryParameter("client_id","HOSIY11XMXHWFADXIPQTF5HRZA3YIWIFGRAOA5NIGXOY3CWI")
                .appendQueryParameter("client_secret","OGATJNY0E0JY15PRXYD5MQ2WW3EMFLRAWFHLAOQYSTMVKMHM")
                .appendQueryParameter("v","20130815")
                .appendQueryParameter("ll",location)
                .appendQueryParameter("query",query).build().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL, this,this);
        queue.add(stringRequest);

    }

    //Este metodo es obligatario
    @Override
    public void onResponse(Object response) {
        //Toast.makeText(this,(String)response, Toast.LENGTH_LONG).show();

        Gson gson = new Gson();
        ApiFourSquareResponse apiFourSquareResponse=gson.fromJson((String) response, ApiFourSquareResponse.class);
        Toast.makeText(this, apiFourSquareResponse.getResponse().getVenues().get(0).getId(),Toast.LENGTH_LONG).show();
        //Toast.makeText(this, apiFourSquareResponse.getResponse().getCategories().get(1).getIdc(),Toast.LENGTH_LONG).show();
        List<Venue> venues = apiFourSquareResponse.getResponse().getVenues();
        ListResultsAdapter listResultsAdapter = new ListResultsAdapter(venues);
        recyclerView.setAdapter(listResultsAdapter);
    }
    //Este metodo es obligatorio
    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
