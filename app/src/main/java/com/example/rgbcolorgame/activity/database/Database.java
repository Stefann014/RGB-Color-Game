package com.example.rgbcolorgame.activity.database;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.rgbcolorgame.activity.dao.ScoreDAO;
import com.example.rgbcolorgame.activity.domain.Score;


@androidx.room.Database(entities = {Score.class}, version = 1)
public abstract class Database extends RoomDatabase {

    private static Database instance;

    public abstract ScoreDAO rezultatDAO();

    //singleton pattern
    public static synchronized Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    Database.class, "baza") // menjaj naziv kad izmenis nesto
                    .fallbackToDestructiveMigration() // da ne bi povecavali verziju, brisemo je ii instaliramo opet
                    .addCallback(roomCallback) // da popunimo necim bazu, ali samo prvi put kad se instancira singlton
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ScoreDAO scoreDAO;


        private PopulateDbAsyncTask(Database db) {
            scoreDAO = db.rezultatDAO();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            dodajOpsteNapomene(scoreDAO);

            return null;
        }
    }

    private static void dodajOpsteNapomene(ScoreDAO scoreDAO) {

    }
}
