package test.mmote.com.widge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by KeKe on 2017/4/17.
 */

public class CanvasView extends View {

    public CanvasView(Context context) {
        this(context, null, 0);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Rect targetRect = new Rect(50, 50, 1000, 126);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(3);
        paint.setTextSize(80);
        paint.setColor(Color.CYAN);
        canvas.drawRect(targetRect, paint);
        paint.setColor(Color.RED);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        // 转载请注明出处：http://blog.csdn.net/hursing
        float baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        // 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()
        String testString = "b:" + fontMetrics.bottom + "t:" + fontMetrics.top;
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(testString, targetRect.centerX(), baseline, paint);
        paint.setTextSize(40);
        canvas.drawText(testString,targetRect.centerX(), baseline,paint);
    }
}
