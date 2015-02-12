package sachan.dheeraj.weatherapp;

/**
 * Created by dheeraj on 12/2/15.
 */

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment {

    private List<String> itemList;
    private ArrayAdapter<String> stringArrayAdapter;

    public ForecastFragment() {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            FetchWeatherTask fetchWeatherTask = new FetchWeatherTask();
            fetchWeatherTask.execute("94043");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // menu events will be handled by the fragment itself
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] list = {"one","two","three","four","five","six","seven","eight","nine","ten",
                "eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen"};

        itemList = new ArrayList<String>(Arrays.asList(list));
        stringArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.list_item_forecast, R.id.list_item_forecast_textview,itemList);

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView =(ListView)rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(stringArrayAdapter);

        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.forecastfragment, menu);
    }

    private static class FetchWeatherTask extends AsyncTask<String,Void,ArrayList<String>>{

        //google Best practises
        private static final String TAG = FetchWeatherTask.class.getSimpleName();

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            return null;
        }
    }
}