package mx.mobilestudio.promohunters.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import mx.mobilestudio.promohunters.R;
import mx.mobilestudio.promohunters.adapter.PromoHuntersAdaptador;
import mx.mobilestudio.promohunters.model.Promo;


/**
 * A simple {@link Fragment} subclass.
 */
public class HotPromoFragment extends Fragment implements ValueEventListener {

    public List<Promo> promos;
    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    public void setPromos(List<Promo> promos){
        this.promos=promos;
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

        getAllSavedPromos();
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
            ArrayList<Promo> promos = new ArrayList<Promo>();
            for (DataSnapshot child : dataSnapshot.getChildren()) {
                Promo promo = child.getValue(Promo.class);
                promos.add(promo);
                //Toast.makeText(getActivity(),"La promo es: "+promo.getTitle()+"su link es: "+ promo.getLink(),Toast.LENGTH_LONG).show();
                saveLocalStoragePromo(promo);
            }
            PromoHuntersAdaptador promoHuntersAdaptador = new PromoHuntersAdaptador(promos);
            recyclerView.setAdapter(promoHuntersAdaptador);
        }



        public void saveLocalStoragePromo(Promo promo){

            Realm realm = Realm.getDefaultInstance();

            realm.beginTransaction();
            //Todas las actualizaciones a la base de datos se ejecutan entre el beginTransaction y commitTransaction

            Promo localPromo = realm.copyToRealm(promo);


            realm.commitTransaction();


        }


        public void getAllSavedPromos(){
          Realm realm = Realm.getDefaultInstance();
            RealmResults<Promo> promos = realm.where(Promo.class).findAll();

            for(Promo currentPromo : promos){
                Toast.makeText(getActivity(), "Title" + currentPromo.getTitle(),Toast.LENGTH_LONG).show();
            }

        }


        @Override
        public void onCancelled (@NonNull DatabaseError databaseError){
            Toast.makeText(getActivity(),"Sin conexión ",Toast.LENGTH_LONG).show();
        }





}
