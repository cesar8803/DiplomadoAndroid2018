package com.example.adrian.conagua_almonaci;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.adrian.conagua_almonaci.Fragmentos.PronosticoClima;
import com.example.adrian.conagua_almonaci.Parseo.Conagua;
import com.example.adrian.conagua_almonaci.Parseo.EstadoListaSpinner;
import com.example.adrian.conagua_almonaci.Parseo.NombresListaSpinner;
import com.example.adrian.conagua_almonaci.Parseo.Result;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener, Response.ErrorListener {

    private Button button;
    public String info1;
    public String info2;
    public static boolean condicionallist;
    private FragmentManager fragmentManager;
    public static String name55;
    public static String name77;
    public Spinner spinner1;
    public Spinner spinner2;
    public List<Result> results;
    public boolean name99 = false;
    public static boolean name100 = false;
    public List<NombresListaSpinner> Listamunicipiosmispinner;
    public static int controlitem=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.b1);
        button.setOnClickListener(this);
        condicionallist = false;
        fragmentManager = getFragmentManager();
        spinner1 = findViewById(R.id.idspinner1);
        spinner2 = findViewById(R.id.idspinner2);
        httpconagua();
    }

    @Override
    protected void onStart() {
        super.onStart();

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                controlitem+=1;
                if (controlitem>2) {
                    httpconagua();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                controlitem+=1;
                if(controlitem>2) {
                    name100 = true;
                    info1 = spinner1.getSelectedItem().toString();
                    info2 = spinner2.getSelectedItem().toString();
                    httpconagua();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }

    @Override
    public void onClick(View view) {
        fragemntotexto();
    }

    public void httpconagua() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String conaguaURL = Uri.parse("https://api.datos.gob.mx/v1/condiciones-atmosfericas").toString();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, conaguaURL, this, this);
        queue.add(stringRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object response) {
        Gson gson = new Gson();
        Conagua conagua = gson.fromJson((String) response, Conagua.class);
        List<Result> results = conagua.getResults();
        List<EstadoListaSpinner> Listamispinner = new ArrayList<>();
        if (name100==false) {
            if (name99 == false) {
                //Spinner spinner = findViewById(R.id.spinner);
                String name33 = "Estado";
                for (Result currentResult : results) {
                    String name22 = currentResult.getState();

                    if (!name22.equals(name33)) {
                        EstadoListaSpinner estadoListaSpinner = new EstadoListaSpinner(name22);
                        Listamispinner.add(estadoListaSpinner);
                        name33 = name22;
                    }
                    ArrayAdapter<EstadoListaSpinner> adapter = new ArrayAdapter<EstadoListaSpinner>(this,
                            android.R.layout.simple_spinner_item, Listamispinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner1.setAdapter(adapter);
                }

                List<NombresListaSpinner> Listamunicipiosmispinner = new ArrayList<>();
                //Spinner spinner2 = findViewById(R.id.spinner2);

                for (Result currentResult : results) {
                    if (name99 == false) {
                        name99 = true;
                        name55 = currentResult.getState();
                    }
                    String name66 = currentResult.getState();
                    name77 = currentResult.getName();
                    if (name55.equals(name66)) {
                        NombresListaSpinner nombresListaSpinner = new NombresListaSpinner(name77);
                        Listamunicipiosmispinner.add(nombresListaSpinner);
                        ArrayAdapter<NombresListaSpinner> adapter = new ArrayAdapter<NombresListaSpinner>(this,
                                android.R.layout.simple_spinner_item, Listamunicipiosmispinner);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(adapter);
                    }
                }
            } else {
                List<NombresListaSpinner> Listamunicipiosmispinner = new ArrayList<>();
                //Spinner spinner2 = findViewById(R.id.spinner2);
                name55 = spinner1.getSelectedItem().toString();
                for (Result currentResult : results) {
                    String name66 = currentResult.getState();
                    name77 = currentResult.getName();
                    if (name55.equals(name66)) {
                        NombresListaSpinner nombresListaSpinner = new NombresListaSpinner(name77);
                        Listamunicipiosmispinner.add(nombresListaSpinner);
                    }
                }
                ArrayAdapter<NombresListaSpinner> adapter = new ArrayAdapter<NombresListaSpinner>(this,
                        android.R.layout.simple_spinner_item, Listamunicipiosmispinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(adapter);
            }
        }else{
            name100=false;
            for (Result currentResult : results){
                name55=currentResult.getState();
                name77=currentResult.getName();
                if(info1.equals(name55)&&info2.equals(name77)){
                    String edoclima=currentResult.getSkydescriptionlong();
                    Toast.makeText(this,edoclima,Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void fragemntotexto() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment pronosticosClima = new PronosticoClima();
        fragmentTransaction.replace(R.id.principalLayout, pronosticosClima);
        fragmentTransaction.commit();
    }



}







