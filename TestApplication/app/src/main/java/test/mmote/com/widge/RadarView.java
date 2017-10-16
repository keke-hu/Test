package test.mmote.com.widge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by KeKe on 2017/8/18.
 * 雷达
 */

public class RadarView extends View {

    private String TAG = this.getClass().getSimpleName();

    private Paint mPaintLine;
    private Paint mPaintSector;  // 扫描效果的画笔
    public boolean mStarted = false;
    private ScanThread mThread;  // 扫描的线程
    private int start = 0;
    private Matrix matrix;
    /**
     * 中心点
     */
    private int cx = 0, cy = 0;
    Shader mShader;

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        mThread = new ScanThread(this);
    }

    public RadarView(Context context) {
        this(context, null);
    }

    /**
     * 画笔的基本设置
     */
    private void initPaint() {
        mPaintLine = new Paint();
        mPaintLine.setStrokeWidth(10);
        mPaintLine.setAntiAlias(true);
        mPaintLine.setStyle(Paint.Style.STROKE);
        mPaintLine.setColor(0xff000000);
        mPaintSector = new Paint();
        mPaintSector.setColor(0x9D00ff00);
        mPaintSector.setAntiAlias(true);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        cx = getMeasuredWidth() / 2;
        cy = getMeasuredHeight() / 2;
        Log.d(TAG, cx + "-----" + cy);
        /**
         * SweepGradient扫描/梯度渲染
         * SweepGradient(float cx, float cy, int color0, int color1)
         * cx	渲染中心点x 坐标
         * cy	渲染中心点y 坐标
         * color0	起始渲染颜色
         * color1	结束渲染颜色
         *
         */
        mShader = new SweepGradient(cx, cy, Color.GREEN, Color.YELLOW);
        mPaintSector.setShader(mShader);
    }

    /**
     * 开始扫描
     */
    public void startScan() {
        mThread.start();
        mStarted = true;
    }

    /**
     * 暂停扫描
     */
    public void stopScan() {
        if (mStarted) {
            Thread.interrupted();
            mStarted = false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /**
         * 画两个圈圈
         */
        canvas.drawCircle(cx, cy, 175, mPaintLine);
        canvas.drawCircle(cx, cy, 350, mPaintLine);
        canvas.concat(matrix);
        canvas.drawCircle(cx, cy, (float) Math.sqrt(cx * cx + cy * cy), mPaintSector);
        super.onDraw(canvas);
    }


    private class ScanThread extends Thread {
        private RadarView view;

        ScanThread(RadarView view) {
            this.view = view;
        }

        @Override
        public void run() {
            while (true) {
                if (mStarted) {
                    view.post(new Runnable() {
                        public void run() {
                            start = start + 1;
                            matrix = new Matrix();
                            matrix.postRotate(start, cx, cy);
                            view.invalidate();
                        }
                    });
                    try {
                        Thread.sleep(15);  // 设置扫描的停止时间
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
