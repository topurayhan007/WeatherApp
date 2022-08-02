package com.topurayhan.weather;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private GpsTracker gpsTracker;
    @SuppressLint("StaticFieldLeak")
    static TextView location, description, humidity, pressure, mainTemp,windSpeed, visibility;
    @SuppressLint("StaticFieldLeak")
    static EditText search;
    @SuppressLint("StaticFieldLeak")
    static TextView hour1temp, hour2temp, hour3temp, hour4temp, hour5temp, hour1, hour2, hour3, hour4, hour5,
            day1temp, day2temp, day3temp, day4temp, day1, day2, day3, day4;
    @SuppressLint("StaticFieldLeak")
    static ImageView hour1img, hour2img, hour3img, hour4img, hour5img, day1img, day2img, day3img, day4img;

    static String cityName = "Dhaka";
    static String prev = "";
    static String key = "488f4111e6b7924073ff22cd896b2e2a";
    static String error = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.search);
        search.clearFocus();
        location = findViewById(R.id.city);
        description = findViewById(R.id.description);
        mainTemp = findViewById(R.id.tempMain);
        humidity = findViewById(R.id.humidity);
        pressure = findViewById(R.id.pressure);
        windSpeed = findViewById(R.id.windSpeed);
        visibility = findViewById(R.id.visibility);

        hour1 = findViewById(R.id.hour1);
        hour2 = findViewById(R.id.hour2);
        hour3 = findViewById(R.id.hour3);
        hour4 = findViewById(R.id.hour4);
        hour5 = findViewById(R.id.hour5);

        hour1temp = findViewById(R.id.hour1temp);
        hour2temp = findViewById(R.id.hour2temp);
        hour3temp = findViewById(R.id.hour3temp);
        hour4temp = findViewById(R.id.hour4temp);
        hour5temp = findViewById(R.id.hour5temp);

        hour1img = findViewById(R.id.hour1img);
        hour2img = findViewById(R.id.hour2img);
        hour3img = findViewById(R.id.hour3img);
        hour4img = findViewById(R.id.hour4img);
        hour5img = findViewById(R.id.hour5img);

        day1 = findViewById(R.id.day1);
        day2 = findViewById(R.id.day2);
        day3 = findViewById(R.id.day3);
        day4 = findViewById(R.id.day4);

        day1temp = findViewById(R.id.day1temp);
        day2temp = findViewById(R.id.day2temp);
        day3temp = findViewById(R.id.day3temp);
        day4temp = findViewById(R.id.day4temp);

        day1img = findViewById(R.id.day1img);
        day2img = findViewById(R.id.day2img);
        day3img = findViewById(R.id.day3img);
        day4img = findViewById(R.id.day4img);

        search.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE){
                cityName = search.getText().toString();

                if(cityName.isEmpty() || cityName.equals(" ")){
                    Toast.makeText(getApplicationContext(), "Please enter a city!", Toast.LENGTH_SHORT).show();
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(15);
                    getWeather(prev, key);
                }
                getWeather(cityName,key);

            }
            return false;
        });

        prev = cityName;
        getWeather(cityName,key);

    }

    private void getWeather(String cityName, String key) {
        Log.d("CityName: ", cityName);
        Weather getData = new Weather();
        getData.execute("https://api.openweathermap.org/data/2.5/forecast?q="+cityName+"&appid="+key+"&units=metric");
//        if (!error.isEmpty()){
//            Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
//            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//            vibrator.vibrate(50);
//            error ="";
//        }
    }

}