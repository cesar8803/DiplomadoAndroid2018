package mx.mobilestudio.promohunters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton imageButton;
    private FragmentManager fragmentManager;

    public static final int FRAGMENT_HOT_PROMO=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButton=findViewById(R.id.Bmas);
        imageButton.setOnClickListener(this);
        fragmentManager=getFragmentManager();

        //mandamos a llamr la fragmento fragment_hot_promo
        attachFragment(FRAGMENT_HOT_PROMO);
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
            Fragment fragmentHotPromo= new HotPromoFragment();
            fragmentTransaction.replace(R.id.main_home_container,fragmentHotPromo);
            fragmentTransaction.commit();
            break;
        }
    }
}
