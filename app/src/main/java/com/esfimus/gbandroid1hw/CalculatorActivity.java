package com.esfimus.gbandroid1hw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.PersistableBundle;
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
        buttonClickListener(findViewById(R.id.button_multi), "*");
        buttonClickListener(findViewById(R.id.button_div), "/");
        buttonClickListener(findViewById(R.id.button_percent), "%");
        buttonClickListener(findViewById(R.id.button_dot), ".");
        buttonClickListener(findViewById(R.id.button_eq), "=");
        buttonClickListener(findViewById(R.id.button_del), "del");
        buttonClickListener(findViewById(R.id.button_ac), "clear");
    }

    private void buttonClickListener(Button button, String value) {
        button.setOnClickListener(v -> {
            presenter.passValue(value);
            result.setText(presenter.getResult());
            auxResult.setText(presenter.getSubResult());
        });
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
        result.setText(presenter.getResult());
        auxResult.setText(presenter.getSubResult());
    }
}