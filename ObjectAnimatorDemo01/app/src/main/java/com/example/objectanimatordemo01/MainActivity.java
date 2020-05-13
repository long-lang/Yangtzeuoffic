package com.example.objectanimatordemo01;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv_ma;
    TextView tv_mr;
    TextView tv_ms;
    TextView tv_mt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       iniAnimation();
    }
    private void iniAnimation(){
        tv_ma=(TextView)findViewById(R.id.tv_ma);
        tv_mr=(TextView)findViewById(R.id.tv_mr);
        tv_ms=(TextView)findViewById(R.id.tv_ms);
        tv_mt=(TextView)findViewById(R.id.tv_mt);
        // 透明度动画
        ObjectAnimator.ofFloat(tv_ma, "alpha", 1, 0, 1)
                .setDuration(4000)
                .start();

        // 缩放
        final AnimatorSet animatorSet = new AnimatorSet();
        tv_ms.setPivotX(tv_ms.getWidth()+250);
        tv_ms.setPivotY(tv_ms.getHeight()+250);
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(tv_ms, "scaleX", 1, 0)
                        .setDuration(2000),
                ObjectAnimator.ofFloat(tv_ms, "scaleY", 1, 0)
                        .setDuration(2000)
        );
        animatorSet.start();

        // 平移 translation
        final AnimatorSet translationAnimatorSet = new AnimatorSet();
        translationAnimatorSet.playTogether(
                ObjectAnimator.ofFloat(tv_mt, "translationX", 20, 100)
                        .setDuration(2000),
                ObjectAnimator.ofFloat(tv_mt, "translationY", 20,100)
                        .setDuration(2000)
        );
        translationAnimatorSet.start();

        // 利用 ObjectAnimator 实现旋转动画
        final AnimatorSet rotateAnimationSet = new AnimatorSet();
        rotateAnimationSet.playTogether(
                ObjectAnimator.ofFloat(tv_mr, "rotation",0, 360)
                        .setDuration(2000)
        );
        rotateAnimationSet.start();
    }
}
