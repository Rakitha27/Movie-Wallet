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

public class MainActivity4 extends AppCompatActivity {
    private ListView movieList;
    private ArrayList<String> movies = new ArrayList<String>();
    private MovieDatabaseHelper movieDatabaseHelper = new MovieDatabaseHelper(this);
    private ArrayList<String> dataList = new ArrayList<String>();
    private ArrayList<Integer> dataPosition = new ArrayList<Integer>();
    private ArrayList<Integer> newPosition = new ArrayList<Integer>();

    private Button addToFavBtn;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        movieList = findViewById(R.id.EditMoviesList);
        addToFavBtn = findViewById(R.id.FavSaveBtn);

        showMovieList();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showMovieList() {
        movies = getMoviesFromDatabase();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, movies);
        movieList.setAdapter(adapter);

        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dataPosition.add(position);

            }
        });

        addToFavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(newPosition + " : refresh");
                System.out.println(dataPosition +" : save");
                for (int position : dataPosition) {
                    if(movieList.isItemChecked(position)){
                        movieDatabaseHelper.addToFavourites(movies.get(position), true);
                    }else{
                        movieDatabaseHelper.addToFavourites(movies.get(position), false);
                    }
                }
            }
        });

        Cursor cursor = movieDatabaseHelper.getFavourites();
        while (!cursor.isAfterLast()) {
            movieList.setItemChecked(movies.indexOf(cursor.getString(cursor.getColumnIndex("MovieName"))), true);
            cursor.moveToNext();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<String> getMoviesFromDatabase() {
        Cursor cursor = movieDatabaseHelper.getFavourites();

        while (!cursor.isAfterLast()) {
            dataList.add(cursor.getString(cursor.getColumnIndex("MovieName")));
            cursor.moveToNext();
        }
        dataList.sort(Comparator.comparing(String::toString));
        return dataList;
    }
}