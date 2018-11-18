package mx.mobilestudio.promohunters;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class PromoFormActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button1;
    private Button button2;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo_form);
        button1=findViewById(R.id.b1);
        button1.setOnClickListener(this);
        button2=findViewById(R.id.b2);
        button2.setOnClickListener(this);
        fragmentManager=getFragmentManager();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.b1){
            fragmentoonline();
        }else if (view.getId()==R.id.b2){
            fragmentofisico();
        }

    }

    public void fragmentoonline (){
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        Fragment fragmento1 = new Fragmento1();
        fragmentTransaction.replace(R.id.activitypromolayout,fragmento1).addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void fragmentofisico (){
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        Fragment fragmento2 = new Fragmento2();
        fragmentTransaction.replace(R.id.activitypromolayout,fragmento2).addToBackStack(null);
        fragmentTransaction.commit();
    }
}
