package com.example.poslovnihodogram;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        final TextView loggedUserTV = findViewById(R.id.headerTV);
        String value = getIntent().getExtras().getString("name");
        value = "Prijavljeni kao: "+ value;
        loggedUserTV.setText(value);
    }
}
