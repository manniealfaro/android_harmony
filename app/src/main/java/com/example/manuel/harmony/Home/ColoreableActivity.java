package com.example.manuel.harmony.Home;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.manuel.harmony.R;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class ColoreableActivity extends Activity implements View.OnClickListener,
        View.OnTouchListener {

    ImageView choosenImageView;
    Button choosePicture;
    Button savePicture;

    Bitmap bmp;
    Bitmap alteredBitmap;
    Canvas canvas;

    int color = Color.RED;

    Paint paint;
    Matrix matrix;
    float downx = 0;
    float downy = 0;
    float upx = 0;
    float upy = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coloreable);

        choosenImageView = (ImageView) this.findViewById(R.id.image_colors);
        //choosePicture = (Button) this.findViewById(R.id.ChoosePictureButton);
        //savePicture = (Button) this.findViewById(R.id.SavePictureButton);

//        savePicture.setOnClickListener(this);
//        choosePicture.setOnClickListener(this);
        choosenImageView.setOnTouchListener(this);

        if(getIntent().hasExtra("image")){
            String im_path = getIntent().getStringExtra("image");
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            bmp = BitmapFactory.decodeFile(im_path, options);
            try {
//                BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
//                bmpFactoryOptions.inJustDecodeBounds = true;
//                bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(
//                        imageFileUri), null, bmpFactoryOptions);
//
//                bmpFactoryOptions.inJustDecodeBounds = false;
//                bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(
//                        imageFileUri), null, bmpFactoryOptions);

                alteredBitmap = Bitmap.createBitmap(bmp.getWidth()*3/4, bmp
                        .getHeight()*3/4, bmp.getConfig());
                canvas = new Canvas(alteredBitmap);
                paint = new Paint();
                paint.setColor(color);
                paint.setStrokeWidth(20);
                matrix = new Matrix();
                canvas.drawBitmap(bmp, matrix, paint);

                choosenImageView.setImageBitmap(alteredBitmap);
                choosenImageView.setOnTouchListener(this);
            } catch (Exception e) {
                Log.v("ERROR", e.toString());
            }
        }

        findViewById(R.id.button_blue).setOnClickListener(this);
        findViewById(R.id.button_green).setOnClickListener(this);
        findViewById(R.id.button_red).setOnClickListener(this);
        findViewById(R.id.button_black).setOnClickListener(this);
        findViewById(R.id.button_upload).setOnClickListener(this);
    }

    public void onClick(View v) {

        if (v == choosePicture) {
            Intent choosePictureIntent = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(choosePictureIntent, 0);
        } else if (v == savePicture) {

            if (alteredBitmap != null) {
                ContentValues contentValues = new ContentValues(3);
                contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, "Draw On Me");

                Uri imageFileUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                //grantUriPermission(String.valueOf(this), imageFileUri, Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
                try {
                    OutputStream imageFileOS = getContentResolver().openOutputStream(imageFileUri);
                    alteredBitmap.compress(Bitmap.CompressFormat.JPEG, 90, imageFileOS);
                    Toast t = Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT);
                    t.show();

                } catch (Exception e) {
                    Log.v("EXCEPTION", e.getMessage());
                }
            }
        }

        switch(v.getId()){
            case R.id.button_blue: {
                changeColor(Color.CYAN);
                paint.setColor(color);
                break;
            }
            case R.id.button_green: {
                changeColor(Color.GREEN);
                paint.setColor(color);
                break;
            }
            case R.id.button_red: {
                changeColor(Color.RED);
                paint.setColor(color);
                break;
            }
            case R.id.button_black: {
                changeColor(Color.BLACK);
                paint.setColor(color);
                break;
            }
            case R.id.button_upload: {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                alteredBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();

//                Intent intent = new Intent(this, ProblemData.class);
//                intent.putExtra("image", byteArray);
//                startActivity(intent);
            }
        }
    }

    public void changeColor(int c){
        this.color = c;
    }





    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK) {
            Uri imageFileUri = intent.getData();
            try {
                BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
                bmpFactoryOptions.inJustDecodeBounds = true;
                bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(
                        imageFileUri), null, bmpFactoryOptions);

                bmpFactoryOptions.inJustDecodeBounds = false;
                bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(
                        imageFileUri), null, bmpFactoryOptions);

                alteredBitmap = Bitmap.createBitmap(bmp.getWidth(), bmp
                        .getHeight(), bmp.getConfig());
                canvas = new Canvas(alteredBitmap);
                paint = new Paint();
                paint.setColor(color);
                paint.setStrokeWidth(20);
                matrix = new Matrix();
                canvas.drawBitmap(bmp, matrix, paint);

                choosenImageView.setImageBitmap(alteredBitmap);
                choosenImageView.setOnTouchListener(this);
            } catch (Exception e) {
                Log.v("ERROR", e.toString());
            }
        }
    }
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downx = event.getX();
                downy = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                upx = event.getX();
                upy = event.getY();
                canvas.drawLine(downx, downy, upx, upy, paint);
                choosenImageView.invalidate();
                downx = upx;
                downy = upy;
                break;
            case MotionEvent.ACTION_UP:
                upx = event.getX();
                upy = event.getY();
                canvas.drawLine(downx, downy, upx, upy, paint);
                choosenImageView.invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;
        }
        return true;
    }
}