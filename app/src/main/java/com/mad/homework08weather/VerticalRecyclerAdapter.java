package com.mad.homework08weather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by neha5 on 07-04-2017.
 */

public class VerticalRecyclerAdapter extends RecyclerView.Adapter<VerticalRecyclerAdapter.ViewHolder> {

    private List<CityDetails> mData;
    // Store the context for easy access
    private Context mContext;
    FirebaseHandler handler;
    DatabaseReference db;


    public VerticalRecyclerAdapter(Context mContext, List<CityDetails> mData ) {
        this.mData = mData;
        this.mContext = mContext;
        db = FirebaseDatabase.getInstance().getReference();
        handler = new FirebaseHandler(db);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageViewFavorite;
        public TextView textViewCityCountry;
        public TextView textViewTemp;
        public TextView textViewUpdatedTime;

        public ViewHolder(View itemView) {
            super(itemView);
            //list view
            imageViewFavorite = (ImageView) itemView.findViewById(R.id.iv_favorite);
            textViewCityCountry = (TextView) itemView.findViewById(R.id.textViewSavedCityNameCountry);
            textViewTemp = (TextView) itemView.findViewById(R.id.textViewSavedCityTemp);
            textViewUpdatedTime = (TextView) itemView.findViewById(R.id.textViewSavedCityUpdated);

        }
    }


    @Override
    public VerticalRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.saved_cities_layout, parent, false);
        //View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_layout, parent, false);

        VerticalRecyclerAdapter.ViewHolder vh = new VerticalRecyclerAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final VerticalRecyclerAdapter.ViewHolder holder, final int position) {
        final CityDetails city = mData.get(position);


        holder.textViewCityCountry.setText(city.getCityName()+", "+city.getCountryCode());
        if(MainActivity.tempUnit) { // true = F
            double tempinF = (Float.parseFloat(city.getTempCel())*1.8)+32;
            holder.textViewTemp.setText("Temperature : " + tempinF+"°F");
        }else{ //false = C
            holder.textViewTemp.setText("Temperature : " + city.getTempCel()+"°C");
        }
        holder.textViewUpdatedTime.setText("Last Updated : "+ city.getLastUpdated());

        if(city.isFavorite()){
            holder.imageViewFavorite.setImageResource(R.drawable.star_gold);
        }else{
            holder.imageViewFavorite.setImageResource(R.drawable.star_gray);
        }

        holder.imageViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(city.isFavorite()){
                    Log.d("demo","Clicked favorite= true");
                    city.setFavorite(false);
                    handler.saveCity(city);
                    holder.imageViewFavorite.setImageResource(R.drawable.star_gray);
                }else{
                    Log.d("demo","Clicked favorite= true");
                    city.setFavorite(true);
                    handler.saveCity(city);
                    holder.imageViewFavorite.setImageResource(R.drawable.star_gold);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(mContext, "Long clicked", Toast.LENGTH_SHORT).show();
                handler.removeCity(city);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
