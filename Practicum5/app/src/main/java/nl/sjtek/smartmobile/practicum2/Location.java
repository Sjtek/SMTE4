package nl.sjtek.smartmobile.practicum2;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class GPSClass implements LocationListener {

    public void onLocationChanged(Location location) {
        // Called when a new location is found by the network location provider.
        Log.i("Message: ","Location changed, " + location.getAccuracy() + " , " + location.getLatitude()+ "," + location.getLongitude());
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {}
    public void onProviderEnabled(String provider) {}
    public void onProviderDisabled(String provider) {}
    LocationManager locationManager;

    locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0,this);

}