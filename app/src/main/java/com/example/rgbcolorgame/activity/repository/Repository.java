package com.example.rgbcolorgame.activity.repository;


import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.rgbcolorgame.activity.dao.ScoreDAO;
import com.example.rgbcolorgame.activity.database.Database;
import com.example.rgbcolorgame.activity.domain.Score;

import java.util.List;

public class Repository {


    private ScoreDAO scoreDAO;
    private LiveData<List<Score>> rezultati;


    public Repository(Application application) {
        Database database = Database.getInstance(application);
        scoreDAO = database.rezultatDAO();
        rezultati = scoreDAO.getAllRezultati();
    }

    // za svaku operaciju je neophodna posebna asinhrona u pozadini

    public void insert(Score score) {
        new InsertRezultatAsyncTask(scoreDAO).execute(score);
    }

    public void update(Score score) {
        new UpdateRezultatAsyncTask(scoreDAO).execute(score);
    }

    public void delete(Score score) {
        new DeleteRezultatAsyncTask(scoreDAO).execute(score);
    }

    public void deleteAllRezultati() {
        new DeleteAllRezultatAsyncTask(scoreDAO).execute();
    }

    public LiveData<List<Score>> getRezultati() {
        return rezultati;
    }


/////////////////// da ne bi pukla aplikacija ovo moramo raditi u pozadini, livedata je automatski sinhronizovan

    private static class InsertRezultatAsyncTask extends AsyncTask<Score, Void, Void> {
        private ScoreDAO scoreDAO;

        private InsertRezultatAsyncTask(ScoreDAO scoreDAO) {
            this.scoreDAO = scoreDAO;
        }

        @Override
        protected Void doInBackground(@NonNull Score... rezultati) {
            scoreDAO.insert(rezultati[0]);
            return null;
        }
    }

    private static class UpdateRezultatAsyncTask extends AsyncTask<Score, Void, Void> {
        private ScoreDAO scoreDAO;

        private UpdateRezultatAsyncTask(ScoreDAO scoreDAO) {
            this.scoreDAO = scoreDAO;
        }

        @Override
        protected Void doInBackground(@NonNull Score... rezultati) {
            scoreDAO.update(rezultati[0]);
            return null;
        }
    }

    private static class DeleteRezultatAsyncTask extends AsyncTask<Score, Void, Void> {
        private ScoreDAO scoreDAO;

        private DeleteRezultatAsyncTask(ScoreDAO scoreDAO) {
            this.scoreDAO = scoreDAO;
        }

        @Override
        protected Void doInBackground(@NonNull Score... rezultati) {
            scoreDAO.delete(rezultati[0]);
            return null;
        }
    }

    private static class DeleteAllRezultatAsyncTask extends AsyncTask<Void, Void, Void> {
        private ScoreDAO scoreDAO;

        private DeleteAllRezultatAsyncTask(ScoreDAO scoreDAO) {
            this.scoreDAO = scoreDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            scoreDAO.deleteAllRezultati();
            return null;
        }
    }
}
