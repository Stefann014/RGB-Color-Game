package com.example.rgbcolorgame.activity.repository;


import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.rgbcolorgame.activity.dao.RezultatDAO;
import com.example.rgbcolorgame.activity.database.Database;
import com.example.rgbcolorgame.activity.domain.Rezultat;

import java.util.List;

public class Repository {


    private RezultatDAO rezultatDAO;
    private LiveData<List<Rezultat>> rezultati;


    public Repository(Application application) {
        Database database = Database.getInstance(application);
        rezultatDAO = database.rezultatDAO();
        rezultati = rezultatDAO.getAllRezultati();
    }

    // za svaku operaciju je neophodna posebna asinhrona u pozadini

    public void insert(Rezultat rezultat) {
        new InsertRezultatAsyncTask(rezultatDAO).execute(rezultat);
    }

    public void update(Rezultat rezultat) {
        new UpdateRezultatAsyncTask(rezultatDAO).execute(rezultat);
    }

    public void delete(Rezultat rezultat) {
        new DeleteRezultatAsyncTask(rezultatDAO).execute(rezultat);
    }

    public void deleteAllRezultati() {
        new DeleteAllRezultatAsyncTask(rezultatDAO).execute();
    }

    public LiveData<List<Rezultat>> getRezultati() {
        return rezultati;
    }


/////////////////// da ne bi pukla aplikacija ovo moramo raditi u pozadini, livedata je automatski sinhronizovan

    private static class InsertRezultatAsyncTask extends AsyncTask<Rezultat, Void, Void> {
        private RezultatDAO rezultatDAO;

        private InsertRezultatAsyncTask(RezultatDAO rezultatDAO) {
            this.rezultatDAO = rezultatDAO;
        }

        @Override
        protected Void doInBackground(@NonNull Rezultat... rezultati) {
            rezultatDAO.insert(rezultati[0]);
            return null;
        }
    }

    private static class UpdateRezultatAsyncTask extends AsyncTask<Rezultat, Void, Void> {
        private RezultatDAO rezultatDAO;

        private UpdateRezultatAsyncTask(RezultatDAO rezultatDAO) {
            this.rezultatDAO = rezultatDAO;
        }

        @Override
        protected Void doInBackground(@NonNull Rezultat... rezultati) {
            rezultatDAO.update(rezultati[0]);
            return null;
        }
    }

    private static class DeleteRezultatAsyncTask extends AsyncTask<Rezultat, Void, Void> {
        private RezultatDAO rezultatDAO;

        private DeleteRezultatAsyncTask(RezultatDAO rezultatDAO) {
            this.rezultatDAO = rezultatDAO;
        }

        @Override
        protected Void doInBackground(@NonNull Rezultat... rezultati) {
            rezultatDAO.delete(rezultati[0]);
            return null;
        }
    }

    private static class DeleteAllRezultatAsyncTask extends AsyncTask<Void, Void, Void> {
        private RezultatDAO rezultatDAO;

        private DeleteAllRezultatAsyncTask(RezultatDAO rezultatDAO) {
            this.rezultatDAO = rezultatDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            rezultatDAO.deleteAllRezultati();
            return null;
        }
    }
}
