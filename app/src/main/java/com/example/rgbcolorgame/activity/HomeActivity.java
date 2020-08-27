package com.example.rgbcolorgame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rgbcolorgame.R;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    Button btnPlay;
    Button btnScoreBoard;
    Spinner spLevel;
    TextView txtPlayerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        srediAtribute();
        srediSpinner();
        srediListenere();
    }

    private void srediListenere() {
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String player = txtPlayerName.getText().toString().trim();
                if (player.isEmpty()) {
                    Toast.makeText(HomeActivity.this, "Insert player name", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(HomeActivity.this, GameActivity.class);
                intent.putExtra(GameActivity.EXTRA_LEVEL, (String) spLevel.getSelectedItem());
                intent.putExtra(GameActivity.EXTRA_PLAYER, player);

                startActivity(intent);
            }
        });
        btnScoreBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void srediSpinner() {

        ArrayList<String> level = new ArrayList<>();
        level.add("Easy");
        level.add("Medium");
        level.add("Hard");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.spinner_size, level); // ako je greska tu je
        adapter.setDropDownViewResource(R.layout.spinner_size);
        spLevel.setAdapter(adapter);
        spLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void srediAtribute() {
        txtPlayerName = findViewById(R.id.txtPlayerName);
        spLevel = findViewById(R.id.spLevel);
        btnScoreBoard = findViewById(R.id.btnScoreboard);
        btnPlay = findViewById(R.id.btnPlay);
    }
}