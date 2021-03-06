package com.broadwebmarketing.parkedhere;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button parkedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        LocationListener mlocListener = new MyLocationListener();
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, mlocListener);
        this.parkedButton = (Button)this.findViewById(R.id.parked);
        this.parkedButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            /*    TextView textOut = (TextView) findViewById(R.id.textLat);
                String text = (String) textOut.getText();
                text = "Parked: " + text;
                textOut.setText(text); */
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /* Class My Location Listener */

    public class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc){
           /* write to variable & store in firebase   */
            double myLat  = loc.getLatitude();
            double myLong = loc.getLongitude();
            /* String textLat = "My current location is: " + "Latitude = " + myLat
                    + " Longitude = " + myLong; */
            String textLat = "Latitude = " + myLat;
            String textLong = "Longitude = " + myLong;
            TextView newText = (TextView) findViewById(R.id.textLat);
            newText.setText(textLat);
            TextView newText2 = (TextView) findViewById(R.id.textLong);
            newText2.setText(textLong);
        }

        @Override
        public void onProviderDisabled(String provider){
            Toast.makeText( getApplicationContext(),"Gps Disabled",Toast.LENGTH_SHORT ).show();
        }

        @Override
        public void onProviderEnabled(String provider){
            Toast.makeText(getApplicationContext(),"Gps Enabled",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

    }/* End of Class MyLocationListener */
}
