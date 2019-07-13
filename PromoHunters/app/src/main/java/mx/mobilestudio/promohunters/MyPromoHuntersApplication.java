package mx.mobilestudio.promohunters;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyPromoHuntersApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }

    private void setUpRealmConfig(){
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
               .name("promo.realm").build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

}
