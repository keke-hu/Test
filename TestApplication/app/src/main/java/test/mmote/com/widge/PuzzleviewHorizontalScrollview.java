package test.mmote.com.widge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

import test.mmote.com.util.DensityUtil;


/**
 * Created by KeKe on 2017/4/19.
 * 只能通过滑动条滑动的ScrollView
 */

public class PuzzleviewHorizontalScrollview extends HorizontalScrollView {
    private Context context;

    public PuzzleviewHorizontalScrollview(Context context) {
        super(context, null);
        this.context=context;
    }

    public PuzzleviewHorizontalScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public PuzzleviewHorizontalScrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        canvas.drawRect(DensityUtil.getDisplayWidth(context) / 2 - 100,
                getHeight() - 100, DensityUtil.getDisplayWidth(context) / 2 + 100, getHeight(), paint);
    }
}
