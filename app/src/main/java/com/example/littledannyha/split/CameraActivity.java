package com.example.littledannyha.split;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends Activity {

    static final int REQUEST_TAKE_PHOTO = 1;
    static final String SAVE_PHOTO_IN_BUNDLE = "com.littledannyha.cameraActivity.savePhotoInBundle";
    private File pictureFile;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("onActivityResult", Integer.toString(requestCode));
        Log.d("onActivityResult", Integer.toString(REQUEST_TAKE_PHOTO));
        Log.d("onActivityResult", Integer.toString(resultCode));
        Log.d("onActivityResult", Integer.toString(RESULT_OK));
        Log.d("onActivityResult", "returned");
        Log.d("data is null", Boolean.toString(data == null));
        Bundle extra = data.getExtras();

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK && data != null) {
            Log.d("onActivityResult", "in if statement");
            Bundle extras = data.getExtras();
            Intent confirmPhotoIntent = new Intent(this, PhotoActivity.class);
            confirmPhotoIntent.putExtra(PhotoActivity.photoURIKey, pictureFile.getAbsolutePath());
            startActivity(confirmPhotoIntent);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVE_PHOTO_IN_BUNDLE, pictureFile.getAbsolutePath());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        if (pictureFile != null) {
//            Intent confirmPhotoIntent = new Intent(this, PhotoActivity.class);
//            confirmPhotoIntent.putExtra(PhotoActivity.photoURIKey, pictureFile.getAbsolutePath());
//            startActivity(confirmPhotoIntent);
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        Log.d("createImageFile", "0");
        String imageFileName = "JPEG_" + timeStamp + "_";
        Log.d("createImageFile", imageFileName);
        Log.d("createImageFile", "1");
//        File storageDir = Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES);
        Log.d("createImageFile", "2");
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg" //,         /* suffix */
//                storageDir      /* directory */
        );
        Log.d("createImageFile", "3");

        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }


    public void dispatchTakePictureIntent(View view) {
//        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        // Ensure that there's a camera activity to handle the intent
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Log.d("created photo file", "before request");
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.d("Exception creating file", "%$#@!");
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                if(!photoFile.exists()){
                    Log.e("FILE DOES NOT EXIST", "FLUUUUBBBB");
                    while(-1 < 0){

                    }
                } else{
                    Log.d("FILE EXISTS", "SQUEEEEEE");
                    if(photoFile.isDirectory()){
                        Log.d("THE FILE", "IS A DIRECTORY");
                        while(-1 < 0){

                        }
                    }

                }
                Log.d("dispatchTakePictureInt", "1");
                pictureFile = photoFile;
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));

                Log.d("dispatchTakePictureInt", "2");
//                startActivity(takePictureIntent);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

}
