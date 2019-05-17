package com.example.david.puzzle_app_project;

import android.opengl.Matrix;

public abstract class RenderObject
{
    //private final int SHADER_LINK_POSITION = 0;
    //private final int SHADER_LINK_COLOUR = 1;

    //protected IntBuffer m_vertexCoordVBO;

    protected float[] m_vertices = new float[16];
    protected float[] m_colours = {1.0f, 1.0f, 1.0f, 1.0f};
    protected short[] m_drawOrder = new short[6];

    private Buffers m_buffers;

    protected Orientation m_orientation;

    private Boolean m_isInitialised = false;


    public RenderObject()
    {
        m_orientation = new Orientation();
        m_buffers = new Buffers();
    }

    private void Initialise()
    {
        // TODO: Could these methods be a better way of preparing the buffers?
        //GLES20.glGenBuffers(1, m_vertexCoordVBO);
        //GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, m_vertexCoordVBO);
        //GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, m_vertices.length, m_vertices, GLES20.GL_STATIC_DRAW);
        //GLES20.glEnableVertexAttribArray(SHADER_LINK_POSITION);
        //GLES20.glVertexAttribPointer(SHADER_LINK_POSITION, 3, GLES20.GL_FLOAT, false, 0, 0);

        // TODO: Could buffers be shared between objects?
        m_buffers.SetVertexBuffer(m_vertices);
        m_buffers.SetDrawOrderBuffer(m_drawOrder);

        m_isInitialised = true;
    }

    public void Render(RenderMethod _renderMethod, Camera _camera)
    {
        if (!m_isInitialised) Initialise();

        float[] mvMatrix = new float[16];
        float[] mvpMatrix = new float[16];
        Matrix.setIdentityM(mvMatrix, 0);
        Matrix.setIdentityM(mvpMatrix, 0);

        Matrix.multiplyMM(mvMatrix, 0, _camera.GetViewMatrix(), 0, m_orientation.GetModelMatrix(), 0);
        Matrix.multiplyMM(mvpMatrix, 0, _camera.GetProjectionMatrix(), 0, mvMatrix, 0);

        _renderMethod.Render(m_buffers, mvpMatrix, m_colours);
    }

    public void SetSolidColour(float _red, float _green, float _blue, float _alpha)
    {
        m_colours = new float[] {_red, _green, _blue, _alpha};
    }
}