package mx.mobilestudio.placefinder;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.support.v7.widget.RecyclerView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.List;

import mx.mobilestudio.placefinder.adapter.ListResultsAdapter;
import mx.mobilestudio.placefinder.fragment.ListResultsFragment;
import mx.mobilestudio.placefinder.fragment.MapsResultsFragment;
import mx.mobilestudio.placefinder.model.ApiFourSquareResponse;
import mx.mobilestudio.placefinder.model.Venue;


public class MainActivity extends AppCompatActivity implements View.OnClickListener , Response.Listener ,Response.ErrorListener {

    private Button miBoton;
    private EditText editText;
    private Toolbar myToolbar;
    private ImageButton map_button;
    private List<Venue> venues;

    private FragmentManager fragmentManager; // Clase que me permite agregar fragmentos a mi Activity


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miBoton = (Button) findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        map_button = findViewById(R.id.mybuttonmap);


         myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //TODO Explicar enlace de ToolBar y Activity



        miBoton.setOnClickListener(this);
        map_button.setOnClickListener(this);

        //Inicializamos fragment manager
        fragmentManager = getFragmentManager();
    }


    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.button){
            String query  = editText.getText().toString();


            if(query.isEmpty()==false){
                callFourSquareApi(query);
            }else{
                Toast.makeText(this,"Debes introducir un valor!!!",Toast.LENGTH_LONG).show();

            }
        }else if(view.getId() == R.id.mybuttonmap){
            if(venues !=null && venues.size()>0){
                attachMapFragment(venues);
                //TODO implementar mandar a llamar el fragmento de Mapa

            }else {
                Toast.makeText(this, "Debes hacer una busqueda!!",Toast.LENGTH_LONG ).show();
            }


        }


    }


    //Metodo para ejecutar el request HTTP mediante Volley
    public void callFourSquareApi(String  query) {

        String location =  "19.433997"+","+"-99.146006"; // HARDCODE

        RequestQueue queue  = Volley.newRequestQueue(this);

        String URL = Uri.parse("https://api.foursquare.com/v2/venues/search").buildUpon()
                        .appendQueryParameter("client_id","HOSIY11XMXHWFADXIPQTF5HRZA3YIWIFGRAOA5NIGXOY3CWI")
                        .appendQueryParameter("client_secret","OGATJNY0E0JY15PRXYD5MQ2WW3EMFLRAWFHLAOQYSTMVKMHM")
                         .appendQueryParameter("v", "20130815")
                        .appendQueryParameter("ll", location)
                        .appendQueryParameter("query",query).build().toString();


        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL, this,this);

        queue.add(stringRequest);
    }


    @Override
    public void onResponse(Object response) {


       // Toast.makeText(this,(String) response, Toast.LENGTH_LONG ).show();

        Gson gson = new Gson();

        ApiFourSquareResponse apiFourSquareResponse = gson.fromJson((String) response, ApiFourSquareResponse.class);

        venues = apiFourSquareResponse.getResponse().getVenues();
        //Aqui validamos que tengamos resultados!!

        if(venues.size()>0){

            attachFragment(venues);
        }else{
            Toast.makeText(this,"No se encontraron resultados!" ,Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }


    // Generamos un metodo para agregar nuestros Fragmentos
    public void attachFragment(List<Venue> venues){

        FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();

        Fragment listresultsfragment =  new ListResultsFragment();

        ((ListResultsFragment) listresultsfragment).setVenues(venues);


        fragmentTransaction.replace(R.id.main_content_container, listresultsfragment);

        fragmentTransaction.commit();

    }

    // Generamos un metodo para agregar nuestros Fragmentos
    public void attachMapFragment(List<Venue> venues){

        FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();

        Fragment mapsResultsfragment =  new MapsResultsFragment();

        ((MapsResultsFragment) mapsResultsfragment).setVenues(venues);


        fragmentTransaction.replace(R.id.main_content_container, mapsResultsfragment);

        fragmentTransaction.commit();

    }

}