package com.example.manuel.harmony.Home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manuel.harmony.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by manuel on 29/11/2017.
 */

public class ProfileFragment extends Fragment {

    public static final String TAG = "ProfileFragment";
    private TextView userNameD;

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_profile, container, false);

        userNameD = (TextView) view.findViewById(R.id.text_email_profile);

        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        userNameD.setText(user.getEmail());

        return view;
    }
}
