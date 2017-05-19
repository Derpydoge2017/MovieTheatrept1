package com.example.admin.movietheatrept1.utils;

import android.content.ContentValues;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

/**
 * Utility functions to handle OpenWeatherMap JSON data.
 */
public final class MovieDataJSON {
    /**
     *
     * @param movieJsonStr JSON response from server
     *
     * @return Array of Strings describing weather data
     *
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static String[] getMovieDataStringsFromJson(Context context, String movieJsonStr)
            throws JSONException {

        /* Movie information. Each day's list info is an element of the "list" array */
        final String MOVIE_LIST = "results";
        
        /* Main movie information needed to display */
        final String MOVIE_ID = "id";
        final String MOVIE_POSTER = "poster_path";
        final String MOVIE_TITLE = "title";

        final String MOVIE_OVERVIEW = "overview";
        final String MOVIE_RELEASE = "release_date";
        final String MOVIE_RATE = "vote_average";

        String[] parsedMovieData = null;

        JSONObject movieJson = new JSONObject(movieJsonStr);

        JSONArray movieArray = movieJson.getJSONArray(MOVIE_LIST);

        parsedMovieData = new String[movieArray.length()];

        for (int i = 0; i < movieArray.length(); i++) {
            /* These are the values that will be collected */
            String movieId;
            String moviePosterPath;
            String movieReleaseDate;
            String movieOverview;
            String movieTitle;
            String movieRate;

            /* Get the JSON object for the movie */
            JSONObject movieToday = movieArray.getJSONObject(i);

            JSONObject movieObject =
                    movieToday.getJSONArray(MOVIE_LIST).getJSONObject(0);
                    movieReleaseDate = movieObject.getString(MOVIE_RELEASE);
                    movieId = movieObject.getString(MOVIE_ID);
                    moviePosterPath = movieObject.getString(MOVIE_POSTER);
                    movieTitle = movieObject.getString(MOVIE_TITLE);
                    movieRate = movieObject.getString(MOVIE_RATE);
                    movieOverview = movieObject.getString(MOVIE_OVERVIEW);


            parsedMovieData[i] = "Title : " + movieTitle + " ," + "Release Date : " + movieReleaseDate + " ," + "Rate : " + movieRate;
        }

        return parsedMovieData;
    }

}