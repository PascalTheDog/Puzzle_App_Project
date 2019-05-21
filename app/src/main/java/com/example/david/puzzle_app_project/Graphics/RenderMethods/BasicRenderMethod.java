package com.example.david.puzzle_app_project.Graphics.RenderMethods;

import android.content.Context;
import android.opengl.GLES20;

import com.example.david.puzzle_app_project.Graphics.Buffers;
import com.example.david.puzzle_app_project.Graphics.ShaderManager;


public class BasicRenderMethod extends RenderMethod
{
    private static final int COORDS_PER_VERTEX = 3;

    public BasicRenderMethod(Context _context)
    {
        super(_context);
    }

    @Override
    public void Render(Buffers _buffers, float[] _mvpMatrix, float[] _colours)
    {
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glEnable(GLES20.GL_CULL_FACE);

        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);

        int program = ShaderManager.ReturnProgramHandle(m_context, ShaderManager.ShaderType.Basic);
        GLES20.glUseProgram(program);
        if (program != 0)
        {
            int mvpMatrixHandle = GLES20.glGetUniformLocation(program, "uMVPMatrix");
            int positionHandle = GLES20.glGetAttribLocation(program, "vPosition");
            int colourHandle = GLES20.glGetUniformLocation(program, "vColour");
            int vertexStride = COORDS_PER_VERTEX * 4;

            GLES20.glEnableVertexAttribArray(positionHandle);

            GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT,
                    false, vertexStride, _buffers.GetVertexBuffer());

            GLES20.glUniform4fv(colourHandle, 1, _colours, 0);
            GLES20.glUniformMatrix4fv(mvpMatrixHandle, 1, false, _mvpMatrix,0);

            GLES20.glDrawElements(GLES20.GL_TRIANGLES, _buffers.GetDrawOrderBuffer().limit(),
                        GLES20.GL_UNSIGNED_SHORT, _buffers.GetDrawOrderBuffer());

            GLES20.glDisableVertexAttribArray(positionHandle);
        }
        GLES20.glUseProgram(0);

        GLES20.glDisable(GLES20.GL_DEPTH_TEST);
        GLES20.glDisable(GLES20.GL_BLEND);
        GLES20.glDisable(GLES20.GL_CULL_FACE);
    }
}
