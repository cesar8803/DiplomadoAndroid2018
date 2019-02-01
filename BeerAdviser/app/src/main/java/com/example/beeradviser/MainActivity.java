package com.example.beeradviser;
import android.view.View;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.List;

public class MainActivity extends Activity {
    private BeerExpert expert = new BeerExpert();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public void onClickFindBeer(View view){
        TextView brands = (TextView) findViewById(R.id.brands);
        Spinner color = (Spinner) findViewById(R.id.color);
        String beercolor=String.valueOf(color.getSelectedItem());
        List<String> milista = expert.getBrands(beercolor);
        StringBuilder milista2 = new StringBuilder();
        for(String cerveza : milista){
            milista2.append(cerveza).append('\n');
        }
        brands.setText(milista2);
    }
}
