package com.mad.homework08weather;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by neha5 on 07-04-2017.
 */

public class FirebaseHandler {
    DatabaseReference db;
    ArrayList<CityDetails> cities = new ArrayList<CityDetails>();
    ICities mActivity;
    boolean saved = false;

    FirebaseHandler(DatabaseReference db, ICities mActivity){
        this.db = db;
        this.mActivity = mActivity;
    }

    FirebaseHandler(DatabaseReference db){
        this.db = db;

    }

    boolean saveCity(CityDetails city){
        saved =false;

        if(city == null){
            saved = false;
        }else{
            db.child("City").child(city.getCityKey()).setValue(city);

        }
        Log.d("demo","saveCity returned saved ="+saved);
        return saved;
    }

    void removeCity(CityDetails city){

        Query cityKeyQuery = db.child("City").orderByChild("cityKey").equalTo(city.getCityKey());
        cityKeyQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    ds.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public ArrayList<CityDetails> retrieveCities(){
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(!saved) {
                    saved = true;
                }
                cities = getData(dataSnapshot);
                Log.d("demo","onchildadded returned"+db.toString()+" "+saved);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                if(saved){
                    saved = true;
                }else{
                    saved = false;
                }

                cities = getData(dataSnapshot);
                Log.d("demo","onchildchanged returned"+saved);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                cities = getData(dataSnapshot);
                Log.d("demo","onChildRemoved");
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //db.child("City").child("favorite").orderByValue().equalTo(true);
        Log.d("demo","cities in retrieve details "+cities.toString()+db.toString());
        Log.d("demo","Before return of cities");

        return cities;
    }

    private ArrayList<CityDetails> getData(DataSnapshot dataSnapshot){
        cities = new ArrayList<>();

        for (DataSnapshot ds: dataSnapshot.getChildren()) {

            CityDetails city = new CityDetails(ds.getValue(CityDetails.class).getCityKey(),ds.getValue(CityDetails.class).getCityName(),ds.getValue(CityDetails.class).getCountryCode(),ds.getValue(CityDetails.class).getTempCel(),ds.getValue(CityDetails.class).getLastUpdated(),ds.getValue(CityDetails.class).isFavorite());
            Log.d("demo","In Handler, City:"+city.toString());
            cities.add(city);
        }
        mActivity.getCitiesDetails(cities);
        return cities;
    }


    public interface ICities{
        public void getCitiesDetails(ArrayList<CityDetails> cities);
    }
}
