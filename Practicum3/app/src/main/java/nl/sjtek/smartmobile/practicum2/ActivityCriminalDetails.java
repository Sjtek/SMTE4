package nl.sjtek.smartmobile.practicum2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ActivityCriminalDetails extends Activity {

    public final static String EXTRA_NAME = "extra-name";
    private Criminal criminal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criminal_details);
        String criminalName = getIntent().getExtras().getString(EXTRA_NAME, "error");
        criminal = new Criminal(criminalName);
        TextView textViewName = (TextView) findViewById(R.id.textViewName);
        textViewName.setText(criminal.getName());
    }
}
