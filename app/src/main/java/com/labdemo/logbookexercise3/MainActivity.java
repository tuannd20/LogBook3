package com.labdemo.logbookexercise3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    Button backward_btn;
    Button forward_btn;
    ImageView view_image_item;
    Button add_url_image_btn;
    TextInputLayout input_url_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view_image_item = findViewById(R.id.imageViewItem);
        input_url_image = findViewById(R.id.inputUrlImage);
        backward_btn = findViewById(R.id.backwardBtn);
        forward_btn = findViewById(R.id.forwardBtn);

        add_url_image_btn = findViewById(R.id.buttonAddUrl);
        add_url_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imageURl = input_url_image.getEditText().getText().toString();

                if (imageURl.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter Image URL !!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Image URL !!!", Toast.LENGTH_SHORT).show();
//                    imageList.add(imageURl);
//                    Glide.with(MainActivity.this).load(imageURl).into(view_image_item);
                    input_url_image.getEditText().setText("");
                }
            }
        });
    }
}