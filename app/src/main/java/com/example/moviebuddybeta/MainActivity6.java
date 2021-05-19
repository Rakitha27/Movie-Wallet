package com.example.moviebuddybeta;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity6 extends AppCompatActivity {
    private ListView searchResults;
    private EditText searchField;
    private Button lookUpBtn;
    private ArrayList<String> movies = new ArrayList<>();
    private MovieDatabaseHelper movieDatabaseHelper = new MovieDatabaseHelper(this);

    public static final String LOG_TAG = MainActivity6.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        searchField = findViewById(R.id.searchField);
        searchResults = findViewById(R.id.movieList);
        lookUpBtn = findViewById(R.id.lookUpBtn);

        onTextChange();
    }

    private void onTextChange() {
        lookUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayResults(searchField.getText().toString());
            }
        });

    }

    private void displayResults(String search) {
        Cursor cursor = movieDatabaseHelper.getMovieBySearchQuery(search);
        Log.d(LOG_TAG, "displayResults: " + cursor.getCount());

        while (cursor.moveToNext()) {
            movies.add(cursor.getString(0));
            cursor.moveToNext();
        }

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movies);
        searchResults.setAdapter(listAdapter);
    }

//    private void closeKeyboard() {
//        View view = this.getCurrentFocus();
//        if (view != null) {
//            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
//    }
}