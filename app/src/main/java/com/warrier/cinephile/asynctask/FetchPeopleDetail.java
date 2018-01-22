package com.warrier.cinephile.asynctask;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import com.warrier.cinephile.BuildConfig;
import com.warrier.cinephile.data.MovieContract.People;
import com.warrier.cinephile.utils.AsyncResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Galahad on 17/11/2017.
 */

public class FetchPeopleDetail extends AsyncTask<String, Void, Void> {

    private final String LOG_TAG = FetchMovieTask.class.getSimpleName();

    private final Context mContext;
    public AsyncResponse response = null;
    private boolean DEBUG = true;

    public FetchPeopleDetail(Context context) {
        mContext = context;
    }

    private void getMovieDataFromJson(String movieJsonStr)
            throws JSONException {

        try {
            JSONObject profileJson = new JSONObject(movieJsonStr);
            ContentValues[] cvArray = new ContentValues[1];

            String adult;
            String biography;
            String birthday;
            String deathday;
            String gender;
            String homepage;
            String id;
            String name;
            String place_of_birth;
            String popularity;
            String profile_path;

            adult = profileJson.getString(People.ADULT);
            biography = profileJson.getString(People.BIOGRAPHY);
            birthday = profileJson.getString(People.BIRTHDAY);
            deathday = profileJson.getString(People.DEATHDAY);
            gender = profileJson.getString(People.GENDER);
            homepage = profileJson.getString(People.HOMEPAGE);
            id = profileJson.getString(People.ID);
            name = profileJson.getString(People.NAME);
            place_of_birth = profileJson.getString(People.PLACE_OF_BIRTH);
            popularity = profileJson.getString(People.POPULARITY);

            profile_path = profileJson.getString(People.PROFILE_PATH);

            ContentValues profileValues = new ContentValues();

            // Then add the data, along with the corresponding name of the data type,
            // so the content provider knows what kind of value is being inserted.

            profileValues.put(People.ADULT, adult);
            profileValues.put(People.BIOGRAPHY, biography);
            profileValues.put(People.BIRTHDAY, birthday);
            profileValues.put(People.DEATHDAY, deathday);
            profileValues.put(People.GENDER, gender);
            profileValues.put(People.HOMEPAGE, homepage);
            profileValues.put(People.ID, id);
            profileValues.put(People.NAME, name);
            profileValues.put(People.PLACE_OF_BIRTH, place_of_birth);
            profileValues.put(People.POPULARITY, popularity);
            profileValues.put(People.PROFILE_PATH, profile_path);
            cvArray[0] = profileValues;
            mContext.getContentResolver().bulkInsert(People.CONTENT_URI, cvArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected Void doInBackground(String... params) {

        if (params.length == 0) {
            return null;
        }

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String movieJsonStr;

        try {
            // Construct the URL for the movieAPI query

            final String MOVIE_BASE_URL = "http://api.themoviedb.org/3/person/";
            final String APPID_PARAM = "api_key";

            Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                    .appendPath(params[0])
                    .appendQueryParameter(APPID_PARAM, BuildConfig.MOVIE_DB_API_KEY)
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
            getMovieDataFromJson(movieJsonStr);
        } catch (IOException e) {

            return null;
        } catch (JSONException e) {

            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {

                }
            }
        }
        return null;
    }
}
