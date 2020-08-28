package com.example.rgbcolorgame.activity.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rgbcolorgame.activity.domain.Score;
import com.example.rgbcolorgame.activity.repository.Repository;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Score>> rezultati;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        rezultati = repository.getRezultati();
    }

    public void insert(Score score) {
        repository.insert(score);
    }

    public void update(Score score) {
        repository.update(score);
    }

    public void delete(Score score) {
        repository.delete(score);
    }

    public void deleteAllRezultati() {
        repository.deleteAllRezultati();
    }

    public LiveData<List<Score>> getRezultati() {
        return rezultati;
    }
}
