package com.example.secmobileapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> result_id, result_city, result_date, result_temperature, result_humidity, result_windDirection;

    RecyclerAdapter(Context context, ArrayList<String> result_id, ArrayList<String> result_city, ArrayList<String> result_date, ArrayList<String> result_temperature, ArrayList<String> result_humidity, ArrayList<String> result_windDirection) {
        this.context = context;
        this.result_id = result_id;
        this.result_city = result_city;
        this.result_date = result_date;
        this.result_temperature = result_temperature;
        this.result_humidity = result_humidity;
        this.result_windDirection = result_windDirection;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycle_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.result_city_txt.setText(String.valueOf(result_city.get(position)));
        holder.result_date_txt.setText(String.valueOf(result_date.get(position)));
        holder.result_temperature_txt.setText(String.valueOf(result_temperature.get(position)));
        holder.result_humidity_txt.setText(result_humidity.get(position));
        holder.result_windDirection_txt.setText(result_windDirection.get(position));
    }

    @Override
    public int getItemCount() {
        return result_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView result_city_txt, result_date_txt, result_temperature_txt,result_humidity_txt,result_windDirection_txt;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            result_city_txt = itemView.findViewById(R.id.city);
            result_date_txt = itemView.findViewById(R.id.date);
            result_temperature_txt = itemView.findViewById(R.id.temperature);
            result_humidity_txt = itemView.findViewById(R.id.humidity);
            result_windDirection_txt = itemView.findViewById(R.id.windDirection);
        }
    }
}
