package com.example.prog_mobile_tp1;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("HelloWorldActivity", "onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // Exercice 4
    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_helloworld) {
            Intent intent = new Intent(this, HelloWorldActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_simple) {
            Intent intent = new Intent(this, SimpleActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_train) {
            Intent intent = new Intent(this, TrainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_agenda) {
            Intent intent = new Intent(this, AgendaActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_lang) { // Exercice 4
            Log.d("MenuActivity", "Change language. current: " + Locale.getDefault().getLanguage());
            if (Locale.getDefault().getLanguage().equals("en")) {
                setLocale("fr");
            } else {
                setLocale("en");
            }
            recreate();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}