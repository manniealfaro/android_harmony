package com.example.manuel.harmony.Climb;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manuel.harmony.Objects.Problem;
import com.example.manuel.harmony.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manuel on 29/11/2017.
 */

public class ClimbListFragment extends Fragment {

    public static final String TAG = "ClimbListFragment";

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private String problemId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_climb_list, container, false);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("problems");

        // store app title to 'app_title' node
        mFirebaseInstance.getReference("app_title").setValue("harmony");

        // app_title change listener
        mFirebaseDatabase.child(problemId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG, "Problems updated");


                List<Problem> list = new ArrayList<>();

                String name = (String) dataSnapshot.child("name").getValue();
                String grade = (String) dataSnapshot.child("grade").getValue();
                String comment = (String) dataSnapshot.child("comment").getValue();
                String uploader = (String) dataSnapshot.child("uploader").getValue();
                byte[] byteArray =  (byte[]) dataSnapshot.child("image").getValue();

                Problem problem = new Problem(name,grade, byteArray, comment, uploader);


                list.add(problem);



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read app data.", error.toException());
            }
        });

        return view;
    }
}
