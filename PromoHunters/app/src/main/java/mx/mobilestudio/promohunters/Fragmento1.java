package mx.mobilestudio.promohunters;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import mx.mobilestudio.promohunters.model.Promo;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragmento1 extends Fragment implements View.OnClickListener, OnSuccessListener, OnFailureListener {
    private Button button;
    private ImageButton buttonimage;
    private EditText editTextTitle;
    private EditText editTextPrice;
    private EditText editTextDescription;
    private EditText editTextLink;
    private static final int SELECT_PHOTO = 100;
    private Uri selectedImage;
    private DatabaseReference databaseReference;
    private StorageReference imageReference;
    private StorageReference storageReference;
    private FirebaseStorage firebaseStorage;
    private ProgressDialog progressDialog;
    private UploadTask uploadTask;


    public Fragmento1() {
        // Required empty public constructor
        databaseReference=FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode){
            case SELECT_PHOTO:
                if(resultCode==RESULT_OK){
                    selectedImage=imageReturnedIntent.getData();
                    Toast.makeText(getActivity(),"Image Selected"+selectedImage.getLastPathSegment(), Toast.LENGTH_LONG).show();

                }
                break;

        }
    }

    //este metodo lo sube la pic (cloud storage)
    public void uploadImage() {
        //Creamos una referencia al folder donde asignaremos las imagenes
        //child significa generar un nodo hijo
        imageReference = storageReference.child("images" + selectedImage.getLastPathSegment());
        // crear un loading para mostrar el progreso de la carga

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMax(100);
        progressDialog.setMessage("Subiendo imagen...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        progressDialog.setCancelable(false);

        //iniciamos la subida de la imagen
        uploadTask = imageReference.putFile(selectedImage);
        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                progressDialog.incrementProgressBy((int) progress);
            }
        });
           //Revisar clases anonimas
        //

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(),"Error al subir imagen.", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //implementation 'com.google.firebase:firebase-auth:16.0.4' esta linea es necesaria para obtenir mi url
                Task<Uri> downloadURL = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                Toast.makeText(getActivity(),"Imagen guardada.", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                
                taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Toast.makeText(getActivity(),"URL"+uri.toString(),Toast.LENGTH_LONG).show();
                        //GlideApp.with(this).load("http://goo.gl/gEgYUd").into(imageView);
                        createNewpromo(uri.toString());
                        Picasso.with(getActivity()).load(uri.toString()).into(buttonimage);

                    }
                });
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragmento1,container,false);
        button = view.findViewById(R.id.button_accept);
        buttonimage = view.findViewById(R.id.botonImage);
        editTextDescription=view.findViewById(R.id.descripcion);
        editTextTitle=view.findViewById(R.id.title);
        editTextPrice=view.findViewById(R.id.precio);
        editTextLink=view.findViewById(R.id.link);
        button.setOnClickListener(this);
        buttonimage.setOnClickListener(this);

        //Iniciamos el storage de Firebase
        firebaseStorage=FirebaseStorage.getInstance();

        //Creamos una referencia al storage
        storageReference=firebaseStorage.getReference();



        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_accept:


                String validateResult = validateForm();
                if (validateResult.isEmpty()) {

                    if (selectedImage != null){
                        uploadImage();
                    }else{
                            createNewpromo("");
                    }

                } else {
                    Toast.makeText(getActivity(), validateResult, Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.botonImage:
                selectImage();
                break;
        }
    }

    public void selectImage(){
        Intent photoPickerIntent = new Intent (Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
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

    public void createNewpromo(String imageLink){
        Promo newPromo= new Promo();
        newPromo.setTitle(editTextTitle.getText().toString());
        newPromo.setPrice(Float.valueOf(editTextPrice.getText().toString()));
        newPromo.setLink(editTextLink.getText().toString());
        newPromo.setDescription(editTextDescription.getText().toString());
        newPromo.setImageLink(imageLink);
        String promoID = databaseReference.push().getKey();
        databaseReference.child("promos").child(promoID).setValue(newPromo).addOnSuccessListener(this).addOnFailureListener(this);

    }

    @Override
    public void onFailure(@NonNull Exception e) {

    }

    @Override
    public void onSuccess(Object o) {
        Toast.makeText(getActivity(), "Vamos!!", Toast.LENGTH_LONG).show();
        getActivity().finish();
    }
}
