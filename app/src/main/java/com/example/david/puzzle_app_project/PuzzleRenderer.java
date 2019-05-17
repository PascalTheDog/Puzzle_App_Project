package com.example.david.puzzle_app_project;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class PuzzleRenderer implements GLSurfaceView.Renderer
{
    private Context m_context;

    private Camera m_camera;

    private RectangleShape m_testSquare;
    private BasicRenderMethod m_testRenderMethod;

    private double m_angle = 0.0;


    PuzzleRenderer(Context _context)
    {
        m_context = _context;
        ShaderManager.ResetProgramHandles();
        Initialise();
    }

    private void Initialise()
    {
        m_testSquare = new RectangleShape();
        m_testRenderMethod = new BasicRenderMethod(m_context);
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig)
    {
        gl10.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl10.glClearDepthf(1.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int _width, int _height)
    {
        gl10.glViewport(0, 0, _width, _height);
        InitialiseCamera(_width, _height);
    }

    @Override
    public void onDrawFrame(GL10 gl10)
    {
        // The main loop will go here.
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        float red = (float)Math.sin(m_angle);
        float green = (float)Math.sin(m_angle + 10);
        float blue = (float)Math.sin(m_angle + 20);
        m_angle = (m_angle + 0.05) % 360;

        m_testSquare.SetSolidColour(red, green, blue, 1.0f);
        m_testSquare.Render(m_testRenderMethod, m_camera);
    }

    private void InitialiseCamera(int _width, int _height)
    {
        Vector3D eye = new Vector3D(0, 0, 5.0);
        Vector3D centre = new Vector3D(0.0, 0.0, -0.5);
        Vector3D up = new Vector3D(0.0, 1.0, 0.0);

        double aspectRatio = (double)_width / (double)_height;
        float projNear = 1.0f;
        float projFar = 300.0f;
        double fieldOfView = 35.0f;

        m_camera = new Camera(eye, centre, up, fieldOfView, aspectRatio, projNear, projFar);
    }
}
