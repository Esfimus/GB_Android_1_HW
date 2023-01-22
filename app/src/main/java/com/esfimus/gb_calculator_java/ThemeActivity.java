package com.esfimus.gb_calculator_java;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

public class ThemeActivity extends AppCompatActivity {
    RadioButton greenButton;
    RadioButton blueButton;
    RadioButton orangeButton;
    Button saveButton;
    String chosenTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(themeChanger());
        setContentView(R.layout.activity_theme);

        initTheme();
    }

    private void initTheme() {
        greenButton = findViewById(R.id.radio_green_theme);
        blueButton = findViewById(R.id.radio_blue_theme);
        orangeButton = findViewById(R.id.radio_orange_theme);
        saveButton = findViewById(R.id.button_save);

        pushedButton();
        radioPush(greenButton, "green");
        radioPush(blueButton, "blue");
        radioPush(orangeButton, "orange");
        savePush(saveButton);
    }

    private void savePush(Button button) {
        button.setOnClickListener(v -> {
            SharedPreferences pref = getSharedPreferences("key", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("theme", chosenTheme);
            editor.apply();
            finish();
        });
    }

    private void radioPush(RadioButton button, String theme) {
        button.setOnClickListener(v -> chosenTheme = theme);
    }

    private void pushedButton() {
        SharedPreferences pref = getSharedPreferences("key", MODE_PRIVATE);
        String theme = pref.getString("theme", "green");
        switch (theme) {
            case "green" : {
                greenButton.setChecked(true);
                chosenTheme = "green";
                return;
            }
            case "blue" : {
                blueButton.setChecked(true);
                chosenTheme = "blue";
                return;
            }
            case "orange" : {
                orangeButton.setChecked(true);
                chosenTheme = "orange";
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