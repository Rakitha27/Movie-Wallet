package com.example.moviebuddybeta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class EditMoviePage extends AppCompatActivity {
    private EditText editMovYear;
    private EditText editMovDirector;
    private EditText editMovActors;
    private EditText editMovieTitle;
    private EditText editMovieReview;
    private RatingBar movieRating;
    private Button updateBtn;
    private String selectedMovie;

    private MovieDatabaseHelper myDB = new MovieDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie_page);
        editMovYear = findViewById(R.id.updateYear);
        editMovDirector = findViewById(R.id.updateDirector);
        editMovActors = findViewById(R.id.updateActors);
        editMovieTitle = findViewById(R.id.updateEdit);
        editMovieReview = findViewById(R.id.updateReview);
        movieRating = findViewById(R.id.movieRating);
        updateBtn = findViewById(R.id.updateBtn);

        Intent intent = getIntent();
        selectedMovie = intent.getStringExtra("selectedMovie");

        displaySelectedMovieData();
        updateSelectedMovie();
    }

    //Displaying the data of selected movie
    private void displaySelectedMovieData() {
        Cursor cursor = myDB.getSelectedMovie(selectedMovie);
        editMovieTitle.setText(cursor.getString(0));
        editMovYear.setText(cursor.getString(1));
        editMovDirector.setText(cursor.getString(2));
        editMovActors.setText(cursor.getString(3));
        movieRating.setRating(cursor.getInt(4));
        editMovieReview.setText(cursor.getString(5));
    }

    private void updateSelectedMovie() {
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.updateMovie(editMovieTitle.getText().toString(), editMovYear.getText().toString(), editMovDirector.getText().toString(),
                        editMovieReview.getText().toString(), editMovActors.getText().toString(), movieRating.getRating(), selectedMovie);
            }
        });
    }
}