package com.topurayhan.weather;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.IonContext;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Weather extends AsyncTask<String, Void, String> {
    String result;
    @Override
    protected String doInBackground(String... urls) {
        result = "";
        URL link;
        HttpURLConnection myConnection = null;

        try{
            link = new URL(urls[0]);
            myConnection = (HttpURLConnection)link.openConnection();
            InputStream in = myConnection.getInputStream();
            InputStreamReader myStreamReader = new InputStreamReader(in);
            int data = myStreamReader.read();
            while (data != -1){
                char current = (char)data;
                result += current;
                data = myStreamReader.read();
            }
            return result;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try{
            // JSON Result stored in myObject
            JSONObject myObject = new JSONObject(result);
            Log.d("myObject", myObject.toString());

            // City name and Country from JSON myObject
            JSONObject city = new JSONObject(myObject.getString("city"));
            String placeName = city.getString("name").toString();
            Log.d("placeName", placeName.toString());
            String country = city.getString("country").toString();
            Log.d("country", country.toString());
            String location = placeName+", "+country;

            // Accessing the "list" Array Object
            JSONArray list = myObject.getJSONArray("list");
            Log.d("list", list.toString());

            // Accessing the 1st Object from the Array Object "list"
            JSONObject list0 = list.getJSONObject(0);
            Log.d("list0", list0.toString());
            JSONObject list1 = list.getJSONObject(1);
            JSONObject list2 = list.getJSONObject(2);
            JSONObject list3 = list.getJSONObject(3);
            JSONObject list4 = list.getJSONObject(4);

            // Accessing the "weather" from an object of Array Object "list"
            JSONArray weather = list0.getJSONArray("weather");
            JSONArray weather1 = list1.getJSONArray("weather");
            JSONArray weather2 = list2.getJSONArray("weather");
            JSONArray weather3 = list3.getJSONArray("weather");
            JSONArray weather4 = list4.getJSONArray("weather");

            Log.d("weather", weather.toString());

            // "description" and "icon" from "weather"
            String desc = weather.getJSONObject(0).getString("description").toString();
            desc = desc.substring(0, 1).toUpperCase() + desc.substring(1);
            Log.d("desc", desc.toString());


            // Accessing the "main" from an object of Array Object "list"
            JSONObject main = list0.getJSONObject("main");
            Log.d("main", main.toString());
            JSONObject main1 = list1.getJSONObject("main");
            JSONObject main2 = list2.getJSONObject("main");
            JSONObject main3 = list3.getJSONObject("main");
            JSONObject main4 = list4.getJSONObject("main");

            String time = list0.getString("dt_txt").toString();
            Log.d("time", time.toString());
            String[] split = time.split(" ");
            String[] str = split[1].split(":");
            Log.d("s1", str[0]);
            int hr = Integer.parseInt(str[0]);
            String hour1 = "";
            if (hr > 12){
                int temp = hr-12;
                hour1 = String.valueOf(temp) + " PM";

            }
            else if (hr == 12){
                hour1 = String.valueOf(hr) + " PM";
            }
            else if (hr == 0){
                hour1 = String.valueOf(12) + " AM";
            }
            else{
                hour1 = String.valueOf(hr) + " AM";
            }
            MainActivity.hour1.setText(hour1);

            String time1 = list1.getString("dt_txt").toString();
            String[] split2 = time1.split(" ");
            String[] str2 = split2[1].split(":");
            Log.d("s2", str2[0]);
            int hr2 = Integer.parseInt(str2[0]);
            String hour2 = "";
            if (hr2 > 12){
                int temp2 = hr2-12;
                hour2 = String.valueOf(temp2) + " PM";

            }
            else if (hr2 == 12){
                hour2 = String.valueOf(hr2) + " PM";
            }
            else if (hr2 == 0){
                hour2 = String.valueOf(12) + " AM";
            }
            else{
                hour2 = String.valueOf(hr2) + " AM";
            }
            MainActivity.hour2.setText(hour2);

            String time2 = list2.getString("dt_txt").toString();
            String[] split3 = time2.split(" ");
            String[] str3 = split3[1].split(":");
            Log.d("s3", str3[0]);
            int hr3 = Integer.parseInt(str3[0]);
            String hour3 = "";
            if (hr3 > 12){
                int temp3 = hr3-12;
                hour3 = String.valueOf(temp3) + " PM";

            }
            else if (hr3 == 12){
                hour3 = String.valueOf(hr3) + " PM";
            }
            else if (hr3 == 0){
                hour3 = String.valueOf(12) + " AM";
            }
            else{
                hour3 = String.valueOf(hr3) + " AM";
            }
            MainActivity.hour3.setText(hour3);

            String time3 = list3.getString("dt_txt").toString();
            String[] split4 = time3.split(" ");
            String[] str4 = split4[1].split(":");
            Log.d("s4", str4[0]);
            int hr4 = Integer.parseInt(str4[0]);
            String hour4 = "";
            if (hr4 > 12){
                int temp4 = hr4-12;
                hour4 = String.valueOf(temp4) + " PM";

            }
            else if (hr4 == 12){
                hour4 = String.valueOf(hr4) + " PM";
            }
            else if (hr4 == 0){
                hour4 = String.valueOf(12) + " AM";
            }
            else{
                hour4 = String.valueOf(hr4) + " AM";
            }
            MainActivity.hour4.setText(hour4);

            String time4 = list4.getString("dt_txt").toString();
            String[] split5 = time4.split(" ");
            String[] str5 = split5[1].split(":");
            Log.d("s5", str5[0]);
            int hr5 = Integer.parseInt(str5[0]);
            String hour5 = "";
            if (hr5 > 12){
                int temp5 = hr5-12;
                hour5 = String.valueOf(temp5) + " PM";

            }
            else if (hr5 == 12){
                hour5 = String.valueOf(hr5) + " PM";
            }
            else if (hr5 == 0){
                hour5 = String.valueOf(12) + " AM";
            }
            else{
                hour5 = String.valueOf(hr5) + " AM";
            }
            MainActivity.hour5.setText(hour5);


            // "temp_max" & "temp_min" from "main"
            double mainT = main.getDouble("temp");
            Log.d("mainTemp", Double.toString(mainT));
            int temp = (int) Math.round(mainT);
            String mainTemp = String.valueOf(temp) ;
            MainActivity.hour1temp.setText(mainTemp+ "°");
            String icon = weather.getJSONObject(0).getString("icon").toString();
            Log.d("icon", icon.toString());
            // Setting the icon of the weather
            Ion.with(MainActivity.hour1img)
                    .load("http://openweathermap.org/img/wn/"+icon+"@2x.png");

            double mainT1 = main1.getDouble("temp");
            Log.d("mainTemp1", Double.toString(mainT1));
            int temp1 = (int) Math.round(mainT1);
            String mainTemp1 = String.valueOf(temp1) + "°";
            MainActivity.hour2temp.setText(mainTemp1);
            String icon1 = weather1.getJSONObject(0).getString("icon").toString();
            Log.d("icon1", icon1.toString());
            Ion.with(MainActivity.hour2img)
                    .load("http://openweathermap.org/img/wn/"+icon1+"@2x.png");

            double mainT2 = main2.getDouble("temp");
            Log.d("mainTemp2", Double.toString(mainT2));
            int temp2 = (int) Math.round(mainT2);
            String mainTemp2 = String.valueOf(temp2) + "°";
            MainActivity.hour3temp.setText(mainTemp2);
            String icon2 = weather2.getJSONObject(0).getString("icon").toString();
            Log.d("icon2", icon2.toString());
            Ion.with(MainActivity.hour3img)
                    .load("http://openweathermap.org/img/wn/"+icon2+"@2x.png");

            double mainT3 = main3.getDouble("temp");
            Log.d("mainTemp3", Double.toString(mainT3));
            int temp3 = (int) Math.round(mainT3);
            String mainTemp3 = String.valueOf(temp3) + "°";
            MainActivity.hour4temp.setText(mainTemp3);
            String icon3 = weather3.getJSONObject(0).getString("icon").toString();
            Log.d("icon3", icon3.toString());
            Ion.with(MainActivity.hour4img)
                    .load("http://openweathermap.org/img/wn/"+icon3+"@2x.png");

            double mainT4 = main4.getDouble("temp");
            Log.d("mainTemp4", Double.toString(mainT4));
            int temp4 = (int) Math.round(mainT4);
            String mainTemp4 = String.valueOf(temp4) + "°";
            MainActivity.hour5temp.setText(mainTemp4);
            String icon4 = weather4.getJSONObject(0).getString("icon").toString();
            Log.d("icon4", icon4.toString());
            Ion.with(MainActivity.hour5img)
                    .load("http://openweathermap.org/img/wn/"+icon4+"@2x.png");


//
//            double max = main.getDouble("temp_max");
//            Log.d("maxTemp", Double.toString(max));
//            int temp1 = (int) Math.round(max);
//            String maxTemp = String.valueOf(temp1);
//
//            double min = main.getDouble("temp_min");
//            Log.d("minTemp", Double.toString(max));
//            int temp2 = (int) Math.round(max);
//            String minTemp = String.valueOf(temp2);
//            Log.d("minTemp", minTemp.toString());

            String humidity = main.getString("humidity").toString() + " %";
            Log.d("humidity", humidity.toString());

            String pressure = main.getString("pressure").toString() + " Pa";
            Log.d("pressure", pressure.toString());

            JSONObject wind = list0.getJSONObject("wind");
            double windTemp = wind.getDouble("speed");
            windTemp = windTemp * 3.6;
            final DecimalFormat df = new DecimalFormat("0.0");
            String windSpeed = df.format(windTemp) + " km/h";
            Log.d("windSpeed", windSpeed.toString());


            int visible = list0.getInt("visibility");
            int tempVisible = visible/1000;
            String visibility = String.valueOf(tempVisible) + " km";
            Log.d("visibility", visibility);


            // 4 Day forecast
            // JSONArray list = myObject.getJSONArray("list");

            List<String> day =new ArrayList<String>();
            List<String> dayIcon =new ArrayList<String>();
            List<String> dayTemp =new ArrayList<String>();

            String[] currDateCheck = list0.getString("dt_txt").toString().split(" ");
            String currDate = currDateCheck[0];
            Log.d("currDate", currDate.toString());

            int counter = 0;

            for (int i = 0; i < 40; i++){
                JSONObject listTemp = list.getJSONObject(i);
                String tempDate = listTemp.getString("dt_txt");
                String[] tempTime = tempDate.split(" ");
                String timeCheck = tempTime[1];

                String currTempDate = tempTime[0];
                //Log.d("currTempDate", currTempDate + " " + timeCheck);

                if (currTempDate.compareTo(currDate) > 0 && timeCheck.compareTo("12:00:00") == 0 && counter < 4){
                    currDate = currTempDate;
                    counter++;
                    Log.d("Dates", tempDate);
                    Log.d("Counter", String.valueOf(counter));

                    // push days, icons, temp

                    // 4 Days name added to ArrayList
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
                    @SuppressLint("SimpleDateFormat") Format f = new SimpleDateFormat("EEEE");
                    Date dateLoop = simpleFormat.parse(tempTime[0].toString());
                    String dayFoo = f.format(dateLoop);
                    Log.d("Day: ", dayFoo);
                    day.add(dayFoo);

                    
                    // 4 Days temp added to ArrayList
                    JSONObject mainLoop = listTemp.getJSONObject("main");
                    double mainTempMin = mainLoop.getDouble("temp_min");
                    Log.d("mainTempMin", Double.toString(mainTempMin));
                    int tempCc = (int) Math.floor(mainTempMin);
                    double mainTempMax = mainLoop.getDouble("temp_max");
                    Log.d("mainTempMin", Double.toString(mainTempMax));
                    int tempCc2 = (int) Math.ceil(mainTempMax);
                    String mainTempStr = String.valueOf(tempCc2) + "° / " + String.valueOf(tempCc) + "°";
                    dayTemp.add(mainTempStr);


                    // 4 Days icon added to ArrayList
                    JSONArray weatherLoop = listTemp.getJSONArray("weather");
                    String iconLoop = weatherLoop.getJSONObject(0).getString("icon").toString();
                    Log.d("icon", icon.toString());
                    dayIcon.add(iconLoop);

                }

            }
            Log.d("Day1: ", day.get(0));
            Log.d("Temp1: ", dayTemp.get(0));
            Log.d("Icon1: ", dayIcon.get(0));



            MainActivity.day1.setText(day.get(0));
            MainActivity.day2.setText(day.get(1));
            MainActivity.day3.setText(day.get(2));
            MainActivity.day4.setText(day.get(3));

            Ion.with(MainActivity.day1img)
                    .load("http://openweathermap.org/img/wn/"+dayIcon.get(0)+"@2x.png");
            Ion.with(MainActivity.day2img)
                    .load("http://openweathermap.org/img/wn/"+dayIcon.get(1)+"@2x.png");
            Ion.with(MainActivity.day3img)
                    .load("http://openweathermap.org/img/wn/"+dayIcon.get(2)+"@2x.png");
            Ion.with(MainActivity.day4img)
                    .load("http://openweathermap.org/img/wn/"+dayIcon.get(3)+"@2x.png");

            MainActivity.day1temp.setText(dayTemp.get(0));
            MainActivity.day2temp.setText(dayTemp.get(1));
            MainActivity.day3temp.setText(dayTemp.get(2));
            MainActivity.day4temp.setText(dayTemp.get(3));




            // Setting all info to views in MainActivity
            MainActivity.location.setText(location);
            MainActivity.mainTemp.setText(mainTemp);
//            MainActivity.maxTemp.setText(maxTemp);
//            MainActivity.minTemp.setText(minTemp);
            MainActivity.description.setText(desc);
            MainActivity.humidity.setText(humidity);
            MainActivity.pressure.setText(pressure);
            MainActivity.visibility.setText(visibility);
            MainActivity.windSpeed.setText(windSpeed);




        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
    }
}

