package com.example.moviebuddybeta;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity7 extends AppCompatActivity {
    private ListView movieResults;
    private ArrayList<String> movies = new ArrayList<>();
    private ArrayList<String> dataList = new ArrayList<>();
    private MovieDatabaseHelper movieDatabaseHelper = new MovieDatabaseHelper(this);
    private Button findMovieBtn;
    private int selectedItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        movieResults = findViewById(R.id.listOfMovies);
        findMovieBtn = findViewById(R.id.findMovieBtn);

        findMovie();

    }

    private void findMovie() {
        movies = getMoviesFromDatabase();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movies);
        movieResults.setAdapter(adapter);

        movieResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItemPosition = position;
            }
        });

        findMovieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getMovieFromApi();
            }
        });
    }

    public ArrayList<String> getMoviesFromDatabase() {
        Cursor cursor = movieDatabaseHelper.viewMovies();

        while (!cursor.isAfterLast()) {
            System.out.println("Movies added : " + cursor.getString(cursor.getColumnIndex("MovieName")));
            dataList.add(cursor.getString(cursor.getColumnIndex("MovieName")));
            cursor.moveToNext();
        }
        dataList.sort(Comparator.comparing(String::toString));
        return dataList;
    }
}