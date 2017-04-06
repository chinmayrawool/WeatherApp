package com.mad.homework08weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class ForecastActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView5DayForecast);
        //RecyclerView.LayoutManager layoutManager =
    }
}
