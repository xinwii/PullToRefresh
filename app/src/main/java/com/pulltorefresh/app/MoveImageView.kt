package com.pulltorefresh.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by ddsc on 9/9/2016.
 */
public class MoveImageView : ImageView {

    public constructor(context: Context) : super(context) {
        init(context)
    }

    public constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    public constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private lateinit var mContext: Context
    private var mRatio: Float = 0f
    // shader containing repeated waves
    private lateinit var mBitmapShader: BitmapShader
    private lateinit var mPaint: Paint
    private lateinit var mMatrix: Matrix
    private var mBitmapWidth: Int = 0//背景图片宽度

    fun setRatio(ratio: Float) {
        mRatio = ratio
        invalidate()
    }

    fun getRatio(): Float {
        return mRatio
    }

    private fun init(context: Context) {
        mContext = context
        mMatrix = Matrix()
        mPaint = Paint()
        mPaint.isAntiAlias = true;
        mPaint.shader = mBitmapShader;
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        var bitmap = BitmapFactory.decodeResource(mContext.resources, R.mipmap.bg_pull_to_refresh)
        mBitmapWidth = bitmap.width;
        mBitmapShader = BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP)
    }


    override fun onDraw(canvas: Canvas) {
        if (mBitmapShader != null) {
            if (mPaint.shader == null) {
                mPaint.shader = mBitmapShader;
            }
            //循环平移
            mMatrix.setScale(1f, 1f, 0f, 0f)
            mMatrix.postTranslate(mBitmapWidth * mRatio, 0f)
            mBitmapShader.setLocalMatrix(mMatrix)
            canvas.drawRect(0f, 0f, width as Float,
                    height as Float, mPaint)
        } else {
            mPaint.shader = null;
        }
    }
}
