package com.example.littledannyha.split;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
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


    static final int REQUEST_TAKE_PHOTO = 111;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("onActivityResult", "returned");
        Log.d("data is null", Boolean.toString(data == null));
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK && data != null) {
            Log.d("onActivityResult", "in if statement");
            //String photoUri = extras.getString(MediaStore.EXTRA_OUTPUT);
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Intent confirmPhotoIntent = new Intent(this, PhotoActivity.class);
            //Log.d("onActivityResult", "pictureFile.getAbsolutePath is null: " + Boolean.toString(pictureFile.getAbsoluteFile() == null));
            //Log.d("onActivityResult", "PhotoActivity.photoURIKey: " + PhotoActivity.photoURIKey);

            //confirmPhotoIntent.putExtra(PhotoActivity.photoURIKey, photoUri);
            startActivity(confirmPhotoIntent);
        } else{
            Log.d("onActivityResult", "not launching new activity");
        }

    }

    //@Override
    //protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
        //if (pictureFile != null) {
            //outState.putString(SAVE_PHOTO_IN_BUNDLE, pictureFile.getAbsolutePath());
        //}
    //}

    //@Override
    //protected void onRestoreInstanceState(Bundle savedInstanceState) {
        //super.onRestoreInstanceState(savedInstanceState);
        //String filepath = savedInstanceState.getString(SAVE_PHOTO_IN_BUNDLE);
        //if (filepath != null){
            //this.pictureFile = new File(filepath);
        //}
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        //if (pictureFile != null) {
//            Intent confirmPhotoIntent = new Intent(this, PhotoActivity.class);
//            confirmPhotoIntent.putExtra(PhotoActivity.photoURIKey, pictureFile.getAbsolutePath());
//            startActivity(confirmPhotoIntent);
        //}
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        Log.d("createImageFile", "0");
        String imageFileName = "JPEG" + timeStamp + "";
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


    public void captureImageFromCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri fileUri = getOutputMediaFile(); // create a file to save the image
        Log.d("captureImageFromCamera","file url is " + fileUri.toString());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
    }


    /**
     * Create a File for saving an image or video
     */
    private static Uri getOutputMediaFile() {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

//        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES), "Split");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/split/";

        try {
            File dir = new File(fullPath);
            if (!dir.exists()) {
                boolean success = dir.mkdirs();
                Log.d("Directory created", Boolean.toString(success));
            }
        } catch (Exception e) {
            Log.e("App", "Exception" + e.getMessage());
        }
        // Create the storage directory if it does not exist
//        if (!mediaStorageDir.exists()) {
//            boolean success = mediaStorageDir.mkdir();
//            if (!success) {
//                return null;
//            }
//        }

        // Create a media file name
//        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "image.jpg");
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG" + timeStamp + ".jpg";
        File mediaFile = new File(fullPath + imageFileName);
        return Uri.fromFile(mediaFile);
    }

}
