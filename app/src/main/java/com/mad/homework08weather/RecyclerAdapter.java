package com.mad.homework08weather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Chinmay Rawool on 3/20/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<ResponseFiveDayApi.DailyForecastsBean> mData;
    // Store the context for easy access
    private Context mContext;
    ForecastActivity activity;

    public RecyclerAdapter(ForecastActivity activity, Context mContext, List<ResponseFiveDayApi.DailyForecastsBean> mData ) {
        this.mData = mData;
        this.mContext = mContext;
        this.activity = activity;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageViewIcon;
        public TextView textViewDate;

        public ViewHolder(View itemView) {
            super(itemView);
            //list view
            imageViewIcon = (ImageView) itemView.findViewById(R.id.imageViewCardIcon);
            textViewDate = (TextView) itemView.findViewById(R.id.textViewCardDate);

            //grid view
            /*imageView = (ImageView) itemView.findViewById(R.id.iv_image);
            nameTextView = (TextView) itemView.findViewById(R.id.grid_tv1);
            descriptionTextView = (TextView) itemView.findViewById(R.id.grid_tv2);
            linear = (LinearLayout) itemView.findViewById(R.id.grid_linear_insert);
            imageButton = (ImageButton) itemView.findViewById(R.id.grid_imagebtn);*/
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);
        //View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ResponseFiveDayApi.DailyForecastsBean object = mData.get(position);
        TextView tv1 = holder.textViewDate;
        ImageView iv = holder.imageViewIcon;
        try {
            //2017-04-06T18:03:00-04:00
            Date date = null;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                String ReDate = object.getDate().substring(0, 10);
                Log.d("demo", "format:" + ReDate);
                date = formatter.parse(ReDate);
                DateFormat format1 = new SimpleDateFormat("dd MMMM, yyyy");
                String dateString = format1.format(date);
                Log.d("demo", "Updated:" + object.getDate() + "    Date:" + date);
                //String timeFormat = new PrettyTime(new Locale("")).format(date);
                tv1.setText(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }catch(NullPointerException e){
            e.printStackTrace();
        }
        int icon = object.getDay().getIcon();
        Picasso.with(mContext).load(generateUrl(icon)).into(iv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, position+"", Toast.LENGTH_SHORT).show();

                activity.display(position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
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

    public interface Idata{
        public void display(int position);
    }

}
