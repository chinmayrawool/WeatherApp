package com.mad.homework08weather;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    String cityName,countryCode,cityKey,countryName;
    boolean tempUnit = false; //false=Celsius, true= fahrenheit
    Location location;
    SharedPreferences.Editor editor;
    SharedPreferences preferences;
    Gson gson;
    String apiKey = "naqiA2sdihu1iBA6uYB4GYXHBCAvpDRO";
    EditText et_city,et_country;
    boolean set= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_title);




        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        try{
            cityName = preferences.getString("CITY_NAME", "");
            countryCode = preferences.getString("COUNTRY_CODE", "");
            countryName = preferences.getString("COUNTRY_NAME", "");
            cityKey = preferences.getString("CITY_KEY", "");
            if(!(cityKey.equals("") && cityName.equals("") && countryCode.equals(""))){ //&& countryName.equals("")
                Log.d("demo","if true");
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearMainDisplay);
                //Current Condition API to get the temperature of the location.
                //Display in the linear layout.
            }else{
                Log.d("demo","else true");
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearMainDisplay);
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

                                ProgressDialog pg = new ProgressDialog(MainActivity.this);
                                pg.setCancelable(false);
                                pg.show();
                                //Okhhtp client call and gson parsing for retrieving key
                                OkHttpClient client = new OkHttpClient();
                                Request request = new Request.Builder()
                                        .url("http://dataservice.accuweather.com/locations/v1/"+countryCode+"/search?apikey="+apiKey+"&q="+cityName)
                                        .build();

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
                                        gson = new Gson();
                                        ResponseLocationApi[] locApi=gson.fromJson(jsonString,ResponseLocationApi[].class);
                                        cityKey = locApi[0].getKey();
                                        Log.d("demo","key="+cityKey);
                                        if(cityKey!=null || !(cityKey.equals(""))) {
                                            editor.putString("CITY_NAME", cityName);
                                            editor.putString("COUNTRY_CODE", countryCode);
                                            editor.putString("CITY_KEY", cityKey);
                                            editor.apply();
                                            set = true;

                                        }else{
                                            set=false;

                                        }

                                    }
                                });

                                pg.dismiss();
                     }
                        });

                        builder.create().show();
                    }
                });
                if(set){
                    Toast.makeText(MainActivity.this, "Current city details saved", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "City not found", Toast.LENGTH_SHORT).show();
                }
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
                countryName = editTextCountry.getText().toString().trim();
                //API CALL for
            }
        });




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

            // Intent to Preference Activity.
        }

        return super.onOptionsItemSelected(item);
    }

}
