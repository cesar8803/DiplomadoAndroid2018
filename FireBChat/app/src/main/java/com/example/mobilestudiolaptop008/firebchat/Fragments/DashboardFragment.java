package com.example.mobilestudiolaptop008.firebchat.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mobilestudiolaptop008.firebchat.Adapters.DashboardAdapter;
import com.example.mobilestudiolaptop008.firebchat.Models.ChatModel;
import com.example.mobilestudiolaptop008.firebchat.Models.DashBoarModel;
import com.example.mobilestudiolaptop008.firebchat.Models.UserModel;
import com.example.mobilestudiolaptop008.firebchat.R;
import com.example.mobilestudiolaptop008.firebchat.Utils.DispatchGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements View.OnClickListener {

    private final String TAG = LoginFragment.class.getSimpleName();
    private FloatingActionButton addchatbtn;
    private RecyclerView recyclerView;
    private DashboardAdapter adapter;
    private FirebaseAuth firebaseAuth;
    private ArrayList<DashBoarModel> dataSource;

    public DashboardFragment() {
        // Required empty public constructor
    }
    public static DashboardFragment newInstance()
    {
        DashboardFragment fragment = new DashboardFragment();
        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_dashboard, container, false);

        addchatbtn = v.findViewById(R.id.chatbtn);
        recyclerView = v.findViewById(R.id.rcchats);
        adapter = new DashboardAdapter();


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        addchatbtn.setOnClickListener(this);

        return v;


    }

    @Override
    public void onClick(View view) {

        Log.d(TAG,"Add Click");


GetUserChats();


    }


    private void GetUserChats()
    {

        FirebaseUser user = firebaseAuth.getCurrentUser();
        DatabaseReference chatreference = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("chats");

        chatreference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {

                    for(DataSnapshot chatSnapshot: dataSnapshot.getChildren())
                    {

                        boolean isActiveChat = (boolean) chatSnapshot.getValue();

                        if(isActiveChat)
                        {

                            DashBoarModel dashboarmodel = new DashBoarModel(chatSnapshot.getKey());
                            dataSource.add(dashboarmodel);

                        }


                        getUsersChatInfo();



                    }


                }
                else
                {
                    Toast.makeText(getActivity(), "No existen chats", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getActivity(), "Error Cargando tus chats: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG,"Error getUsersChats: " + databaseError.getMessage());


            }
        });

    }
    private void getUsersChatInfo()
    {

        final DispatchGroup dispatchGroup = new DispatchGroup();
        for(int x=0; x<dataSource.size();x++)
        {

            String chatId= dataSource.get(x).getChatId();

            final DatabaseReference userchatReferences = FirebaseDatabase.getInstance().getReference().child("chats").child(chatId).child("users");

            dispatchGroup.enter();
final int finalx=x;
            userchatReferences.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Log.d(TAG,"getUserInfo " + dataSnapshot.getValue() + "");


                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {

                        UserModel userModel =  new UserModel(dataSnapshot1.getKey());

                        if (!userModel.getId().equals(firebaseAuth.getCurrentUser().getUid()))
                        {
                            dataSource.get(finalx).getFriends().add(userModel);

                        }

                        }



                    dispatchGroup.leave();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    dispatchGroup.leave();
                }
            });




        }


        dispatchGroup.notify(new Runnable() {
            @Override
            public void run() {

                Log.d(TAG,"Ha finalizado nuestras consultas");

                for(DashBoarModel chat: dataSource)
                {

                    for(final UserModel user: chat.getFriends())

                    {
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                                .getReference(getString(R.string.get_users_str)).child(user.getId());
                        dispatchGroup.enter();

                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists())
                                {

                                    UserModel userModel = dataSnapshot.getValue(UserModel.class);
user.setImageurl(userModel.getImageurl());
user.setName(user.getName());




                                }
dispatchGroup.leave();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.d("Errr",databaseError.getMessage());
dispatchGroup.leave();
                            }
                        });



                    }

                }


                dispatchGroup.notify(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("",dataSource.get(0).getChatId());
                    }
                });


            }
        });


    }




}
