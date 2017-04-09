package com.mad.homework08weather;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements FirebaseHandler.ICities {
    String cityName,countryCode,cityKey,countryName;
    static boolean tempUnit = false; //false=Celsius, true= fahrenheit
    Location location;
    SharedPreferences.Editor editor;
    SharedPreferences preferences;
    Gson gson;
    String apiKey = "naqiA2sdihu1iBA6uYB4GYXHBCAvpDRO";
    EditText et_city,et_country;
    String currentCityWeatherText, currentCityUpdatedTime;
    double currentCityTempCel, currentCityTempF;
    int currentCityWeatherIcon;
    LinearLayout linearLayout;
    OkHttpClient client;
    ProgressDialog pg;
    ArrayList<CityDetails> cities= new ArrayList<>();;
    RecyclerView rvSavedCities;
    FirebaseHandler handler;
    LinearLayout linearSavedCity;
    DatabaseReference rbRoot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("demo","onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_title);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        linearLayout = (LinearLayout) findViewById(R.id.linearMainDisplay);
        pg = new ProgressDialog(MainActivity.this);
        linearSavedCity = (LinearLayout) findViewById(R.id.linearSavedCity);
        cities = new ArrayList<>();
        rbRoot = FirebaseDatabase.getInstance().getReference();
        handler = new FirebaseHandler(rbRoot,this);

        cities = handler.retrieveCities();

        Log.d("demo","Cities in main: "+cities.toString());


        client = new OkHttpClient();
        try{
            cityName = preferences.getString("CITY_NAME", "");
            countryCode = preferences.getString("COUNTRY_CODE", "");
            countryName = preferences.getString("COUNTRY_NAME", "");
            cityKey = preferences.getString("CITY_KEY", "");

            if(!(cityKey.equals("") && cityName.equals("") && countryCode.equals(""))){ //&& countryName.equals("")
                Log.d("demo","if true");

                displayCurrentWeather();

            }else{
                Log.d("demo","else true");

                //add preferences if no previous preferences found
                linearLayout.removeAllViews();
                TextView tv=new TextView(this);
                tv.setText("Current city not yet set");
                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                tv.setGravity(View.TEXT_ALIGNMENT_CENTER);
                Button btnSetCity = new Button(this);
                btnSetCity.setText("Set City");
                btnSetCity.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                linearLayout.addView(tv);
                linearLayout.addView(btnSetCity);

                btnSetCity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        final View view = getLayoutInflater().inflate(R.layout.alert_set_city,null);
                        builder.setTitle("Enter city details")
                        .setView(view)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("demo","Cancel clicked");

                            }
                        }).setPositiveButton("Set", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("demo","Set clicked");
                                et_city = (EditText) view.findViewById(R.id.alert_city_name);
                                et_country = (EditText) view.findViewById(R.id.alert_country_name);


                                cityName = et_city.getText().toString().trim();
                                countryCode = et_country.getText().toString().trim();


                                //Okhttp client call and gson parsing for retrieving key
                                //client = new OkHttpClient();
                                Request request = new Request.Builder()
                                        .url("http://dataservice.accuweather.com/locations/v1/"+countryCode.trim()+"/search?apikey="+apiKey.trim()+"&q="+cityName.trim())
                                        .build();

                                pg.setCancelable(false);
                                pg.show();


                                client.newCall(request).enqueue(new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {
                                        MainActivity.this.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
                                            }});
                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        // Log.d("demo", response.body().string());
                                        String jsonString = response.body().string();
                                        Log.d("demo","1st api="+jsonString);
                                        gson = new Gson();
                                        ResponseLocationApi[] locApi=gson.fromJson(jsonString,ResponseLocationApi[].class);
                                        Log.d("demo","Loc API: "+locApi.toString());
                                        cityKey = locApi[0].getKey();
                                        Log.d("demo","key="+cityKey);
                                        //flag =true;
                                        if(cityKey!=null || !(cityKey.equals(""))) {
                                            editor.putString("CITY_NAME", cityName);
                                            editor.putString("COUNTRY_CODE", countryCode);
                                            editor.putString("CITY_KEY", cityKey);
                                            editor.apply();


                                        }
                                        if(cityKey!=null || !(cityKey.equals(""))){
                                            //Toast.makeText(MainActivity.this, "Current city details saved", Toast.LENGTH_SHORT).show();
                                            //cityKey, apiKEy
                                            displayCurrentWeather();
                                        }else{
                                            Toast.makeText(MainActivity.this, "City not found", Toast.LENGTH_SHORT).show();
                                        }
                                        pg.dismiss();
                                    }
                                });



                        }
                                           });

                        builder.create().show();

                    }
                });





            }


        }catch(NullPointerException e){
            e.printStackTrace();
        }


        //Use this in Search button
        /*editor.putString("CITY_NAME",cityName);
        editor.putString("COUNTRY_CODE",countryCode);
        editor.putString("CITY_KEY",cityKey);
        editor.apply();*/



        findViewById(R.id.btn_MainSearchCity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextCity = (EditText) findViewById(R.id.et_MainCity);
                EditText editTextCountry = (EditText) findViewById(R.id.et_MainCountry);
                cityName = editTextCity.getText().toString().trim();
                countryCode = editTextCountry.getText().toString().trim();
                //API CALL for
                Intent intent = new Intent(getApplicationContext(),ForecastActivity.class);
                intent.putExtra("CITY_NAME",cityName);
                intent.putExtra("COUNTRY_CODE",countryCode);
                startActivity(intent);
            }
        });

        if(cities.size()==0) {
            Log.d("demo","cities is null");
            linearSavedCity.removeAllViews();
            TextView textViewSavedCities = new TextView(MainActivity.this);
            textViewSavedCities.setText("There are no cities to display.\nSearch the city from the search box and save.");
            textViewSavedCities.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            linearSavedCity.addView(textViewSavedCities);

        }else{
            Log.d("demo","cities is not null");
            /*rvSavedCities = new RecyclerView(MainActivity.this);
            VerticalRecyclerAdapter adapter = new VerticalRecyclerAdapter(MainActivity.this,cities);
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
            rvSavedCities.setLayoutManager(layoutManager);
            rvSavedCities.setAdapter(adapter);
            linearSavedCity.removeAllViews();
            linearSavedCity.addView(rvSavedCities);*/
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.settings) {
            Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
            startActivity(intent);
            // Intent to Preference Activity.
        }

        return super.onOptionsItemSelected(item);
    }

    private String generateUrl(int icon){
        StringBuilder temp = new StringBuilder();
        if(icon<10){
            temp.append("0");
            temp.append(String.valueOf(icon));
        }else{
           temp.append(String.valueOf(icon));
        }
        return "http://developer.accuweather.com/sites/default/files/"+temp.toString()+"-s.png";
    }

    void displayCurrentWeather(){


        //client = new OkHttpClient();
        Request request1 = new Request.Builder()
                .url("http://dataservice.accuweather.com/currentconditions/v1/"+cityKey.trim()+"?apikey="+apiKey.trim())
                .build();
        //http://dataservice.accuweather.com/currentconditions/v1/349818?apikey=BotmqfxxxoKXpCFHuiD2U58aRCE1Fq2H
        client.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Failed Current Forecast API", Toast.LENGTH_SHORT).show();
                    }});
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Log.d("demo", response.body().string());
                String jsonString = response.body().string();
                Log.d("demo","json API2 Current="+jsonString);
                gson = new Gson();
                ResponseCurrentForecastApi[] currentForecast =gson.fromJson(jsonString,ResponseCurrentForecastApi[].class);
                Log.d("demo","current forecast="+currentForecast[0].toString());
                currentCityWeatherText = currentForecast[0].getWeatherText();
                currentCityTempCel = currentForecast[0].getTemperature().getMetric().getValue();
                currentCityTempF = currentForecast[0].getTemperature().getImperial().getValue();
                currentCityWeatherIcon = currentForecast[0].getWeatherIcon();
                currentCityUpdatedTime = currentForecast[0].getLocalObservationDateTime();

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(true) {
                            View view1 = getLayoutInflater().inflate(R.layout.current_city_forecast_layout, null);
                            linearLayout.removeAllViews();
                            TextView textView1 = (TextView) view1.findViewById(R.id.textViewCurrentCityName);
                            textView1.setText(cityName + ", " + countryCode);
                            TextView textView2 = (TextView) view1.findViewById(R.id.textViewCurrentCityWeatherText);
                            textView2.setText(currentCityWeatherText);
                            ImageView iv = (ImageView) view1.findViewById(R.id.imageViewCurrentCityIcon);
                            String url = generateUrl(currentCityWeatherIcon);
                            Picasso.with(getApplicationContext()).load(url).into(iv);
                            TextView textView3 = (TextView) view1.findViewById(R.id.textViewCurrentCityTemperature);
                            if (tempUnit) {
                                //F
                                textView3.setText("Temperature: " + currentCityTempF + "°F");
                            } else {
                                //Cel
                                textView3.setText("Temperature: " + currentCityTempCel + "°C");
                            }

                            //2017-04-06T18:03:00-04:00


                            Date date = null;
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            try {
                                String ReDate = currentCityUpdatedTime.substring(0, 10) + " " + currentCityUpdatedTime.substring(11, 18);
                                Log.d("demo", "format:" + ReDate);
                                date = formatter.parse(ReDate);
                                Log.d("demo", "Updated:" + currentCityUpdatedTime + "    Date:" + date);

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String timeFormat = new PrettyTime(new Locale("")).format(date);
                            TextView textView4 = (TextView) view1.findViewById(R.id.textViewCurrentCityUpdatedTime);
                            textView4.setText("Updated " + timeFormat);

                            linearLayout.addView(view1);
                        }
                    }});


            }
        });
    }

    @Override
    public void getCitiesDetails(ArrayList<CityDetails> cities) {
        this.cities = cities;
        Log.d("demo","Cities:"+cities.toString());

        Collections.sort(cities,CityDetails.favComparator);

        rvSavedCities = new RecyclerView(MainActivity.this);
        rvSavedCities.setLongClickable(true);
        VerticalRecyclerAdapter adapter = new VerticalRecyclerAdapter(MainActivity.this,cities);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        rvSavedCities.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();
        rvSavedCities.setAdapter(adapter);
        linearSavedCity.removeAllViews();
        linearSavedCity.addView(rvSavedCities);

    }
}
