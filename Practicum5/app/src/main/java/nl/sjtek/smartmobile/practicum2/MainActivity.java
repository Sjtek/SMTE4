package nl.sjtek.smartmobile.practicum2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    CriminalProvider criminalProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mugList = (ListView)findViewById(R.id.mugList);
        criminalProvider = new CriminalProvider(this);
        mugList.setAdapter(new CriminalListAdapter(this, criminalProvider.GetCriminals() ));
        mugList.setOnItemClickListener(this);
    }
    ListView mugList;


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Criminal selectedCriminal = criminalProvider.GetCriminal(position);
        Intent posIntent = new Intent(this, DetailsActivity.class);
        posIntent.putExtra("chosenCriminalPosition", position);
        startActivity(posIntent);

    }
}
