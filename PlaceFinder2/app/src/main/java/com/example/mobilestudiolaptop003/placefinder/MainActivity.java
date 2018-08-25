package com.example.mobilestudiolaptop003.placefinder;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobilestudiolaptop003.placefinder.model.ApiFourSquareResponse;
import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener, Response.ErrorListener {

    private Button miBoton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miBoton = (Button) findViewById(R.id.button);

        miBoton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {


       callFourSquareApi("gasolinera");

    }

    // Metodo para ejecutar el request HTTP mediante Volley
    public void callFourSquareApi(String query) {
        String location = "19.395209" + "," + "-99.1544203"; // HARDCODE
        RequestQueue queue = Volley.newRequestQueue(this);

        // Uri.parse: Clase JAVA para interpretar URL con signos y caract. especiales

        String URL = Uri.parse("https://api.foursquare.com/v2/venues/search").buildUpon()
                .appendQueryParameter("client_id", "HOSIY11XMXHWFADXIPQTF5HRZA3YIWIFGRAOA5NIGXOY3CWI")
                .appendQueryParameter("client_secret", "OGATJNY0E0JY15PRXYD5MQ2WW3EMFLRAWFHLAOQYSTMVKMHM")
                .appendQueryParameter("v","20130815")
                .appendQueryParameter("ll", location)
                .appendQueryParameter("query", query).build().toString();

        // Contiene parámetros: METODO, URL, LISTENER en caso SUCCESS, LISTENER en caso ERROR
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, this, this);

        queue.add(stringRequest);

    }

    // View.OnClickListener: Método para recibir la respuesta exitosa
    @Override
    public void onResponse(Object response) {

        // Casteo del tipo del contenido del mensaje
        //Toast.makeText(this, (String) response, Toast.LENGTH_LONG ).show();

        // Usar GSON: Crear una instancia de Gson
        Gson gson = new Gson();

        ApiFourSquareResponse apiFourSquareResponse = gson.fromJson((String) response, ApiFourSquareResponse.class);

        Toast.makeText(this, apiFourSquareResponse.getResponse().getVenues().get(11).getName(), Toast.LENGTH_LONG).show();

    }

    // View.OnClickListener: Método para recibir la respuesta de error
    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
