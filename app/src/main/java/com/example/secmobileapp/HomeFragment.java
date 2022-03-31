package com.example.secmobileapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.RequestQueue;

import java.util.ArrayList;


@SuppressLint("ValidFragment")
public class HomeFragment extends Fragment {

    private Context context;

    private DBService dbService;
    private ArrayList<String> result_id, result_city, result_date, result_temperature,result_humidity,result_windDirection;
    private RecyclerAdapter recyclerAdapter;

    //private OnFragmentInteractionListener mListener;

    @SuppressLint("ValidFragment")
    public HomeFragment(Context context,DBService dbService) {
        this.context = context;
        this.dbService = dbService;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        result_id = new ArrayList<>();
        result_city = new ArrayList<>();
        result_date = new ArrayList<>();
        result_temperature = new ArrayList<>();
        result_humidity = new ArrayList<>();
        result_windDirection = new ArrayList<>();

        storeDataInArrays();

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter(context, result_id, result_city, result_date, result_temperature,result_humidity,result_windDirection);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        return view;
    }

    private void storeDataInArrays() {

        Cursor cursor = dbService.readAllData();

        if (cursor.getCount() == 0) {
            Toast.makeText(context, "there are no saved games", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                result_id.add(cursor.getString(0));
                result_city.add(cursor.getString(1));
                result_date.add(cursor.getString(2));
                result_temperature.add(cursor.getString(3));
                result_humidity.add(cursor.getString(4));
                result_windDirection.add(cursor.getString(5));
            }
        }
    }






    /*// TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
