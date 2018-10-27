package mx.mobilestudio.promohunters.fragment;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mx.mobilestudio.promohunters.R;


public class OnlineFormFragment extends Fragment implements View.OnClickListener {


    private Button button;
    private EditText editTextTitle;
    private EditText editTextPrice;
    private EditText editTextDesc;
    private EditText editTextLink;



    public OnlineFormFragment() {
        // Required empty public constructor
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


        String validateResult = validateForm();

        if(validateResult.isEmpty()){
            //Los datos estan correctos, podemos guardarlos en Firebase

        }else {

            Toast.makeText(getActivity(), validateResult,Toast.LENGTH_LONG).show();
        }

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



}
