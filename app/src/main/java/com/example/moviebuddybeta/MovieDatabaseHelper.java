package com.example.moviebuddybeta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDatabaseHelper extends SQLiteOpenHelper {
    private static final String DataBaseName = "MovieDatabase.db";
    private static final int version = 2;
    private static final String COL_1 = "Movies";
    private static final String COL_2 = "MovieName";
    private static final String COL_3 = "MovieYear";
    private static final String COL_4 = "MovieDirector";
    private static final String COL_5 = "MovieCast";
    private static final String COL_6 = "MovieRating";
    private static final String COL_7 = "MovieReview";
    private static final String COL_8 = "isFavourite";


    public MovieDatabaseHelper(Context context) {
        super(context, DataBaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + COL_1 + " (" + COL_2 + " VARCHAR(255)," + COL_3 + " VARCHAR(255)," + COL_4 + " VARCHAR(255)," + COL_5 + " VARCHAR(255)," + COL_6 + " VARCHAR(255),"
                + COL_7 + " VARCHAR(255)," + COL_8 + " BOOLEAN DEFAULT 'false'" + ");");
        System.out.println("Database created Successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + COL_1);
        onCreate(db);
    }

    public Cursor viewMovies() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] FROM = {COL_2, COL_3, COL_4, COL_5, COL_6, COL_7, COL_8};

        Cursor cursor = db.query(COL_1, FROM, null, null, null, null, null);
        cursor.moveToNext();
        return cursor;
    }

    public void insertMovie(String movieTitle, String movieYear, String movieDirector, String movieReview, String movieCast, String movieRating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MovieDatabaseHelper.COL_2, movieTitle);
        values.put(MovieDatabaseHelper.COL_3, movieYear);
        values.put(MovieDatabaseHelper.COL_4, movieDirector);
        values.put(MovieDatabaseHelper.COL_7, movieReview);
        values.put(MovieDatabaseHelper.COL_5, movieCast);
        values.put(MovieDatabaseHelper.COL_6, movieRating);
        db.insert(MovieDatabaseHelper.COL_1, null, values);
    }

    public void updateMovie(String movieTitle, String movieYear, String movieDirector, String movieReview, String movieCast, float movieRating, String selectedMovie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MovieDatabaseHelper.COL_2, movieTitle);
        values.put(MovieDatabaseHelper.COL_3, movieYear);
        values.put(MovieDatabaseHelper.COL_4, movieDirector);
        values.put(MovieDatabaseHelper.COL_7, movieReview);
        values.put(MovieDatabaseHelper.COL_5, movieCast);
        values.put(MovieDatabaseHelper.COL_6, movieRating);
        db.update(MovieDatabaseHelper.COL_1, values, "MovieName = " + "'" + selectedMovie + "'", null);
    }

    public void addToFavourites(String movieName, Boolean isFavourite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MovieDatabaseHelper.COL_8, isFavourite);
        db.update(MovieDatabaseHelper.COL_1, values, "MovieName = " + "'" + movieName + "'", null);
    }

    public Cursor getFavourites() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] FROM = {COL_2, COL_8};

        Cursor cursor = db.query(COL_1, FROM, "isFavourite = 1", null, null, null, null);
        cursor.moveToNext();
        return cursor;
    }

    public Cursor getSelectedMovie(String selectedMovie) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] FROM = {COL_2, COL_3, COL_4, COL_5, COL_6, COL_7};

        Cursor cursor = db.query(COL_1, FROM, "MovieName = " + "'" + selectedMovie + "'", null, null, null, null);
        cursor.moveToNext();
        return cursor;
    }

    public Cursor getMovieBySearchQuery(String searchText) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * " +
                "FROM " + COL_1 +
                " WHERE " + COL_2 + " LIKE " + "'%" + searchText + "%'" + " OR " +
                COL_4 + " LIKE " + "'%" + searchText + "%'" +
                " OR " + COL_5 + " LIKE " + "'%" + searchText + "%'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
}