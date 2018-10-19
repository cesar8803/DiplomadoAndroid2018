package com.example.adrian.pf;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
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
import com.example.adrian.pf.Fragment.ListResultsFragment;
import com.example.adrian.pf.Fragment.MapsResultsFragment;
import com.example.adrian.pf.Parseo.ApiFourSquareResponse;
import com.example.adrian.pf.Parseo.Venue;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener,Response.ErrorListener {
    private Button boton1;
    private ImageButton imageButton1;
    private EditText editText;
    private FragmentManager fragmentManager;
    private List<Venue> venues;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton1=findViewById(R.id.B1);
        boton1.setOnClickListener(this);

        editText=findViewById(R.id.editText);

        imageButton1=findViewById(R.id.mybuttonmap);
        imageButton1.setOnClickListener(this);

        fragmentManager=getFragmentManager();

    }

    @Override
    public void onClick(View view) {
        String query=editText.getText().toString();
        if (view.getId()==R.id.B1){
            if (query.isEmpty()==true){
                Toast.makeText(this,"Debes ingresar alguna palabra.",Toast.LENGTH_LONG).show();
            }else{
                MirequestHTTP(query); }
        }else if(view.getId()==R.id.mybuttonmap){
            fragmentomapas(venues);
        }

    }

    public void MirequestHTTP(String query){
        String cordenadas = "19.433997"+","+"-99.146006";
        RequestQueue queue = Volley.newRequestQueue(this);
        String miURL = Uri.parse("https://api.foursquare.com/v2/venues/search").buildUpon()
                .appendQueryParameter("client_id","HOSIY11XMXHWFADXIPQTF5HRZA3YIWIFGRAOA5NIGXOY3CWI")
                .appendQueryParameter("client_secret","OGATJNY0E0JY15PRXYD5MQ2WW3EMFLRAWFHLAOQYSTMVKMHM")
                .appendQueryParameter("v","20130815")
                .appendQueryParameter("ll",cordenadas)
                .appendQueryParameter("query", query).build().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, miURL, this, this);
        queue.add(stringRequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {


    }

    @Override
    public void onResponse(Object response) {
        Gson gson = new Gson();
        ApiFourSquareResponse apiFourSquareResponse = gson.fromJson((String) response,
                ApiFourSquareResponse.class);
                venues = apiFourSquareResponse.getResponse().getVenues();

        fragmentolista();
    }

    public void fragmentolista() {
        //FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        Fragment listresultsfragment=new ListResultsFragment();
        ((ListResultsFragment)listresultsfragment).setVenues(venues);
        fragmentTransaction.replace(R.id.main_content_container,listresultsfragment);
        fragmentTransaction.commit();
    }

    public void fragmentomapas(List<Venue> venues){
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        Fragment mapsResultsFragment=new MapsResultsFragment();
        ((MapsResultsFragment)mapsResultsFragment).setVenues(venues);
        fragmentTransaction.replace(R.id.main_content_container,mapsResultsFragment);
        fragmentTransaction.commit();
    }
}
