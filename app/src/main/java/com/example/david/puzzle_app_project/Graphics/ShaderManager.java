package com.example.david.puzzle_app_project.Graphics;

import android.content.Context;
import android.opengl.GLES20;

import com.example.david.puzzle_app_project.R;

import static com.example.david.puzzle_app_project.FileManager.ReturnFileAsString;

public class ShaderManager
{
    public enum Shader
    {
        BASIC(R.raw.basic_vert_shader, R.raw.basic_frag_shader,
                "uMVPMatrix", "vPosition", "vColour", "uTexture", "aTextureCoordinate");


        private int m_vShaderResource;
        private int m_fShaderResource;

        private String m_mvpVariable;
        private String m_positionVariable;
        private String m_colourVariable;
        private String m_textureVariable;
        private String m_textureCoordinateVariable;

        private int m_mvpHandle;
        private int m_positionHandle;
        private int m_colourHandle;
        private int m_textureHandle;
        private int m_textureCoordinateHandle;

        private int m_programHandle = 0;


        Shader(int _vShaderResource, int _fShaderResource,
               String _mvpVariable, String _positionVariable, String _colourVariable,
               String _textureVariable, String _textureCoordinateVariable)
        {
            m_vShaderResource = _vShaderResource;
            m_fShaderResource = _fShaderResource;

            m_mvpVariable = _mvpVariable;
            m_positionVariable = _positionVariable;
            m_colourVariable = _colourVariable;
            m_textureVariable = _textureVariable;
            m_textureCoordinateVariable = _textureCoordinateVariable;
        }

        private void LinkShaderVariables()
        {
            m_mvpHandle = GLES20.glGetUniformLocation(m_programHandle, m_mvpVariable);
            m_positionHandle = GLES20.glGetAttribLocation(m_programHandle, m_positionVariable);
            m_colourHandle = GLES20.glGetUniformLocation(m_programHandle, m_colourVariable);
            m_textureHandle = GLES20.glGetUniformLocation(m_programHandle, m_textureVariable);
            m_textureCoordinateHandle = GLES20.glGetAttribLocation(m_programHandle, m_textureCoordinateVariable);
        }

        public int GetMVPHandle() {return m_mvpHandle;}
        public int GetPositionHandle() {return m_positionHandle;}
        public int GetColourHandle() {return m_colourHandle;}
        public int GetTextureHandle() {return m_textureHandle;}
        public int GetTextureCoordinateHandle() {return m_textureCoordinateHandle;}

        public void SetProgramHandle(int _programHandle)
        {
            m_programHandle = _programHandle;
            LinkShaderVariables();
        }
        public int GetProgramHandle()
        {
            return m_programHandle;
        }
    }


    private static int CreateShader(int _type, String _shaderCode)
    {
        int shader = GLES20.glCreateShader(_type);

        GLES20.glShaderSource(shader, _shaderCode);
        GLES20.glCompileShader(shader);

        // TODO: It would be nice to have proper error logging and exception handling here...
        int[] compileResult = new int[1];
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compileResult, 0);
        if (compileResult[0] != GLES20.GL_TRUE)
        {
            System.out.println("[ShaderManager.CreateShader] The shader failed to compile.");
            return 0;
        }

        return shader;
    }

    private static int CreateProgram(Context _context, int _vShaderFileName, int _fShaderFileName)
    {
        String vShaderCode = ReturnFileAsString(_context, _vShaderFileName);
        String fShaderCode = ReturnFileAsString(_context, _fShaderFileName);

        int vertexShader = CreateShader(GLES20.GL_VERTEX_SHADER, vShaderCode);
        int fragmentShader = CreateShader(GLES20.GL_FRAGMENT_SHADER, fShaderCode);

        int program = GLES20.glCreateProgram();
        GLES20.glAttachShader(program, vertexShader);
        GLES20.glAttachShader(program, fragmentShader);
        GLES20.glLinkProgram(program);

        // TODO: Errors should be handled better than this.  An exception should be thrown.
        // TODO: Could all of the shader programs be created in the same call, as with textures?
        int test[] = new int[1];
        GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, test, 0);
        if (test[0] != GLES20.GL_TRUE)
        {
            String error = GLES20.glGetProgramInfoLog(test[0]);
            System.out.println("[ShaderManager.CreateProgram] " + error);
        }

        GLES20.glDeleteShader(vertexShader);
        GLES20.glDeleteShader(fragmentShader);

        return program;
    }

    public static void CreateProgramHandles(Context _context)
    {
        for (Shader iterator : Shader.values())
        {
            iterator.SetProgramHandle(CreateProgram(_context, iterator.m_vShaderResource, iterator.m_fShaderResource));
        }
    }
}
