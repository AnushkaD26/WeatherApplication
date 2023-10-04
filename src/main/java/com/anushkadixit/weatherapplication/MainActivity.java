package com.anushkadixit.weatherapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    String CITY;
    String API="402cc6daa94b77ed1c3d343b92e522d4";
    ImageView search;
    EditText etCity;

    TextView
    city,country,time,temp,forecast,humidity,
           min_temp,max_temp,sunrises,sunsets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        {
            etCity = findViewById(R.id.Your_city);
            search = findViewById(R.id.search);

            city = findViewById(R.id.city);
            country = findViewById(R.id.country);
            time = findViewById(R.id.time);
            temp = findViewById(R.id.temp);
            forecast = findViewById(R.id.forecast);

            humidity = findViewById(R.id.humidity);
            max_temp = findViewById(R.id.max_temp);
            min_temp = findViewById(R.id.min_temp);
            sunrises = findViewById(R.id.sunrises);
            sunsets = findViewById(R.id.sunsets);


            search.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {

                    CITY = etCity.getText().toString();

                    new weatherTask().execute();

                }

            });

        }
    }





class weatherTask extends AsyncTask<String, Void, String> {

    protected  void onPreExecute(){

        super.onPreExecute();
    }
    @Override
    protected String doInBackground(String... args){

        String response=
                HttpRequest.excuteGet("https://api.openweathermap.org/data/2.5/weather?q="
                        + CITY + "&units=metric&appid=" + API);
        return response;
    }
    @Override

    protected void onPostExecute(String result){
        try{
            JSONObject jsonObj = new JSONObject(result);

            JSONObject main = jsonObj.getJSONObject("main");

            JSONObject weather = jsonObj.getJSONArray("weather").
                    getJSONObject(0);


            JSONObject sys = jsonObj.getJSONObject("sys");
            //Call value in api

            String city_name=jsonObj.getString("name");
            String countryName= sys.getString("country");
            Long updatedAt=jsonObj.getLong("dt");

            String updatedAtText="Last Updated at: "+
                    new SimpleDateFormat("dd/MM/yyyy hh:mm a",
                            Locale.ENGLISH).format(new Date(updatedAt*1000));

            String temperature=main.getString("temp");
            String cast=weather.getString("description");
            String humidity_=main.getString("humidity");
            String temp_min = main.getString("temp_min");
            String temp_max=main.getString("temp_max");

            Long rise=sys.getLong("sunrise");

            String sunrise = new SimpleDateFormat("hh:mm a",
            Locale.ENGLISH).format(new Date(rise * 1000));

            Long set = sys.getLong("sunset");

            String sunset=new SimpleDateFormat("hh:mm a",
                    Locale.ENGLISH).format(new Date(set*1000));

            // Set all values in textbox

            city.setText(city_name);
            country.setText(countryName);
            time.setText(updatedAtText);
            temp.setText(temperature + "°C");
            forecast.setText(cast);
            humidity.setText(humidity_+"%");
            min_temp.setText(temp_min+"°C");
            max_temp.setText(temp_max+"°C");
            sunrises.setText(sunrise);
            sunsets.setText(sunset);

        }
        catch (Exception e) {
            Toast.makeText(MainActivity.this, "Error:"+ e.toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
}
