package mx.mobilestudio.storelocator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        public void callLiverAPI(String query){

            RequestQueue queue = Volley.newRequestQueue(this);
            String URL = Uri.parse("https://api.myjson.com/bins/r882c").buildUpon();
            StringRequest stringRequest = new StringRequest(Request.Method.GET,URL, this,this);
            queue.add(stringRequest);

        }




    }
}
