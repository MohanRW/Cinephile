package com.warrier.cinephile.data;

import android.annotation.TargetApi;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Galahad on 17/11/2017.
 */

public class MovieProvider extends ContentProvider {


    private static final int MOVIE = 100;
    private static final int MOVIE_WITH_ID = 101;
    private static final int VIDEOS = 200;
    private static final int VIDEOS_WITH_ID = 201;
    private static final int REVIEWS = 300;
    private static final int REVIEWS_WITH_ID = 301;
    private static final int GENRES = 400;
    private static final int GENRES_WITH_ID = 401;
    private static final int FAVOURITES_MOVIES = 500;
    private static final int FAVOURITES_MOVIES_WITH_ID = 501;
    private static final int SIMILAR_MOVIES = 700;
    private static final int SIMILAR_MOVIES_WITH_ID = 701;
    private static final int CASTS = 800;
    private static final int CASTS_WITH_ID = 801;
    private static final int PEOPLE = 900;
    private static final int PEOPLE_WITH_ID = 901;
    private static final int CREW = 1000;
    private static final int CREW_WITH_ID = 1001;
    private static final int MOVIE_DETAILS = 1100;
    private static final int MOVIE_DETAILS_WITH_ID = 1101;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private MovieDBHelper mOpenHelper;

    private static UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = MovieContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, MovieContract.PATH_MOVIES, MOVIE);
        matcher.addURI(authority, MovieContract.PATH_MOVIES + "/*", MOVIE_WITH_ID);

        matcher.addURI(authority, MovieContract.PATH_VIDEOS, VIDEOS);
        matcher.addURI(authority, MovieContract.PATH_VIDEOS + "/*", VIDEOS_WITH_ID);
        matcher.addURI(authority, MovieContract.PATH_REVIEWS, REVIEWS);
        matcher.addURI(authority, MovieContract.PATH_REVIEWS + "/*", REVIEWS_WITH_ID);
        matcher.addURI(authority, MovieContract.PATH_GENRES, GENRES);
        matcher.addURI(authority, MovieContract.PATH_GENRES + "/*", GENRES_WITH_ID);
        matcher.addURI(authority, MovieContract.PATH_FAVOURITES_MOVIES, FAVOURITES_MOVIES);
        matcher.addURI(authority, MovieContract.PATH_FAVOURITES_MOVIES + "/*", FAVOURITES_MOVIES_WITH_ID);
        matcher.addURI(authority, MovieContract.PATH_CASTS, CASTS);
        matcher.addURI(authority, MovieContract.PATH_CASTS + "/*", CASTS_WITH_ID);
        matcher.addURI(authority, MovieContract.PATH_CREWS, CREW);
        matcher.addURI(authority, MovieContract.PATH_CREWS + "/*", CREW_WITH_ID);
        matcher.addURI(authority, MovieContract.PATH_SIMILAR_MOVIES, SIMILAR_MOVIES);
        matcher.addURI(authority, MovieContract.PATH_SIMILAR_MOVIES + "/*", SIMILAR_MOVIES_WITH_ID);
        matcher.addURI(authority, MovieContract.PATH_MOVIE_DETAILS, MOVIE_DETAILS);
        matcher.addURI(authority, MovieContract.PATH_MOVIE_DETAILS + "/*", MOVIE_DETAILS_WITH_ID);

        matcher.addURI(authority, MovieContract.PATH_PEOPLE, PEOPLE);
        matcher.addURI(authority, MovieContract.PATH_PEOPLE + "/*", PEOPLE_WITH_ID);


        return matcher;

    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new MovieDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case MOVIE: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        MovieContract.Movies.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case MOVIE_WITH_ID: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        MovieContract.Movies.TABLE_NAME,
                        projection,
                        MovieContract.Movies.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder);
                break;
            }
            case FAVOURITES_MOVIES: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        MovieContract.FavouritesMovies.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case FAVOURITES_MOVIES_WITH_ID: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        MovieContract.FavouritesMovies.TABLE_NAME,
                        projection,
                        MovieContract.FavouritesMovies.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder);
                break;
            }

            case VIDEOS: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        MovieContract.Videos.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case VIDEOS_WITH_ID: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        MovieContract.Videos.TABLE_NAME,
                        projection,
                        MovieContract.Videos.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder);
                break;
            }
            case REVIEWS: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        MovieContract.Reviews.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case REVIEWS_WITH_ID: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        MovieContract.Reviews.TABLE_NAME,
                        projection,
                        MovieContract.Reviews.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder);
                break;
            }
            case GENRES: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        MovieContract.Genres.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case GENRES_WITH_ID: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        MovieContract.Genres.TABLE_NAME,
                        projection,
                        MovieContract.Genres.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder);
                break;
            }

            case SIMILAR_MOVIES: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        MovieContract.SimilarMovies.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case SIMILAR_MOVIES_WITH_ID: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        MovieContract.SimilarMovies.TABLE_NAME,
                        projection,
                        MovieContract.SimilarMovies.MOVIE_ID_OLD + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder);
                break;
            }

            case CASTS: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        MovieContract.Cast.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case CASTS_WITH_ID: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        MovieContract.Cast.TABLE_NAME,
                        projection,
                        MovieContract.Cast.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder);
                break;
            }

            case PEOPLE: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        MovieContract.People.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case PEOPLE_WITH_ID: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        MovieContract.People.TABLE_NAME,
                        projection,
                        MovieContract.People.ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder);
                break;
            }

            case CREW: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        MovieContract.Crew.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case CREW_WITH_ID: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        MovieContract.Crew.TABLE_NAME,
                        projection,
                        MovieContract.Crew.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder);
                break;
            }

            case MOVIE_DETAILS: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        MovieContract.MovieDetails.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case MOVIE_DETAILS_WITH_ID: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        MovieContract.MovieDetails.TABLE_NAME,
                        projection,
                        MovieContract.MovieDetails.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder);
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;

    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {

            case MOVIE:
                return MovieContract.Movies.CONTENT_TYPE;
            case MOVIE_WITH_ID:
                return MovieContract.Movies.CONTENT_ITEM_TYPE;
            case FAVOURITES_MOVIES:
                return MovieContract.FavouritesMovies.CONTENT_TYPE;
            case FAVOURITES_MOVIES_WITH_ID:
                return MovieContract.FavouritesMovies.CONTENT_ITEM_TYPE;
            case VIDEOS:
                return MovieContract.Videos.CONTENT_TYPE;
            case VIDEOS_WITH_ID:
                return MovieContract.Videos.CONTENT_ITEM_TYPE;
            case REVIEWS:
                return MovieContract.Reviews.CONTENT_TYPE;
            case REVIEWS_WITH_ID:
                return MovieContract.Reviews.CONTENT_ITEM_TYPE;
            case GENRES:
                return MovieContract.Genres.CONTENT_TYPE;
            case GENRES_WITH_ID:
                return MovieContract.Genres.CONTENT_ITEM_TYPE;
            case CASTS:
                return MovieContract.Cast.CONTENT_TYPE;
            case CASTS_WITH_ID:
                return MovieContract.Cast.CONTENT_ITEM_TYPE;
            case CREW:
                return MovieContract.Crew.CONTENT_TYPE;
            case CREW_WITH_ID:
                return MovieContract.Crew.CONTENT_ITEM_TYPE;
            case SIMILAR_MOVIES:
                return MovieContract.SimilarMovies.CONTENT_TYPE;
            case SIMILAR_MOVIES_WITH_ID:
                return MovieContract.SimilarMovies.CONTENT_ITEM_TYPE;
            case MOVIE_DETAILS:
                return MovieContract.MovieDetails.CONTENT_TYPE;
            case MOVIE_DETAILS_WITH_ID:
                return MovieContract.MovieDetails.CONTENT_ITEM_TYPE;


            case PEOPLE:
                return MovieContract.People.CONTENT_TYPE;
            case PEOPLE_WITH_ID:
                return MovieContract.People.CONTENT_ITEM_TYPE;



            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }


    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case MOVIE: {
                long _id = db.insert(MovieContract.Movies.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = MovieContract.Movies.buildMoviesUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case FAVOURITES_MOVIES: {
                long _id = db.insert(MovieContract.FavouritesMovies.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = MovieContract.FavouritesMovies.buildMoviesUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case VIDEOS: {
                long _id = db.insert(MovieContract.Videos.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = MovieContract.Videos.buildMoviesUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case REVIEWS: {
                long _id = db.insert(MovieContract.Reviews.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = MovieContract.Reviews.buildReviewsUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case GENRES: {
                long _id = db.insert(MovieContract.Genres.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = MovieContract.Genres.buildMoviesUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }

            case SIMILAR_MOVIES: {
                long _id = db.insert(MovieContract.SimilarMovies.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = MovieContract.SimilarMovies.buildSimilarMoviesUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case CASTS: {
                long _id = db.insert(MovieContract.Cast.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = MovieContract.Cast.buildCastUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case CREW: {
                long _id = db.insert(MovieContract.Crew.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = MovieContract.Crew.buildCrewUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case PEOPLE: {
                long _id = db.insert(MovieContract.People.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = MovieContract.People.buildPeopleUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case MOVIE_DETAILS: {
                long _id = db.insert(MovieContract.MovieDetails.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = MovieContract.MovieDetails.buildMovieDetailsUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;

        if (null == selection) selection = "1";
        switch (match) {
            case MOVIE:
                rowsDeleted = db.delete(
                        MovieContract.Movies.TABLE_NAME, selection, selectionArgs);
                break;
            case MOVIE_WITH_ID:
                rowsDeleted = db.delete(MovieContract.Movies.TABLE_NAME,
                        MovieContract.Movies.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;

            case FAVOURITES_MOVIES:
                rowsDeleted = db.delete(
                        MovieContract.FavouritesMovies.TABLE_NAME, selection, selectionArgs);
                break;
            case FAVOURITES_MOVIES_WITH_ID:
                rowsDeleted = db.delete(MovieContract.FavouritesMovies.TABLE_NAME,
                        MovieContract.FavouritesMovies.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case VIDEOS:
                rowsDeleted = db.delete(
                        MovieContract.Videos.TABLE_NAME, selection, selectionArgs);
                break;
            case VIDEOS_WITH_ID:
                rowsDeleted = db.delete(MovieContract.Videos.TABLE_NAME,
                        MovieContract.Videos.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case REVIEWS:
                rowsDeleted = db.delete(
                        MovieContract.Reviews.TABLE_NAME, selection, selectionArgs);
                break;
            case REVIEWS_WITH_ID:
                rowsDeleted = db.delete(MovieContract.Reviews.TABLE_NAME,
                        MovieContract.Reviews.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case GENRES:
                rowsDeleted = db.delete(
                        MovieContract.Genres.TABLE_NAME, selection, selectionArgs);
                break;
            case GENRES_WITH_ID:
                rowsDeleted = db.delete(MovieContract.Genres.TABLE_NAME,
                        MovieContract.Genres.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case MOVIE_DETAILS:
                rowsDeleted = db.delete(
                        MovieContract.MovieDetails.TABLE_NAME, selection, selectionArgs);
                break;
            case MOVIE_DETAILS_WITH_ID:
                rowsDeleted = db.delete(MovieContract.MovieDetails.TABLE_NAME,
                        MovieContract.MovieDetails.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case SIMILAR_MOVIES:
                rowsDeleted = db.delete(
                        MovieContract.SimilarMovies.TABLE_NAME, selection, selectionArgs);
                break;
            case SIMILAR_MOVIES_WITH_ID:
                rowsDeleted = db.delete(MovieContract.SimilarMovies.TABLE_NAME,
                        MovieContract.SimilarMovies.MOVIE_ID_OLD + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case CASTS:
                rowsDeleted = db.delete(
                        MovieContract.Cast.TABLE_NAME, selection, selectionArgs);
                break;
            case CASTS_WITH_ID:
                rowsDeleted = db.delete(MovieContract.Cast.TABLE_NAME,
                        MovieContract.Cast.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case CREW:
                rowsDeleted = db.delete(
                        MovieContract.Crew.TABLE_NAME, selection, selectionArgs);
                break;
            case CREW_WITH_ID:
                rowsDeleted = db.delete(MovieContract.Crew.TABLE_NAME,
                        MovieContract.Crew.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            case PEOPLE:
                rowsDeleted = db.delete(
                        MovieContract.People.TABLE_NAME, selection, selectionArgs);
                break;
            case PEOPLE_WITH_ID:
                rowsDeleted = db.delete(MovieContract.People.TABLE_NAME,
                        MovieContract.People.ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;


            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated;

        switch (match) {
            case MOVIE:
                rowsUpdated = db.update(MovieContract.Movies.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            case MOVIE_WITH_ID: {
                rowsUpdated = db.update(MovieContract.Movies.TABLE_NAME,
                        values,
                        MovieContract.Movies.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            }
            case FAVOURITES_MOVIES:
                rowsUpdated = db.update(MovieContract.FavouritesMovies.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            case FAVOURITES_MOVIES_WITH_ID: {
                rowsUpdated = db.update(MovieContract.FavouritesMovies.TABLE_NAME,
                        values,
                        MovieContract.FavouritesMovies.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            }
            case VIDEOS:
                rowsUpdated = db.update(MovieContract.Videos.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            case VIDEOS_WITH_ID: {
                rowsUpdated = db.update(MovieContract.Videos.TABLE_NAME,
                        values,
                        MovieContract.Videos.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            }
            case REVIEWS:
                rowsUpdated = db.update(MovieContract.Reviews.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            case REVIEWS_WITH_ID: {
                rowsUpdated = db.update(MovieContract.Reviews.TABLE_NAME,
                        values,
                        MovieContract.Reviews.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            }
            case GENRES:
                rowsUpdated = db.update(MovieContract.Genres.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            case GENRES_WITH_ID: {
                rowsUpdated = db.update(MovieContract.Genres.TABLE_NAME,
                        values,
                        MovieContract.Genres.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            }
            case MOVIE_DETAILS:
                rowsUpdated = db.update(MovieContract.MovieDetails.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            case MOVIE_DETAILS_WITH_ID: {
                rowsUpdated = db.update(MovieContract.MovieDetails.TABLE_NAME,
                        values,
                        MovieContract.MovieDetails.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            }
            case CREW:
                rowsUpdated = db.update(MovieContract.Crew.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            case CREW_WITH_ID: {
                rowsUpdated = db.update(MovieContract.Crew.TABLE_NAME,
                        values,
                        MovieContract.Crew.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            }
            case CASTS:
                rowsUpdated = db.update(MovieContract.Cast.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            case CASTS_WITH_ID: {
                rowsUpdated = db.update(MovieContract.Cast.TABLE_NAME,
                        values,
                        MovieContract.Cast.MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            }
            case PEOPLE:
                rowsUpdated = db.update(MovieContract.People.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            case PEOPLE_WITH_ID: {
                rowsUpdated = db.update(MovieContract.People.TABLE_NAME,
                        values,
                        MovieContract.People.ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            }
            case SIMILAR_MOVIES:
                rowsUpdated = db.update(MovieContract.SimilarMovies.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            case SIMILAR_MOVIES_WITH_ID: {
                rowsUpdated = db.update(MovieContract.SimilarMovies.TABLE_NAME,
                        values,
                        MovieContract.SimilarMovies.MOVIE_ID_OLD + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }
    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MOVIE:
                db.beginTransaction();
                int returnCount = 0;
                try {
                    for (ContentValues value : values) {

                        long _id = db.insert(MovieContract.Movies.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;

            case FAVOURITES_MOVIES:
                db.beginTransaction();
                returnCount = 0;
                try {
                    for (ContentValues value : values) {

                        long _id = db.insert(MovieContract.FavouritesMovies.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;
            case VIDEOS:
                db.beginTransaction();
                returnCount = 0;
                try {
                    for (ContentValues value : values) {

                        long _id = db.insert(MovieContract.Videos.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;
            case REVIEWS:
                db.beginTransaction();
                returnCount = 0;
                try {
                    for (ContentValues value : values) {

                        long _id = db.insert(MovieContract.Reviews.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;
            case GENRES:
                db.beginTransaction();
                returnCount = 0;
                try {
                    for (ContentValues value : values) {

                        long _id = db.insert(MovieContract.Genres.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;
            case SIMILAR_MOVIES:
                db.beginTransaction();
                returnCount = 0;
                try {
                    for (ContentValues value : values) {

                        long _id = db.insert(MovieContract.SimilarMovies.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;
            case CASTS:
                db.beginTransaction();
                returnCount = 0;
                try {
                    for (ContentValues value : values) {

                        long _id = db.insert(MovieContract.Cast.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;
            case CREW:
                db.beginTransaction();
                returnCount = 0;
                try {
                    for (ContentValues value : values) {

                        long _id = db.insert(MovieContract.Crew.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;
            case PEOPLE:
                db.beginTransaction();
                returnCount = 0;
                try {
                    for (ContentValues value : values) {

                        long _id = db.insert(MovieContract.People.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;

            default:
                return super.bulkInsert(uri, values);
        }
    }
    @Override
    @TargetApi(11)
    public void shutdown() {
        mOpenHelper.close();
        super.shutdown();
    }

}
