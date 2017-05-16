package com.example.admin.movietheatrept1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mFilmTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        /*
         * Using findViewById, we get a reference to our TextView from xml. This allows us to
         * do things like set the text of the TextView.
         */
        mFilmTextView = (TextView) findViewById(R.id.tv_film_data);

        /*
         * This String array contains dummy film data. Later in the course, we're going to get
         * real weather data. For now, we want to get something on the screen as quickly as
         * possible, so we'll display this dummy data.
         */
        String[] dummyFilmData = {
                "Iron Man, May 17",
                "Iron Man, May 17",
                "Iron Man, May 17",
                "Iron Man, May 17",
                "Iron Man, May 17",
                "Iron Man, May 17",
                "Iron Man, May 17",
                "Iron Man, May 17",
                "Iron Man, May 17",
                "Iron Man, May 17",
                "Iron Man, May 17",
                "Iron Man, May 17",
                "Iron Man, May 17",
                "Iron Man, May 17",
                "Iron Man, May 17",
                "Iron Man, May 17",
                "Iron Man, May 17",
                "Iron Man, May 17",
                "Iron Man, May 17",
                "Iron Man, May 17",
                "Iron Man, May 17",
                "Iron Man, May 17",
                "Iron Man, May 17",
                "Iron Man, May 17",
                "Iron Man, May 17",
        };

        /*
         * Iterate through the array and append the Strings to the TextView. The reason why we add
         * the "\n\n\n" after the String is to give visual separation between each String in the
         * TextView. Later, we'll learn about a better way to display lists of data.
         */
        for (String dummyFilmDay : dummyFilmData) {
            mFilmTextView.append(dummyFilmDay + "\n\n");
        }
    }
}
