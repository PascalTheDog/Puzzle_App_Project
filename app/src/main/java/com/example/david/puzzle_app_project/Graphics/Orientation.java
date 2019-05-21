package com.example.david.puzzle_app_project.Graphics;

import android.opengl.Matrix;

public class Orientation
{
    private Vector3D m_position = new Vector3D();
    private Vector3D m_scaleFactor = new Vector3D(1.0, 1.0, 1.0);

    private float[] m_rotationMatrix = new float[16];

    public Orientation()
    {
        Matrix.setIdentityM(m_rotationMatrix, 0);
    }

    public void SetPosition(Vector3D _position) {m_position = _position;}
    public void SetPosition(float _x, float _y, float _z)
    {
        m_position.x = _x;
        m_position.y = _y;
        m_position.z = _z;
    }
    public Vector3D GetPosition() {return m_position;}

    public void SetScaleFactor(Vector3D _scaleFactor) {m_scaleFactor = _scaleFactor;}
    public Vector3D GetScaleFactor() {return m_scaleFactor;}

    public float[] GetRotationMatrix() {return m_rotationMatrix;}

    public float[] GetModelMatrix()
    {
        float[] modelMatrix = new float[16];
        Matrix.setIdentityM(modelMatrix, 0);
        Matrix.translateM(modelMatrix, 0, (float)m_position.x, (float)m_position.y, (float)m_position.z);
        Matrix.multiplyMM(modelMatrix, 0, modelMatrix, 0, m_rotationMatrix, 0);
        return modelMatrix;
    }
}