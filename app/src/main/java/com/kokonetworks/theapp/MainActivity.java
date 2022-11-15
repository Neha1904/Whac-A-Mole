package com.kokonetworks.theapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_TOP_TEN = "EXTRA_TOP_TEN";
    private Field field;
    private TextView tvLevel;
    private TextView tvScore, tvTopTen;

    private Button btnStart, btnTopTen;
    private ArrayList<Integer> leaderboard = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        field = findViewById(R.id.field);
        tvLevel = findViewById(R.id.tvLevel);
        btnStart = findViewById(R.id.btnStart);
        btnTopTen = findViewById(R.id.btnTopTen);
        tvScore = findViewById(R.id.tvScore);
        tvTopTen = findViewById(R.id.tvTopTen);

        setEventListeners();
    }

    void setEventListeners(){
        btnStart.setOnClickListener(view -> {
            btnStart.setVisibility(View.GONE);
            field.startGame();
        });
        btnTopTen.setOnClickListener(view -> {
            Intent intent = new Intent(this, LeaderBoardActivity.class);
            intent.putExtra(EXTRA_TOP_TEN, leaderboard);
            startActivity(intent);
        });

        field.setListener(listener);
    }

    private final Field.Listener listener = new Field.Listener() {

        @Override
        public void onGameEnded(int score) {
            btnStart.setVisibility(View.VISIBLE);
            updateTopTen(score);
            tvScore.setText(String.format(getString(R.string.your_score), score));
        }

        @Override
        public void onLevelChange(int level) {
            tvLevel.setText(String.format(getString(R.string.level), level));
        }

        @Override
        public void setScore(int score) {
            tvScore.setText(String.format(getString(R.string.your_score), score));
        }

    };

    private void updateTopTen(int score) {
        if(leaderboard.size() == 10) {
            if(leaderboard.get(0) < score && leaderboard.get(leaderboard.size()-1) < score);
                leaderboard.set(leaderboard.size()-1, score);
        } else {
            leaderboard.add(score);
        }
        Collections.sort(leaderboard, Collections.reverseOrder());
        tvTopTen.setText("List of Top Ten : " + leaderboard.toString());
    }
}