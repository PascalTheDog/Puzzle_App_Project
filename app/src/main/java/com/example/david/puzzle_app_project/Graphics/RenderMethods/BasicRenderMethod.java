package com.example.david.puzzle_app_project.Graphics.RenderMethods;


import android.opengl.GLES20;

import com.example.david.puzzle_app_project.Graphics.RenderObjects.RenderObject;
import com.example.david.puzzle_app_project.Graphics.ShaderManager;


public class BasicRenderMethod extends RenderMethod
{
    private static final int COORDS_PER_VERTEX = 3;
    private static final int TEXTURE_COORDS_PER_VERTEX = 2;


    @Override
    public void Render(float[] _mvpMatrix, RenderObject _renderObject)
    {
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glEnable(GLES20.GL_CULL_FACE);

        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);

        int program = ShaderManager.Shader.BASIC.GetProgramHandle();
        GLES20.glUseProgram(program);
        if (program != 0)
        {
            int mvpMatrixHandle = ShaderManager.Shader.BASIC.GetMVPHandle();
            int positionHandle = ShaderManager.Shader.BASIC.GetPositionHandle();
            int colourHandle = ShaderManager.Shader.BASIC.GetColourHandle();
            int textureCoordinateHandle = ShaderManager.Shader.BASIC.GetTextureCoordinateHandle();
            int textureUniformHandle = ShaderManager.Shader.BASIC.GetTextureHandle();

            int vertexStride = COORDS_PER_VERTEX * 4;
            int textureStride = TEXTURE_COORDS_PER_VERTEX * 4;

            if(_renderObject.HasTexture())
            {
                GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, _renderObject.GetTexture().GetHandle());
                GLES20.glUniform1i(textureUniformHandle, 0);
                GLES20.glEnableVertexAttribArray(textureCoordinateHandle);
                GLES20.glVertexAttribPointer(textureCoordinateHandle, TEXTURE_COORDS_PER_VERTEX,
                        GLES20.GL_FLOAT, false, textureStride,
                        _renderObject.GetBuffers().GetTextureBuffer());
            }

            GLES20.glEnableVertexAttribArray(positionHandle);
            GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT,
                    false, vertexStride, _renderObject.GetBuffers().GetVertexBuffer());

            GLES20.glUniform4fv(colourHandle, 1, _renderObject.GetColours(), 0);

            GLES20.glUniformMatrix4fv(mvpMatrixHandle, 1, false, _mvpMatrix,0);

            GLES20.glDrawElements(GLES20.GL_TRIANGLES, _renderObject.GetBuffers().GetDrawOrderBuffer().limit(),
                        GLES20.GL_UNSIGNED_SHORT, _renderObject.GetBuffers().GetDrawOrderBuffer());

            GLES20.glDisableVertexAttribArray(positionHandle);
            if(_renderObject.HasTexture())
                GLES20.glDisableVertexAttribArray(textureCoordinateHandle);
            GLES20.glUseProgram(0);
        }

        GLES20.glDisable(GLES20.GL_DEPTH_TEST);
        GLES20.glDisable(GLES20.GL_BLEND);
        GLES20.glDisable(GLES20.GL_CULL_FACE);
    }
}
