package com.kokonetworks.theapp;

import static com.kokonetworks.theapp.MainActivity.EXTRA_TOP_TEN;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class LeaderBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        TextView tvTopTen = findViewById(R.id.tvTopTen);
        ArrayList<Integer> leaderboard = (ArrayList<Integer>) getIntent().getSerializableExtra(EXTRA_TOP_TEN);
        tvTopTen.setText("List of Top Ten : " + leaderboard.toString());
    }
}