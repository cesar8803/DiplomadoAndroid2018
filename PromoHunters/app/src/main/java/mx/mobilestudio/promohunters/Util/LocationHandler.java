package mx.mobilestudio.promohunters.Util;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnSuccessListener;

import static com.google.android.gms.location.LocationServices.FusedLocationApi;
import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class LocationHandler implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    private GoogleApiClient apiClient;
    private AppCompatActivity activity;
    public static final int PETICION_PERMISO=101;
    private LocationRequest locationRequest; //Nos permite tener actualizacones recurrentes // numero de updates
    //

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
        enableLocationUpdates();
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
            FusedLocationProviderClient fusedLocationProviderClient = getFusedLocationProviderClient(activity);
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>(){
                @Override
                public void onSuccess(Location location){
                        if (location != null) {
                            String lat = String.valueOf(location.getLatitude());
                            String lon = String.valueOf(location.getLongitude());

                            Toast.makeText(activity, "Localizacion LAT" + lat + "Localizacion LON" + lon, Toast.LENGTH_LONG).show();
                        }
                }
            });
        }
    }

    private void enableLocationUpdates(){
        locationRequest=new LocationRequest();
        locationRequest.setInterval(2000);
        locationRequest.setFastestInterval(1000);//es el valor maxio de actualizaciones que vamos a recibir
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationSettingsRequest locationSettingsRequest =
                new LocationSettingsRequest.Builder()
                        .addLocationRequest(locationRequest)
                        .build();
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(
                        apiClient, locationSettingsRequest);
        SettingsClient settingsClient=LocationServices.getSettingsClient(activity);
        settingsClient.checkLocationSettings(locationSettingsRequest);

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        getFusedLocationProviderClient(activity).requestLocationUpdates(locationRequest,new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                Location location = locationResult.getLastLocation();

                if(location != null){
                    String lat = String.valueOf(location.getLatitude());
                    String lon = String.valueOf(location.getLongitude());
                    //String latTrim = lat.substring(0,5);
                    Toast.makeText(activity,"Localizacion LAT"+ lat + "Localizacion LON"+ lon, Toast.LENGTH_LONG).show();
                }
            }
        }, Looper.myLooper());

          }
}
