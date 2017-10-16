package test.mmote.com.testapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

import static test.mmote.com.testapplication.R.id.tv_resolution;

/**
 * Created by KeKe on 2017/5/25.
 */

public class BitmapWidthActivity extends BaseActivity {
    @Bind(tv_resolution)
    TextView tvResolution;
    @Bind(R.id.tv_width)
    TextView tvWidth;
    @Bind(R.id.tv_height)
    TextView tvHeight;
    @Bind(R.id.iv_test)
    ImageView ivTest;
    @Bind(R.id.iv_test1)
    ImageView ivTest1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_width);
        ButterKnife.bind(this);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.maimeng_logo);
        tvResolution.setText(getResources().getDisplayMetrics().density + "");
        tvHeight.setText(bitmap.getHeight() + "");
        tvWidth.setText(bitmap.getWidth() + "");
    }

    @Override
    protected void onResume() {
        super.onResume();
        ivTest1.setImageBitmap(createBitmap(ivTest));
    }

    public Bitmap createBitmap(ImageView imageView) {
        Bitmap bitmap = Bitmap.createBitmap(300, 200, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        imageView.draw(canvas);
        return bitmap;
    }
}
