package com.example.manuel.harmony.Camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.manuel.harmony.Climb.ClimbActivity;
import com.example.manuel.harmony.Objects.Boulder;
import com.example.manuel.harmony.Objects.Problem;
import com.example.manuel.harmony.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class ProblemData extends AppCompatActivity {
    Spinner spin;
    String[] datos = {"6a","6a+","6b","6b+","6c","6c+","7a","7a+","7b","7b+","7c","7c+","8a","8a+","8b"};

    String grade;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String useremail = user.getEmail();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_data);

        if(getIntent().hasExtra("image")){
            byte[] byteArray = getIntent().getByteArrayExtra("image");
            Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

            ImageView imv = (ImageView) findViewById(R.id.imageView2);
            imv.setImageBitmap(image);
        }

        spin = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
        spin.setAdapter(adapter);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                grade = datos[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void upload(View v){
        EditText comment = (EditText) findViewById(R.id.comments);
        EditText name = (EditText) findViewById(R.id.boudler_name);

        ImageView im = (ImageView) findViewById(R.id.imageView2);
        Bitmap bitIm = ((BitmapDrawable)im.getDrawable()).getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitIm.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("problems");

        // Creating new user node, which returns the unique key value
        // new user node would be /users/$userid/
        String problemId = mDatabase.push().getKey();

        // creating user object
        Problem problem = new Problem(name.getText().toString(),grade, comment.getText().toString(), useremail);

        // pushing user to 'users' node using the userId
        mDatabase.child(problemId).setValue(problem);

        Intent intent = new Intent(this, ClimbActivity.class);
        startActivity(intent);
    }
}
