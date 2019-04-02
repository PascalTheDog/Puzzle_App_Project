package com.example.david.puzzle_app_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PuzzleTypeSelectActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle _savedInstanceState)
    {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.puzzle_type_select_activity);
    }

    public void StartPuzzleFilterActivity(View _view)
    {
        Intent intent = new Intent(this, PuzzleFilterActivity.class);
        startActivity(intent);
    }
}
