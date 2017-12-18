package com.example.manuel.harmony.Camera;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.manuel.harmony.BaseActivity;
import com.example.manuel.harmony.R;
import com.example.manuel.harmony.helpers.BottomNavigationViewHelper;
import com.github.florent37.camerafragment.CameraFragment;
import com.github.florent37.camerafragment.configuration.Configuration;
import com.github.florent37.camerafragment.widgets.RecordButton;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import butterknife.Bind;

public class CameraActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "CameraActivity";
    private static final int ACTIVITY_NUM = 1;
    private Context mContext = CameraActivity.this;

    static final int REQUEST_TAKE_PHOTO = 1;

    CameraFragment cameraFragment;

    @Bind(R.id.btn_take_photo)
    RecordButton recordButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Log.d(TAG, "onCreate: started.");

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);

        if(permissionCheck != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_TAKE_PHOTO);
        }

        findViewById(R.id.btn_take_photo).setOnClickListener(this);

        cameraFragment = CameraFragment.newInstance(new Configuration.Builder().build());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.lay_cam, cameraFragment, cameraFragment.getTag())
                .commit();



        setupBottomNavigationView();
    }

    protected void onStart(Bundle savedInstance) {
        setContentView(R.layout.activity_camera);
        Log.d(TAG, "onStart: started.");

    }


    public void onRecordButtonClicked(View v) {
        Intent intent = new Intent(this, ColoreableActivity.class);
        startActivity(intent);
    }

    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");

        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavigationViewEx);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);

        BottomNavigationViewHelper.enableNAvigation(mContext, bottomNavigationViewEx);

        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_take_photo){
            Intent intent = new Intent(this, ColoreableActivity.class);
            startActivity(intent);
        }
    }


//    String mCurrentPhotoPath;
//
//    public void toCamera(View v){
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        // Ensure that there's a camera activity to handle the intent
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            // Create the File where the photo should go
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                // Error occurred while creating the File
//                ex.printStackTrace();
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                Uri photoURI = FileProvider.getUriForFile(this,
//                        "com.example.manuel.demos.fileprovider",
//                        photoFile);
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
//            }
//        }
//    }
//
//    private File createImageFile() throws IOException {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */
//        );
//
//        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = image.getAbsolutePath();
//        System.out.println("imgen guardada: "+mCurrentPhotoPath);
//        return image;
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data){
//
//        if(mCurrentPhotoPath != null) {
//            Intent intent = new Intent(this, ColoreableActivity.class);
//            intent.putExtra("image", mCurrentPhotoPath);
//            startActivity(intent);
//        }
//
//    }

}
