package com.esfimus.gbandroid1hw;

import androidx.appcompat.app.AppCompatActivity;
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
        setContentView(R.layout.activity_calculator);

        clicksAndChanges();
    }

    private void clicksAndChanges() {
        presenter = new Presenter();
        result = findViewById(R.id.result);
        auxResult = findViewById(R.id.subresult);

        findViewById(R.id.button_0).setOnClickListener(v -> {
            presenter.passValue("0");
            result.setText(presenter.getResult());
            auxResult.setText(presenter.getSubResult());
        });

        findViewById(R.id.button_1).setOnClickListener(v -> {
            presenter.passValue("1");
            result.setText(presenter.getResult());
            auxResult.setText(presenter.getSubResult());
        });

        findViewById(R.id.button_2).setOnClickListener(v -> {
            presenter.passValue("2");
            result.setText(presenter.getResult());
            auxResult.setText(presenter.getSubResult());
        });
    }
}