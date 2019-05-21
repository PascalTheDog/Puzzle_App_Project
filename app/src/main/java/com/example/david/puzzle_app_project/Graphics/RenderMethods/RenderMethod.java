package com.example.david.puzzle_app_project.Graphics.RenderMethods;

import android.content.Context;

import com.example.david.puzzle_app_project.Graphics.Buffers;


public abstract class RenderMethod
{
    Context m_context;

    RenderMethod(Context _context)
    {
        m_context = _context;
    }

    public abstract void Render(Buffers _buffers, float[] _mvpMatrix, float[] _colours);
}
