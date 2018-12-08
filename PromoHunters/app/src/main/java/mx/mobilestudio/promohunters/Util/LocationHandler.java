package mx.mobilestudio.promohunters.Util;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static com.google.android.gms.location.LocationServices.FusedLocationApi;

public class LocationHandler implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    private GoogleApiClient apiClient;
    private AppCompatActivity activity;
    public static final int PETICION_PERMISO=101;

    public LocationHandler(AppCompatActivity activity){
        this.activity=activity;
        apiClient= new GoogleApiClient.Builder(this.activity).enableAutoManage(this.activity,this).addConnectionCallbacks(this)
                .addApi(LocationServices.API).build();
        //enableAutoManage La gestion de los servicios de gecalizacion se hace en automatico.
        //OnConnectionFailedListener se manda a llamar caundo existe un error
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //cONECTADOS CORRECTAMENTE A Google Play Services (LOCATION)
        getLatLonLocation();
    }



    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        //Se ha producido un error en el automanagment de la geolocalizacion
        //Y la conexion a LocationServices no se ha establesido
        Log.e("Error","Error grave al conectar con Google Play Services (LOCATION)");
    }

    public void getLatLonLocation(){
        if(ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            //Rquerimos permiso
            ActivityCompat.requestPermissions(activity, new String [] {Manifest.permission.ACCESS_FINE_LOCATION}, PETICION_PERMISO);
        }else{
            FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>(){
                @Override
                public void onSuccess(Location location){
                    if(location!= null){
                        String lat = String.valueOf(location.getLatitude());
                        String lon = String.valueOf(location.getLongitude());

                        Toast.makeText(activity, "Localizacion LAT"+lat+"Localizacion LON"+lon, Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

    }
}
