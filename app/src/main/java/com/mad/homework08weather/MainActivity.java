package com.mad.homework08weather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String cityName,countryCode,cityKey,countryName;
    boolean tempUnit = false; //false=Celsius, true= fahrenheit
    Location location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_title);


        SharedPreferences.Editor editor;
        SharedPreferences preferences;

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        try{
            cityName = preferences.getString("CITY_NAME", "");
            countryCode = preferences.getString("COUNTRY_CODE", "");
            countryName = preferences.getString("COUNTRY_NAME", "");
            cityKey = preferences.getString("CITY_KEY", "");
            if(!(cityKey.equals("") || cityName.equals("") || countryCode.equals("")) || countryName.equals("")){
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearMainDisplay);
                //Current Condition API to get the temperature of the location.
                //Display in the linear layout.
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
