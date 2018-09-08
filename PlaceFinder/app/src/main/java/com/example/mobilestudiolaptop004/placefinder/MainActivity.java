package com.example.mobilestudiolaptop004.placefinder;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.Adapter.ListResultsAdapter;
import com.example.Fragment.ListResultFragment;
import com.example.mobilestudiolaptop004.placefinder.model.ApiFourSquareResponse;
import com.example.mobilestudiolaptop004.placefinder.model.Venue;
import com.google.gson.Gson;

import java.util.List;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener, Response.ErrorListener {
//Volley --> libreria para trabajar http, esta es de google

    private Button boton1;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private EditText editText;

    private FragmentManager fragmentManager; //Clase que me permite agregar fragmentos a mi activity
    private Toolbar myToolbar;

    private ImageButton imageButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton1= findViewById(R.id.B1);
        boton1.setOnClickListener(this);

        editText=findViewById(R.id.editText);
        //editText.setOnClickListener(this); en este no es necesario ya que no estoy buscano alguna accion

        imageButton1=findViewById(R.id.mybuttonmap);
        imageButton1.setOnClickListener(this);

        recyclerView=(RecyclerView) findViewById(R.id.recycler_view_results);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //inicializamos fragment manager
        fragmentManager=getFragmentManager();

        myToolbar=(Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //TODO Explicar enlace de toolbar y activity



    }

    @Override
    public void onClick(View view) {
        String query = editText.getText().toString();
        Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
        //callFourSquareApi("gasolinera");  esta linea es de practica pasada
        if (query.isEmpty()==false){
            callFourSquareApi(query);
            attachFragment();
        }else{
            Toast.makeText(this,"Ingresa algo dude!!!!", Toast.LENGTH_LONG).show();
        }

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
        //Toast.makeText(this, apiFourSquareResponse.getResponse().getVenues().get(0).getId(),Toast.LENGTH_LONG).show();
        //Toast.makeText(this, apiFourSquareResponse.getResponse().getCategories().get(1).getIdc(),Toast.LENGTH_LONG).show();
        List<Venue> venues = apiFourSquareResponse.getResponse().getVenues();
            if(venues.size()>0){
                ListResultsAdapter listResultsAdapter = new ListResultsAdapter(venues);
                recyclerView.setAdapter(listResultsAdapter);
            }else{
                Toast.makeText(this,"No Results bro..",Toast.LENGTH_LONG).show();
            }


    }
    //Este metodo es obligatorio
    @Override
    public void onErrorResponse(VolleyError error) {

    }

    //genramos un metodo para agregar nuestros fragmentos
    public void attachFragment(){
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        Fragment listresultsfragment = new ListResultFragment();
        fragmentTransaction.replace(R.id.main_content_container,listresultsfragment);
        fragmentTransaction.commit();
    }
}
