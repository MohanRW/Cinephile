package com.warrier.cinephile.asynctask;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.preference.PreferenceManager;

import com.warrier.cinephile.BuildConfig;
import com.warrier.cinephile.MainActivity;
import com.warrier.cinephile.data.MovieContract;
import com.warrier.cinephile.utils.AsyncResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;

/**
 * Created by Galahad on 17/11/2017.
 */

public class FetchMovieTask extends AsyncTask<String, Void, Integer> {

    private final String LOG_TAG = FetchMovieTask.class.getSimpleName();

    private final Context mContext;
    public AsyncResponse response = null;

    public FetchMovieTask(Context context) {
        mContext = context;
    }

    private int getMovieDataFromJson(String movieJsonStr, String mode)
            throws JSONException {
        int inserted = 0;

        // These are the names of the JSON objects that need to be extracted.
        final String MOVIE_ID = "id";
        final String ORGLANG = "original_language";
        final String ORGTITLE = "original_title";
        final String OVER = "overview";
        final String RELDATE = "release_date";
        final String POSTERPATH = "poster_path";
        final String POPULARITY = "popularity";
        final String VOTAVG = "vote_average";
        final String ADULT = "adult";
        final String TITLE = "title";
        final String BACKDROP_PATH = "backdrop_path";
        final String VOTE_COUNT = "vote_count";
        final String PAGE = "page";

        boolean PREF_CHILD;
        String PREF_LANGUAGE;
        String PREF_REGION;

        String KEY_ADULT = "child_mode";
        String KEY_LANGUAGE = "language";
        String KEY_REGION = "region";

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        PREF_CHILD = sharedPreferences.getBoolean(KEY_ADULT, true);
        PREF_LANGUAGE = sharedPreferences.getString(KEY_LANGUAGE, "");
        PREF_REGION = sharedPreferences.getString(KEY_REGION, "");


        final String RESULT = "results";
        final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/w185";
        final String BACKDROP_BASE_URL = "http://image.tmdb.org/t/p/w500";

        try {
            JSONObject movieJson = new JSONObject(movieJsonStr);
            JSONArray movieArray = movieJson.getJSONArray(RESULT);

            String page = movieJson.getString(PAGE);

            Vector<ContentValues> cVVector = new Vector<ContentValues>(movieArray.length());

            for (int i = 0; i < movieArray.length(); i++) {

                String movie_id;
                String orgLang;
                String orgTitle;
                String overview;
                String relDate;
                String postURL;
                String popularity;
                String votAvg;
                String vote_count;
                String backdropURL;
                String title;
                String adult;


                JSONObject movieInfo = movieArray.getJSONObject(i);

                movie_id = movieInfo.getString(MOVIE_ID);
                orgLang = movieInfo.getString(ORGLANG);
                orgTitle = movieInfo.getString(ORGTITLE);
                overview = movieInfo.getString(OVER);
                relDate = movieInfo.getString(RELDATE);

                postURL = Uri.parse(POSTER_BASE_URL).buildUpon().
                        appendEncodedPath(movieInfo.getString(POSTERPATH)).build().toString();
                popularity = movieInfo.getString(POPULARITY);
                votAvg = movieInfo.getString(VOTAVG);
                vote_count = movieInfo.getString(VOTE_COUNT);
                title = movieInfo.getString(TITLE);
                adult = movieInfo.getString(ADULT);

                backdropURL = Uri.parse(BACKDROP_BASE_URL).buildUpon().
                        appendEncodedPath(movieInfo.getString(BACKDROP_PATH)).build().toString();


                ContentValues movieValues = new ContentValues();

                // Then add the data, along with the corresponding name of the data type,
                // so the content provider knows what kind of value is being inserted.

                movieValues.put(MovieContract.Movies.PAGE, page);
                movieValues.put(MovieContract.Movies.POSTER_PATH, postURL);
                movieValues.put(MovieContract.Movies.ADULT, adult);
                movieValues.put(MovieContract.Movies.OVERVIEW, overview);
                movieValues.put(MovieContract.Movies.RELEASE_DATE, relDate);
                movieValues.put(MovieContract.Movies.MOVIE_ID, movie_id);
                movieValues.put(MovieContract.Movies.ORIGINAL_TITLE, orgTitle);
                movieValues.put(MovieContract.Movies.ORIGINAL_LANGUAGE, orgLang);
                movieValues.put(MovieContract.Movies.TITLE, title);
                movieValues.put(MovieContract.Movies.BACKDROP_PATH, backdropURL);
                movieValues.put(MovieContract.Movies.POPULARITY, popularity);
                movieValues.put(MovieContract.Movies.VOTE_COUNT, vote_count);
                movieValues.put(MovieContract.Movies.VOTE_AVERAGE, votAvg);
                movieValues.put(MovieContract.Movies.MODE, mode);
                movieValues.put(MovieContract.Movies.PREF_LANGUAGE, PREF_LANGUAGE);
                movieValues.put(MovieContract.Movies.PREF_ADULT, String.valueOf(!PREF_CHILD));
                movieValues.put(MovieContract.Movies.PREF_REGION, PREF_REGION);
                cVVector.add(movieValues);
            }

            // add to database
            if (cVVector.size() > 0) {
                ContentValues[] cvArray = new ContentValues[cVVector.size()];
                cVVector.toArray(cvArray);
                inserted = mContext.getContentResolver().bulkInsert(MovieContract.Movies.CONTENT_URI, cvArray);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return inserted;
    }
    @Override
    protected Integer doInBackground(String... params) {

        if (params.length == 0) {
            return null;
        }
        int inserted = 0;

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String movieJsonStr;
        boolean isMovie = true;

        try {
            // Construct the URL for the movieAPI query

            String MOVIE_BASE_URL = "https://api.themoviedb.org/3/";

            switch (params[0]) {
                case MainActivity.FRAGMENT_TAG_MOV_NOW_PLAYING:
                    MOVIE_BASE_URL += "movie/now_playing?";
                    break;
                case MainActivity.FRAGMENT_TAG_MOV_POPULAR:
                    MOVIE_BASE_URL += "movie/popular?";
                    break;

                case MainActivity.FRAGMENT_TAG_MOV_TOP_RATED:
                    MOVIE_BASE_URL += "movie/top_rated?";
                    break;

                case MainActivity.FRAGMENT_TAG_MOV_UPCOMING:
                    MOVIE_BASE_URL += "movie/upcoming?";
                    break;


                default:
                    break;
            }

            final String APPID_PARAM = "api_key";
            final String PAGE_PARAM = "page";
            final String LANG_PARAM = "language";
            final String REGION_PARAM = "region";
            final String ADULT_PARAM = "adult";

            boolean PREF_CHILD;
            String PREF_LANGUAGE;
            String PREF_REGION;

            String KEY_ADULT = "child_mode";
            String KEY_LANGUAGE = "language";
            String KEY_REGION = "region";

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            PREF_CHILD = sharedPreferences.getBoolean(KEY_ADULT, true);
            PREF_LANGUAGE = sharedPreferences.getString(KEY_LANGUAGE, "");
            PREF_REGION = sharedPreferences.getString(KEY_REGION, "");

            Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                    .appendQueryParameter(APPID_PARAM, BuildConfig.MOVIE_DB_API_KEY)
                    .appendQueryParameter(LANG_PARAM, PREF_LANGUAGE)
                    .appendQueryParameter(PAGE_PARAM, "1")
                    .appendQueryParameter(REGION_PARAM, PREF_REGION)
                    .appendQueryParameter(ADULT_PARAM, String.valueOf(!PREF_CHILD))
                    .build();

            URL url = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line).append("\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            movieJsonStr = buffer.toString();
            if (isMovie) {
                inserted = getMovieDataFromJson(movieJsonStr, params[0]);
            }
        } catch (IOException e) {
            //Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the movie data, there's no point in attemping
            // to parse it.
            return null;
        } catch (JSONException e) {
            //Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    //Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        return inserted;
    }
    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        if (result != null) {
            response.onFinish(result > 0);
        }
    }

}
