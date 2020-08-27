package com.example.rgbcolorgame.activity.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rgbcolorgame.activity.domain.Rezultat;
import com.example.rgbcolorgame.activity.repository.Repository;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Rezultat>> rezultati;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        rezultati = repository.getRezultati();
    }

    public void insert(Rezultat rezultat) {
        repository.insert(rezultat);
    }

    public void update(Rezultat rezultat) {
        repository.update(rezultat);
    }

    public void delete(Rezultat rezultat) {
        repository.delete(rezultat);
    }

    public void deleteAllRezultati() {
        repository.deleteAllRezultati();
    }

    public LiveData<List<Rezultat>> getRezultati() {
        return rezultati;
    }
}
