package mx.mobilestudio.promohunters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import mx.mobilestudio.promohunters.fragment.HotPromoFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton imageButtonAddNewPromo;
    public static final int FRAGMENT_HOT_PROMO=1;
    private FragmentManager fragmentManager;

    private String token;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButtonAddNewPromo = findViewById(R.id.button_add_new);
        imageButtonAddNewPromo.setOnClickListener(this);
        fragmentManager=getFragmentManager();

        attachFragment(FRAGMENT_HOT_PROMO);


        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(MainActivity.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                token = FirebaseInstanceId.getInstance().getToken();
                Log.d("FCM_TOKEN", token);
                Toast.makeText(MainActivity.this, token, Toast.LENGTH_LONG).show();
            }
        });



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
