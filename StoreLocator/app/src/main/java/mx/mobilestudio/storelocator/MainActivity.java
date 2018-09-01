package mx.mobilestudio.storelocator;

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
import com.google.gson.Gson;

import java.util.List;

import mx.mobilestudio.storelocator.LiverpoolPackage.ApiLiverpoolResponse;
import mx.mobilestudio.storelocator.LiverpoolPackage.Store;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener, Response.ErrorListener
{
    private Button miBoton;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miBoton = findViewById(R.id.button);
        //recyclerView = (RecyclerView) findViewById(R.id.recycler_view_results);
        layoutManager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(layoutManager);

        miBoton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        callLiverAPI();

    }

    public void callLiverAPI(){

        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = Uri.parse("https://api.myjson.com/bins/r882c").toString();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL, this,this);
        queue.add(stringRequest);

    }
    @Override
    public void onResponse(Object response) {

        // Toast.makeText(this,(String) response, Toast.LENGTH_LONG ).show();
        Gson gson = new Gson();
        ApiLiverpoolResponse apiLiverpoolResponse = gson.fromJson((String) response,ApiLiverpoolResponse.class);
        //ApiFourSquareResponse apiFourSquareResponse = gson.fromJson((String) response, ApiFourSquareResponse.class);
        Toast.makeText(this, apiLiverpoolResponse.getStore().get(2).getTitulo(),Toast.LENGTH_LONG).show();
        //Toast.makeText(this, apiLiverpoolResponse.getStore().get(2).getHorario(),Toast.LENGTH_LONG).show();

        //Toast.makeText(this, apiFourSquareResponse.getResponse().getVenues().get(2).getName(),Toast.LENGTH_LONG).show();
        List<Store> store = apiLiverpoolResponse.getStore();
        //List<Venue> venues = apiFourSquareResponse.getResponse().getVenues();
        //ListResultsAdapter listResultsAdapter = new ListResultsAdapter(store);
        //recyclerView.setAdapter(listResultsAdapter);
    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
