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


    public VerticalRecyclerAdapter(Context mContext, List<CityDetails> mData ) {
        this.mData = mData;
        this.mContext = mContext;

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
        CityDetails city = mData.get(position);


        holder.textViewCityCountry.setText(city.getCityName()+", "+city.getCountryCode());
        if(MainActivity.tempUnit) { // true = F
            //holder.textViewTemp.setText("Temperature : " + city.getTempCel()+"°F");
        }else{
            holder.textViewTemp.setText("Temperature : " + city.getTempCel()+"°C");
        }
        holder.textViewUpdatedTime.setText("Last Updated : "+ city.getLastUpdated());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
