package com.mad.homework08weather;

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
            db.child("City").child(city.getCityKey()).setValue(city);
            saved = true;
        }

        return saved;
    }

    public ArrayList<CityDetails> retrieveCities(){
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getData(dataSnapshot);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getData(dataSnapshot);
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

        return cities;
    }

    private void getData(DataSnapshot dataSnapshot){
        cities.clear();

        for (DataSnapshot ds: dataSnapshot.getChildren()) {
            CityDetails city = ds.getValue(CityDetails.class);
            cities.add(city);
        }
    }
}
