package test.mmote.com.widge;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import test.mmote.com.util.DensityUtil;

/**
 * Created by KeKe on 2017/10/18.
 */

public class WaveTextView extends View {
    Path path = new Path();
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    float height;//view的高度
    float width;//view的宽度
    float waveHeight = 50;//浪高

    int segment = (int) Math.pow(2, 2);//完整的波浪的个数，铺满2个view的宽度

    float offsetX = 0;

    float waveCenterY = 0;//波浪的中心点y坐标
    float widthUnit = 0;//四分之一波浪的长度
    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "offsetX", 0, DensityUtil.getDisplayWidth(getContext()));

    public WaveTextView(Context context) {
        super(context);
    }

    public WaveTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WaveTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setColor(Color.YELLOW);
        objectAnimator.setDuration(800);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        objectAnimator.end();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        objectAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        height = getHeight();
        width = getWidth();
        widthUnit = (width * 2) / (segment * 4);
        waveCenterY = height / 2;
        path.rewind();
        path.moveTo(-width + offsetX, waveCenterY);
        for (int i = 0; i < segment; i++) {
            path.rQuadTo(widthUnit, waveHeight, widthUnit * 2, 0);
            path.rQuadTo(widthUnit, -waveHeight, widthUnit * 2, 0);
        }
        path.lineTo(width, height);
        path.lineTo(0, height);
        path.close();
        canvas.drawPath(path, paint);
    }


    public float getOffsetX() {
        return offsetX;
    }


    @SuppressWarnings("unused")
    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
        invalidate();
    }
}
