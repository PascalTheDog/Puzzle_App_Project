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

    public Rectangle()
    {
        m_vertices = SQUARE_COORDS;
        m_drawOrder = DRAW_ORDER;
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
    }
}
