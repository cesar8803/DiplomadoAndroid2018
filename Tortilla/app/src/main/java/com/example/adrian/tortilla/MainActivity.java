package com.example.adrian.tortilla;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import com.example.adrian.tortilla.Fragments.ListFragment;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adrian.tortilla.Fragments.MapFragmentTortilla;
import com.example.adrian.tortilla.Parseo.Result;
import com.example.adrian.tortilla.Parseo.Tortilla;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Response.ErrorListener, Response.Listener {

    private Button buttonLista;
    private Button buttonMaps;
    private static FragmentManager fragmentManager;
    private static List<Result> results;
    public static Double latitudTortilla;
    public static Double longitudTortilla;
    public static String nombresote;
    public static boolean TodasTortillas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonLista=findViewById(R.id.Blista);
        buttonLista.setOnClickListener(this);
        buttonMaps=findViewById(R.id.Bmapa);
        buttonMaps.setOnClickListener(this);
        fragmentManager=getFragmentManager();
        TodasTortillas=false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.Blista){
            TodasTortillas=false;
            MiResquestHTTP();

        }else if (v.getId()==R.id.Bmapa){
            TodasTortillas=true;
            MiResquestHTTP();

        }
    }

    public void MiResquestHTTP() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String TortillaURL=Uri.parse("https://api.datos.gob.mx/v1/profeco.precios").toString();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,TortillaURL,this,this);
        queue.add(stringRequest);
    }

    @Override
    public void onResponse(Object response) {
        Gson gson = new Gson();
        Tortilla tortilla=gson.fromJson((String)response,Tortilla.class);
        results=tortilla.getResults();
        if (TodasTortillas==false) {
            FragmentoLista();
        }else if (TodasTortillas==true){
            FragmentoMapa();
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }



    public void FragmentoLista() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment listFragment = new ListFragment();
        ((ListFragment)listFragment).setResults(results);
        fragmentTransaction.replace(R.id.principalReLay,listFragment);
        fragmentTransaction.commit();
    }


        public static void FragmentoMapa () {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment mapFragmentTortilla = new MapFragmentTortilla();
            ((MapFragmentTortilla)mapFragmentTortilla).setResults(results);
            fragmentTransaction.replace(R.id.principalReLay, mapFragmentTortilla);
            fragmentTransaction.commit();
        }





}
