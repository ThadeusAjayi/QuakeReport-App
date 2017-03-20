package com.example.android.quakereport;

import android.app.Activity;
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

import static java.lang.Math.floor;

/**
 * Created by simranjain1507 on 08/02/17.
 */


public class EarthquakeAdapter extends ArrayAdapter<Earthquake>{

    String primaryLoaction;
    String location_offset;
    private static final String location_seperator=" of ";



    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    public View getView(int position, View convertview, ViewGroup parent) {
        View listItemView = convertview;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list, parent, false);

        }

        Earthquake currentQuake = getItem(position);

        TextView mag = (TextView) listItemView.findViewById(R.id.mag);
        String formattedMag = formatMag(currentQuake.getMag());
        mag.setText(formattedMag);

//        // Fetch the background from the TextView, which is a GradientDrawable.
//        GradientDrawable magnitudeCircle = (GradientDrawable) listItemView.getBackground();
//        // Get the appropriate background color based on the current earthquake magnitude
//        int magnitudeColor = getMagnitudeColor(currentQuake.getMag());
//        // Set the color on the magnitude circle
//        magnitudeCircle.setColor(magnitudeColor);
//

        String originalLocation = currentQuake.getPlace();

        if (originalLocation.contains(location_seperator)) {
            String parts[] = originalLocation.split(location_seperator);
            location_offset = parts[0] + location_seperator;
            primaryLoaction = parts[1];
        } else {
            location_offset = getContext().getString(R.string.near_the);
            primaryLoaction = originalLocation;
        }

        TextView offset = (TextView) listItemView.findViewById(R.id.location_offset);
        offset.setText(location_offset);

        TextView primary = (TextView) listItemView.findViewById(R.id.primary_location);
        primary.setText(primaryLoaction);

        Date dateObject = new Date(currentQuake.getTime());
        TextView date = (TextView) listItemView.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);
        date.setText(formattedDate);

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formateTime(dateObject);
        timeView.setText(formattedTime);




        return listItemView;

    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);
    }

    private String formatMag(Double mag) {
        DecimalFormat magn=new DecimalFormat("0.0");
        return magn.format(mag);
    }

    private String formateTime(Date dateObject) {
        SimpleDateFormat timeFormat=new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("LLL dd,yyyy");
        return dateFormat.format(dateObject);
    }


}

