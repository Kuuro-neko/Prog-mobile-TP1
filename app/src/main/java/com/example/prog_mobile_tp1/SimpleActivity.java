package com.example.prog_mobile_tp1;

import static android.provider.Settings.System.getString;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SimpleActivity extends MenuActivity {
    // La version entièrement XML n'est plus utilisée, mais vous pouvez vous référer à /res/layout/activity_simple.xml pour voir comment le layout était avant d'être généré en Java
    private void addRow(LinearLayout layout, String label) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        TextView textView = new TextView(this);
        textView.setText(label);
        textView.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()));
        EditText editText = new EditText(this);
        editText.setHint("");
        editText.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics()));
        row.addView(textView);
        row.addView(editText);
        layout.addView(row);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Setup du layout principal et vertical
        LinearLayout verticalLayout = new LinearLayout(this);
        ConstraintLayout mainLayout = new ConstraintLayout(this);
        mainLayout.setId(View.generateViewId());
        verticalLayout.setOrientation(LinearLayout.VERTICAL);
        verticalLayout.setPadding(16, 16, 16, 16);
        verticalLayout.setId(View.generateViewId());
        mainLayout.addView(verticalLayout);

        // Champs à remplir
        addRow(verticalLayout, getString(R.string.label_lastname));
        addRow(verticalLayout, getString(R.string.label_firstname));
        addRow(verticalLayout, getString(R.string.label_age));
        addRow(verticalLayout, getString(R.string.label_skills));
        addRow(verticalLayout, getString(R.string.label_phone));

        // Contraintes pour center les champs
        ConstraintSet set = new ConstraintSet();
        set.clone(mainLayout);
        set.connect(verticalLayout.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
        set.connect(verticalLayout.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
        set.connect(verticalLayout.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
        set.connect(verticalLayout.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
        set.setHorizontalBias(verticalLayout.getId(), 0.5f);
        set.setVerticalBias(verticalLayout.getId(), 0.5f);
        set.applyTo(mainLayout);

        // Affichage du layout
        setContentView(mainLayout);

        ViewCompat.setOnApplyWindowInsetsListener(mainLayout, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}