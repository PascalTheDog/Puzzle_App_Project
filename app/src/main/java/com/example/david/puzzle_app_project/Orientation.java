package com.example.david.puzzle_app_project;

import android.opengl.Matrix;

public class Orientation
{
    private float[] m_modelMatrix = new float[16];

    public Orientation()
    {
        Matrix.setIdentityM(m_modelMatrix, 0);
    }

    public float[] GetModelMatrix() {return m_modelMatrix;}
}