package com.example.mobilestudiolaptop008.pushnotificatios.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FcmServices extends FirebaseInstanceIdService {
    public FcmServices() {
    }


    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String refreshtoken = FirebaseInstanceId.getInstance().getToken();
sendRegisterTokenToServer(refreshtoken);
    }


    private void sendRegisterTokenToServer(String refresh)
    {

        Log.d("****",refresh);

    }

}
