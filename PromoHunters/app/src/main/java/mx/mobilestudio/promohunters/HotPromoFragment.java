package mx.mobilestudio.promohunters;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import mx.mobilestudio.promohunters.Adaptador.PromoHuntersAdaptador;
import mx.mobilestudio.promohunters.model.Promo;


/**
 * A simple {@link Fragment} subclass.
 */
public class HotPromoFragment extends Fragment implements ValueEventListener {

    public List<Promo> promolist;
    public ArrayList<Promo> promos = new ArrayList<Promo>();

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    public void setPromos(List<Promo> promos){
        this.promolist=promos;
    }

    private DatabaseReference databaseReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewroot=inflater.inflate(R.layout.fragment_hot_promo, container, false);
        recyclerView=viewroot.findViewById(R.id.recyclerviewf);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //aqui manda a llamar ami otra clase

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(isConnected==false){
            getAllSavedPromos();
        }

        return viewroot;
        //return inflater.inflate(R.layout.fragment_hot_promo, container, false);
    }

    public HotPromoFragment() {
        // Required empty public constructor
        databaseReference = FirebaseDatabase.getInstance().getReference("promos");
        databaseReference.addValueEventListener(this);
    }

        @Override
        public void onDataChange (@NonNull DataSnapshot dataSnapshot){
            for (DataSnapshot child : dataSnapshot.getChildren()) {
                Promo promo = child.getValue(Promo.class);
                promos.add(promo);
                //Toast.makeText(getActivity(),"La promo es: "+promo.getTitle()+"su link es: "+ promo.getLink(),Toast.LENGTH_LONG).show();
                saveLocalStoragePromo(promo);
            }
               renderRecyclerView();
        }

        public void saveLocalStoragePromo(Promo promo){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        //Todas las actualizaciones se ejecutan between el begintransaction y commitTransaction
        try{
                Promo localPromo = realm.copyToRealm(promo);
            } catch(Exception e)   {
            Log.v("Error", "Primary Key already exist");
            }

            realm.commitTransaction();
        }

        public void getAllSavedPromos(){
        Realm realm = Realm.getDefaultInstance();
            RealmResults<Promo> promos = realm.where(Promo.class).findAll();
            for (Promo currentPromo : promos){
                Toast.makeText(getActivity(),"Title"+currentPromo.getTitle(),Toast.LENGTH_LONG).show();
                this.promos.add(currentPromo);
            }
            renderRecyclerView();

        }

        @Override
        public void onCancelled (@NonNull DatabaseError databaseError){
            Toast.makeText(getActivity(),"Sin internet",Toast.LENGTH_LONG).show();
        }

        public void renderRecyclerView(){
            PromoHuntersAdaptador promoHuntersAdaptador = new PromoHuntersAdaptador(promos);
            recyclerView.setAdapter(promoHuntersAdaptador);
        }


}
