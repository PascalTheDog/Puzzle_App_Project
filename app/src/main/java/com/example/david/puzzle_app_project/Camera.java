package com.example.david.puzzle_app_project;

import android.opengl.Matrix;

public class Camera
{
    private float[] m_viewMatrix = new float[16];
    private float[] m_projectionMatrix = new float[16];

    public Camera(Vector3D _eye, Vector3D _centre, Vector3D _up,
                  double _fieldOfView, double _aspect, float _projNear, float _projFar)
    {
        SetViewMatrix(_eye, _centre, _up);
        SetProjectionMatrix(_fieldOfView, _aspect, _projNear, _projFar);
    }

    public void SetViewMatrix(Vector3D _eye, Vector3D _centre, Vector3D _up)
    {
        Matrix.setLookAtM(m_viewMatrix, 0,
                (float)_eye.x, (float)_eye.y, (float)_eye.z,
                (float)_centre.x, (float)_centre.y, (float)_centre.z,
                (float)_up.x, (float)_up.y, (float)_up.z);
    }

    public void SetProjectionMatrix(double _fieldOfView, double _aspect, float _projNear, float _projFar)
    {
        double projTop = Math.tan(_fieldOfView * Math.PI / 360.0) * _projNear;
        double projBottom = -projTop;
        double projLeft = projBottom * _aspect;
        double projRight = -projLeft;

        Matrix.frustumM(m_projectionMatrix, 0,
                (float)projLeft, (float)projRight,
                (float)projBottom, (float)projTop,
                _projNear, _projFar);
    }

    public float[] GetViewMatrix() {return m_viewMatrix;}
    public float[] GetProjectionMatrix() {return m_projectionMatrix;}
}
