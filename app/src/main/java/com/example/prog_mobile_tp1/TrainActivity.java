package com.example.prog_mobile_tp1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Exercice 8
public class TrainActivity extends MenuActivity {

    ListView trainList;
    private void updateTrainList(String departureStation, String arrivalStation) {
        String[] trainDepStations = getResources().getStringArray(R.array.train_departures_stations);
        String[] trainArrStations = getResources().getStringArray(R.array.train_arrival_stations);
        String[] trainDepTimes = getResources().getStringArray(R.array.train_departures_times);
        String[] trainArrTimes = getResources().getStringArray(R.array.train_arrival_times);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (int i = 0; i < trainDepStations.length; i++) {
            if (trainDepStations[i].contains(departureStation) && trainArrStations[i].contains(arrivalStation)) {
                String trainInfo = "Train " + (i + 1) + ": " + trainDepStations[i] + " -> " + trainArrStations[i] + "\n" +
                        getString(R.string.label_departure) + ": " + trainDepTimes[i] + ", " + getString(R.string.label_arrival) + ": " + trainArrTimes[i];
                adapter.add(trainInfo);
            }
        }
        trainList.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_train);

        EditText departure = findViewById(R.id.edit_departure);
        EditText arrival = findViewById(R.id.edit_arrival);

        trainList = findViewById(R.id.train_list);

        if (getIntent().hasExtra("departure") || getIntent().hasExtra("arrival")) {
            String departureStation = getIntent().getStringExtra("departure");
            String arrivalStation = getIntent().getStringExtra("arrival");
            updateTrainList(departureStation, arrivalStation);
        }

        Button searchTrainButton = findViewById(R.id.button_search);
        searchTrainButton.setOnClickListener(v -> {
            String departureText = departure.getText().toString().trim();
            String arrivalText = arrival.getText().toString().trim();

            if (departureText.isEmpty()) departureText = "";
            if (arrivalText.isEmpty()) arrivalText = "";

            updateTrainList(departureText, arrivalText);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}