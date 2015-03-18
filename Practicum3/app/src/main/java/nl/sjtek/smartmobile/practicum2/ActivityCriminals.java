package nl.sjtek.smartmobile.practicum2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class ActivityCriminals extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private Criminal[] crimials = new Criminal[0];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criminals);

        String[] criminalNames = getResources().getStringArray(R.array.criminals);
        crimials = new Criminal[criminalNames.length];
        for (int i = 0; i < criminalNames.length; i++) {
            crimials[i] = new Criminal(criminalNames[i]);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CriminalsAdapter(crimials);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_criminals, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class CriminalsAdapter extends RecyclerView.Adapter<CriminalsAdapter.ViewHolder> {

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView textViewName;
            public Criminal currentItem;

            public ViewHolder(View itemView) {
                super(itemView);
                textViewName = (TextView) itemView.findViewById(R.id.textViewName);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), ActivityCriminalDetails.class);
                        intent.putExtra(ActivityCriminalDetails.EXTRA_NAME, currentItem.getName());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplicationContext().startActivity(intent);
                    }
                });
            }
        }

        private Criminal[] criminals;

        public CriminalsAdapter(List<Criminal> criminals) {
            this.criminals = new Criminal[criminals.size()];
            criminals.toArray(this.criminals);
        }

        public CriminalsAdapter(Criminal[] criminals) {
            this.criminals = criminals;
        }

        @Override
        public CriminalsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.criminal_card, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(CriminalsAdapter.ViewHolder holder, int position) {
            Criminal currentCriminal = criminals[position];
            holder.textViewName.setText(currentCriminal.getName());
            holder.currentItem = criminals[position];

        }

        @Override
        public int getItemCount() {
            return criminals.length;
        }
    }
}
