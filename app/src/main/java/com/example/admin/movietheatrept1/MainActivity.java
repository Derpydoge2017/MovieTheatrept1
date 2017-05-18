package com.example.admin.movietheatrept1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.admin.movietheatrept1.utils.MovieDataJSON;
import com.example.admin.movietheatrept1.utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView mFilmTextView;

    private TextView mUrlDisplayData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        /*
         * Using findViewById, we get a reference to our TextView from xml. This allows us to
         * do things like set the text of the TextView.
         */
        mFilmTextView = (TextView) findViewById(R.id.tv_film_data);

        mUrlDisplayData = (TextView) findViewById(R.id.tv_url_display);

        loadMovieData();





        /*
         * Iterate through the array and append the Strings to the TextView. The reason why we add
         * the "\n\n\n" after the String is to give visual separation between each String in the
         * TextView. Later, we'll learn about a better way to display lists of data.
         */
        //for (String dummyFilmDay : dummyFilmData) {
        //    mFilmTextView.append(dummyFilmDay + "\n\n");
        //}


    }

    public class MovieQueryTask extends AsyncTask<String, Void, String[]> {

        // COMPLETED (2) Override the doInBackground method to perform the query. Return the results. (Hint: You've already written the code to perform the query)
        @Override
        protected String[] doInBackground(String... params) {
            String movieSearchResults = null;
            URL movieSearchUrl = NetworkUtils.buildUrl();
            try {
                String jsonMovieResponse = NetworkUtils
                        .getResponseFromHttpUrl(movieSearchUrl);

                String[] simpleJsonMovieData = MovieDataJSON
                        .getMovieDataStringsFromJson(MainActivity.this, jsonMovieResponse);

                return simpleJsonMovieData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        // COMPLETED (7) Override the onPostExecute method to display the results of the network request
        @Override
        protected void onPostExecute(String[] movieData) {
            if (movieData != null) {
                /*
                 * Iterate through the array and append the Strings to the TextView. The reason why we add
                 * the "\n\n\n" after the String is to give visual separation between each String in the
                 * TextView. Later, we'll learn about a better way to display lists of data.
                 */
                for (String movieString : movieData) {
                    mFilmTextView.append(movieString);
                }
            }
        }
    }

    private void loadMovieData() {
        new MovieQueryTask().execute();
    }


}
