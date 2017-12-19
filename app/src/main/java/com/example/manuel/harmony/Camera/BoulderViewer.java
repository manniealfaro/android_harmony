package com.example.manuel.harmony.Camera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.manuel.harmony.R;

public class BoulderViewer extends AppCompatActivity {

    TextView name_problem;
    TextView grade_problem;
    TextView coments_problem;
    TextView uploader_problem;
    TextView ascents_problem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boulder_viewer);

        name_problem = (TextView) findViewById(R.id.prob_name_tv);
        grade_problem = (TextView) findViewById(R.id.prob_grade_tv);
        coments_problem = (TextView) findViewById(R.id.prob_com_tv);
        uploader_problem = (TextView) findViewById(R.id.prob_uploader_tv);
        ascents_problem = (TextView) findViewById(R.id.prob_ascents_tv);

        if(getIntent().hasExtra("problem")){
            //name_problem.setText(name_problem.getText()+getIntent().getE);
        }
    }
}
