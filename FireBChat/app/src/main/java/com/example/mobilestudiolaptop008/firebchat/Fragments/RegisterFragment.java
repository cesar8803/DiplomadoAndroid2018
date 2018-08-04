package com.example.mobilestudiolaptop008.firebchat.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mobilestudiolaptop008.firebchat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {


    private final String TAG = RegisterFragment.class.getSimpleName();
    private TextInputLayout txtNombre;
    private TextInputLayout txtEmail;
    private TextInputLayout txtContra;
    private TextInputLayout txtContra2;
    private Button btnRegistra;


    private ProgressDialog progressDialog;


    private OnRegisterListener onRegisterListener;



    private FirebaseAuth firebaseAuth;


    public RegisterFragment() {
        // Required empty public constructor
    }


    public static RegisterFragment newInstance()
    {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;



    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        txtNombre = v.findViewById(R.id.txtnombre);
        txtEmail = v.findViewById(R.id.txtemail);
        txtContra = v.findViewById(R.id.txtcontra);
        txtContra2 = v.findViewById(R.id.txtcontra2);
        btnRegistra = v.findViewById(R.id.btnresgitra);

        btnRegistra.setOnClickListener(this);


    return v;


    }

    @Override
    public void onClick(View view) {

        String Name=  txtNombre.getEditText().getText().toString().trim();
        String Email=  txtEmail.getEditText().getText().toString().trim();
        String Contra= txtContra.getEditText().getText().toString().trim();
        String Contra2= txtContra2.getEditText().getText().toString().trim();

if (validate(Name,Email,Contra,Contra))
{

    firebaseRegisterWith(Name,Email,Contra);

}





    }

private boolean validate(String name,String Email, String contra, String Contra2)
{

    txtNombre.setError(null);
    txtEmail.setError(null);
    txtContra.setError(null);
    txtContra2.setError(null);


    if(name.isEmpty())
    {
        txtNombre.setError("Nombre Requerido");
        return false;
    }

    if(Email.isEmpty())
    {
        txtEmail.setError("Email Requerido");
        return false;
    }

    if(contra.isEmpty())
    {
        txtContra.setError("Contraseña Requerida");
        return false;
    }

    if(Contra2.isEmpty())
    {
        txtContra2.setError("Confirmar Contraseña Requerida");
        return false;
    }

    if (!contra.equals(Contra2))
    {
        txtContra2.setError("La contraseña no coincide Requerida");
        return false;

    }


return  true;



}


private void firebaseRegisterWith(final String name,String Email, String contra)
{

    progressDialog.setMessage("Cargando");
    progressDialog.show();

    firebaseAuth.createUserWithEmailAndPassword(Email,contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {

            if(task.isSuccessful())
            {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                saveUserOnDatabase(user,name);

            }
            else
            {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();

            }

        }
    });



}

private void saveUserOnDatabase(final FirebaseUser user,final String Name)
{
    progressDialog.setMessage("Creando Usuario");
    Toast.makeText(getActivity(), "Save on database todo ok", Toast.LENGTH_SHORT).show();

    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());

    HashMap<String,Object> userData = new HashMap<String,Object>(){{
        put("name",Name);
        put("email",user.getEmail());


    }
    };

    databaseReference.updateChildren(userData, new DatabaseReference.CompletionListener() {
        @Override
        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

            progressDialog.dismiss();

            if(databaseError != null)
            {
                Toast.makeText(getActivity(), "Hubo un error " +  databaseError.getMessage() , Toast.LENGTH_SHORT).show();

            }
            else
            {
               onRegisterListener.OnRegisterComplete();
                Log.d(TAG,"********************************Todo ok guardado en la base de datos********************************");
            }

        }
    });

}

public interface OnRegisterListener
{

    void OnRegisterComplete();

}

    public void setOnRegisterListener(OnRegisterListener onRegisterListener) {
        this.onRegisterListener = onRegisterListener;
    }

}
