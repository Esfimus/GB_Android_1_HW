package com.esfimus.gb_calculator_java;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

public class ThemeActivity extends AppCompatActivity {
    RadioButton green;
    RadioButton blue;
    RadioButton orange;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(themeChanger());
        setContentView(R.layout.activity_theme);

        initTheme();
    }

    private void initTheme() {
        green = findViewById(R.id.radio_green_theme);
        blue = findViewById(R.id.radio_blue_theme);
        orange = findViewById(R.id.radio_orange_theme);
        save = findViewById(R.id.button_save);

        pushedButton();
        radioPush(green, "green");
        radioPush(blue, "blue");
        radioPush(orange, "orange");

        save.setOnClickListener(v -> {
            Intent intent = new Intent(this, CalculatorActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void radioPush(RadioButton button, String theme) {
        button.setOnClickListener(v -> {
            SharedPreferences pref = getSharedPreferences("key", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("theme", theme);
            editor.apply();
        });
    }

    private void pushedButton() {
        SharedPreferences pref = getSharedPreferences("key", MODE_PRIVATE);
        String theme = pref.getString("theme", "green");
        switch (theme) {
            case "green" : {
                green.setChecked(true);
                return;
            }
            case "blue" : {
                blue.setChecked(true);
                return;
            }
            case "orange" : {
                orange.setChecked(true);
            }
        }
    }

    private int themeChanger() {
        SharedPreferences pref = getSharedPreferences("key", MODE_PRIVATE);
        String theme = pref.getString("theme", "green");
        switch (theme) {
            case "blue" : return R.style.Blue_theme;
            case "orange" : return R.style.Orange_theme;
            default : return R.style.Theme_GB_Calculator_Java;
        }
    }
}