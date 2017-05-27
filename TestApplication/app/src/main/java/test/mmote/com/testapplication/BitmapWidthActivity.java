package test.mmote.com.testapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by KeKe on 2017/5/25.
 */

public class BitmapWidthActivity extends BaseActivity {
    @Bind(R.id.tv_resolution)
    TextView tvResolution;
    @Bind(R.id.tv_width)
    TextView tvWidth;
    @Bind(R.id.tv_height)
    TextView tvHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_width);
        ButterKnife.bind(this);
        Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.mipmap.maimeng_logo);
        tvResolution.setText(getResources().getDisplayMetrics().density+"");
        tvHeight.setText(bitmap.getHeight());
        tvWidth.setText(bitmap.getWidth());
    }
}
