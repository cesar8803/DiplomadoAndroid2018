package mx.mobilestudio.promohunters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.List;

import mx.mobilestudio.promohunters.model.Promo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private ImageButton imageButton;
    private FragmentManager fragmentManager;

    //hambuerger menu

    private DrawerLayout drawerLayout;
    //hamburger menu


    private List<Promo> promos;
    public static final int FRAGMENT_HOT_PROMO=1;
    private DatabaseReference databaseReference;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButton=findViewById(R.id.Bmas);
        imageButton.setOnClickListener(this);
        fragmentManager=getFragmentManager();

        //mandamos a llamr la fragmento fragment_hot_promo
        attachFragment(FRAGMENT_HOT_PROMO);

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
        //intent es una peticion que se hace atravez de una clase
            Intent intent = new Intent(this, PromoFormActivity.class);
            startActivity(intent);

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
