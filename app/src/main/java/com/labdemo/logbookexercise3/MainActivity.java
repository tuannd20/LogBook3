package com.labdemo.logbookexercise3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageDataBase imageDataBase;

    Button backward_btn;
    Button forward_btn;
    ImageView view_image_item;
    Button add_url_image_btn;
    TextInputLayout input_url_image;
    ArrayList<String> imageUrlList;
    int countImageUrl;
    int currentImage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view_image_item = findViewById(R.id.imageViewItem);
        input_url_image = findViewById(R.id.inputUrlImage);
        backward_btn = findViewById(R.id.backwardBtn);
        forward_btn = findViewById(R.id.forwardBtn);

        imageDataBase = new ImageDataBase(this);

        getAllImage();
//        countImageUrl = imageUrlList.size();
        getImageItem(currentImage);

        backward_btn.setOnClickListener(this::renderImageWhenOnclick);
        forward_btn.setOnClickListener(this::renderImageWhenOnclick);
//        if (imageUrlList.size() == 0) {
//            Glide.with(MainActivity.this).load("https://img.freepik.com/free-vector/no-data-concept-illustration_114360-536.jpg?w=2000").into(view_image_item);
//        } else {
//            Glide.with(MainActivity.this).load(imageUrlList.get(1)).into(view_image_item);
//        }
        input_url_image.getEditText().setText("https://img1.kienthucvui.vn/uploads/2020/03/17/hinh-anh-hoat-hinh-3d-de-thuong-cho-dien-thoai_123431706.jpg");
        handleInsertImageUrl();
    }

    private void getAllImage() {
        String urlImageNoData = "https://img.freepik.com/free-vector/no-data-concept-illustration_114360-536.jpg?w=2000";
        Cursor cursor = imageDataBase.displayAllImage();
        imageUrlList = new ArrayList<>();
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            Glide.with(MainActivity.this).load(urlImageNoData).into(view_image_item);
        } else {
            while (cursor.moveToNext()) {
                imageUrlList.add(cursor.getString(1));
//                cursor.moveToNext();
                countImageUrl = imageUrlList.size();
            }
        }
    }

    private void renderImageWhenOnclick(View view) {
        if (view == forward_btn) {
            currentImage++;
            if (currentImage == imageUrlList.size()) {
                currentImage = 0;
            }
        } else {
            if (currentImage == 0) {
                currentImage = imageUrlList.size();
            }
            currentImage--;
        }
        getImageItem(currentImage);
    }

    private void handleInsertImageUrl() {
        add_url_image_btn = findViewById(R.id.buttonAddUrl);
        add_url_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imageURl = input_url_image.getEditText().getText().toString();

                if (imageURl.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter Image URL !!!", Toast.LENGTH_SHORT).show();
                } else {
                    addImageUrl(imageURl);
                    input_url_image.getEditText().setText("");
                }
            }
        });
    }

    private void addImageUrl(String url) {
        ImageDataBase db = new ImageDataBase(this);
        db.addNewImageUrl(url);
    }

    public void getImageItem(int value) {
        Glide.with(MainActivity.this).load(imageUrlList.get(value)).into(view_image_item);
    }
}