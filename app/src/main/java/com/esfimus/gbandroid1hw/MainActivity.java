package com.esfimus.gbandroid1hw;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showText(View view) {
        EditText destination = findViewById(R.id.editText01);
        String destinationText = destination.getText().toString();
        TextView messageToShow = findViewById(R.id.textMessage);
        messageToShow.setText(destinationText);
    }

    public void nextActivity(View view) {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }
}