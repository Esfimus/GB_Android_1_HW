package com.esfimus.gb_calculator_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    Presenter presenter;
    TextView result;
    TextView auxResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(themeChanger());
        setContentView(R.layout.activity_calculator);

        clicksAndChanges();
    }

    private void clicksAndChanges() {
        presenter = new Presenter();
        result = findViewById(R.id.result);
        auxResult = findViewById(R.id.subresult);

        buttonClickListener(findViewById(R.id.button_0), "0");
        buttonClickListener(findViewById(R.id.button_1), "1");
        buttonClickListener(findViewById(R.id.button_2), "2");
        buttonClickListener(findViewById(R.id.button_3), "3");
        buttonClickListener(findViewById(R.id.button_4), "4");
        buttonClickListener(findViewById(R.id.button_5), "5");
        buttonClickListener(findViewById(R.id.button_6), "6");
        buttonClickListener(findViewById(R.id.button_7), "7");
        buttonClickListener(findViewById(R.id.button_8), "8");
        buttonClickListener(findViewById(R.id.button_9), "9");
        buttonClickListener(findViewById(R.id.button_plus), "+");
        buttonClickListener(findViewById(R.id.button_minus), "-");
        buttonClickListener(findViewById(R.id.button_multi), "x");
        buttonClickListener(findViewById(R.id.button_div), "/");
        buttonClickListener(findViewById(R.id.button_brackets), "(");
        buttonClickListener(findViewById(R.id.button_dot), ".");
        buttonClickListener(findViewById(R.id.button_eq), "=");
        buttonClickListener(findViewById(R.id.button_del), "d");
        buttonClickListener(findViewById(R.id.button_ac), "c");

        findViewById(R.id.imageView).setOnClickListener(v -> {
            Intent intent = new Intent(this, ThemeActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void buttonClickListener(Button button, String value) {
        button.setOnClickListener(v -> {
            presenter.passValue(value);
            result.setText(presenter.getMainResult());
            auxResult.setText(presenter.getSubResult());
        });
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putParcelable("Presenter", presenter);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        presenter = instanceState.getParcelable("Presenter");
        result.setText(presenter.getMainResult());
        auxResult.setText(presenter.getSubResult());
    }
}