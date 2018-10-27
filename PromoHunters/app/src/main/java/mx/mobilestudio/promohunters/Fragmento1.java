package mx.mobilestudio.promohunters;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragmento1 extends Fragment implements View.OnClickListener {
    private Button button;
    private EditText editTextTitle;
    private EditText editTextPrice;
    private EditText editTextDesc;
    private EditText editTextLink;

    public Fragmento1() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragmento1,container,false);
        button = view.findViewById(R.id.button_accept);
        editTextPrice=view.findViewById(R.id.precio);
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
            validateString=validateString+"Debe ser valido";
        }


        if (editTextTitle.getText().toString().isEmpty()){
            validateString=validateString+"Debes escribir un Link valido";
        }
        if (editTextTitle.getText().toString().isEmpty()){
            validateString=validateString+"Debes escribir un Description valido";
        }

        return validateString;
    }
}
