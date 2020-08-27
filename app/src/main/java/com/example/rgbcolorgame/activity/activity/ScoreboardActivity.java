package com.example.rgbcolorgame.activity.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rgbcolorgame.R;
import com.example.rgbcolorgame.activity.adapter.RezultatAdapter;
import com.example.rgbcolorgame.activity.domain.Rezultat;
import com.example.rgbcolorgame.activity.viewModel.ViewModel;

import java.util.List;

public class ScoreboardActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Rezultat> rezultati;
    final RezultatAdapter adapter = new RezultatAdapter();
    ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        srediAtribute();
        srediViewModel();
        srediBrisanje();
    }

    private void srediAtribute() {
        recyclerView = findViewById(R.id.rvScoreboard);
        rezultati = null;
        srediRV();
    }

    private void srediRV() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void srediViewModel() {
        viewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(this.getApplication()))
                .get(ViewModel.class);
        srediObserver();
    }

    private void srediObserver() {
        viewModel.getRezultati().observe(this, new Observer<List<Rezultat>>() {
            @Override
            public void onChanged(@Nullable List<Rezultat> rezultati) {
                ScoreboardActivity.this.rezultati = rezultati;
                if (ScoreboardActivity.this.rezultati != null && ScoreboardActivity.this.rezultati.size() == 0) {
                    poruka();
                }
                adapter.submitList(ScoreboardActivity.this.rezultati);
            }
        });
    }

    private void poruka() {
        String buffer = "\nThere is no any results.";
        prikaziPoruku("No scores!", buffer);
    }

    public void prikaziPoruku(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    private void srediBrisanje() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(adapter.getRezultatAt(viewHolder.getAdapterPosition()));
                Toast.makeText(ScoreboardActivity.this, "Score is deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

    }

}