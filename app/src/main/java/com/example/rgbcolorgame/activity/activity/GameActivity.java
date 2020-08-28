package com.example.rgbcolorgame.activity.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.rgbcolorgame.R;
import com.example.rgbcolorgame.activity.domain.Score;
import com.example.rgbcolorgame.activity.viewModel.ViewModel;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    public static final String EXTRA_PLAYER =
            "com.example.rgbcolorgame.activity.activity.EXTRA_PLAYER";
    public static final String EXTRA_LEVEL =
            "com.example.malipcelar.activity.activity.EXTRA_LEVEL";

    int levelSpeed = 0;
    String player;

    // Screen Size
    private int screenWidth;
    private int screenHeight;

    // Images
    private Button btnGore;
    private Button btnDole;
    private Button btnDesno;
    private Button btnLevo;

    // Button
    private Button btnPause;

    // Position
    private float arrowUpX;
    private float arrowUpY;
    private float arrowDownX;
    private float arrowDownY;
    private float arrowRightX;
    private float arrowRightY;
    private float arrowLeftX;
    private float arrowLeftY;

    // Initialize Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();

    // Status Check
    // private boolean pause_flg = false;

    private Chronometer chronometer;

    private int index = -1;
    private int skor = 0;
    private int brojRundi = 0;
    private long protekleMiliSekunde = 0;
    ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        srediAtribute();
        srediIntent();
        if (levelSpeed != 0) {
            pokreniHronometar();
            srediPomeranje(levelSpeed);
            srediDugmice();
            srediListenere();
            srediViewModel();
        } else {
            pokreniHronometar();
            srediDugmice();
            srediListenere();
            srediViewModel();
        }
    }

    private void srediViewModel() {
        viewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(this.getApplication()))
                .get(ViewModel.class);
    }

    private void srediIntent() {
        Intent intent = getIntent();
        player = intent.getStringExtra(EXTRA_PLAYER);
        String level = intent.getStringExtra(EXTRA_LEVEL);

        if (level != null && level.equals("Easy")) {
            levelSpeed = 0;
        }
        if (level != null && level.equals("Medium")) {
            levelSpeed = 16;
        }

        if (level != null && level.equals("Hard")) {
            levelSpeed = 6;
        }
    }

    @SuppressLint("SetTextI18n")
    private void srediDugmice() {
        Random r = new Random();
        int low = 0;
        int high = 255;
        int red = r.nextInt((high - low) + 1) + low;
        int green = r.nextInt((high - low) + 1) + low;
        int blue = r.nextInt((high - low) + 1) + low;
        btnPause.setText("Guess the color: RGB (" + red + ", " + green + ", " + blue + ")");

        int donja = 1;
        int gornja = 4;

        int randomBroj = r.nextInt((gornja - donja) + 1) + donja;
        Log.d("RANDOM BROJ", randomBroj + "");

        switch (randomBroj) {
            case 1:
                btnGore.setBackgroundColor(Color.rgb(red, green, blue));
                btnDole.setBackgroundColor(Color.rgb(r.nextInt((high - low) + 1) + low, r.nextInt(high - low), r.nextInt(high - low)));
                btnLevo.setBackgroundColor(Color.rgb(r.nextInt((high - low) + 1) + low, r.nextInt(high - low), r.nextInt(high - low)));
                btnDesno.setBackgroundColor(Color.rgb(r.nextInt((high - low) + 1) + low, r.nextInt(high - low), r.nextInt(high - low)));
                index = 1;
                Log.d("TAGGG", "GORE");
                break;
            case 2:
                btnDole.setBackgroundColor(Color.rgb(red, green, blue));
                btnGore.setBackgroundColor(Color.rgb(r.nextInt((high - low) + 1) + low, r.nextInt(high - low), r.nextInt(high - low)));
                btnLevo.setBackgroundColor(Color.rgb(r.nextInt((high - low) + 1) + low, r.nextInt(high - low), r.nextInt(high - low)));
                btnDesno.setBackgroundColor(Color.rgb(r.nextInt((high - low) + 1) + low, r.nextInt(high - low), r.nextInt(high - low)));
                index = 2;
                Log.d("TAGGG", "DOLE");
                break;
            case 3:
                btnLevo.setBackgroundColor(Color.rgb(red, green, blue));
                btnDole.setBackgroundColor(Color.rgb(r.nextInt((high - low) + 1) + low, r.nextInt(high - low), r.nextInt(high - low)));
                btnGore.setBackgroundColor(Color.rgb(r.nextInt((high - low) + 1) + low, r.nextInt(high - low), r.nextInt(high - low)));
                btnDesno.setBackgroundColor(Color.rgb(r.nextInt((high - low) + 1) + low, r.nextInt(high - low), r.nextInt(high - low)));
                index = 3;
                Log.d("TAGGG", "LEVO");
                break;
            case 4:
                btnDesno.setBackgroundColor(Color.rgb(red, green, blue));
                btnDole.setBackgroundColor(Color.rgb(r.nextInt((high - low) + 1) + low, r.nextInt(high - low), r.nextInt(high - low)));
                btnLevo.setBackgroundColor(Color.rgb(r.nextInt((high - low) + 1) + low, r.nextInt(high - low), r.nextInt(high - low)));
                btnGore.setBackgroundColor(Color.rgb(r.nextInt((high - low) + 1) + low, r.nextInt(high - low), r.nextInt(high - low)));
                index = 4;
                Log.d("TAGGG", "DESNO");
                break;
            default:
                break;
        }
    }

    private void srediPomeranje(int levelSpeed) {
        // Get Screen Size.
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        // Move to out of screen.
        btnGore.setX(-80.0f);
        btnGore.setY(-80.0f);
        btnDole.setX(-80.0f);
        btnDole.setY(screenHeight + 80.0f);
        btnDesno.setX(screenWidth + 80.0f);
        btnDesno.setY(-80.0f);
        btnLevo.setX(-80.0f);
        btnLevo.setY(-80.0f);

        // Start the timer.
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        promeniPoziciju();
                    }
                });
            }
        }, 0, levelSpeed);

    }

    private void srediAtribute() {
        btnGore = findViewById(R.id.btnUp);
        btnDole = findViewById(R.id.btnDown);
        btnDesno = findViewById(R.id.btnRight);
        btnLevo = findViewById(R.id.btnLeft);
        btnPause = findViewById(R.id.btnPause);
        chronometer = findViewById(R.id.chronometer);

        chronometer.setFormat("Time: %s");
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {

            }
        });
    }

    public void srediListenere() {

        btnGore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == 1) {
                    skor += 10;
                    brojRundi++;

                    if (brojRundi < 5) {
                        srediDugmice();
                        srediListenere();
                        vratiVidljivost();
                    } else {
                        chronometer.stop();
                        protekleMiliSekunde = SystemClock.elapsedRealtime() - chronometer.getBase();
                        int brSek = Math.round(protekleMiliSekunde / 1000);
                        skor -= brSek;
                        Score score = new Score(skor, "2020-05-05", brSek, player);
                        viewModel.insert(score);
                        Intent intent = new Intent(GameActivity.this, ScoreboardActivity.class);
                        intent.putExtra(ScoreboardActivity.EXTRA_SCORE, score);
                        startActivity(intent);
                    }
                    Log.d("SKOR", skor + "");
                } else {
                    skor -= 2;
                    btnGore.setVisibility(View.GONE);
                }
            }
        });

        btnDole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == 2) {
                    skor += 10;
                    brojRundi++;

                    if (brojRundi < 5) {
                        srediDugmice();
                        srediListenere();
                        vratiVidljivost();
                    } else {
                        chronometer.stop();
                        protekleMiliSekunde = SystemClock.elapsedRealtime() - chronometer.getBase();
                        int brSek = Math.round(protekleMiliSekunde / 1000);
                        skor -= brSek;
                        Score score = new Score(skor, "2020-05-05", brSek, player);
                        viewModel.insert(score);
                        Intent intent = new Intent(GameActivity.this, ScoreboardActivity.class);
                        intent.putExtra(ScoreboardActivity.EXTRA_SCORE, score);
                        startActivity(intent);
                    }
                    Log.d("SKOR", skor + "");
                } else {
                    skor -= 2;
                    btnDole.setVisibility(View.GONE);
                }
            }
        });

        btnLevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == 3) {
                    skor += 10;
                    brojRundi++;

                    if (brojRundi < 5) {
                        srediDugmice();
                        srediListenere();
                        vratiVidljivost();
                    } else {
                        chronometer.stop();
                        protekleMiliSekunde = SystemClock.elapsedRealtime() - chronometer.getBase();
                        int brSek = Math.round(protekleMiliSekunde / 1000);
                        skor -= brSek;
                        Score score = new Score(skor, "2020-05-05", brSek, player);
                        viewModel.insert(score);
                        Intent intent = new Intent(GameActivity.this, ScoreboardActivity.class);
                        intent.putExtra(ScoreboardActivity.EXTRA_SCORE, score);
                        startActivity(intent);
                    }
                    Log.d("SKOR", skor + "");
                } else {
                    skor -= 2;
                    btnLevo.setVisibility(View.GONE);
                }
            }
        });

        btnDesno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == 4) {
                    skor += 10;
                    brojRundi++;

                    if (brojRundi < 5) {
                        srediDugmice();
                        srediListenere();
                        vratiVidljivost();
                    } else {
                        chronometer.stop();
                        protekleMiliSekunde = SystemClock.elapsedRealtime() - chronometer.getBase();
                        int brSek = Math.round(protekleMiliSekunde / 1000);
                        skor -= brSek;
                        Score score = new Score(skor, "2020-05-05", brSek, player);
                        viewModel.insert(score);
                        Intent intent = new Intent(GameActivity.this, ScoreboardActivity.class);
                        intent.putExtra(ScoreboardActivity.EXTRA_SCORE, score);
                        startActivity(intent);
                    }

                    Log.d("SKOR", skor + "");
                } else {
                    skor -= 2;
                    btnDesno.setVisibility(View.GONE);
                }
            }
        });

        btnPause.setClickable(false);

    }

    private void vratiVidljivost() {
        btnDesno.setVisibility(View.VISIBLE);
        btnLevo.setVisibility(View.VISIBLE);
        btnGore.setVisibility(View.VISIBLE);
        btnDole.setVisibility(View.VISIBLE);
    }

    private void pokreniHronometar() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }

    public void promeniPoziciju() {
        // Up
        arrowUpY -= 10;
        if (btnGore.getY() + btnGore.getHeight() < 0) {
            arrowUpX = (float) Math.floor(Math.random() * (screenWidth - btnGore.getWidth()));
            arrowUpY = screenHeight + 100.0f;
        }
        btnGore.setX(arrowUpX);
        btnGore.setY(arrowUpY);

        // Down
        arrowDownY += 10;
        if (btnDole.getY() > screenHeight) {
            arrowDownX = (float) Math.floor(Math.random() * (screenWidth - btnDole.getWidth()));
            arrowDownY = -100.0f;
        }
        btnDole.setX(arrowDownX);
        btnDole.setY(arrowDownY);

        // Right
        arrowRightX += 10;
        if (btnDesno.getX() > screenWidth) {
            arrowRightX = -100.0f;
            arrowRightY = (float) Math.floor(Math.random() * (screenHeight - btnDesno.getHeight()));
        }
        btnDesno.setX(arrowRightX);
        btnDesno.setY(arrowRightY);

        // Left
        arrowLeftX -= 10;
        if (btnLevo.getX() + btnLevo.getWidth() < 0) {
            arrowLeftX = screenWidth + 100.0f;
            arrowLeftY = (float) Math.floor(Math.random() * (screenHeight - btnLevo.getHeight()));
        }
        btnLevo.setX(arrowLeftX);
        btnLevo.setY(arrowLeftY);

    }

    /*
        public void pausePushed(int levelSpeed) {

            if (!pause_flg) {

                pause_flg = true;

                // Stop the timer.
                timer.cancel();
                timer = null;
            } else {

                pause_flg = false;

                // Create and Start the timer.
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                promeniPoziciju();
                            }
                        });
                    }
                }, 0, levelSpeed);
            }
        }
    */
    @Override
    protected void onPause() {
        super.onPause();
        chronometer.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        chronometer.start();
    }

}