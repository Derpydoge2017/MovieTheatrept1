package com.example.admin.movietheatrept1.utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils {

    final static String MOVIE_BASE_URL =
            "http://api.themoviedb.org/3/movie/popular";

    final static String QUERY_RESULTS = "results";

    final static String QUERY_TITLE = "title";

    final static String QUERY_ID = "id";

    final static String QUERY_POSTER = "poster_path";

    final static String QUERY_OVERVIEW = "overview";

    final static String QUERY_RELEASE_DATE = "release_date";

    final static String QUERY_RATE = "vote_average";

    final static String QUERY_KEY = "c13604c96bab2582efa77bde0ddff06b";

    final static String QUERY_API = "?api_key=";

    /*
     * The sort field. One of stars, forks, or updated.
     * Default: results are sorted by best match if no field is specified.
     */
    //final static String PARAM_SORT = "sort";
    //final static String sortBy = "stars";

    /**
     * Builds the URL used to query theMovieDb.
     *
     * @return The URL to use to query the weather server.
     */



    public static URL buildUrl() {
        // COMPLETED (1) Fill in this method to build the proper Movie query URL
        Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_API, QUERY_KEY)
                //.appendQueryParameter(PARAM_SORT, sortBy)
                .build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
             Log.i("BUILT URL: ", url.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}