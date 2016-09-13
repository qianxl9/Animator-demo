package com.example.jh.animator;

import android.animation.TypeEvaluator;
import android.util.Log;

import java.io.StringReader;

/**
 * Created by jh on 2016/9/12.
 */
public class ColorEvaluetor implements TypeEvaluator {
    private int mCurrentRed = -1;
    private int mCurrentGreen = -1;
    private int mCurrentBlue = -1;
    int startxRed;
    int startGreen;

    @Override
    public Object evaluate(float v, Object o, Object t1) {
        String startValue = (String) o;
        String endValue = (String) t1;
        startxRed = Integer.parseInt(startValue.substring(1,3),16);
        startGreen = Integer.parseInt(startValue.substring(3,5),16);
        int startBlue = Integer.parseInt(startValue.substring(5,7),16);
        int endRed = Integer.parseInt(endValue.substring(1,3),16);
        int endGreen = Integer.parseInt(endValue.substring(3,5),16);
        int endBlue = Integer.parseInt(endValue.substring(5,7),16);

        if (mCurrentRed == -1) {
            mCurrentRed = startxRed;
        }
        if (mCurrentBlue == -1) {
            mCurrentBlue = startBlue;
        }
        if (mCurrentGreen == -1) {
            mCurrentGreen = startGreen;
        }

        int redDiff = Math.abs(startxRed-endRed);
        int blueDiff = Math.abs(startBlue-endBlue);
        int greenDiff = Math.abs(startGreen-endGreen);
        int colorDiff = redDiff+blueDiff+greenDiff;



        if (mCurrentRed != endRed) {
            mCurrentRed = getCurrentColor(startxRed,endRed,colorDiff,0,v);
        }else if (mCurrentGreen != endGreen) {
            mCurrentGreen = getCurrentColor(startGreen,endGreen,colorDiff,redDiff,v);
        }
        else if (mCurrentBlue != endBlue) {
            mCurrentBlue = getCurrentColor(startBlue,endBlue,colorDiff,redDiff+greenDiff,v);
        }

        String currentColor = "#"+getHexString(mCurrentRed)+getHexString(mCurrentGreen)+getHexString(mCurrentBlue);

        return currentColor;
    }

    private String getHexString(int mCurrentRed) {
        String hexString = Integer.toHexString(mCurrentRed);
        if (hexString.length() == 1) {
            hexString = "0"+hexString;
        }
        return hexString;
    }

    private int getCurrentColor(int startRed, int endRed, int colorDiff, int i, float v) {

        int currentColor;

        if (startRed>endRed) {
            currentColor = (int) (startRed-(v*(endRed-startRed)));
            if (startRed<endRed)
                startRed=endRed;
        }else {
            currentColor = (int) (startRed+(v*(endRed-startRed)));
            if (startRed>endRed)
                startRed=endRed;
        }

        return currentColor;
    }
}
