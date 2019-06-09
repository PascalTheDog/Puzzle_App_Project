package com.example.david.puzzle_app_project.Graphics.RenderMethods;

import com.example.david.puzzle_app_project.Graphics.RenderObjects.RenderObject;


public abstract class RenderMethod
{
    public abstract void Render(float[] _mvpMatrix, RenderObject _renderObject);
}
