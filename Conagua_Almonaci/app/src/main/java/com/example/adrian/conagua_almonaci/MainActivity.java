package com.example.adrian.conagua_almonaci;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.adrian.conagua_almonaci.Fragmentos.PronosticoClima;
import com.example.adrian.conagua_almonaci.Parseo.Conagua;
import com.example.adrian.conagua_almonaci.Parseo.ListaRespaldo;
import com.example.adrian.conagua_almonaci.Parseo.NombresListaSpinner;
import com.example.adrian.conagua_almonaci.Parseo.Result;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener, Response.ErrorListener {

    private Button button;
    public String info1;
    public String info2;
    public static boolean condicionallist;
    private FragmentManager fragmentManager;
    public static String name77;
    public String name55;
    public Spinner spinner1;
    public Spinner spinner2;
    public List<Result> results;
    public static boolean name99 = false;
    public boolean name100;
    public List<NombresListaSpinner> Listamunicipiosmispinner;
    public static final List<ListaRespaldo> ListaRespal = null;
    public static final List<ListaRespaldo> ListaRespaldo = null;
    public static int controlitem=0;
    public static String edoapi="Aguascalientes";
    public String breakfor;
    public boolean name9;
    public static boolean controlspinner2;
    public static boolean Contrrolprocesospinner2;
    public static String edoclima;
    public static String ListaRes1;
    public static String ListaRes2;

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
        info1="A";
        info2="B";
        controlspinner2=true;

        httpconagua(edoapi);
    }

    @Override
    protected void onStart() {
        super.onStart();
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                controlitem+=1;
                if (controlitem>2) {
                    edoapi = spinner1.getSelectedItem().toString();
                    controlspinner2=true;
                    Contrrolprocesospinner2=false;
                    httpconagua(edoapi);
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
                if(controlitem>2&&Contrrolprocesospinner2==true) {
                    name100 = true;
                    info1 = spinner1.getSelectedItem().toString();
                    info2 = spinner2.getSelectedItem().toString();
                    controlspinner2=false;
                    httpconagua(edoapi);

                }
                Contrrolprocesospinner2=true;

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }


    @Override
    public void onClick(View view) {


    }

    public void httpconagua(String edoapi) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String conaguaURL = Uri.parse("https://api.datos.gob.mx/v1/condiciones-atmosfericas").buildUpon()
                .appendQueryParameter("state",edoapi).build().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, conaguaURL, this, this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String hola = "hola";
    }

    @Override
    public void onResponse(Object response) {
        Gson gson = new Gson();
        Conagua conagua = gson.fromJson((String) response, Conagua.class);
        List<Result> results = conagua.getResults();

        name9=false;
            if (name99==false){
                Spinner spinner1 = findViewById(R.id.idspinner1);
                ArrayAdapter<CharSequence> adapters1 = ArrayAdapter.createFromResource(this,R.array.Estados, android.R.layout.simple_spinner_item);
                adapters1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapters1);
                List<NombresListaSpinner> Listamunicipiosmispinner = new ArrayList<>();
                for (Result currentResult : results) {
                    if (name99 == false) {
                        name99 = true;
                        breakfor= "iniciodemivariable";
                    }

                    name77 = currentResult.getName();
                    if(name77.equals(breakfor)){
                        break;
                    }
                    if (breakfor=="iniciodemivariable"){
                        breakfor=currentResult.getName();
                    }

                    NombresListaSpinner nombresListaSpinner = new NombresListaSpinner(name77);
                    Listamunicipiosmispinner.add(nombresListaSpinner);
                }
                ArrayAdapter<NombresListaSpinner> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, Listamunicipiosmispinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(adapter);
            } else{
                if (controlspinner2==true) {
                    List<NombresListaSpinner> Listamunicipiosmispinner = new ArrayList<>();

                    List<ListaRespaldo> ListaRespal = new ArrayList<>();
                    //Spinner spinner2 = findViewById(R.id.spinner2);
                    for (Result currentResult : results) {
                        if (name9 == false) {
                            name9 = true;
                            breakfor = "iniciodemivariable";
                        }
                        name77 = currentResult.getName();
                        if (name77.equals(breakfor)) {
                            break;
                        }
                        if (breakfor == "iniciodemivariable") {
                            breakfor = currentResult.getName();
                        }
                        name77 = currentResult.getName();
                        ListaRes1=currentResult.getState();
                        ListaRes2=currentResult.getSkydescriptionlong();
                        NombresListaSpinner nombresListaSpinner = new NombresListaSpinner(name77);
                        Listamunicipiosmispinner.add(nombresListaSpinner);

                        ListaRespaldo listaRespaldo = new ListaRespaldo(name77,ListaRes1,ListaRes2);
                        ListaRespal.add(listaRespaldo);

                    }


                    ArrayAdapter<NombresListaSpinner> adapter = new ArrayAdapter<>(this,
                            android.R.layout.simple_spinner_item, Listamunicipiosmispinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter);
                }
            }

         if (controlspinner2==false) {
                controlspinner2=true;
             for (Result currentResult : results) {
                 name55 = currentResult.getState();
                 name77 = currentResult.getName();
                 if (info1.equals(name55) && info2.equals(name77)) {
                     edoclima = currentResult.getSkydescriptionlong();
                     Toast.makeText(this, edoclima, Toast.LENGTH_LONG).show();
                     fragemntotexto();
                     break;
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







