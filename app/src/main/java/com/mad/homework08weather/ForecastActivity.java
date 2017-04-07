package com.mad.homework08weather;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ForecastActivity extends AppCompatActivity implements RecyclerAdapter.Idata{
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    OkHttpClient client;
    String apiKey = "naqiA2sdihu1iBA6uYB4GYXHBCAvpDRO";
    ProgressDialog pg;
    String cityName,countryCode,cityKey;
    SharedPreferences.Editor editor;
    SharedPreferences preferences;
    ArrayList<ResponseFiveDayApi.DailyForecastsBean> list = new ArrayList<ResponseFiveDayApi.DailyForecastsBean>();
    int position =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarForecastActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_title);


        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        GridLayoutManager gridLayoutManager =  new GridLayoutManager(this,1,GridLayoutManager.HORIZONTAL,false);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView5DayForecast);

        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setLayoutManager(gridLayoutManager);


        pg = new ProgressDialog(ForecastActivity.this);
        client = new OkHttpClient();
        if(!getIntent().getExtras().getString("CITY_NAME").equals("") && !getIntent().getExtras().getString("COUNTRY_CODE").equals("")){
            cityName = getIntent().getExtras().getString("CITY_NAME");
            countryCode = getIntent().getExtras().getString("COUNTRY_CODE");

            Request request = new Request.Builder()
                    .url("http://dataservice.accuweather.com/locations/v1/"+countryCode.trim()+"/search?apikey="+apiKey.trim()+"&q="+cityName.trim())
                    .build();

            pg.setCancelable(false);
            pg.show();


            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    ForecastActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ForecastActivity.this, "City not found", Toast.LENGTH_SHORT).show();
                            finish();
                        }});
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    // Log.d("demo", response.body().string());
                    String jsonString = response.body().string();
                    Log.d("demo","1st api="+jsonString);
                    Gson gson = new Gson();
                    ResponseLocationApi[] locApi=gson.fromJson(jsonString,ResponseLocationApi[].class);
                    Log.d("demo","Loc API: "+locApi.toString());
                    cityKey = locApi[0].getKey();
                    Log.d("demo","key="+cityKey);
                    //flag =true;
                    if(cityKey!=null || !(cityKey.equals(""))) {
                        /*editor.putString("CITY_NAME", cityName);
                        editor.putString("COUNTRY_CODE", countryCode);
                        editor.putString("CITY_KEY", cityKey);
                        editor.apply();*/

                        ForecastActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ForecastActivity.this, "cityKey="+cityKey, Toast.LENGTH_SHORT).show();
                                //http://dataservice.accuweather.com/forecasts/v1/daily/5day/{CITY_UNIQUE_KEY}?apikey={YOUR_API_KEY}
                                display5dayForecast();
                                pg.dismiss();
                            }});


                    }

                }
            });


        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_5day_forecast,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.saveCity) {
            Toast.makeText(this, "Save City clicked", Toast.LENGTH_SHORT).show();

            //Add city details to Firebase database
        }
        if(item.getItemId()==R.id.setCurrentCity) {
            Toast.makeText(this, "Set as Current City clicked", Toast.LENGTH_SHORT).show();
            // Add to Shared preferences
        }
        if(item.getItemId()==R.id.settingForecast) {
            Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
            /*Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
            startActivity(intent);*/
            // Intent to Preference Activity.
        }

        return super.onOptionsItemSelected(item);
    }

    void display5dayForecast(){

        //client = new OkHttpClient();
        Request request1 = new Request.Builder()
                .url("http://dataservice.accuweather.com/forecasts/v1/daily/5day/"+cityKey.trim()+"?apikey="+apiKey.trim())
                .build();
        //http://dataservice.accuweather.com/forecasts/v1/daily/5day/{CITY_UNIQUE_KEY}?apikey=BotmqfxxxoKXpCFHuiD2U58aRCE1Fq2H
        client.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ForecastActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ForecastActivity.this, "Failed 5Day Forecast API", Toast.LENGTH_SHORT).show();
                    }});
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Log.d("demo", response.body().string());
                String jsonString = response.body().string();
                Log.d("demo","json API2 Current="+jsonString);
                Gson gson = new Gson();
                final ResponseFiveDayApi responseFiveDayForecast =gson.fromJson(jsonString,ResponseFiveDayApi.class);
                Log.d("demo","current forecast="+responseFiveDayForecast.toString());
                /*currentCityWeatherText = currentForecast[0].getWeatherText();
                currentCityTempCel = currentForecast[0].getTemperature().getMetric().getValue();
                currentCityTempF = currentForecast[0].getTemperature().getImperial().getValue();
                currentCityWeatherIcon = currentForecast[0].getWeatherIcon();
                currentCityUpdatedTime = currentForecast[0].getLocalObservationDateTime();*/

                ForecastActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(true) {
                            Toast.makeText(ForecastActivity.this, "In UI thread 5 day forecast", Toast.LENGTH_SHORT).show();
                            TextView textViewForecastTitle = (TextView) findViewById(R.id.textViewForecastTitle);
                            textViewForecastTitle.setText("Daily Forecast for "+cityName+", "+countryCode);
                            TextView textViewHeadline = (TextView) findViewById(R.id.textViewHeadline);
                            textViewHeadline.setText(responseFiveDayForecast.getHeadline().getText());
                            list = (ArrayList<ResponseFiveDayApi.DailyForecastsBean>) responseFiveDayForecast.getDailyForecasts();
                            Log.d("demo",list.toString());
                            adapter = new RecyclerAdapter(ForecastActivity.this,getApplicationContext(),list);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                            display(position);

                            findViewById(R.id.textViewExtendedForecast).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String link = responseFiveDayForecast.getHeadline().getMobileLink();
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse(link));
                                    startActivity(intent);
                                }
                            });




                        }
                    }});


            }
        });
    }

    public void display(final int position){
        Toast.makeText(ForecastActivity.this, "pos="+position, Toast.LENGTH_SHORT).show();

        //textViewDateLabel, textViewTemperature, imageViewDay, textViewForecastDay, imageViewNight, textViewForecastNight
        TextView textViewDateLabel = (TextView) findViewById(R.id.textViewDateLabel);
        //2017-04-06T18:03:00-04:00
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String ReDate = list.get(position).getDate().substring(0, 10);
            Log.d("demo", "format:" + ReDate);
            date = formatter.parse(ReDate);
            DateFormat format1 = new SimpleDateFormat("MMMM dd, yyyy");
            String dateString = format1.format(date);
            Log.d("demo", "Updated:" + list.get(position).getDate() + "    Date:" + date);
            //String timeFormat = new PrettyTime(new Locale("")).format(date);
            textViewDateLabel.setText("Forecast on "+dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Temperature: ");
        sb.append(String.valueOf(list.get(position).getTemperature().getMaximum().getValue()));
        sb.append("° / ");
        sb.append(String.valueOf(list.get(position).getTemperature().getMinimum().getValue()));
        sb.append("°");
        TextView textViewTemperature = (TextView) findViewById(R.id.textViewTemperature);
        textViewTemperature.setText(sb.toString());

        ImageView imageViewDay = (ImageView) findViewById(R.id.imageViewDay);
        ImageView imageViewNight = (ImageView) findViewById(R.id.imageViewNight);
        TextView textViewForecastDay = (TextView) findViewById(R.id.textViewForecastDay);
        TextView textViewForecastNight = (TextView) findViewById(R.id.textViewForecastNight);

        Picasso.with(getApplicationContext()).load(generateUrl(list.get(position).getDay().getIcon())).into(imageViewDay);
        Picasso.with(getApplicationContext()).load(generateUrl(list.get(position).getNight().getIcon())).into(imageViewNight);
        textViewForecastDay.setText(list.get(position).getDay().getIconPhrase());
        textViewForecastNight.setText(list.get(position).getNight().getIconPhrase());

        findViewById(R.id.textViewMoreDetails).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = list.get(position).getMobileLink();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(link));
                startActivity(intent);
            }
        });
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


}
