package com.example.secmobileapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);
    private DBService dbService;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = MainActivity.this;
        setContentView(R.layout.activity_main);

        checkInternet();

        dbService = new DBService(MainActivity.this);
        dbService.clearDataBase();

        BottomNavigationView botNavView = findViewById(R.id.botNavView);
        botNavView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, new HomeFragment(MainActivity.this, dbService)).commit();

        for (int i = 0; i < WeatherDataService.availableCities.length; i++) {
            final String city = WeatherDataService.availableCities[i];
            weatherDataService.getCityId(city, new WeatherDataService.VolleyResponseListener() {
                @Override
                public void onError(String message) {
                    Toast.makeText(MainActivity.this, "cannot get city id", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(final String cityId) {
                    weatherDataService.getCityForecastById(cityId, new WeatherDataService.ForecastByIdResponseListener() {
                        @Override
                        public void onError(String message) {
                            Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(List<WeatherReportModel> weatherReportModels) {
                            for (WeatherReportModel weatherReportModel : weatherReportModels) {
                                dbService.addResult(city, weatherReportModel.getApplicableDate(), String.valueOf(weatherReportModel.getTheTemp()), String.valueOf(weatherReportModel.getHumidity()), String.valueOf(weatherReportModel.getWindDirection()));
                                System.out.println(cityId + " :: " + city + " :: " + weatherReportModel.getApplicableDate() + " :: " + weatherReportModel.getTheTemp());
                                //Toast.makeText(MainActivity.this, weatherReportModels.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }
    }

    private void checkInternet() {
        if (!isConnected(context)) {
            final AlertDialog dialog = new AlertDialog.Builder(context).setTitle("connection").setMessage("please, connect to the internet").setPositiveButton("of course", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create();
            dialog.show();
        }
    }

    private boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo networkInfo : info) {
                    if (networkInfo.getState() == NetworkInfo.State.CONNECTED)
                    return true;
                }
            }

            NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            return wifiConn != null && wifiConn.isConnected() || mobileConn != null && mobileConn.isConnected();
        }
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.item1:
                    checkInternet();
                    selectedFragment = new HomeFragment(MainActivity.this, dbService);
                    break;
                case R.id.item2:
                    checkInternet();
                    selectedFragment = new ExploreFragment(MainActivity.this, dbService);
                    break;
                case R.id.item3:
                    checkInternet();
                    selectedFragment = new ProfileFragment();
                    break;
            }

            assert selectedFragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, selectedFragment).commit();

            return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.startmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.developerItem) {
            showDeveloper();
        }
        return true;
    }

    private void showDeveloper() {
        startActivity(new Intent(this, AboutDeveloper.class));
    }
}
