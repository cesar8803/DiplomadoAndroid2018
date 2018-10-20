package mx.mobilestudio.promohunters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import mx.mobilestudio.promohunters.fragment.InitialSelectionFragment;

public class PromoFormActivity extends AppCompatActivity {


    private RelativeLayout relativeLayout;
    private FragmentManager fragmentManager;

    public static final int FRAGMENT_INTIAL_DESITION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo_form);
        relativeLayout = findViewById(R.id.main_fragment_container);
        fragmentManager = getFragmentManager();
        attachFragment(FRAGMENT_INTIAL_DESITION);
    }

    public void attachFragment(int FRAGMENT_REQUIRED_ID){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (FRAGMENT_REQUIRED_ID){
            case FRAGMENT_INTIAL_DESITION:

                Fragment initialSelectionFragment = new InitialSelectionFragment();

                fragmentTransaction.replace(R.id.main_fragment_container, initialSelectionFragment);

                fragmentTransaction.commit();
                //TODO terminar el agregar el fragmento
                break;


        }

    }
}
