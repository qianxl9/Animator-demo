package com.example.jh.animator;

import android.animation.TimeInterpolator;

/**
 * Created by jh on 2016/9/13.
 */
public class DecelerateAccelerateInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float v) {
        float result;
        if (v<=0.5f) {
            result =(float) (Math.sin(Math.PI*v))/2;
        }else {
            result = (float) (2-Math.sin(Math.PI*v))/2;
        }

        return result;
    }
}
