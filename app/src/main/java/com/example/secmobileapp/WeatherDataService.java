package com.example.secmobileapp;

import android.content.Context;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataService {

    public static final String QUERY_FOR_CITY_ID = "https://www.metaweather.com/api/location/search/?query=";
    public static final String QUERY_FOR_CITY_WEATHER_BY_ID = "https://www.metaweather.com/api/location/";
    private Context context;
    private String cityID = "";
    static final String[] availableCities = {"London","Moscow","Berlin","Minsk","Kiev","Venice","Paris","Dubai","Delhi"};

    public WeatherDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String cityId);
    }

    public interface ForecastByIdResponseListener {
        void onError(String message);

        void onResponse(List<WeatherReportModel> weatherReportModels);
    }


    public void getCityId(String cityName, final VolleyResponseListener volleyRespListener) {
        String url = QUERY_FOR_CITY_ID + cityName;

        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                cityID = "";
                try {
                    JSONObject cityInfo = response.getJSONObject(0);
                    cityID = cityInfo.getString("woeid");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                volleyRespListener.onResponse(cityID);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyRespListener.onError("Error");
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public void getCityForecastById(String cityId, final ForecastByIdResponseListener forecastByIdResponseListener) {

        String url = QUERY_FOR_CITY_WEATHER_BY_ID + cityId;

        final List<WeatherReportModel> weatherReportModels = new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray consolidatedWeatherArr = response.getJSONArray("consolidated_weather");

                    WeatherReportModel oneDay = new WeatherReportModel();



                    for (int i=0;i< consolidatedWeatherArr.length();i++){
                        JSONObject first_day_from_api = (JSONObject) consolidatedWeatherArr.get(i);
                        oneDay.setId(first_day_from_api.getInt("id"));
                        oneDay.setWeatherStateName(first_day_from_api.getString("weather_state_name"));
                        oneDay.setWeatherStateAbbr(first_day_from_api.getString("weather_state_abbr"));
                        oneDay.setWindDirectionCompass(first_day_from_api.getString("wind_direction_compass"));
                        oneDay.setCreated(first_day_from_api.getString("created"));
                        oneDay.setApplicableDate(first_day_from_api.getString("applicable_date"));
                        oneDay.setMinTemp(first_day_from_api.getDouble("min_temp"));
                        oneDay.setMaxTemp(first_day_from_api.getDouble("max_temp"));
                        oneDay.setTheTemp(first_day_from_api.getDouble("the_temp"));
                        oneDay.setWindSpeed(first_day_from_api.getDouble("wind_speed"));
                        oneDay.setWindDirection(first_day_from_api.getDouble("wind_direction"));
                        oneDay.setAirPressure(first_day_from_api.getInt("air_pressure"));
                        oneDay.setHumidity(first_day_from_api.getInt("humidity"));
                        oneDay.setVisibility(first_day_from_api.getDouble("visibility"));
                        oneDay.setPredictability(first_day_from_api.getInt("predictability"));
                        weatherReportModels.add(oneDay);
                    }



                    forecastByIdResponseListener.onResponse(weatherReportModels);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    /*public List<WeatherReportModel> getCityForecastByName(String cityName) {

    }*/
}
