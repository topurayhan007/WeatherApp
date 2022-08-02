package com.topurayhan.weather;

import static android.widget.Toast.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    static TextView location;
    @SuppressLint("StaticFieldLeak")
    static TextView description;
    @SuppressLint("StaticFieldLeak")
    static TextView humidity;
    @SuppressLint("StaticFieldLeak")
    static TextView pressure;
    @SuppressLint("StaticFieldLeak")
    static ImageView icon;
    @SuppressLint("StaticFieldLeak")
    static TextView mainTemp;
    @SuppressLint("StaticFieldLeak")
    static EditText search;
    @SuppressLint("StaticFieldLeak")
    static TextView windSpeed;
    @SuppressLint("StaticFieldLeak")
    static TextView visibility;
    @SuppressLint("StaticFieldLeak")
    static TextView hour1temp, hour2temp, hour3temp, hour4temp, hour5temp, hour1, hour2, hour3, hour4, hour5,
            day1temp, day2temp, day3temp, day4temp, day1, day2, day3, day4;
    @SuppressLint("StaticFieldLeak")
    static ImageView hour1img, hour2img, hour3img, hour4img, hour5img, day1img, day2img, day3img, day4img;

    static String cityName = "Dhaka";
    static String prev = "";
    static String key = "488f4111e6b7924073ff22cd896b2e2a";
    static String iconLink = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = (EditText) findViewById(R.id.search);
        location = (TextView) findViewById(R.id.city);
        description = (TextView) findViewById(R.id.description);
        mainTemp = (TextView) findViewById(R.id.tempMain);
        humidity = (TextView) findViewById(R.id.humidity);
        pressure = (TextView) findViewById(R.id.pressure);
        windSpeed = (TextView) findViewById(R.id.windSpeed);
        visibility = (TextView) findViewById(R.id.visibility);

        hour1 = (TextView) findViewById(R.id.hour1);
        hour2 = (TextView) findViewById(R.id.hour2);
        hour3 = (TextView) findViewById(R.id.hour3);
        hour4 = (TextView) findViewById(R.id.hour4);
        hour5 = (TextView) findViewById(R.id.hour5);

        hour1temp = (TextView) findViewById(R.id.hour1temp);
        hour2temp = (TextView) findViewById(R.id.hour2temp);
        hour3temp = (TextView) findViewById(R.id.hour3temp);
        hour4temp = (TextView) findViewById(R.id.hour4temp);
        hour5temp = (TextView) findViewById(R.id.hour5temp);

        hour1img = (ImageView) findViewById(R.id.hour1img);
        hour2img = (ImageView) findViewById(R.id.hour2img);
        hour3img = (ImageView) findViewById(R.id.hour3img);
        hour4img = (ImageView) findViewById(R.id.hour4img);
        hour5img = (ImageView) findViewById(R.id.hour5img);

        day1 = (TextView) findViewById(R.id.day1);
        day2 = (TextView) findViewById(R.id.day2);
        day3 = (TextView) findViewById(R.id.day3);
        day4 = (TextView) findViewById(R.id.day4);

        day1temp = (TextView) findViewById(R.id.day1temp);
        day2temp = (TextView) findViewById(R.id.day2temp);
        day3temp = (TextView) findViewById(R.id.day3temp);
        day4temp = (TextView) findViewById(R.id.day4temp);

        day1img = (ImageView) findViewById(R.id.day1img);
        day2img = (ImageView) findViewById(R.id.day2img);
        day3img = (ImageView) findViewById(R.id.day3img);
        day4img = (ImageView) findViewById(R.id.day4img);

        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean enter = false;
                if (i == EditorInfo.IME_ACTION_DONE){

                    cityName = search.getText().toString();
                    search.clearFocus();

                    if(cityName.isEmpty() || cityName.equals(" ")){
                        Toast.makeText(getApplicationContext(), "Empty input!", Toast.LENGTH_SHORT).show();
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(15);
                        getWeather(prev, key);
                    }
                    else if (checkWhiteSpace(cityName)) {
                        cityName = cityName.substring(0, cityName.length() - 1);
                        prev = cityName;
                        getWeather(cityName, key);
                    }

                }
                return false;
            }


        });

        getWeather(cityName, key);
        prev = cityName;


    }

    private void getWeather(String cityName, String key) {
        Log.d("CityName: ", cityName);
        Weather getData = new Weather();
        getData.execute("https://api.openweathermap.org/data/2.5/forecast?q="+cityName+"&appid="+key+"&units=metric");
    }

    public boolean checkWhiteSpace(String cityName) {
        String str = cityName;
        if (str.charAt(str.length()-1) == ' '){
            return true;
        }
        else
            return false;
    }
}