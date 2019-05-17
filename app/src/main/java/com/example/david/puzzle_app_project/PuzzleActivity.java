package com.example.david.puzzle_app_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PuzzleActivity extends AppCompatActivity
{
    private PuzzleView m_puzzleView;

    @Override
    protected void onCreate(Bundle _savedInstanceState)
    {
        super.onCreate(_savedInstanceState);
        m_puzzleView = new PuzzleView(this);
        setContentView(m_puzzleView);
    }

    public void StartMainMenuActivity(View _view)
    {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}