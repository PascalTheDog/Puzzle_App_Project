package com.example.david.puzzle_app_project;

import android.content.Context;


public abstract class RenderMethod
{
    protected Context m_context;

    public RenderMethod(Context _context)
    {
        m_context = _context;
    }

    public abstract void Render(Buffers _buffers, float[] _mvpMatrix, float[] _colours);
}
