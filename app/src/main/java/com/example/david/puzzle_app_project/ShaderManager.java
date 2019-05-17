package com.example.david.puzzle_app_project;

import android.content.Context;
import android.opengl.GLES20;

import static com.example.david.puzzle_app_project.FileManager.ReturnFileAsString;

public class ShaderManager
{
    enum ShaderType
    {
        Basic
    }

    private static int m_basicShaderProgram;

    private static int CreateShader(int _type, String _shaderCode)
    {
        int shader = GLES20.glCreateShader(_type);

        GLES20.glShaderSource(shader, _shaderCode);
        GLES20.glCompileShader(shader);

        // TODO: It would be nice to have error logging here...
        int[] compileResult = new int[1];
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compileResult, 0);
        if (compileResult[0] != GLES20.GL_TRUE)
        {
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
        int test[] = new int[1];
        GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, test, 0);
        if (test[0] != GLES20.GL_TRUE)
        {
            String error = GLES20.glGetProgramInfoLog(test[0]);
            System.out.println(error);
        }

        GLES20.glDeleteShader(vertexShader);
        GLES20.glDeleteShader(fragmentShader);

        return program;
    }

    public static int ReturnProgramHandle(Context _context, ShaderType _shaderType)
    {
        int programHandle;
        int vShaderFileName;
        int fShaderFileName;

        switch (_shaderType)
        {
            case Basic:
                programHandle = m_basicShaderProgram;
                vShaderFileName = R.raw.basic_vert_shader;
                fShaderFileName = R.raw.basic_frag_shader;
                break;
            default:
                // TODO: Is there a "not implemented" exception that could be used here?
                return 0;
        }

        if (programHandle == 0)
        {
            programHandle = CreateProgram(_context, vShaderFileName, fShaderFileName);
        }
        return programHandle;
    }

    public static void ResetProgramHandles()
    {
        // This is needed to reset program handles when the activity is recreated.
        // TODO: This is awkward and ugly.  Is there a better way of handling this?
        m_basicShaderProgram = 0;
    }
}
