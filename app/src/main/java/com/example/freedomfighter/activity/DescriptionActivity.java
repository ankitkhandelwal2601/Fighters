package com.example.freedomfighter.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.freedomfighter.R;
import com.squareup.picasso.Picasso;

public class DescriptionActivity extends AppCompatActivity {

    ImageView descBioImage;
    TextView descName;
    TextView descBirth;
    TextView descBio;
    RelativeLayout progressLayout;
    ProgressBar progressBar;
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        descBioImage = findViewById(R.id.imgBioImage);
        descName = findViewById(R.id.descBioName);
        descBirth = findViewById(R.id.descBioBirthDate);
        descBio = findViewById(R.id.txtBioDesc);

        progressLayout = findViewById(R.id.progressLayout);
        progressBar = findViewById(R.id.progressBar);

        progressLayout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        String bioTextName = intent.getStringExtra("name");
        String bioDescription = intent.getStringExtra("description");
        String bioBirthDate = intent.getStringExtra("birth");
        String bioImage = intent.getStringExtra("image");

        progressLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        getSupportActionBar().setTitle(bioTextName);

        descName.setText(bioTextName);
        descBio.setText(bioDescription);
        descBirth.setText(bioBirthDate);
        Picasso.get().load(bioImage).into(descBioImage);
    }
}