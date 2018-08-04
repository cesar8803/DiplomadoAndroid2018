package com.example.mobilestudiolaptop008.firebchat.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private final String TAG = LoginFragment.class.getSimpleName();
    private TextInputLayout emailtxt;
    private TextInputLayout passtxt;
    private Button loginbtn;
    private Button registerbtn;

    private ProgressDialog progressDialog;

    private OnLoginClickListener onLoginClickListener;
    private FirebaseAuth firebaseAuth;



    private OnCompleteLoginListener onCompleteLoginListener;


    public static LoginFragment newInstance()
    {
        LoginFragment fragment = new LoginFragment();
        return fragment;

    }

    public LoginFragment() {
        // Required empty public constructor
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

        View v = inflater.inflate(R.layout.fragment_login, container, false);
emailtxt = v.findViewById(R.id.usuariotxt);
passtxt = v.findViewById(R.id.passtxt);
loginbtn =v.findViewById(R.id.entrarbtn);
registerbtn =v.findViewById(R.id.registrabtn);

loginbtn.setOnClickListener(this);
registerbtn.setOnClickListener(this);


        return v;





    }

    public void setOnLoginClickListener(OnLoginClickListener onLoginClickListener) {
        this.onLoginClickListener = onLoginClickListener;
    }

    @Override
    public void onClick(View view) {


        switch (view.getId())
        {
            case R.id.entrarbtn:

                String Email=  emailtxt.getEditText().getText().toString().trim();
                String Pass=  passtxt.getEditText().getText().toString().trim();

                if (validate(Email,Pass))
                {

                    firebaseEnterWith(Email,Pass);

                }





            break;
            case R.id.registrabtn:
                onLoginClickListener.onRegisterButtonPress();
                break;
        }

    }

public interface OnLoginClickListener
{
    void onRegisterButtonPress();


}

    private void firebaseEnterWith(final String Email,String pass)
    {

        progressDialog.setMessage("Cargando");
        progressDialog.show();


firebaseAuth.signInWithEmailAndPassword(Email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {


        if(task.isSuccessful())
        {

            progressDialog.dismiss();

            if(firebaseAuth.getCurrentUser() != null)
            {
                onCompleteLoginListener.OnLoginComplete();

            }
            else
            {
                Toast.makeText(getActivity(), "Error: NO hay usuario", Toast.LENGTH_SHORT).show();
            }


        }
        else
        {

            Toast.makeText(getActivity(), "Error: " +  task.getException().getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
});


    }


    private boolean validate(String email,String pass)
    {

        emailtxt.setError(null);
        passtxt.setError(null);


        if(email.isEmpty())
        {
            emailtxt.setError("Usuario Requerido");
            return false;
        }

        if(pass.isEmpty())
        {
            passtxt.setError("Password Requerido");
            return false;
        }



        return  true;



    }



public interface OnCompleteLoginListener
{

    void OnLoginComplete();

}

    public void setOnCompleteLoginListener(OnCompleteLoginListener onCompleteLoginListener) {
        this.onCompleteLoginListener = onCompleteLoginListener;
    }

}
