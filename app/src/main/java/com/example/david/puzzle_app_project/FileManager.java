package com.example.david.puzzle_app_project;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileManager
{
    private static String ConvertFileToString(InputStream _inputStream)
    {
        StringBuffer tempBuffer = new StringBuffer();
        BufferedReader in = new BufferedReader(new InputStreamReader(_inputStream));

        try
        {
            String read = in.readLine();
            while (read != null)
            {
                tempBuffer.append(read);
                tempBuffer.append("\n");
                read = in.readLine();
            }
        }
        catch (Exception e)
        {
            Log.e("ERROR: FILE READ ", "An error occurred in \"LoadFileAsString\": " + e.getLocalizedMessage());
        }

        return tempBuffer.toString();
    }

    public static String ReturnFileAsString(Context _context, int _fileLocation)
    {
        String returnString = "";

        try
        {
            InputStream inputStream = _context.getResources().openRawResource(_fileLocation);
            returnString = ConvertFileToString(inputStream);
            inputStream.close();
        }
        catch (Exception e)
        {
            Log.e("ERROR: FILE READ ", "An error occurred in \"ReturnFileAsString\": " + e.getLocalizedMessage());
        }

        return returnString;
    }

    public static String ReturnFileAsString(Context _context, String _fileName)
    {
        String returnString = "";

        try
        {
            InputStream inputStream = _context.getAssets().open(_fileName);
            returnString = ConvertFileToString(inputStream);
            inputStream.close();
        }
        catch (Exception e)
        {
            Log.e("ERROR: FILE READ ", "An error occurred in \"ReturnFileAsString\": " + e.getLocalizedMessage());
        }

        return returnString;
    }
}
