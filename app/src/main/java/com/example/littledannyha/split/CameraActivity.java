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
    private File pictureFile;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("onActivityResult", "returned");
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            Intent confirmPhotoIntent = new Intent(this, PhotoActivity.class);
            confirmPhotoIntent.putExtra(PhotoActivity.photoURIKey, pictureFile.getAbsolutePath());
            startActivity(confirmPhotoIntent);
        }

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
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }


    public void dispatchTakePictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Log.d("created photo file", "before request");
//        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Log.d("created photo file", "request photo intent");
                pictureFile = photoFile;
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivity(takePictureIntent);
//                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }

    }

}
