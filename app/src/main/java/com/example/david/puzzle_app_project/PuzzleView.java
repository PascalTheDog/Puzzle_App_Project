package com.example.david.puzzle_app_project;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class PuzzleView extends GLSurfaceView
{
    private final PuzzleRenderer m_renderer;

    public PuzzleView(Context _context)
    {
        super(_context);
        setEGLContextClientVersion(2);

        m_renderer = new PuzzleRenderer(_context);
        setRenderer(m_renderer);

        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
