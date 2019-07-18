package com.example.repairgrab.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.repairgrab.R;

import java.io.File;
import java.io.IOException;

public class NotifyErrorActivity extends AppCompatActivity {

    private final int REQUEST_CAMERA = 1, REQUEST_GALLERY = 2, REQUEST_VIDEO = 3;
    private Button btnCamera, btnGallery;
    private Dialog myDialog;
    private VideoView video;
    private MediaController media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_error);
        media = new MediaController(this);

    }

    public void playVideo() {
   video.setMediaController(media);
   media.setAnchorView(video);
   video.start();
    }

    public void uploadImage(View view) {

        showDialog();

    }

    public void showDialog() {
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.dialog_upload_picture);
        myDialog.setTitle("Choose Image");

        btnCamera = myDialog.findViewById(R.id.btnCamera);
        btnGallery = myDialog.findViewById(R.id.btnGallery);
        btnGallery.setEnabled(true);
        btnCamera.setEnabled(true);

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchGetPictureFromGalleryIntent();
            }

        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        myDialog.show();
    }

    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_CAMERA);
        }
    }


    public void dispatchGetPictureFromGalleryIntent() {
        Intent intentGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intentGallery.setType("image/*");
        startActivityForResult(intentGallery, REQUEST_GALLERY);
    }

    public void uploadVideo(View view) {
        Intent intentVideo = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        intentVideo.setType("video/*");
        startActivityForResult(intentVideo, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //  int order =  SharedPreferenceUtils.retrieveDataInt(getContext(),getContext().getString(R.string.order));

        if (resultCode == RESULT_OK) {
            // String tmpImageTag = "img_task_detail_" + order;

            // isChanged = true;

            if (requestCode == REQUEST_CAMERA) {
                ImageView imageToShow = findViewById(R.id.uploadImage);
                imageToShow.setVisibility(View.VISIBLE);

                Uri imageURI = data.getData();
                imageToShow.setImageURI(imageURI);
                // totalImagesNumberToUpload++;
                //imageDataEncoded.put(tmpImageTag, imageURI.toString());
                //FirebaseUtils.uploadImageToStorage(imageURI, tmpImageTag,this);

            } else if (requestCode == REQUEST_GALLERY) {
                ImageView imageToShow = findViewById(R.id.uploadImage);
                imageToShow.setVisibility(View.VISIBLE);
                Uri pickedImage = data.getData();
                imageToShow.setImageURI(pickedImage);
                // totalImagesNumberToUpload++;
                //imageDataEncoded.put(tmpImageTag, pickedImage.toString());
                //FirebaseUtils.uploadImageToStorage(pickedImage, tmpImageTag,this);

            } else if (requestCode == REQUEST_CAMERA) {
                video = findViewById(R.id.uploadVideo);
                video.setVisibility(View.VISIBLE);
                Uri pickedVideo = data.getData();
                video.setVideoURI(pickedVideo);
                video.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playVideo();
                    }
                });

            }
        }
    }
}
