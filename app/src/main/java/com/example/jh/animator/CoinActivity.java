package com.example.jh.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by jh on 2016/9/13.
 */
public class CoinActivity extends AppCompatActivity {

    private ImageView coinImage;
    private ImageView waletImage;
    private Button startButton;
    private AnimatorListenerAdapter animatorA;
    private int height;
    private float  distance;
    private ObjectAnimator teleportA;
    private AnimatorSet totran;
    private AnimatorSet scalXY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coin_layout);
        boolean x = getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
              height = getWindow().getDecorView().getHeight();
            }
        });
        coinImage = (ImageView) findViewById(R.id.image_coin);
        waletImage = (ImageView) findViewById(R.id.image_wallet);
        startButton = (Button) findViewById(R.id.startA);
        initAnimatorA();
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimator();
            }
        });
    }

    private void startAnimator() {
        distance = (height/2.1f);
        teleportA = ObjectAnimator.ofFloat(coinImage,"translationY",-distance).setDuration(1);
        ObjectAnimator rotationA = ObjectAnimator.ofFloat(coinImage,"rotationY",0,360f);
        ObjectAnimator tranA = ObjectAnimator.ofFloat(coinImage,"translationY",0);
        ObjectAnimator sclaX = ObjectAnimator.ofFloat(waletImage,"scaleX",1.1f,0.9f,1);
        ObjectAnimator sclaY = ObjectAnimator.ofFloat(waletImage,"scaleY",0.75f,1.2f,1);

        totran = new AnimatorSet().setDuration(1500);
        scalXY = new AnimatorSet().setDuration(500);
        scalXY.play(sclaX).with(sclaY);

        totran.setInterpolator(new AccelerateInterpolator(3f));
        totran.play(rotationA).with(tranA);


        totran.addListener(animatorA);
        scalXY.addListener(animatorA);
        teleportA.addListener(animatorA);

        teleportA.start();
    }

    private void initAnimatorA(){
        animatorA = new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (teleportA.equals(animation)) {
                    totran.start();
                }else if (totran.equals(animation)) {
                    scalXY.start();
                }
            }
        };
    }

}
