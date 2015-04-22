package nl.sjtek.smartmobile.practicum2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class DetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        int chosenCriminalPosition = getIntent().getExtras().getInt("chosenCriminalPosition");

        CriminalProvider criminalProvider = new CriminalProvider(this);
        Criminal selectedCriminal = criminalProvider.GetCriminal(chosenCriminalPosition);
        List<Crime> crimes = selectedCriminal.crimes;

        ListView listViewCrimes = (ListView) findViewById(R.id.listViewCrimes);
        listViewCrimes.setAdapter(new CrimesListAdapter(this, crimes));

        TextView criminalName = (TextView) this.findViewById(R.id.mugNameDetail);
        criminalName.setText(selectedCriminal.name);

        TextView criminalBounty = (TextView) this.findViewById(R.id.mugBountyDetail);
        criminalBounty.setText(String.valueOf(selectedCriminal.getBountyInDollars()));

        ImageView criminalImage = (ImageView) this.findViewById(R.id.mugShotDetail);
        criminalImage.setImageDrawable(selectedCriminal.mugshot);


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
