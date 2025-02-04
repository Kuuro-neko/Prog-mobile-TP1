package com.example.prog_mobile_tp1;

import static android.content.Intent.getIntent;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
// Exercice 7
public class PhoneActivity extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_phone);

        TextView phoneTextView = findViewById(R.id.label_phone);
        phoneTextView.setText(getIntent().getStringExtra("phone"));

        Uri phoneUri = getIntent().getStringExtra("phone") != null ? Uri.parse("tel:" + getIntent().getStringExtra("phone")) : null;

        Button callButton = findViewById(R.id.button_call);
        callButton.setOnClickListener(v -> {
            if (phoneUri != null) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL, phoneUri);
                startActivity(callIntent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}