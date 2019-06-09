package com.example.david.puzzle_app_project.Graphics.RenderObjects;

public class RegularPolygon extends Shape
{
    public RegularPolygon(short _numberOfEdges)
    {
        //TODO: There is perhaps a tidier way of doing this using "GL_TRIANGLE_FAN".

        // It is unlikely that more than 60 segments will be required.
        if (_numberOfEdges < 3) _numberOfEdges = 3;

        int numberOfCoords = (_numberOfEdges + 1) * 3;
        int numberOfTexCoords = (_numberOfEdges + 1) * 2;

        m_vertices = new float[numberOfCoords];
        m_drawOrder = new short[_numberOfEdges * 3];
        m_textureCoordinates = new float[numberOfTexCoords];

        double segmentAngle = 0.0;
        double segmentAngleStep = Math.toRadians(360.0 / _numberOfEdges);
        double radius = 0.5;

        for (short counter = 0; counter < _numberOfEdges; counter++)
        {
            int coord = counter * 3;
            m_vertices[coord] = (float)(Math.sin(segmentAngle) * radius);
            m_vertices[coord + 1] = (float)(Math.cos(segmentAngle) * radius);
            m_vertices[coord + 2] = 0.0f;

            m_drawOrder[coord] = counter;
            m_drawOrder[coord + 1] = _numberOfEdges;
            m_drawOrder[coord + 2] = (short)((counter + 1) % _numberOfEdges);

            int texCoord = counter * 2;
            m_textureCoordinates[texCoord] =  0.5f + m_vertices[coord];
            m_textureCoordinates[texCoord + 1] = 0.5f - m_vertices[coord + 1];

            segmentAngle += segmentAngleStep;
        }

        m_textureCoordinates[numberOfTexCoords - 1] = 0.5f;
        m_textureCoordinates[numberOfTexCoords - 2] = 0.5f;
    }
}
