package com.example.david.puzzle_app_project.Graphics;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;


public class Buffers
{
    private static final int BYTES_PER_FLOAT = 4;
    private static final int BYTES_PER_SHORT = 2;

    private FloatBuffer m_vertexBuffer;
    private FloatBuffer m_colourBuffer;
    private ShortBuffer m_drawOrderBuffer;
    private FloatBuffer m_textureBuffer;


    private static FloatBuffer InitialiseFloatBuffer(float[] _inputArray)
    {
        FloatBuffer outputBuffer;

        ByteBuffer buffer = ByteBuffer.allocateDirect(_inputArray.length * BYTES_PER_FLOAT);
        buffer.order(ByteOrder.nativeOrder());
        outputBuffer = buffer.asFloatBuffer();
        outputBuffer.put(_inputArray);
        outputBuffer.position(0);

        return outputBuffer;
    }

    private static ShortBuffer InitialiseShortBuffer(short[] _inputArray)
    {
        ShortBuffer outputBuffer;

        ByteBuffer dOrderBuffer = ByteBuffer.allocateDirect(_inputArray.length * BYTES_PER_SHORT);
        dOrderBuffer.order(ByteOrder.nativeOrder());
        outputBuffer = dOrderBuffer.asShortBuffer();
        outputBuffer.put(_inputArray);
        outputBuffer.position(0);

        return outputBuffer;
    }

    public void SetVertexBuffer(float[] _vertices)
    {
        m_vertexBuffer = InitialiseFloatBuffer(_vertices);
    }
    public FloatBuffer GetVertexBuffer() {return m_vertexBuffer;}

    public void SetColourBuffer(float[] _colours)
    {
        m_colourBuffer = InitialiseFloatBuffer(_colours);
    }
    public FloatBuffer GetColourBuffer() {return m_colourBuffer;}

    public void SetDrawOrderBuffer(short[] _drawOrder)
    {
        m_drawOrderBuffer = InitialiseShortBuffer(_drawOrder);
    }
    public ShortBuffer GetDrawOrderBuffer() {return m_drawOrderBuffer;}

    public void SetTextureBuffer(float[] _textureCoordinates)
    {
        m_textureBuffer = InitialiseFloatBuffer(_textureCoordinates);
    }
    public FloatBuffer GetTextureBuffer() {return m_textureBuffer;}
}
