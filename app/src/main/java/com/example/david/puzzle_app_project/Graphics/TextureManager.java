package com.example.david.puzzle_app_project.Graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import com.example.david.puzzle_app_project.R;

public class TextureManager
{
    public enum Texture
    {
        TEST(R.drawable.pascal_the_dog);

        private int m_resourceId;
        private int m_handle = 0;

        Texture(int _resourceId)
        {
            m_resourceId = _resourceId;
        }

        public int GetResourceId() {return m_resourceId;}

        public void SetHandle(int _handle) {m_handle = _handle;}
        public int GetHandle() {return m_handle;}
    }

    public static void CreateTextureHandles(Context _context)
    {
        final int[] textureHandles = new int[Texture.values().length];

        GLES20.glGenTextures(textureHandles.length, textureHandles, 0);

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        int counter = 0;
        for (Texture iterator : Texture.values())
        {
            if (textureHandles[counter] != 0)
            {
                final Bitmap bitmap = BitmapFactory.decodeResource(_context.getResources(), iterator.GetResourceId(), options);

                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandles[counter]);

                GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
                GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);

                GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

                bitmap.recycle();

                iterator.SetHandle(textureHandles[counter]);
            }
            else
            {
                throw new RuntimeException("[TextureManager.LoadTextures] There was an error loading the texture " + iterator + ".");
            }

            counter++;
        }
    }

}
