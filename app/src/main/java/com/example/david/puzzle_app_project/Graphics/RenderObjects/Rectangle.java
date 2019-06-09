package com.example.david.puzzle_app_project.Graphics.RenderObjects;

public class Rectangle extends Shape
{
    private static final float SQUARE_COORDS[] = {
            -0.5f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f,
            0.5f, 0.5f, 0.0f
    };
    private static final short DRAW_ORDER[] = {
            0, 1, 2, 0, 2, 3
    };
    private static final float TEXTURE_COORDS[] = {
            0.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f
    };

    public Rectangle()
    {
        m_vertices = SQUARE_COORDS;
        m_drawOrder = DRAW_ORDER;
        m_textureCoordinates = TEXTURE_COORDS;
    }

    public Rectangle(float _relativeWidth, float _relativeHeight)
    {
        float normalisedWidth, normalisedHeight;

        if (_relativeWidth < _relativeHeight)
        {
            normalisedWidth = _relativeWidth / _relativeHeight;
            normalisedHeight = 1.0f;
        }
        else
        {
            normalisedWidth = 1.0f;
            normalisedHeight = _relativeHeight / _relativeWidth;
        }

        float xMax = normalisedWidth / 2.0f;
        float yMax = normalisedHeight / 2.0f;

        m_vertices = new float[] {
                -xMax, yMax, 0.0f,
                -xMax, -yMax, 0.0f,
                xMax, -yMax, 0.0f,
                xMax, yMax, 0.0f
        };
        m_drawOrder = DRAW_ORDER;

        float xMaxTex = 0.5f + xMax;
        float xMinTex = 0.5f - xMax;

        float yMaxTex = 0.5f - yMax;
        float yMinTex = 0.5f + yMax;

        m_textureCoordinates = new float[] {
                xMinTex, yMaxTex,
                xMinTex, yMinTex,
                xMaxTex, yMinTex,
                xMaxTex, yMaxTex
        };
    }
}
