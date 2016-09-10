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
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by ddsc on 9/9/2016.
 */
public class MoveImageView extends ImageView {
    private final String TAG = "MoveImageView";
    private Context mContext;
    private float ratio;
    // shader containing repeated waves
    private BitmapShader bitmapShader;
    private Paint paint;
    private Matrix matrix;

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
        this.ratio = ratio;
        invalidate();
    }

    public float getRatio() {
        return ratio;
    }

    private void init(Context context) {
        mContext = context;
        matrix = new Matrix();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(bitmapShader);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.bg_pull_to_refresh);
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw: " + ratio);
        if (bitmapShader != null) {
            if (paint.getShader() == null) {
                paint.setShader(bitmapShader);
            }
           matrix.setScale(1f, 1f, 0, getHeight()/2);
            matrix.postTranslate(getWidth() * ratio, 0);
            bitmapShader.setLocalMatrix(matrix);
            canvas.drawRect(0, 0, getWidth(),
                    getHeight(), paint);
        } else {
            paint.setShader(null);
        }
    }
}
