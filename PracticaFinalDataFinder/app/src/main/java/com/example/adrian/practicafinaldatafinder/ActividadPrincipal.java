package com.example.adrian.practicafinaldatafinder;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.example.adrian.practicafinaldatafinder.Adptr.Adap;
import com.example.adrian.practicafinaldatafinder.Fragment.ListResultFragment;
import com.example.adrian.practicafinaldatafinder.Mo.Inicio_d_Mo;
import com.example.adrian.practicafinaldatafinder.Mo.Venue;
import com.google.gson.Gson;

import java.util.List;

import static java.lang.String.valueOf;

public class ActividadPrincipal extends AppCompatActivity implements View.OnClickListener, Response.Listener, Response.ErrorListener{
    private Button utton1;
    private Button utton2;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FragmentManager fragmentManager;
    private List<Venue> venues;
    //necesito declarar asi mi variable para poder usarla en la clase Adap
    public static int buuton=0;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_layout);
            utton1=findViewById(R.id.utt1);
            utton1.setOnClickListener(this);
            utton2=findViewById(R.id.utt2);
            utton2.setOnClickListener(this);

        recyclerView=(RecyclerView) findViewById(R.id.rc);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        fragmentManager=getFragmentManager();
    }

    public static int getBuuton()
    {
        return buuton;
    }

    @Override
    public void onClick(View view) {
        //Toast.makeText(this,"Boiler",Toast.LENGTH_LONG).show();
        callPapi("sushi");
        switch (view.getId()){
            case R.id.utt1:
                buuton=1;
                Toast.makeText(this,valueOf(buuton),Toast.LENGTH_LONG).show();
                break;
            case R.id.utt2:
                buuton=2;
                Toast.makeText(this,valueOf(buuton),Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void callPapi(String query){
        String locat="19.433997"+","+"-99.146006";
        RequestQueue queue = Volley.newRequestQueue(this);
        String URL= Uri.parse("https://api.foursquare.com/v2/venues/search").buildUpon()
        .appendQueryParameter("client_id","HOSIY11XMXHWFADXIPQTF5HRZA3YIWIFGRAOA5NIGXOY3CWI")
        .appendQueryParameter("client_secret","OGATJNY0E0JY15PRXYD5MQ2WW3EMFLRAWFHLAOQYSTMVKMHM")
        .appendQueryParameter("v","20130815")
        .appendQueryParameter("ll",locat)
        .appendQueryParameter("query",query).build().toString();
    StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,this,this);
    queue.add(stringRequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object response) {
    Gson gson = new Gson();
        Inicio_d_Mo inicio_d_mo=gson.fromJson((String)response, Inicio_d_Mo.class);
        List<Venue> venues = inicio_d_mo.getResponse().getVenues();
        Adap adap = new Adap(venues);
        recyclerView.setAdapter(adap);

    }

    public void attachFragmentList(List<Venue>venues){
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        Fragment listresultsfragment = new ListResultFragment();
        ((ListResultFragment)listresultsfragment).setVenues(venues);
        fragmentTransaction.replace(R.id.main_content_container,listresultsfragment);
        fragmentTransaction.commit();
    }

    public void attachFragmentMaps(List<Venue>venues){

    }
}
