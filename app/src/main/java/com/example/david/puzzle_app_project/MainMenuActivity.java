package com.example.david.puzzle_app_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle _savedInstanceState)
    {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.main_menu_activity);
    }

    public void StartPuzzleTypeSelectActivity(View _view)
    {
        Intent intent = new Intent(this, PuzzleTypeSelectActivity.class);
        startActivity(intent);
    }

    public void StartPuzzleCreatorActivity(View _view)
    {
        Intent intent = new Intent(this, PuzzleCreatorActivity.class);
        startActivity(intent);
    }

    public void StartStatsActivity(View _view)
    {
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
    }

    public void StartOptionsMenuActivity(View _view)
    {
        Intent intent = new Intent(this, OptionsMenuActivity.class);
        startActivity(intent);
    }
}