package com.warrier.cinephile.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;



public class MovieContract {


    public static final String CONTENT_AUTHORITY = "com.warrier.cinephile";
    public static final String PATH_MOVIES = "movies";
    public static final String PATH_VIDEOS = "videos";
    public static final String PATH_SIMILAR_MOVIES = "similar_movies";
    public static final String PATH_CASTS = "casts";
    public static final String PATH_CREWS = "crews";
    public static final String PATH_REVIEWS = "reviews";
    public static final String PATH_GENRES = "genres";
    public static final String PATH_MOVIE_DETAILS = "movie_details";
    public static final String PATH_FAVOURITES_MOVIES = "favourites_movies";
    public static final String PATH_PEOPLE = "people";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    public static final class Movies implements BaseColumns {

        public static final String TABLE_NAME = "movies";

        public static final String PAGE = "page";
        public static final String POSTER_PATH = "poster_path";
        public static final String ADULT = "adult";
        public static final String OVERVIEW = "overview";
        public static final String RELEASE_DATE = "release_date";
        public static final String MOVIE_ID = "id";
        public static final String ORIGINAL_TITLE = "original_title";
        public static final String ORIGINAL_LANGUAGE = "original_language";
        public static final String TITLE = "title";
        public static final String BACKDROP_PATH = "backdrop_path";
        public static final String POPULARITY = "popularity";
        public static final String VOTE_COUNT = "vote_count";
        public static final String VOTE_AVERAGE = "vote_average";
        public static final String FAVOURED = "favoured";
        public static final String SHOWED = "shown";
        public static final String DOWNLOADED = "downloaded";
        public static final String MODE = "mode";
        public static final String PREF_LANGUAGE = "pref_lang";
        public static final String PREF_ADULT = "pref_adult";
        public static final String PREF_REGION = "pref_region";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();

        static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;
        static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;

        static Uri buildMoviesUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }


        public static Uri buildMoviesUriWithMovieId(String MovieId) {
            return CONTENT_URI.buildUpon().appendPath(MovieId).build();
        }

        public static Uri buildMovieUri() {
            return CONTENT_URI.buildUpon().build();
        }

        public static String getIDFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }


    }

    public static final class SimilarMovies implements BaseColumns {

        public static final String TABLE_NAME = "similar_movies";
        public static final String MOVIE_ID_OLD = "id_old";
        public static final String PAGE = "page";
        public static final String POSTER_PATH = "poster_path";
        public static final String ADULT = "adult";
        public static final String OVERVIEW = "overview";
        public static final String RELEASE_DATE = "release_date";
        public static final String MOVIE_ID = "id";
        public static final String ORIGINAL_TITLE = "original_title";
        public static final String ORIGINAL_LANGUAGE = "original_language";
        public static final String TITLE = "title";
        public static final String BACKDROP_PATH = "backdrop_path";
        public static final String POPULARITY = "popularity";
        public static final String VOTE_COUNT = "vote_count";
        public static final String VOTE_AVERAGE = "vote_average";
        public static final String FAVOURED = "favoured";
        public static final String SHOWED = "shown";
        public static final String DOWNLOADED = "downloaded";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_SIMILAR_MOVIES).build();

        static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SIMILAR_MOVIES;
        static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SIMILAR_MOVIES;

        static Uri buildSimilarMoviesUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }


        public static Uri buildSimilarMoviesUriWithMovieId(String MovieId) {
            return CONTENT_URI.buildUpon().appendPath(MovieId).build();
        }

        public static Uri buildSimilarMovieUri() {
            return CONTENT_URI.buildUpon().build();
        }

        public static String getIDFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }


    }
    public static final class MovieDetails implements BaseColumns {

        public static final String TABLE_NAME = "movie_details";

        public static final String MOVIE_ID = "id";
        public static final String ADULT = "adult";
        public static final String BACKDROP_PATH = "backdrop_path";
        public static final String BUDGET = "budget";
        public static final String HOMEPAGE = "homepage";
        public static final String ORIGINAL_LANGUAGE = "original_language";
        public static final String ORIGINAL_TITLE = "original_title";
        public static final String OVERVIEW = "overview";
        public static final String POPULARITY = "popularity";
        public static final String POSTER_PATH = "poster_path";
        public static final String RELEASE_DATE = "release_date";
        public static final String REVENUE = "revenue";
        public static final String RUNTIME = "runtime";
        public static final String STATUS = "status";
        public static final String TAGLINE = "tagline";
        public static final String TITLE = "title";
        public static final String VOTE_AVERAGE = "vote_average";
        public static final String VOTE_COUNT = "vote_count";
        public static final String FAVOURED = "favoured";
        public static final String SHOWED = "shown";
        public static final String DOWNLOADED = "downloaded";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE_DETAILS).build();

        static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE_DETAILS;
        static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE_DETAILS;

        static Uri buildMovieDetailsUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }


        public static Uri buildMovieDetailsUriWithMovieId(String MovieId) {
            return CONTENT_URI.buildUpon().appendPath(MovieId).build();
        }

        public static Uri buildMovieDetailsUri() {
            return CONTENT_URI.buildUpon().build();
        }

        public static String getIDFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

    }
    public static final class Cast implements BaseColumns {

        public static final String TABLE_NAME = "cast";

        public static final String CAST_ID = "cast_id";
        public static final String CHARACTER = "character";
        public static final String CREDIT_ID = "credit_id";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String ORDER = "order_cast";
        public static final String PROFILE_PATH = "profile_path";
        public static final String MOVIE_ID = "movie_id";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_CASTS).build();

        static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CASTS;
        static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CASTS;

        static Uri buildCastUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
        public static Uri buildCastUri() {
            return CONTENT_URI.buildUpon().build();
        }

        public static Uri buildCastsUriWithMovieId(String MovieId) {
            return CONTENT_URI.buildUpon().appendPath(MovieId).build();
        }


    }
    public static final class People implements BaseColumns {

        public static final String TABLE_NAME = "people";

        public static final String ADULT = "adult";
        public static final String BIOGRAPHY = "biography";
        public static final String BIRTHDAY = "birthday";
        public static final String DEATHDAY = "deathday";
        public static final String GENDER = "gender";
        public static final String HOMEPAGE = "homepage";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String PLACE_OF_BIRTH = "place_of_birth";
        public static final String POPULARITY = "popularity";
        public static final String PROFILE_PATH = "profile_path";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PEOPLE).build();

        static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PEOPLE;
        static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PEOPLE;

        static Uri buildPeopleUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        //content://work....../people/peopleId
        public static Uri buildPeopleUriWithPeopleId(String peopleId) {
            return CONTENT_URI.buildUpon().appendPath(peopleId).build();
        }

        public static Uri buildPersonUri() {
            return CONTENT_URI.buildUpon().build();
        }

        public static String getIDFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

    }
    public static final class Crew implements BaseColumns {

        public static final String TABLE_NAME = "crew";

        public static final String CREDIT_ID = "credit_id";
        public static final String DEPARTMENT = "department";
        public static final String ID = "id";
        public static final String JOB = "job";
        public static final String NAME = "name";
        public static final String PROFILE_PATH = "profile_path";
        public static final String MOVIE_ID = "movie_id";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_CREWS).build();

        static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CREWS;
        static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CREWS;

        static Uri buildCrewUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildCrewsUriWithMovieId(String MovieId) {
            return CONTENT_URI.buildUpon().appendPath(MovieId).build();
        }

        public static Uri buildCrewUri() {
            return CONTENT_URI.buildUpon().build();
        }

        public static String getIDFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

    }
    public static final class FavouritesMovies implements BaseColumns {

        public static final String TABLE_NAME = "favourites_movies";
        public static final String MOVIE_ID = "id";

        static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVOURITES_MOVIES).build();

        static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FAVOURITES_MOVIES;
        static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FAVOURITES_MOVIES;

        static Uri buildMoviesUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildMoviesUriWithMovieId(String MovieId) {
            return CONTENT_URI.buildUpon().appendPath(MovieId).build();
        }

        public static Uri buildMovieUri() {
            return CONTENT_URI.buildUpon().build();
        }

        public static String getIDFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }

    public static final class Videos implements BaseColumns {

        public static final String TABLE_NAME = "videos";

        public static final String VIDEO_ID = "id";
        public static final String KEY = "key";
        public static final String NAME = "name";
        public static final String SITE = "site";
        public static final String SIZE = "size";
        public static final String TYPE = "type";
        public static final String MOVIE_ID = "movie_id";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_VIDEOS).build();

        static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_VIDEOS;
        static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_VIDEOS;

        static Uri buildMoviesUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }


        public static Uri buildMoviesUriWithMovieId(String MovieId) {
            return CONTENT_URI.buildUpon().appendPath(MovieId).build();
        }

        public static Uri buildVideosUri() {
            return CONTENT_URI.buildUpon().build();
        }

        public static String getIDFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }


    }
    public static final class Reviews implements BaseColumns {

        public static final String TABLE_NAME = "reviews";

        public static final String PAGE = "page";
        public static final String TOTAL_PAGE = "total_page";
        public static final String TOTAL_RESULTS = "total_results";
        public static final String ID_REVIEWS = "id_reviews";
        public static final String AUTHOR = "author";
        public static final String CONTENT = "content";
        public static final String URL = "url";
        public static final String MOVIE_ID = "movie_id";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_REVIEWS).build();

        static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_REVIEWS;
        static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_REVIEWS;

        static Uri buildReviewsUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }


        public static Uri buildReviewsUriWithMovieId(String MovieId) {
            return CONTENT_URI.buildUpon().appendPath(MovieId).build();
        }

        public static Uri buildReviewUri() {
            return CONTENT_URI.buildUpon().build();
        }

        public static String getIDFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }


    }

    public static final class Genres implements BaseColumns {

        public static final String TABLE_NAME = "genres";

        public static final String ID_GENRES = "id_genres";
        public static final String NAME = "name";
        public static final String MOVIE_ID = "movie_id";


        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_GENRES).build();

        static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GENRES;
        static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GENRES;

        static Uri buildMoviesUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }


        public static Uri buildGenresUriWithMovieId(String MovieId) {
            return CONTENT_URI.buildUpon().appendPath(MovieId).build();
        }

        public static Uri buildGenreUri() {
            return CONTENT_URI.buildUpon().build();
        }

        public static String getIDFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

    }

}
