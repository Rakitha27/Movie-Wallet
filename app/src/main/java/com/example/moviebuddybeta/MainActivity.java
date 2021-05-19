package com.example.moviebuddybeta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayMov(View view) {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }

    public void registerMov(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void editMov(View view) {
        Intent intent = new Intent(this, MainActivity5.class);
        startActivity(intent);
    }

    public void searchMov(View view) {
        Intent intent = new Intent(this, MainActivity6.class);
        startActivity(intent);
    }

    public void favoritesMov(View view) {
        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);
    }

    public void ratingsMov(View view) {
        Intent intent = new Intent(this, MainActivity7.class);
        startActivity(intent);
    }
}