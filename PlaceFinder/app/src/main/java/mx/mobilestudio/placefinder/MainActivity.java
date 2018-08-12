package mx.mobilestudio.placefinder;

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
import com.google.gson.Gson;

import mx.mobilestudio.placefinder.model.ApiFourSquareResponse;


public class MainActivity extends AppCompatActivity implements View.OnClickListener , Response.Listener ,Response.ErrorListener {

    private Button miBoton;
    private Button miBoton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miBoton = (Button) findViewById(R.id.button);
        miBoton2 = (Button) findViewById(R.id.button2);

        miBoton.setOnClickListener(this);
        miBoton2.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        callFourSquareApi("gasolinera");

    }


    //Metodo para ejecutar el request HTTP mediante Volley
    public void callFourSquareApi(String  query) {

        String location =  "19.395209"+","+"-99.1544203"; // HARDCODE

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

        Toast.makeText(this, apiFourSquareResponse.getResponse().getVenues().get(2).getName(),Toast.LENGTH_LONG).show();
    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }
}