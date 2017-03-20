package com.example.android.quakereport;

/**
 * Created by simranjain1507 on 08/02/17.
 */
public class Earthquake {

    private double mag;
    private String place;
    private Long time;
    private String uri;


    public Earthquake( String Gplace,Long Gtime,double Gmag,String Guri)
    {
        mag=Gmag;
        place=Gplace;
        time=Gtime;
        uri=Guri;
    }

   public double getMag()
    {
        return mag;
    }
    public String getPlace()
    {
        return place;
    }


    public Long getTime()
    {
        return time;
    }

    public String getUrl()
    {
        return uri;
    }


}
