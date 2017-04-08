package com.mad.homework08weather;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by neha5 on 07-04-2017.
 */

public class FirebaseHandler {
    DatabaseReference db;
    ArrayList<CityDetails> cities = new ArrayList<CityDetails>();

    FirebaseHandler(DatabaseReference db){
        this.db = db;
    }

    boolean saveCity(CityDetails city){
        boolean saved = false;

        if(city == null){
            saved = false;
        }else{
            db.child("City").push().setValue(city);
            saved = true;
        }

        return saved;
    }

    public ArrayList<CityDetails> retrieveCities(){
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                cities = getData(dataSnapshot);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                cities = getData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Log.d("demo","cities in retrieve details "+cities.toString());
        Log.d("demo","Before return of cities");
        return cities;
    }

    private ArrayList<CityDetails> getData(DataSnapshot dataSnapshot){
        cities = new ArrayList<>();

        for (DataSnapshot ds: dataSnapshot.getChildren()) {

            CityDetails city = new CityDetails(ds.getValue(CityDetails.class).getCityKey(),ds.getValue(CityDetails.class).getCityName(),ds.getValue(CityDetails.class).getCountryCode(),ds.getValue(CityDetails.class).getTempCel(),ds.getValue(CityDetails.class).getLastUpdated(),ds.getValue(CityDetails.class).isFavorite());
            Log.d("demo","In Handler, City:"+city.toString());
            cities.add(city);
            Log.d("demo","Cities size="+cities.size());
        }

        return cities;
    }
}
