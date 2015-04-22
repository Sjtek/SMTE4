package nl.sjtek.smartmobile.practicum2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class ActivityCriminalDetails extends Activity {

    public final static String EXTRA_NAME = "extra-name";
    private Criminal criminal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criminal_details);
        String criminalName = getIntent().getExtras().getString(EXTRA_NAME, "error");
        criminal = new Criminal();
        criminal.name = criminalName;
        TextView textViewName = (TextView) findViewById(R.id.textViewName);
        textViewName.setText(criminal.name);
    }
}
