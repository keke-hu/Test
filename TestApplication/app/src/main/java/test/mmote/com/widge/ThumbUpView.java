package test.mmote.com.widge;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import test.mmote.com.activity.R;
import test.mmote.com.util.DensityUtil;

/**
 * Created by KeKe on 2017/10/23.
 * <p>
 * 仿即刻App点赞效果
 */

public class ThumbUpView extends View implements View.OnClickListener {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint testPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Point point = new Point(300, 300);
    boolean liked = false;
    Bitmap shiningBitmap, likeSelectedBitmap, likeUnSelectedBitmap;
    Matrix matrix = new Matrix();
    boolean isCompleted = true;
    float progress = 1;
    float percentage = 0;
    float offset = 10;
    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "progress", 0.8f, 1.2f, 1.0f);
    ObjectAnimator percentageAnimator = ObjectAnimator.ofFloat(this, "percentage", 0f, 1f);
    float textBaseLine;

    {
        shiningBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_messages_like_selected_shining);
        likeSelectedBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_messages_like_selected);
        likeUnSelectedBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_messages_like_unselected);
        textBaseLine = point.y + likeUnSelectedBitmap.getHeight() + offset;
        testPaint.setColor(Color.GRAY);
        testPaint.setTextSize(DensityUtil.sp2px(getContext(), 14));
        matrix = new Matrix();
        percentageAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isCompleted = false;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isCompleted = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public ThumbUpView(Context context) {
        super(context);
    }

    public ThumbUpView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public ThumbUpView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (liked) {
            if (isCompleted) {
                canvas.drawBitmap(shiningBitmap, point.x, point.y, paint);
            }
            canvas.save();
            matrix.reset();
            matrix.setScale(progress, progress, point.x + likeSelectedBitmap.getWidth() / 2, point.y + likeSelectedBitmap.getHeight() / 2);
            canvas.setMatrix(matrix);
            canvas.drawBitmap(likeSelectedBitmap, point.x - (likeSelectedBitmap.getWidth() - shiningBitmap.getWidth()) / 2,
                    point.y + shiningBitmap.getHeight() / 2, paint);
            canvas.restore();
            canvas.drawText("3", point.x + likeSelectedBitmap.getWidth() + 10,
                    textBaseLine - testPaint.getFontSpacing() * percentage, testPaint);
        } else {
            canvas.drawBitmap(likeUnSelectedBitmap, point.x - (likeUnSelectedBitmap.getWidth() - shiningBitmap.getWidth()) / 2,
                    point.y + shiningBitmap.getHeight() / 2, paint);
            canvas.drawText("3", point.x + likeSelectedBitmap.getWidth() + 10,
                    textBaseLine + testPaint.getFontSpacing() * percentage, testPaint);
        }
    }


    @Override
    public void onClick(View v) {
        if (isCompleted) {
            liked = !liked;
            objectAnimator.start();
            percentageAnimator.start();
        }
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
        invalidate();
    }
}
