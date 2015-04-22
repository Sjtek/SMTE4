package nl.sjtek.smartmobile.practicum2;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("InflateParams")  // See: https://code.google.com/p/android-developer-preview/issues/detail?id=1203
public class CriminalListAdapter extends ArrayAdapter<Criminal> {

	private Context context;
	private List<Criminal> criminals;

	public CriminalListAdapter(Context context, List<Criminal> criminals) {
		super(context, R.layout.criminallistitem, criminals);
		
		this.context = context;
		this.criminals = criminals;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		Criminal requestedCriminal = criminals.get(position);

        View criminalView = inflater.inflate(R.layout.criminallistitem, null);

        TextView criminalName = (TextView) criminalView.findViewById(R.id.mugName);
        criminalName.setText(requestedCriminal.name);
        TextView criminalBounty = (TextView) criminalView.findViewById(R.id.mugBounty);
        criminalBounty.setText(String.valueOf(requestedCriminal.getBountyInDollars()));

        ImageView criminalImage = (ImageView) criminalView.findViewById(R.id.mugShot);
        criminalImage.setImageDrawable(requestedCriminal.mugshot);


        return criminalView;
	}

}
