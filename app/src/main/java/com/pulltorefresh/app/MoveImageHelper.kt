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
    lateinit var  animationDrawable:AnimationDrawable
    lateinit var  anim:ObjectAnimator

    /**
     * 初始化
     *
     * @param refreshAnim   点点动画
     * @param moveImageView 街道背景
     */
    constructor(refreshAnim:ImageView , moveImageView:ImageView ) {
        animationDrawable =  refreshAnim!!.background as AnimationDrawable
        anim = ObjectAnimator.ofFloat(
                moveImageView, "ratio", 1f, 0f)
        anim.repeatCount = ValueAnimator.INFINITE
        anim.duration = 5000
        anim.interpolator =  LinearInterpolator()

    }

    fun start() {
        if (!animationDrawable.isRunning) {
            animationDrawable.start()
        }
        if (!anim.isRunning) {
            anim.start()
        }
    }

    fun stop() {
        if (animationDrawable.isRunning) {
            animationDrawable.stop()
        }
        if (anim.isRunning) {
            anim.end()
        }
    }
}
