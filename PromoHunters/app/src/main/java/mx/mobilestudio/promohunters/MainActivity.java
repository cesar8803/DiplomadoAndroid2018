package mx.mobilestudio.promohunters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppcompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Toast;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.List;

import mx.mobilestudio.promohunters.Util.LocationHandler;
import mx.mobilestudio.promohunters.model.Promo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private ImageButton imageButton;
    private FragmentManager fragmentManager;

    //hambuerger menu

    private DrawerLayout drawerLayout;
    //hamburger menu

    private FirebaseAuth firebaseAuth;
    private List<Promo> promos;
    public static final int FRAGMENT_HOT_PROMO=1;
    private DatabaseReference databaseReference;
    public LocationHandler locationHandler;
    private String token;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == LocationHandler.PETICION_PERMISO){
            if(grantResults.length==1 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                //pERMISO CONCEDIDO
                locationHandler.getLatLonLocation();
            }
        }else{
            //permiso denegado
            //Deberiamos ya no pedir permiso y a funcionalidad de Geolocalizacion quedaria deshabilitado
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButton=findViewById(R.id.Bmas);
        imageButton.setOnClickListener(this);
        fragmentManager=getFragmentManager();

        //mandamos a llamr la fragmento fragment_hot_promo
        attachFragment(FRAGMENT_HOT_PROMO);


        //inicializamos firebaseAuth
        firebaseAuth=FirebaseAuth.getInstance();
        configureToolBar();
        configureNavigationDrawer();



        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(MainActivity.this, new OnSuccessListener< InstanceIdResult >() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                token = FirebaseInstanceId.getInstance().getToken();
                Log.d("FCM_TOKEN", token);
                Toast.makeText(MainActivity.this, token, Toast.LENGTH_LONG).show();
            }
        });

        //LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationHandler=new LocationHandler(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //esto detecta solo los click que se le dan a toolbar
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
        configureNavigationDrawer();
        Toolbar toolbar = findViewById(R.id.mitoolbar);
        //Metodo que llamamos para configurar el toolbar(Habilitar Acciones Y otras funcionaldidades)
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_add);
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    private void configureNavigationDrawer(){
        drawerLayout=findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {

        if(firebaseAuth.getCurrentUser()==null){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }else{
            //el usuario esta loggeado
            //
            //intent es una peticion que se hace atravez de una clase
            Intent intent = new Intent(this, PromoFormActivity.class);
            startActivity(intent);
        }


    }



    public void attachFragment(int FRAGMENT_REQUIRED_ID){

        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        switch (FRAGMENT_REQUIRED_ID){
            case FRAGMENT_HOT_PROMO:
            Fragment hotPromoFragment= new HotPromoFragment();
            ((HotPromoFragment)hotPromoFragment).setPromos(promos);
            fragmentTransaction.replace(R.id.main_home_container,hotPromoFragment);
            fragmentTransaction.commit();
            break;
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if(itemId==R.id.hotpromo){
            attachFragment(FRAGMENT_HOT_PROMO);
        }else if(itemId==R.id.search){
            Toast.makeText(this,"en construccion", Toast.LENGTH_LONG).show();
        }
        drawerLayout.closeDrawers();
        return false;
    }
}
