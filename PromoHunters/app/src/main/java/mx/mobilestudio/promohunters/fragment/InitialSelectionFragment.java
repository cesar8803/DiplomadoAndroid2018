package mx.mobilestudio.promohunters.fragment;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mx.mobilestudio.promohunters.MainActivity;
import mx.mobilestudio.promohunters.PromoFormActivity;
import mx.mobilestudio.promohunters.R;


public class InitialSelectionFragment extends Fragment implements View.OnClickListener {

    public Button onlineButton;
    public Button storeButton;

    public InitialSelectionFragment() {
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
        View view = inflater.inflate(R.layout.fragment_initial_selection, container, false);

        onlineButton = view.findViewById(R.id.online);
        storeButton = view.findViewById(R.id.physical);

        onlineButton.setOnClickListener(this);
        storeButton.setOnClickListener(this);

        return view;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.online:

                PromoFormActivity promoFormActivity = (PromoFormActivity) getActivity();

                promoFormActivity.attachFragment(PromoFormActivity.FRAGMENT_ONLINE_FORM);

                break;
            case R.id.physical:

                break;
        }

    }
}
