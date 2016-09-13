package com.example.jh.animator;

import android.animation.TypeEvaluator;

/**
 * Created by jh on 2016/9/12.
 */
public class PointEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float v, Object o, Object t1) {
        Point startP = (Point) o;
        Point endP = (Point) t1;
        float x = startP.getX()+v*(endP.getX()-startP.getX());
        float y = startP.getY()+v*(endP.getY()-startP.getY());
        Point point = new Point(x,y);
        return point;
    }
}
