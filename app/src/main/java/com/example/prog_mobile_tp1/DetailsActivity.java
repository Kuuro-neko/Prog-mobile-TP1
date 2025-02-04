package com.example.prog_mobile_tp1;

import static android.content.Intent.getIntent;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailsActivity extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        TextView lastNameTextView = (TextView) findViewById(R.id.label_lastname);
        TextView firstNameTextView = (TextView) findViewById(R.id.label_firstname);
        TextView ageTextView = (TextView) findViewById(R.id.label_age);
        TextView skillsTextView = (TextView) findViewById(R.id.label_skills);
        TextView phoneTextView = (TextView) findViewById(R.id.label_phone);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            lastNameTextView.setText(lastNameTextView.getText() + " : " + extras.getString("lastname"));
            firstNameTextView.setText(firstNameTextView.getText() + " : " + extras.getString("firstname"));
            ageTextView.setText(ageTextView.getText() + " : " + extras.getString("age"));
            skillsTextView.setText(skillsTextView.getText() + " : " + extras.getString("skills"));
            phoneTextView.setText(phoneTextView.getText() + " : " + extras.getString("phone"));
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("No data received");
        }

        Button buttonPrevious = findViewById(R.id.button_previous);
        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button buttonOK = findViewById(R.id.button_ok);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, PhoneActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}