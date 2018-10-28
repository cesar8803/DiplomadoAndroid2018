package mx.mobilestudio.promohunters;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import mx.mobilestudio.promohunters.model.Promo;


/**
 * A simple {@link Fragment} subclass.
 */
public class HotPromoFragment extends Fragment implements ValueEventListener {
    private DatabaseReference databaseReference;

    public HotPromoFragment() {
        // Required empty public constructor
        databaseReference=FirebaseDatabase.getInstance().getReference("promos");
        databaseReference.addValueEventListener(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hot_promo, container, false);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        ArrayList<Promo> promos = new ArrayList<Promo>();

        for (DataSnapshot child: dataSnapshot.getChildren()){
            Promo promo = child.getValue(Promo.class);
            promos.add(promo);
            Toast.makeText(getActivity(),"La promo es: "+promo.getTitle()+"su link es: "+ promo.getLink(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
