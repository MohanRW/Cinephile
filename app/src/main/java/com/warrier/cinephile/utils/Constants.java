package com.warrier.cinephile.utils;

import com.warrier.cinephile.data.MovieContract;

/**
 * Created by Galahad on 17/11/2017.
 */

public class Constants {


    public static final String[] MOVIE_COLUMNS = {

            MovieContract.Movies.TABLE_NAME + "." + MovieContract.Movies._ID,
            MovieContract.Movies.PAGE,
            MovieContract.Movies.POSTER_PATH,
            MovieContract.Movies.ADULT,
            MovieContract.Movies.OVERVIEW,
            MovieContract.Movies.RELEASE_DATE,
            MovieContract.Movies.MOVIE_ID,
            MovieContract.Movies.ORIGINAL_TITLE,
            MovieContract.Movies.ORIGINAL_LANGUAGE,
            MovieContract.Movies.TITLE,
            MovieContract.Movies.BACKDROP_PATH,
            MovieContract.Movies.POPULARITY,
            MovieContract.Movies.VOTE_COUNT,
            MovieContract.Movies.VOTE_AVERAGE,
            MovieContract.Movies.FAVOURED,
            MovieContract.Movies.SHOWED,
            MovieContract.Movies.DOWNLOADED,
            MovieContract.Movies.MODE
    };
    public static final String[] MOVIE_COLUMNS_MIN = {
            MovieContract.Movies.TABLE_NAME + "." + MovieContract.Movies._ID
    };


    public static final String[] MOVIE_DETAILS_COLUMNS_MIN = {
            MovieContract.MovieDetails.TABLE_NAME + "." + MovieContract.MovieDetails._ID,
            MovieContract.MovieDetails.MOVIE_ID
    };
    public static final String[] PEOPLE_DETAILS_COLUMNS_MIN = {
            MovieContract.People.TABLE_NAME + "." + MovieContract.People._ID,
            MovieContract.People.ID
    };
    public static final String[] MOVIE_DETAILS_COLUMNS = {

            MovieContract.MovieDetails.TABLE_NAME + "." + MovieContract.MovieDetails._ID,
            MovieContract.MovieDetails.MOVIE_ID,
            MovieContract.MovieDetails.ADULT,
            MovieContract.MovieDetails.BACKDROP_PATH,
            MovieContract.MovieDetails.BUDGET,
            MovieContract.MovieDetails.HOMEPAGE,
            MovieContract.MovieDetails.ORIGINAL_LANGUAGE,
            MovieContract.MovieDetails.ORIGINAL_TITLE,
            MovieContract.MovieDetails.OVERVIEW,
            MovieContract.MovieDetails.POPULARITY,
            MovieContract.MovieDetails.POSTER_PATH,
            MovieContract.MovieDetails.RELEASE_DATE,
            MovieContract.MovieDetails.REVENUE,
            MovieContract.MovieDetails.RUNTIME,
            MovieContract.MovieDetails.STATUS,
            MovieContract.MovieDetails.TAGLINE,
            MovieContract.MovieDetails.TITLE,
            MovieContract.MovieDetails.VOTE_AVERAGE,
            MovieContract.MovieDetails.VOTE_COUNT,
            MovieContract.MovieDetails.FAVOURED,
            MovieContract.MovieDetails.SHOWED,
            MovieContract.MovieDetails.DOWNLOADED
    };
    public static final String[] CREW_COLUMNS = {

            MovieContract.Crew.TABLE_NAME + "." + MovieContract.Crew._ID,
            MovieContract.Crew.CREDIT_ID,
            MovieContract.Crew.DEPARTMENT,
            MovieContract.Crew.ID,
            MovieContract.Crew.JOB,
            MovieContract.Crew.NAME,
            MovieContract.Crew.PROFILE_PATH,
            MovieContract.Crew.MOVIE_ID
    };
    public static final String[] CAST_COLUMNS = {

            MovieContract.Cast._ID,
            MovieContract.Cast.CAST_ID,
            MovieContract.Cast.CHARACTER,
            MovieContract.Cast.CREDIT_ID,
            MovieContract.Cast.ID,
            MovieContract.Cast.NAME,
            MovieContract.Cast.ORDER,
            MovieContract.Cast.PROFILE_PATH,
            MovieContract.Cast.MOVIE_ID
    };
    public static final String[] SIMILAR_MOVIE_COLUMNS = {

            MovieContract.SimilarMovies.TABLE_NAME + "." + MovieContract.SimilarMovies._ID,
            MovieContract.SimilarMovies.MOVIE_ID_OLD,
            MovieContract.SimilarMovies.PAGE,
            MovieContract.SimilarMovies.POSTER_PATH,
            MovieContract.SimilarMovies.ADULT,
            MovieContract.SimilarMovies.OVERVIEW,
            MovieContract.SimilarMovies.RELEASE_DATE,
            MovieContract.SimilarMovies.MOVIE_ID,
            MovieContract.SimilarMovies.ORIGINAL_TITLE,
            MovieContract.SimilarMovies.ORIGINAL_LANGUAGE,
            MovieContract.SimilarMovies.TITLE,
            MovieContract.SimilarMovies.BACKDROP_PATH,
            MovieContract.SimilarMovies.POPULARITY,
            MovieContract.SimilarMovies.VOTE_COUNT,
            MovieContract.SimilarMovies.VOTE_AVERAGE,
            MovieContract.SimilarMovies.FAVOURED,
            MovieContract.SimilarMovies.SHOWED,
            MovieContract.SimilarMovies.DOWNLOADED,
    };
    public static final String[] VIDEO_COLUMNS = {

            MovieContract.Videos.TABLE_NAME + "." + MovieContract.Videos._ID,
            MovieContract.Videos.TABLE_NAME + "." + MovieContract.Videos.VIDEO_ID,
            MovieContract.Videos.TABLE_NAME + "." + MovieContract.Videos.KEY,
            MovieContract.Videos.TABLE_NAME + "." + MovieContract.Videos.NAME,
            MovieContract.Videos.TABLE_NAME + "." + MovieContract.Videos.SITE,
            MovieContract.Videos.TABLE_NAME + "." + MovieContract.Videos.SIZE,
            MovieContract.Videos.TABLE_NAME + "." + MovieContract.Videos.TYPE,
            MovieContract.Videos.TABLE_NAME + "." + MovieContract.Videos.MOVIE_ID,
    };
    public static final String[] REVIEW_COLUMNS = {

            MovieContract.Reviews.TABLE_NAME + "." + MovieContract.Reviews._ID,
            MovieContract.Reviews.TABLE_NAME + "." + MovieContract.Reviews.PAGE,
            MovieContract.Reviews.TABLE_NAME + "." + MovieContract.Reviews.TOTAL_PAGE,
            MovieContract.Reviews.TABLE_NAME + "." + MovieContract.Reviews.TOTAL_RESULTS,
            MovieContract.Reviews.TABLE_NAME + "." + MovieContract.Reviews.ID_REVIEWS,
            MovieContract.Reviews.TABLE_NAME + "." + MovieContract.Reviews.AUTHOR,
            MovieContract.Reviews.TABLE_NAME + "." + MovieContract.Reviews.CONTENT,
            MovieContract.Reviews.TABLE_NAME + "." + MovieContract.Reviews.URL,
            MovieContract.Reviews.TABLE_NAME + "." + MovieContract.Reviews.MOVIE_ID

    };
    public static final String[] GENRE_COLUMNS = {

            MovieContract.Genres.TABLE_NAME + "." + MovieContract.Genres._ID,
            MovieContract.Genres.TABLE_NAME + "." + MovieContract.Genres.NAME,
            MovieContract.Genres.TABLE_NAME + "." + MovieContract.Genres.ID_GENRES,
            MovieContract.Genres.TABLE_NAME + "." + MovieContract.Genres.MOVIE_ID
    };


    public static final String[] PEOPLE_COLUMNS = {

            MovieContract.People.TABLE_NAME + "." + MovieContract.People._ID,
            MovieContract.People.ADULT,
            MovieContract.People.BIOGRAPHY,
            MovieContract.People.BIRTHDAY,
            MovieContract.People.DEATHDAY,
            MovieContract.People.GENDER,
            MovieContract.People.HOMEPAGE,
            MovieContract.People.ID,
            MovieContract.People.NAME,
            MovieContract.People.PLACE_OF_BIRTH,
            MovieContract.People.POPULARITY,
            MovieContract.People.PROFILE_PATH
    };
    public static final String[] FAVOURITE_MOVIE_COLUMNS = {

            MovieContract.FavouritesMovies.TABLE_NAME + "." + MovieContract.FavouritesMovies._ID,
            MovieContract.FavouritesMovies.MOVIE_ID
    };

    public static final int PEOPLE_COL_BIOGRAPHY = 2;
    public static final int PEOPLE_COL_BIRTHDAY = 3;
    public static final int PEOPLE_COL_HOMEPAGE = 6;
    public static final int PEOPLE_COL_NAME = 8;
    public static final int PEOPLE_COL_PLACE_OF_BIRTH = 9;
    public static final int PEOPLE_COL_PROFILE_PATH = 11;
    public static final int MOV_DETAILS_COL_MOVIE_ID = 1;
    public static final int MOV_DETAILS_COL_BUDGET = 4;
    public static final int MOV_DETAILS_COL_HOMEPAGE = 5;
    public static final int MOV_DETAILS_COL_ORIGINAL_LANGUAGE = 6;
    public static final int MOV_DETAILS_COL_ORIGINAL_TITLE = 7;
    public static final int MOV_DETAILS_COL_OVERVIEW = 8;
    public static final int MOV_DETAILS_COL_POPULARITY = 9;
    public static final int MOV_DETAILS_COL_POSTER_PATH = 10;
    public static final int MOV_DETAILS_COL_RELEASE_DATE = 11;
    public static final int MOV_DETAILS_COL_REVENUE = 12;
    public static final int MOV_DETAILS_COL_RUNTIME = 13;
    public static final int MOV_DETAILS_COL_STATUS = 14;
    public static final int MOV_DETAILS_COL_VOTE_AVERAGE = 17;
    public static final int CREW_COL_ID = 3;
    public static final int CREW_COL_JOB = 4;
    public static final int CREW_COL_NAME = 5;
    public static final int CAST_COL_CHARACTER = 2;
    public static final int CAST_COL_ID = 4;
    public static final int CAST_COL_NAME = 5;
    public static final int CAST_COL_PROFILE_PATH = 7;
    public static final int SIMILAR_MOV_COL_POSTER_PATH = 3;
    public static final int SIMILAR_MOV_COL_MOVIE_ID = 7;
    public static final int SIMILAR_MOV_COL_ORIGINAL_TITLE = 8;
    public static final int MOV_COL_POSTER_PATH = 2;
    public static final int MOV_COL_RELEASE_DATE = 5;
    public static final int MOV_COL_MOVIE_ID = 6;
    public static final int MOV_COL_BACKDROP_PATH = 10;
    public static final int MOV_COL_POPULARITY = 11;
    public static final int MOV_COL_VOTE_AVERAGE = 13;
    public static final int COL_VIDEOS_KEY = 2;
    public static final int COL_VIDEOS_NAME = 3;
    public static final int COL_VIDEOS_TV_KEY = 2;
    public static final int COL_REVIEW_AUTHOR = 5;
    public static final int COL_REVIEW_CONTENT = 6;
    public static final int COL_REVIEW_URL = 7;
    public static final int COL_GENRE_NAME = 1;
    public static int FAV_MOV_COL_ID = 0;
    public static int FAV_MOV_COL_MOVIE_ID = 1;
    public static int PEOPLE_COL__ID = 0;
    public static int PEOPLE_COL_ADULT = 1;
    public static int PEOPLE_COL_DEATHDAY = 4;
    public static int PEOPLE_COL_GENDER = 5;
    public static int PEOPLE_COL_ID = 7;
    public static int PEOPLE_COL_POPULARITY = 10;
    public static int MOV_DETAILS_COL_ID = 0;
    public static int MOV_DETAILS_COL_ADULT = 2;
    public static int MOV_DETAILS_COL_BACKDROP_PATH = 3;
    public static int MOV_DETAILS_COL_TAGLINE = 15;
    public static int MOV_DETAILS_COL_TITLE = 16;
    public static int MOV_DETAILS_COL_VOTE_COUNT = 18;
    public static int MOV_DETAILS_COL_FAVOURED = 19;
    public static int MOV_DETAILS_COL_SHOWED = 20;
    public static int MOV_DETAILS_COL_DOWNLOADED = 21;
    public static int CREW_COL__ID = 0;
    public static int CREW_COL_CREDIT_ID = 1;
    public static int CREW_COL_DEPARTMENT = 2;
    public static int CREW_COL_PROFILE_PATH = 6;
    public static int CREW_COL_MOVIE_ID = 7;
    public static int CAST_COL__ID = 0;
    public static int CAST_COL_CAST_ID = 1;
    public static int CAST_COL_CREDIT_ID = 3;
    public static int CAST_COL_ORDER = 6;
    public static int CAST_COL_MOVIE_ID = 8;
    public static int SIMILAR_MOV_COL_ID = 0;
    public static int SIMILAR_MOV_COL_MOVIE_ID_OLD = 1;
    public static int SIMILAR_MOV_COL_PAGE = 2;
    public static int SIMILAR_MOV_COL_ADULT = 4;
    public static int SIMILAR_MOV_COL_OVERVIEW = 5;
    public static int SIMILAR_MOV_COL_RELEASE_DATE = 6;
    public static int SIMILAR_MOV_COL_ORIGINAL_LANG = 9;
    public static int SIMILAR_MOV_COL_TITLE = 10;
    public static int SIMILAR_MOV_COL_BACKDROP_PATH = 11;
    public static int SIMILAR_MOV_COL_POPULARITY = 12;
    public static int SIMILAR_MOV_COL_VOTE_COUNT = 13;
    public static int SIMILAR_MOV_COL_VOTE_AVERAGE = 14;
    public static int SIMILAR_MOV_COL_FAVOURED = 15;
    public static int SIMILAR_MOV_COL_SHOWED = 16;
    public static int SIMILAR_MOV_COL_DOWNLOADED = 17;
    public static int MOV_COL_ID = 0;
    public static int MOV_COL_PAGE = 1;
    public static int MOV_COL_ADULT = 3;
    public static int MOV_COL_OVERVIEW = 4;
    public static int MOV_COL_ORIGINAL_TITLE = 7;
    public static int MOV_COL_ORIGINAL_LANG = 8;
    public static int MOV_COL_TITLE = 9;
    public static int MOV_COL_VOTE_COUNT = 12;
    public static int MOV_COL_FAVOURED = 14;
    public static int MOV_COL_SHOWED = 15;
    public static int MOV_COL_DOWNLOADED = 16;
    public static int MOV_COL_MODE = 17;
    public static int COL_VIDEOS_ID = 0;
    public static int COL_VIDEOS_VIDEO_ID = 1;
    public static int COL_VIDEOS_SITE = 4;
    public static int COL_VIDEOS_SIZE = 5;
    public static int COL_VIDEOS_TYPE = 6;
    public static int COL_VIDEOS_MOVIE_ID = 7;
    public static int COL_REVIEW_ID = 0;
    public static int COL_REVIEW_PAGE = 1;
    public static int COL_REVIEW_TOTAL_PAGE = 2;
    public static int COL_REVIEW_TOTAL_RESULTS = 3;
    public static int COL_REVIEW_ID_REVIEWS = 4;
    public static int COL_REVIEW_MOVIE_ID = 8;
    public static int COL_GENRE_ID = 0;
    public static int COL_GENRE_ID_GENRE = 3;
    public static int COL_GENRE_MOVIE_ID = 4;
    public static final int CREATOR_COL_ID = 2;
    public static final int CREATOR_COL_NAME = 3;
}
