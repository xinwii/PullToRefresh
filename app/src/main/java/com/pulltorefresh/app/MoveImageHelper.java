package com.pulltorefresh.app;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * Created by ddsc on 9/10/2016.
 */
public class MoveImageHelper {
    private AnimationDrawable animationDrawable;
    private ObjectAnimator anim;

    /**
     * 初始化
     *
     * @param refreshAnim   点点动画
     * @param moveImageView 街道背景
     */
    public MoveImageHelper(ImageView refreshAnim, ImageView moveImageView) {
        animationDrawable = (AnimationDrawable) refreshAnim.getBackground();
        anim = ObjectAnimator.ofFloat(
                moveImageView, "ratio", 1f, 0f);
        anim.setRepeatCount(ValueAnimator.INFINITE);
        anim.setDuration(5000);
        anim.setInterpolator(new LinearInterpolator());

    }

    public void start() {
        if (!animationDrawable.isRunning()) {
            animationDrawable.start();
        }
        if (!anim.isRunning()) {
            anim.start();
        }
    }

    public void stop() {
        if (animationDrawable.isRunning()) {
            animationDrawable.stop();
        }
        if (anim.isRunning()) {
            anim.end();
        }
    }
}
