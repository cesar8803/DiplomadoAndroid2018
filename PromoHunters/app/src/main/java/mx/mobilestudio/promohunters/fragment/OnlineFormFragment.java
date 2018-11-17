package mx.mobilestudio.promohunters.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mx.mobilestudio.promohunters.R;
import mx.mobilestudio.promohunters.model.Promo;

import static android.app.Activity.RESULT_OK;


public class OnlineFormFragment extends Fragment implements View.OnClickListener, OnSuccessListener, OnFailureListener {


    private Button button;
    private ImageButton imageButton;
    private EditText editTextTitle;
    private EditText editTextPrice;
    private EditText editTextDesc;
    private EditText editTextLink;
    private static final int SELECT_PHOTO = 100;
    private Uri selectedImage;

    private DatabaseReference databaseReference;



    public OnlineFormFragment() {
        // Required empty public constructor
        databaseReference=FirebaseDatabase.getInstance().getReference();

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View view =  inflater.inflate(R.layout.fragment_online_form, container, false);


        button = view.findViewById(R.id.buttonAddnewPromo);

        editTextTitle = view.findViewById(R.id.title);
        editTextPrice = view.findViewById(R.id.precio);
        editTextLink = view.findViewById(R.id.link);
        editTextDesc = view.findViewById(R.id.descripcion);
        imageButton = view.findViewById(R.id.botonImagen);


        imageButton.setOnClickListener(this);
        button.setOnClickListener(this);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.buttonAddnewPromo:


                String validateResult = validateForm();

                if(validateResult.isEmpty()){
                    //Los datos estan correctos, podemos guardarlos en Firebase

                    createNewpromo();


                }else {

                    Toast.makeText(getActivity(), validateResult,Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.botonImagen:


                selecImage();
                break;

        }




    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode){
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    selectedImage = imageReturnedIntent.getData();

                    Toast.makeText(getActivity(), "Image selected!!  "+selectedImage.getLastPathSegment(),Toast.LENGTH_SHORT).show();

                }
                break;

        }

    }



    public void selecImage(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        getActivity().startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }



    public String validateForm(){

        String validateString="";

        if (editTextTitle.getText().toString().isEmpty()){
            validateString = validateString +"Debes colocar un Titulo valido\n";

        }
        if (editTextPrice.getText().toString().isEmpty()){
            validateString = validateString +"Debes colocar un Precio valido\n";

        }
        boolean isValidURL = URLUtil.isValidUrl(editTextLink.getText().toString());

        if (!isValidURL || editTextLink.getText().toString().isEmpty()){


                validateString = validateString +"Debes colocar un Link valido\n";



        }

        if (editTextDesc.getText().toString().isEmpty()){
            validateString = validateString +"Debes colocar una Descripción valida\n";


        }


        return validateString;

    }

    public void createNewpromo(){
        Promo newPromo= new Promo();
        newPromo.setTitle(editTextTitle.getText().toString());
        newPromo.setPrice(Float.valueOf(editTextPrice.getText().toString()));
        newPromo.setLink(editTextLink.getText().toString());
        newPromo.setDescription(editTextDesc.getText().toString());
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
