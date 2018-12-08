package mx.mobilestudio.promohunters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import mx.mobilestudio.promohunters.fragment.HotPromoFragment;
import mx.mobilestudio.promohunters.util.LocationHandler;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton imageButtonAddNewPromo;
    public static final int FRAGMENT_HOT_PROMO=1;
    private FragmentManager fragmentManager;
    private DrawerLayout drawerLayout;
    public  LocationHandler locationHandler;


    private String token;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButtonAddNewPromo = findViewById(R.id.button_add_new);
        imageButtonAddNewPromo.setOnClickListener(this);
        fragmentManager=getFragmentManager();

        attachFragment(FRAGMENT_HOT_PROMO);


        configureToolBar();
        configureNavigationDrawer();

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(MainActivity.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                token = FirebaseInstanceId.getInstance().getToken();
                Log.d("FCM_TOKEN", token);
                Toast.makeText(MainActivity.this, token, Toast.LENGTH_LONG).show();
            }
        });


       // LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

         locationHandler = new LocationHandler(this);




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == LocationHandler.PETICION_PERMISO_GEOLOCATE){

            if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                // Permiso concedido
                locationHandler.getLatLonLocation();

            }

        }else{
            //permiso denegado:
            // Deberiamos ya no pedir permiso y la funcionalidad de Geolocalización quedaria deshabilitada

        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Detecta los clicks que se dan en el ToolBar

        int itemId = item.getItemId();

        switch (itemId){

            //Android Home

            case android.R.id.home:

                drawerLayout.openDrawer(GravityCompat.START);

            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    private void configureToolBar(){

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        //Metodo que llamamos para poder configurar el ToolBar (Habilitar Acciones y Otras Funcionalidades)
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(android.R.drawable.ic_menu_add);
        actionbar.setDisplayHomeAsUpEnabled(true);

    }


    private void configureNavigationDrawer(){
        drawerLayout = findViewById(R.id.drawer_layout);
    }


    @Override
    public void onClick(View view) {
        //intent es una peticion que se hace atravez de una clase
        Intent intent = new Intent(this,PromoFormActivity.class);
        startActivity(intent);

    }


    public void attachFragment(int FRAGMENT_REQUIRED_ID){

        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        switch (FRAGMENT_REQUIRED_ID){
            case FRAGMENT_HOT_PROMO:
                Fragment hotPromoFragment= new HotPromoFragment();
                fragmentTransaction.replace(R.id.main_home_container,hotPromoFragment).addToBackStack(null);
                fragmentTransaction.commit();

                break;
        }
    }

}
