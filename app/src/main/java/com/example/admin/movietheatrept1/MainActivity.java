package com.example.admin.movietheatrept1;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.admin.movietheatrept1.utils.MovieDataJSON;
import com.example.admin.movietheatrept1.utils.NetworkUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        /*
         * Using findViewById, we get a reference to our RecyclerView from xml. This allows us to
         * do things like set the adapter of the RecyclerView and toggle the visibility.
         */
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_numbers);


        /*
         * LinearLayoutManager can support HORIZONTAL or VERTICAL orientations. The reverse layout
         * parameter is useful mostly for HORIZONTAL layouts that should reverse for right to left
         * languages.
         */
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        mRecyclerView.setHasFixedSize(true);

        /*
         * The MovieAdapter is responsible for linking our movie data with the Views that
         * will end up displaying our movie data.
         */
        mMovieAdapter = new MovieAdapter((MovieAdapter.MovieAdapterOnClickHandler) this);

        /* Setting the adapter attaches it to the RecyclerView in our layout. */
        mRecyclerView.setAdapter(mMovieAdapter);

        /* Once all of our views are setup, we can load the weather data. */
        loadMovieData();
    }

    /**
     * This method will get the user's preferred location for weather, and then tell some
     * background method to get the weather data in the background.
     */
    private void loadMovieData() {
        showWeatherDataView();
        new MovieQueryTask().execute();
    }

    /**
     * This method is overridden by our MainActivity class in order to handle RecyclerView item
     * clicks.
     *
     * @param movieForDay The weather for the day that was clicked
     */
    @Override
    public void onClick(String movieForDay) {
        Context context = this;
        Toast.makeText(context, movieForDay, Toast.LENGTH_SHORT)
                .show();
    }

    private void showWeatherDataView() {
        /* Make sure the weather data is visible */
        mRecyclerView.setVisibility(View.VISIBLE);
    }


    public class MovieQueryTask extends AsyncTask<String, Void, String[]> {

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

        @Override
        protected void onPostExecute(String[] movieData) {
            if (movieData != null) {
                mMovieAdapter.setMovieData(movieData);
            }
        }

        private void loadMovieData() {
            new MovieQueryTask().execute();
        }


    }
}

