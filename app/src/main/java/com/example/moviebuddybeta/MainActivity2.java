package com.example.moviebuddybeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {
    EditText title, year, director, act, rating, review;
    Button saveData;

    private MovieDatabaseHelper movieDatabaseHelper = new MovieDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        title = (EditText) findViewById(R.id.titleAnswer);
        year = (EditText) findViewById(R.id.yearAnswer);
        director = (EditText) findViewById(R.id.directorAnswer);
        act = (EditText) findViewById(R.id.actorAnswer);
        rating = (EditText) findViewById(R.id.ratingAnswer);
        review = (EditText) findViewById(R.id.reviewAnswer);
        saveData = (Button) findViewById(R.id.button);

        registerMovie();
    }

    public void registerMovie() {
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieDatabaseHelper.insertMovie(title.getText().toString(), year.getText().toString(), director.getText().toString(),
                        review.getText().toString(), act.getText().toString(), rating.getText().toString());

                title.setText("");
                year.setText("");
                director.setText("");
                review.setText("");
                act.setText("");
                rating.setText("");
            }
        });
    }
}