package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReportAdapter extends ArrayAdapter<EarthQuakeData> {
    private Context context;
    private final String LOCATION_SEPERATOR = " of ";
    private ArrayList<EarthQuakeData> earthQuakeList;
    public ReportAdapter(Context context, ArrayList<EarthQuakeData> earthQuakeList){
        super(context, 0, earthQuakeList);
        this.context = context;
        this.earthQuakeList = earthQuakeList;
    }
    public ReportAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            v = LayoutInflater.from(context).inflate(R.layout.earthquake_list, parent, false);
        }
        TextView magTV = (TextView) v.findViewById(R.id.mag);
        TextView primaryLocationTV = (TextView) v.findViewById(R.id.primaryLocation);
        TextView locationOffset = (TextView) v.findViewById(R.id.locationOffset);
        TextView dateTV = (TextView) v.findViewById(R.id.date);
        TextView timeTV = (TextView) v.findViewById(R.id.time);
        GradientDrawable magnitudeCircle = (GradientDrawable) magTV.getBackground();


        long timeInMilliseconds = earthQuakeList.get(position).getDate();
        String date = formatDate(timeInMilliseconds);
        String time = formatTime(timeInMilliseconds);
        Double earthquakeMagnitude = earthQuakeList.get(position).getMag();
        String formattedMagnitude = formatMagnitude(earthquakeMagnitude);
        String location = earthQuakeList.get(position).getLocation();


        //set values to the various textviews
        magTV.setText(formattedMagnitude);
        magnitudeCircle.setColor(magnitudeCircleColor(earthquakeMagnitude));
        primaryLocationTV.setText(splitLocation(location)[1]);
        locationOffset.setText(splitLocation(location)[0]);
        dateTV.setText(date);
        timeTV.setText(time);

        return v;
    }

    private int magnitudeCircleColor(double magnitude){
        int magnitudeFloor = (int) Math.floor(magnitude);
        int color;
        switch (magnitudeFloor){
            case 0:
            case 1:
                color = R.color.magnitude1;
                break;
            case 2:
                color = R.color.magnitude2;
                break;
            case 3:
                color = R.color.magnitude3;
                break;
            case 4:
                color = R.color.magnitude4;
                break;
            case 5:
                color = R.color.magnitude5;
                break;
            case 6:
                color = R.color.magnitude7;
                break;
            case 7:
                color = R.color.magnitude8;
                break;
            case 8:
                color = R.color.magnitude8;
                break;
            case 9:
                color = R.color.magnitude9;
                break;
            default:
                color = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(context, color);
    }

    private String[] splitLocation(String location){
        String[] locationArray = new String[2];
        if(location.contains(LOCATION_SEPERATOR)){
            locationArray = location.split(LOCATION_SEPERATOR);
            locationArray[0] = locationArray[0] + LOCATION_SEPERATOR;
        }
        else{
            locationArray[0] =  "Near the";
            locationArray[1] = location;
        }
        return locationArray;
    }

    private String formatDate(long date){

        Date dateObject = new Date(date);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("LLL dd, yyyy");
        String dateToDisplay = dateFormatter.format(dateObject);
        return dateToDisplay;
    }
    private String formatTime(long date){

        Date dateObject = new Date(date);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("h:mm a");
        String timeToDisplay = dateFormatter.format(dateObject);
        return timeToDisplay;
    }

    private String formatMagnitude(double magnitude){
        DecimalFormat formatter  = new DecimalFormat("0.0");
        return formatter.format(magnitude);
    }
}
