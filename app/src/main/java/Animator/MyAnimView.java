package Animator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;

import com.example.jh.animator.ColorEvaluetor;
import com.example.jh.animator.DecelerateAccelerateInterpolator;
import com.example.jh.animator.Point;
import com.example.jh.animator.PointEvaluator;

/**
 * Created by jh on 2016/9/12.
 */
public class MyAnimView extends View {
    public static final float REDIUS = 100f;

    private Point currenPoint;
    private Paint mPaint;


    public MyAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#cc9966"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currenPoint == null ) {
            currenPoint = new Point(REDIUS,REDIUS);
            drawCircle(canvas);
            startAnimation();
        }else {
            drawCircle(canvas);
        }
    }

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        mPaint.setColor(Color.parseColor(color));
        invalidate();
    }

    private void drawCircle(Canvas canvas) {
        float x = currenPoint.getX();
        float y = currenPoint.getY();
        canvas.drawCircle(x,y,REDIUS,mPaint);
    }

    private void startAnimation() {
        Point startP = new Point(getWidth()/2,REDIUS);
        Point endP = new Point(getWidth()/2,getHeight()-REDIUS);
        ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(),startP,endP);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currenPoint = (Point) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        ObjectAnimator objeca = ObjectAnimator.ofObject(this,"color",new ColorEvaluetor(),"#0000ff","#ff0000");
        AnimatorSet set = new AnimatorSet().setDuration(2000);
        set.play(animator).with(objeca);
        set.setInterpolator(new DecelerateAccelerateInterpolator());
        set.start();
    }
}
