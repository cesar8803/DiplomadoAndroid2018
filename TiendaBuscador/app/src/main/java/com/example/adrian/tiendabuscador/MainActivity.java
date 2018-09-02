package com.example.adrian.tiendabuscador;

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
import com.example.adrian.tiendabuscador.Adap.ListaTiendas;
import com.example.adrian.tiendabuscador.Mdl.InicioLista;
import com.example.adrian.tiendabuscador.Mdl.Store;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener, Response.ErrorListener{
    private Button bot1;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        bot1=findViewById(R.id.j1);
        bot1.setOnClickListener(this);

        recyclerView=(RecyclerView) findViewById(R.id.recresul);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View view) {
        callLiverAPI();
    }
        public void callLiverAPI(){
            RequestQueue queue = Volley.newRequestQueue(this);
            String URL = Uri.parse("https://api.myjson.com/bins/r882c").toString();
            StringRequest stringRequest=new StringRequest(Request.Method.GET,URL,this,this);
            queue.add(stringRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }



    @Override
    public void onResponse(Object response) {
        Gson gson = new Gson();
        InicioLista inicioLista=gson.fromJson((String)response,InicioLista.class);
        List<Store> stores = inicioLista.getStores();
        ListaTiendas listaTiendas = new ListaTiendas(stores);
        recyclerView.setAdapter(listaTiendas);
    }
}
