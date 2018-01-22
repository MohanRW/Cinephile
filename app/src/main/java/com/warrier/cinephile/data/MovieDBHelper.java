package com.warrier.cinephile.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.warrier.cinephile.data.MovieContract.Cast;
import com.warrier.cinephile.data.MovieContract.Crew;
import com.warrier.cinephile.data.MovieContract.FavouritesMovies;
import com.warrier.cinephile.data.MovieContract.Genres;
import com.warrier.cinephile.data.MovieContract.MovieDetails;
import com.warrier.cinephile.data.MovieContract.Movies;
import com.warrier.cinephile.data.MovieContract.Videos;
import com.warrier.cinephile.data.MovieContract.SimilarMovies;
import com.warrier.cinephile.data.MovieContract.Reviews;
import com.warrier.cinephile.data.MovieContract.People;

/**
 * Created by Galahad on 17/11/2017.
 */

public class MovieDBHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "movie.db";
    private static final int DATABASE_VERSION = 2;

    public MovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE__TABLE = "CREATE TABLE " + Movies.TABLE_NAME + " (" +
                Movies._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Movies.PAGE + "  TEXT," +
                Movies.POSTER_PATH + "  TEXT," +
                Movies.ADULT + "  TEXT," +
                Movies.OVERVIEW + "  TEXT," +
                Movies.RELEASE_DATE + "  TEXT," +
                Movies.MOVIE_ID + "  TEXT," +
                Movies.ORIGINAL_TITLE + "  TEXT," +
                Movies.ORIGINAL_LANGUAGE + "  TEXT," +
                Movies.TITLE + "  TEXT," +
                Movies.BACKDROP_PATH + "  TEXT," +
                Movies.POPULARITY + "  TEXT," +
                Movies.VOTE_COUNT + "  TEXT," +
                Movies.VOTE_AVERAGE + "  TEXT," +
                Movies.FAVOURED + " INTEGER NOT NULL DEFAULT 0," +
                Movies.SHOWED + " INTEGER NOT NULL DEFAULT 0," +
                Movies.DOWNLOADED + " INTEGER NOT NULL DEFAULT 0," +
                Movies.MODE + " TEXT," +
                Movies.PREF_LANGUAGE + " TEXT," +
                Movies.PREF_ADULT + " TEXT," +
                Movies.PREF_REGION + " TEXT,"
                + "UNIQUE (" + Movies.MOVIE_ID + ") ON CONFLICT REPLACE)";

        final String SQL_CREATE__TABLE_SIMILAR_MOVIES = "CREATE TABLE " + SimilarMovies.TABLE_NAME + " (" +
                SimilarMovies._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                SimilarMovies.MOVIE_ID_OLD + "  TEXT," +
                SimilarMovies.PAGE + "  TEXT," +
                SimilarMovies.POSTER_PATH + "  TEXT," +
                SimilarMovies.ADULT + "  TEXT," +
                SimilarMovies.OVERVIEW + "  TEXT," +
                SimilarMovies.RELEASE_DATE + "  TEXT," +
                SimilarMovies.MOVIE_ID + "  TEXT," +
                SimilarMovies.ORIGINAL_TITLE + "  TEXT," +
                SimilarMovies.ORIGINAL_LANGUAGE + "  TEXT," +
                SimilarMovies.TITLE + "  TEXT," +
                SimilarMovies.BACKDROP_PATH + "  TEXT," +
                SimilarMovies.POPULARITY + "  TEXT," +
                SimilarMovies.VOTE_COUNT + "  TEXT," +
                SimilarMovies.VOTE_AVERAGE + "  TEXT," +
                SimilarMovies.FAVOURED + " INTEGER NOT NULL DEFAULT 0," +
                SimilarMovies.SHOWED + " INTEGER NOT NULL DEFAULT 0," +
                SimilarMovies.DOWNLOADED + " INTEGER NOT NULL DEFAULT 0,"
                + "UNIQUE (" + SimilarMovies.MOVIE_ID_OLD + ") ON CONFLICT REPLACE)";

        final String SQL_CREATE__TABLE_MOVIE_DETAILS = "CREATE TABLE " + MovieDetails.TABLE_NAME + " (" +
                MovieDetails._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MovieDetails.MOVIE_ID + "  TEXT," +
                MovieDetails.ADULT + "  TEXT," +
                MovieDetails.BACKDROP_PATH + "  TEXT," +
                MovieDetails.BUDGET + "  TEXT," +
                MovieDetails.HOMEPAGE + "  TEXT," +
                MovieDetails.ORIGINAL_LANGUAGE + "  TEXT," +
                MovieDetails.ORIGINAL_TITLE + "  TEXT," +
                MovieDetails.OVERVIEW + "  TEXT," +
                MovieDetails.POPULARITY + "  TEXT," +
                MovieDetails.POSTER_PATH + "  TEXT," +
                MovieDetails.RELEASE_DATE + "  TEXT," +
                MovieDetails.REVENUE + "  TEXT," +
                MovieDetails.RUNTIME + "  TEXT," +
                MovieDetails.STATUS + "  TEXT," +
                MovieDetails.TAGLINE + "  TEXT," +
                MovieDetails.TITLE + "  TEXT," +
                MovieDetails.VOTE_AVERAGE + "  TEXT," +
                MovieDetails.VOTE_COUNT + "  TEXT," +
                MovieDetails.FAVOURED + " INTEGER NOT NULL DEFAULT 0," +
                MovieDetails.SHOWED + " INTEGER NOT NULL DEFAULT 0," +
                MovieDetails.DOWNLOADED + " INTEGER NOT NULL DEFAULT 0,"
                + "UNIQUE (" + MovieDetails.MOVIE_ID + ") ON CONFLICT REPLACE)";

        final String SQL_CREATE__TABLE_CAST = "CREATE TABLE " + Cast.TABLE_NAME + " (" +
                Cast._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Cast.CAST_ID + "  TEXT," +
                Cast.CHARACTER + "  TEXT," +
                Cast.CREDIT_ID + "  TEXT," +
                Cast.ID + "  TEXT," +
                Cast.NAME + "  TEXT," +
                Cast.ORDER + "  TEXT," +
                Cast.PROFILE_PATH + "  TEXT," +
                Cast.MOVIE_ID + "  TEXT,"
                + "UNIQUE (" + Cast.MOVIE_ID + " , " + Cast.CAST_ID + ") ON CONFLICT REPLACE)";

        final String SQL_CREATE__TABLE_PEOPLE = "CREATE TABLE " + People.TABLE_NAME + " (" +
                People._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                People.ADULT + "  TEXT," +
                People.BIOGRAPHY + "  TEXT," +
                People.BIRTHDAY + "  TEXT," +
                People.DEATHDAY + "  TEXT," +
                People.GENDER + "  TEXT," +
                People.HOMEPAGE + "  TEXT," +
                People.ID + "  TEXT," +
                People.NAME + "  TEXT," +
                People.PLACE_OF_BIRTH + "  TEXT," +
                People.POPULARITY + "  TEXT," +
                People.PROFILE_PATH + "  TEXT,"
                + "UNIQUE (" + People.ID + ") ON CONFLICT REPLACE)";

        final String SQL_CREATE__TABLE_CREW = "CREATE TABLE " + Crew.TABLE_NAME + " (" +
                Crew._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Crew.CREDIT_ID + "  TEXT," +
                Crew.DEPARTMENT + "  TEXT," +
                Crew.ID + "  TEXT," +
                Crew.JOB + "  TEXT," +
                Crew.NAME + "  TEXT," +
                Crew.PROFILE_PATH + "  TEXT," +
                Crew.MOVIE_ID + "  TEXT,"
                + "UNIQUE (" + Crew.CREDIT_ID + " , " + Crew.MOVIE_ID + ") ON CONFLICT REPLACE)";

        final String SQL_CREATE__TABLE_FAVOURITES_MOVIES = "CREATE TABLE " + FavouritesMovies.TABLE_NAME + " (" +
                FavouritesMovies._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FavouritesMovies.MOVIE_ID + "  TEXT,"
                + "UNIQUE (" + FavouritesMovies.MOVIE_ID + ") ON CONFLICT REPLACE)";

        final String SQL_CREATE__TABLE_VIDEOS = "CREATE TABLE " + Videos.TABLE_NAME + " (" +
                Videos._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Videos.VIDEO_ID + "  TEXT," +
                Videos.KEY + "  TEXT," +
                Videos.NAME + "  TEXT," +
                Videos.SITE + "  TEXT," +
                Videos.SIZE + "  TEXT," +
                Videos.TYPE + "  TEXT," +
                Videos.MOVIE_ID + "  TEXT ," +
                "UNIQUE (" + Videos.MOVIE_ID + " , " + Videos.VIDEO_ID + ") ON CONFLICT REPLACE)";

        final String SQL_CREATE__TABLE_REVIEWS = "CREATE TABLE " + Reviews.TABLE_NAME + " (" +
                Reviews._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Reviews.PAGE + "  TEXT," +
                Reviews.TOTAL_PAGE + "  TEXT," +
                Reviews.TOTAL_RESULTS + "  TEXT," +
                Reviews.ID_REVIEWS + "  TEXT," +
                Reviews.AUTHOR + "  TEXT ," +
                Reviews.CONTENT + "  TEXT," +
                Reviews.URL + "  TEXT," +
                Reviews.MOVIE_ID + "  TEXT )";

        final String SQL_CREATE__TABLE_GENRES = "CREATE TABLE " + Genres.TABLE_NAME + " (" +
                Genres._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Genres.NAME + "  TEXT," +
                Genres.ID_GENRES + "  TEXT," +
                Genres.MOVIE_ID + "  TEXT ," +
                "UNIQUE (" + Genres.MOVIE_ID + " , " + Genres.ID_GENRES + ") ON CONFLICT REPLACE)";

        sqLiteDatabase.execSQL(SQL_CREATE__TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE__TABLE_FAVOURITES_MOVIES);
        sqLiteDatabase.execSQL(SQL_CREATE__TABLE_VIDEOS);
        sqLiteDatabase.execSQL(SQL_CREATE__TABLE_REVIEWS);
        sqLiteDatabase.execSQL(SQL_CREATE__TABLE_GENRES);
        sqLiteDatabase.execSQL(SQL_CREATE__TABLE_MOVIE_DETAILS);
        sqLiteDatabase.execSQL(SQL_CREATE__TABLE_CAST);
        sqLiteDatabase.execSQL(SQL_CREATE__TABLE_CREW);
        sqLiteDatabase.execSQL(SQL_CREATE__TABLE_SIMILAR_MOVIES);

        sqLiteDatabase.execSQL(SQL_CREATE__TABLE_PEOPLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Movies.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FavouritesMovies.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Videos.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Reviews.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Genres.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieDetails.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Cast.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Crew.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SimilarMovies.TABLE_NAME);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + People.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}
