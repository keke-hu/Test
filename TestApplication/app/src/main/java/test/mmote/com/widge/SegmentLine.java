package test.mmote.com.widge;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import test.mmote.com.activity.R;


/**
 * Created by KeKe on 2017/10/24.
 * 分段分割线
 */

public class SegmentLine extends View {

    float segmentWidth = 0;
    @ColorInt
    int segmentColor = Color.WHITE;
    Paint segmentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    public SegmentLine(Context context) {
        this(context, null);
    }

    public SegmentLine(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SegmentLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SegmentLine);
            segmentWidth = ta.getDimension(R.styleable.SegmentLine_lineWidth, 0);
            segmentColor = ta.getColor(R.styleable.SegmentLine_lineColor, Color.WHITE);
            ta.recycle();
        }
        segmentPaint.setColor(segmentColor);
        segmentPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        segmentPaint.setStrokeWidth(getHeight());
        canvas.drawRect(0, 0, segmentWidth, getHeight(), segmentPaint);
    }
}
