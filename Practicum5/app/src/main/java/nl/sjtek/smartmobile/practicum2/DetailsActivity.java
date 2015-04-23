package nl.sjtek.smartmobile.practicum2;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;


public class DetailsActivity extends Activity implements LocationListener {

    private Criminal selectedCiminal;
    private LocationManager locationManager;
    private boolean checkLocation = false;
    private TextView dienTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        dienTextView = (TextView) findViewById(R.id.dienAfstandTextView);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        int chosenCriminalPosition = getIntent().getExtras().getInt("chosenCriminalPosition");

        CriminalProvider criminalProvider = new CriminalProvider(this);
        selectedCiminal = criminalProvider.GetCriminal(chosenCriminalPosition);
        List<Crime> crimes = selectedCiminal.crimes;

        ListView listViewCrimes = (ListView) findViewById(R.id.listViewCrimes);
        listViewCrimes.setAdapter(new CrimesListAdapter(this, crimes));

        TextView criminalName = (TextView) this.findViewById(R.id.mugNameDetail);
        criminalName.setText(selectedCiminal.name);

        TextView criminalBounty = (TextView) this.findViewById(R.id.mugBountyDetail);
        criminalBounty.setText(String.valueOf(selectedCiminal.getBountyInDollars()));

        ImageView criminalImage = (ImageView) this.findViewById(R.id.mugShotDetail);
        criminalImage.setImageDrawable(selectedCiminal.mugshot);


    }

    public void onSCAToggle(View view) {
        checkLocation = ((Switch) view).isChecked();

        if (checkLocation) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0,this);
        } else {
            locationManager.removeUpdates(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkLocation) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0,this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        float distance = location.distanceTo(selectedCiminal.lastKnownLocation);
        dienTextView.setText("Distance: " + distance + "m");
        if (distance < 100 && checkLocation) {
            long[] pattern = new long[] { 20, 50, 100, 200, 40, 100 };
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator.hasVibrator()) {
                vibrator.vibrate(pattern, 2);
            }
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private class CrimesListAdapter extends ArrayAdapter<Crime> {

        private Context context;
        private List<Crime> crimes;

        public CrimesListAdapter(Context context, List<Crime> crimes) {
            super(context, R.layout.criminallistitem, crimes);

            this.context = context;
            this.crimes = crimes;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            Crime requestedCrime = crimes.get(position);

            View criminalView = inflater.inflate(R.layout.crime_list_item, null);

            TextView textViewName = (TextView) criminalView.findViewById(R.id.textViewName);
            TextView textViewBounty = (TextView) criminalView.findViewById(R.id.textViewBounty);
            textViewName.setText(requestedCrime.name);
            textViewBounty.setText(String.valueOf(requestedCrime.bountyInDollars));


            return criminalView;
        }

    }
}
