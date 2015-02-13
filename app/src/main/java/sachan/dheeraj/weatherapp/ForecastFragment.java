package sachan.dheeraj.weatherapp;

/**
 * Created by dheeraj on 12/2/15.
 */

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import HttpAgent.HttpAgent;
import jsonParser.JsonHandler;
import models.WeatherApiResponse;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment {

    private List<String> itemList;
    private ArrayAdapter<String> stringArrayAdapter;

    public ForecastFragment() {
    }

    @Override
    public void onStart(){
        super.onStart();
        updateWeather();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            updateWeather();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String formatHighlow(double high,double low) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String unitType = sharedPreferences.getString(getString(R.string.temperature_key),getString(R.string.temp_default_value));

        if(unitType.equals(getString(R.string.temp_default_celsius))){
            Toast.makeText(getActivity(),getString(R.string.temp_default_celsius),Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getActivity(),getString(R.string.temp_default_fahrenheit),Toast.LENGTH_LONG).show();
        }
        return "done";
    }

    private void updateWeather(){
        //Toast.makeText(getActivity(), "updating data", Toast.LENGTH_LONG).show();
        FetchWeatherTask fetchWeatherTask = new FetchWeatherTask();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String location = prefs.getString("location", "none");
        Toast.makeText(getActivity(),location,Toast.LENGTH_LONG).show();
        fetchWeatherTask.execute(location);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // menu events will be handled by the fragment itself
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       /* String[] list = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen"};*/

        itemList = new ArrayList<String>(/*Arrays.asList(list)*/);
        stringArrayAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item_forecast,
                R.id.list_item_forecast_textview, itemList);

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(stringArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = stringArrayAdapter.getItem(position);
                Intent intent = new Intent(getActivity(),DetailActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT,data);
                startActivity(intent);
                //Toast.makeText(getActivity(),position+"*"+data,Toast.LENGTH_LONG).show();
            }
        });


        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.forecastfragment, menu);
    }

    private class FetchWeatherTask extends AsyncTask<String, Void, String[]> {

        //google Best practises
        private final String TAG = FetchWeatherTask.class.getSimpleName();

        @Override
        protected String[] doInBackground(String... params) {

            String response = HttpAgent.get("http://api.openweathermap.org/data/2.5/forecast/daily?q="+params[0]+"&mode=json&units=metric&cnt=7");
            Log.v(TAG, "-------------------------------------------------------");
            Log.v(TAG, response == null ? "no response" : response);
            Log.v(TAG, "-------------------------------------------------------");
            if (response != null) {
                WeatherApiResponse weatherApiResponse = JsonHandler.parse(response, WeatherApiResponse.class);
                return weatherApiResponse.getStringsArray();
            }
            return null;
        }

        @Override
        public void onPostExecute(String[] strings) {
            if (strings != null) {
                stringArrayAdapter.clear();
                    stringArrayAdapter.addAll(strings);
            }
        }
    }
}