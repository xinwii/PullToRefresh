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
public class MoveImageView extends ImageView {
    private Context mContext;
    private float mRatio;
    // shader containing repeated waves
    private BitmapShader mBitmapShader;
    private Paint mPaint;
    private Matrix mMatrix;
    private int mBitmapWidth = 0;//背景图片宽度

    public MoveImageView(Context context) {
        super(context);
        init(context);
    }

    public MoveImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MoveImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setRatio(float ratio) {
        mRatio = ratio;
        invalidate();
    }

    public float getRatio() {
        return mRatio;
    }

    private void init(Context context) {
        mContext = context;
        mMatrix = new Matrix();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(mBitmapShader);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.bg_pull_to_refresh);
        mBitmapWidth = bitmap.getWidth();
        mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmapShader != null) {
            if (mPaint.getShader() == null) {
                mPaint.setShader(mBitmapShader);
            }
            //循环平移
            mMatrix.setScale(1f, 1f, 0, 0);
            mMatrix.postTranslate(mBitmapWidth * mRatio, 0);
            mBitmapShader.setLocalMatrix(mMatrix);
            canvas.drawRect(0, 0, getWidth(),
                    getHeight(), mPaint);
        } else {
            mPaint.setShader(null);
        }
    }
}
