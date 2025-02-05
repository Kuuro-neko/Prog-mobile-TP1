package com.example.prog_mobile_tp1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Date;

// Exercice 9
public class AgendaActivity extends MenuActivity {

    private int day;
    private int month;
    private int year;

    private ListView eventList;
    private TextView eventListLabel;
    private ArrayList<CalendarEvent> events = new ArrayList<>();

    private static class CalendarEvent {
        private int day;
        private int month;
        private int year;
        private String description;

        public CalendarEvent(int day, int month, int year, String description) {
            this.day = day;
            this.month = month;
            this.year = year;
            this.description = description;
        }


        public String getDescription() {
            return description;
        }

        public String getDate() {
            return day + "/" + (month + 1) + "/" + year;
        }
    }

    private void updateEventList() {

        eventListLabel.setText(getString(R.string.label_events) + " " + this.day + "/" + (this.month + 1) + "/" + this.year);
        ArrayList<String> eventStrings = new ArrayList<>();
        for (CalendarEvent event : events) {
            if (event.day == this.day && event.month == this.month && event.year == this.year) {
                eventStrings.add(event.getDate() + " - " + event.getDescription());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eventStrings);

        eventList.setAdapter(adapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agenda);

        CalendarView calendarView = findViewById(R.id.calendar);

        eventListLabel = findViewById(R.id.label_events);
        eventList = findViewById(R.id.event_list);

        Date date = new Date(calendarView.getDate());
        this.day = date.getDate();
        this.month = date.getMonth();
        this.year = date.getYear() + 1900;

        updateEventList();

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String datetest = dayOfMonth + "/" + (month + 1) + "/" + year;
            this.day = dayOfMonth;
            this.month = month;
            this.year = year;

            updateEventList();
        });

        EditText editText = findViewById(R.id.edit_new_event);
        Button button = findViewById(R.id.button_add);

        button.setOnClickListener(v -> {
            String description = editText.getText().toString();
            if (description.isEmpty()) {
                Toast.makeText(this, "Description vide", Toast.LENGTH_SHORT).show();
                return;
            }

            if (day == 0 && month == 0 && year == 0) {
                Toast.makeText(this, "Date non sélectionnée", Toast.LENGTH_SHORT).show();
                return;
            }

            CalendarEvent event = new CalendarEvent(
                    day,
                    month,
                    year,
                    description
            );
            events.add(event);

            updateEventList();
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}