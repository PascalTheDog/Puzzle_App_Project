package com.example.david.puzzle_app_project;

public class RectangleShape extends Shape
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

    RectangleShape()
    {
        m_vertices = SQUARE_COORDS;
        m_drawOrder = DRAW_ORDER;
    }
}
