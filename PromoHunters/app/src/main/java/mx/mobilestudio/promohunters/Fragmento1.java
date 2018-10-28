package mx.mobilestudio.promohunters;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mx.mobilestudio.promohunters.model.Promo;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragmento1 extends Fragment implements View.OnClickListener, OnSuccessListener, OnFailureListener {
    private Button button;
    private EditText editTextTitle;
    private EditText editTextPrice;
    private EditText editTextDescription;
    private EditText editTextLink;

    private DatabaseReference databaseReference;

    public Fragmento1() {
        // Required empty public constructor
        databaseReference=FirebaseDatabase.getInstance().getReference();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragmento1,container,false);
        button = view.findViewById(R.id.button_accept);
        editTextDescription=view.findViewById(R.id.descripcion);
        editTextTitle=view.findViewById(R.id.title);
        editTextPrice=view.findViewById(R.id.precio);
        editTextLink=view.findViewById(R.id.link);
        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        String validateResult=validateForm();
        if(validateResult.isEmpty()){

            createNewpromo();

        }else{
            Toast.makeText(getActivity(),validateResult,Toast.LENGTH_LONG).show();
        }
    }

    public String validateForm(){
        String validateString="";
        if (editTextTitle.getText().toString().isEmpty()){
            validateString=validateString+"Debes escribir un Titulo valido";
        }
        if (editTextTitle.getText().toString().isEmpty()){
            validateString=validateString+"Debes escribir un Precio valido";
        }
        boolean isValidURL= URLUtil.isValidUrl(editTextLink.getText().toString());
        if(!isValidURL || editTextLink.getText().toString().isEmpty()){
            validateString=validateString+"Debe ser valido el link";
        }


        if (editTextTitle.getText().toString().isEmpty()){
            validateString=validateString+"Debes escribir un Link valido";
        }
        if (editTextTitle.getText().toString().isEmpty()){
            validateString=validateString+"Debes escribir un Description valido";
        }

        return validateString;
    }

    public void createNewpromo(){
        Promo newPromo= new Promo();
        newPromo.setTitle(editTextTitle.getText().toString());
        newPromo.setPrice(Float.valueOf(editTextPrice.getText().toString()));
        newPromo.setLink(editTextLink.getText().toString());
        newPromo.setDescription(editTextDescription.getText().toString());
        String promoID = databaseReference.push().getKey();
        databaseReference.child("promos").child(promoID).setValue(newPromo).addOnSuccessListener(this).addOnFailureListener(this);

    }

    @Override
    public void onFailure(@NonNull Exception e) {

    }

    @Override
    public void onSuccess(Object o) {
        Toast.makeText(getActivity(), "Good to go!!", Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }
}
