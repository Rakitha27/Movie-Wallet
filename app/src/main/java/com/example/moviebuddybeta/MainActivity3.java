package com.example.moviebuddybeta;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity3 extends AppCompatActivity {
    private ListView movieList;
    private ArrayList<String> movies = new ArrayList<String>();
    private MovieDatabaseHelper movieDatabaseHelper = new MovieDatabaseHelper(this);
    private ArrayList<String> dataList = new ArrayList<String>();
    private ArrayList<Integer> dataPosition = new ArrayList<Integer>();
    private ArrayList<Integer> newPosition = new ArrayList<Integer>();
    private Button addFavourite;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        movieList = findViewById(R.id.listOfMovies);
        addFavourite = findViewById(R.id.addToFavBtn);

        showMovies();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showMovies() {
        movies = retrieveMovies();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, movies);
        movieList.setAdapter(adapter);

        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(dataPosition.contains(position)){
                    newPosition.add(position);
                }else{
                dataPosition.add(position);
                }
            }
        });

        addFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int position : dataPosition) {
                    movieDatabaseHelper.addToFavourites(movies.get(position), true);
                    }
                for (int position : newPosition){
                    movieDatabaseHelper.addToFavourites(movies.get(position), false);
                    }
                }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<String> retrieveMovies() {
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