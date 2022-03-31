package com.example.secmobileapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


@SuppressLint("ValidFragment")
public class ExploreFragment extends Fragment {

    //private OnFragmentInteractionListener mListener;
    private EditText editText;
    private DBService dbService;
    private Context context;
    private ArrayList<String> result_id, result_city, result_date, result_temperature, result_humidity, result_windDirection;
    private RecyclerAdapter recyclerAdapter;
    private View view;


    @SuppressLint("ValidFragment")
    ExploreFragment(Context context, DBService dbService) {
        this.dbService = dbService;
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_explore, container, false);
        editText = view.findViewById(R.id.editTexts);

        result_id = new ArrayList<>();
        result_city = new ArrayList<>();
        result_date = new ArrayList<>();
        result_temperature = new ArrayList<>();
        result_humidity = new ArrayList<>();
        result_windDirection = new ArrayList<>();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewExplore);
        recyclerAdapter = new RecyclerAdapter(context, result_id, result_city, result_date, result_temperature, result_humidity, result_windDirection);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        listener();
        return view;
    }

    private void listener() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                while (!result_id.isEmpty()) {
                    result_id.clear();
                    result_city.clear();
                    result_date.clear();
                    result_temperature.clear();
                    result_humidity.clear();
                    result_windDirection.clear();
                }
                RecyclerView recyclerView = view.findViewById(R.id.recyclerViewExplore);
                recyclerAdapter = new RecyclerAdapter(context, result_id, result_city, result_date, result_temperature, result_humidity, result_windDirection);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                storeDataInArrays(String.valueOf(editText.getText()));

                if(String.valueOf(editText.getText()).equals("gay")){
                    final AlertDialog dialog = new AlertDialog.Builder(context).setTitle("gay").setMessage("are you gay?").setPositiveButton("of course", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create();
                    dialog.show();
                }
            }
        });
    }

    private void storeDataInArrays(String city) {
        Cursor cursor = dbService.readDataForCity(city);

        if (cursor.getCount() != 0) {
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
}
