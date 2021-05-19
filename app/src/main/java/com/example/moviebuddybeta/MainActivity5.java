package com.example.moviebuddybeta;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity5 extends AppCompatActivity {
    private ListView editMovList;
    private ArrayList<String> movieList = new ArrayList<>();
    private ArrayList<String> dataList = new ArrayList<String>();
    private MovieDatabaseHelper myDB = new MovieDatabaseHelper(this);

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        editMovList = findViewById(R.id.EditMoviesList);

        movieList();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void movieList() {
        movieList = getMoviesFromDatabase();
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movieList);
        editMovList.setAdapter(listAdapter);

        editMovList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Redirecting to another new page
                Intent intent = new Intent(MainActivity5.this, EditMoviePage.class);
                intent.putExtra("selectedMovie", movieList.get(position));
                startActivity(intent);
            }
        });
    }

    public ArrayList<String> getMoviesFromDatabase() {
        Cursor cursor = myDB.viewMovies();

        while (!cursor.isAfterLast()) {
            dataList.add(cursor.getString(cursor.getColumnIndex("MovieName")));
            cursor.moveToNext();
        }

        dataList.sort(Comparator.comparing(String::toString));
        return dataList;

    }
}