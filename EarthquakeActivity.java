/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    private static final String LOG_TAG = EarthquakeActivity.class.getName();
    private EarthquakeAdapter mAdapter;
    /** URL for earthquake data from the USGS dataset */
          private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);



        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
//        ArrayAdapter<Earthquake> adapter = new ArrayAdapter<Earthquake>(
//                this, android.R.layout.simple_list_item_1, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        mAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());
        earthquakeListView.setAdapter(mAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                      @Override
                                                      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                          Earthquake currentEarthquake=mAdapter.getItem(position);
                                                          Uri earthquakeuri=Uri.parse(currentEarthquake.getUrl());
                                                          Intent webIntent=new Intent(Intent.ACTION_VIEW,earthquakeuri);
                                                          startActivity(webIntent);
                                                      }
                                                  }
        );
        EarthquakeAsyncTask task=new EarthquakeAsyncTask();
        task.execute(USGS_REQUEST_URL);
    }
private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<Earthquake>>
{

    @Override
    protected List<Earthquake> doInBackground(String... urls) {
        if(urls.length<1 || urls[0]==null)
        {
            return null;
        }
        List<Earthquake> result=QueryUtils.fetchEarthquakeData(urls[0]);
        return result;
    }
    @Override
    protected void onPostExecute(List<Earthquake> data)
    {
            mAdapter.addAll(data);
       
        if(data!=null && data.isEmpty())
        {
            mAdapter.clear();
           
        }
    }
}
}

