package com.example.david.puzzle_app_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PuzzleFilterActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle _savedInstanceState)
    {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.puzzle_filter_activity);
    }

    public void StartPuzzleActivity(View _view)
    {
        Intent intent = new Intent(this, PuzzleActivity.class);
        startActivity(intent);
    }
}
