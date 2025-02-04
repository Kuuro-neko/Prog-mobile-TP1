package com.example.prog_mobile_tp1;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;

public class TrainActivity extends MenuActivity {

    JSONArray fakeTrainData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_train);
        fakeTrainData = new JSONArray();
        // ChatGPT est très bon pour générer ces données ! :)
        fakeTrainData.put("train_number : 1, departure_time : 12:00, arrival_time : 13:00, departure_station : Paris, arrival_station : Lyon");
        fakeTrainData.put("train_number : 2, departure_time : 14:00, arrival_time : 15:00, departure_station : Lyon, arrival_station : Marseille");
        fakeTrainData.put("train_number : 3, departure_time : 16:00, arrival_time : 17:00, departure_station : Marseille, arrival_station : Nice");
        fakeTrainData.put("train_number : 4, departure_time : 18:00, arrival_time : 19:00, departure_station : Nice, arrival_station : Montpellier");
        fakeTrainData.put("train_number : 5, departure_time : 20:00, arrival_time : 21:00, departure_station : Montpellier, arrival_station : Bordeaux");
        fakeTrainData.put("train_number : 6, departure_time : 22:00, arrival_time : 23:00, departure_station : Bordeaux, arrival_station : Toulouse");
        fakeTrainData.put("train_number : 7, departure_time : 00:00, arrival_time : 01:00, departure_station : Toulouse, arrival_station : Paris");
        fakeTrainData.put("train_number : 8, departure_time : 02:00, arrival_time : 03:00, departure_station : Paris, arrival_station : Lyon");
        fakeTrainData.put("train_number : 9, departure_time : 04:00, arrival_time : 05:00, departure_station : Lyon, arrival_station : Marseille");
        fakeTrainData.put("train_number : 10, departure_time : 06:00, arrival_time : 07:00, departure_station : Marseille, arrival_station : Nice");
        fakeTrainData.put("train_number : 11, departure_time : 08:00, arrival_time : 09:00, departure_station : Nice, arrival_station : Montpellier");
        fakeTrainData.put("train_number : 12, departure_time : 10:00, arrival_time : 11:00, departure_station : Montpellier, arrival_station : Bordeaux");
        fakeTrainData.put("train_number : 13, departure_time : 12:00, arrival_time : 13:00, departure_station : Bordeaux, arrival_station : Toulouse");
        fakeTrainData.put("train_number : 14, departure_time : 14:00, arrival_time : 15:00, departure_station : Toulouse, arrival_station : Paris");
        fakeTrainData.put("train_number : 15, departure_time : 16:00, arrival_time : 17:00, departure_station : Paris, arrival_station : Lyon");
        fakeTrainData.put("train_number : 16, departure_time : 18:00, arrival_time : 19:00, departure_station : Lyon, arrival_station : Marseille");
        fakeTrainData.put("train_number : 17, departure_time : 20:00, arrival_time : 21:00, departure_station : Marseille, arrival_station : Nice");
        fakeTrainData.put("train_number : 18, departure_time : 22:00, arrival_time : 23:00, departure_station : Nice, arrival_station : Montpellier");
        fakeTrainData.put("train_number : 19, departure_time : 00:00, arrival_time : 01:00, departure_station : Montpellier, arrival_station : Bordeaux");
        fakeTrainData.put("train_number : 20, departure_time : 02:00, arrival_time : 03:00, departure_station : Bordeaux, arrival_station : Toulouse");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}