package com.example.admin.movietheatrept1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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

        /*
         * This String array contains dummy film data. Later in the course, we're going to get
         * real weather data. For now, we want to get something on the screen as quickly as
         * possible, so we'll display this dummy data.
         */
        //String[] dummyFilmData = {
             //   "Iron Man, May 17",
             //  "Iron Man, May 17",
             //   "Iron Man, May 17",
             //   "Iron Man, May 17",
             //   "Iron Man, May 17",
             //   "Iron Man, May 17",
            //    "Iron Man, May 17",
            //    "Iron Man, May 17",
            //    "Iron Man, May 17",
            //    "Iron Man, May 17",
           //     "Iron Man, May 17",
          //      "Iron Man, May 17",
         //       "Iron Man, May 17",
         //       "Iron Man, May 17",
        //        "Iron Man, May 17",
        //        "Iron Man, May 17",
        //        "Iron Man, May 17",
        //        "Iron Man, May 17",
        //        "Iron Man, May 17",
        //        "Iron Man, May 17",
        //        "Iron Man, May 17",
        //        "Iron Man, May 17",
        //        "Iron Man, May 17",
        //        "Iron Man, May 17",
        //        "Iron Man, May 17",
        //};

        makeMovieSearchQuery();



        /*
         * Iterate through the array and append the Strings to the TextView. The reason why we add
         * the "\n\n\n" after the String is to give visual separation between each String in the
         * TextView. Later, we'll learn about a better way to display lists of data.
         */
        //for (String dummyFilmDay : dummyFilmData) {
        //    mFilmTextView.append(dummyFilmDay + "\n\n");
        //}



    }

    private void makeMovieSearchQuery() {
        URL movieSearchUrl = NetworkUtils.buildUrl();
        mUrlDisplayData.setText(movieSearchUrl.toString());
        String movieSearchResults = null;
        try {
            movieSearchResults = NetworkUtils.getResponseFromHttpUrl(movieSearchUrl);
            mUrlDisplayData.setText(movieSearchResults);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
