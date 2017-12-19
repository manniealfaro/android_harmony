package com.example.manuel.harmony.Climb;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.manuel.harmony.Objects.Problem;
import com.example.manuel.harmony.R;
import com.google.firebase.database.ChildEventListener;
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

    private DatabaseReference mDatabase;
    private DatabaseReference mProblemReference;
    private ChildEventListener mProblemListener;

    private ArrayList<Problem> problemList;

    private String problemId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_climb_list, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mProblemReference = FirebaseDatabase.getInstance().getReference("problems");

        problemList = new ArrayList<>();



        return view;
    }

    public void onStart() {
        super.onStart();

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                // A new message has been added
                // onChildAdded() will be called for each node at the first time
                Problem problem = dataSnapshot.getValue(Problem.class);
                problemList.add(problem);

                Log.e(TAG, "onChildAdded:" + problem.name);

                Problem latest = problemList.get(problemList.size() - 1);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.e(TAG, "onChildRemoved:" + dataSnapshot.getKey());

                // A message has been removed
                Problem problem = dataSnapshot.getValue(Problem.class);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.e(TAG, "onChildChanged:" + dataSnapshot.getKey());

                // A message has changed
                Problem problem = dataSnapshot.getValue(Problem.class);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.e(TAG, "onChildMoved:" + dataSnapshot.getKey());

                // A message has changed position
                Problem problem = dataSnapshot.getValue(Problem.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "postMessages:onCancelled", databaseError.toException());
            }
        };

        mProblemReference.addChildEventListener(childEventListener);

        // copy for removing at onStop()
        mProblemListener = childEventListener;
    }

    @Override
    public void onStop() {
        super.onStop();

        if (mProblemListener != null) {
            mProblemReference.removeEventListener(mProblemListener);
        }

        for (Problem problem: problemList) {
            Log.e(TAG, "listItem: " + problem.name);
        }
    }

}
