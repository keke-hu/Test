package test.mmote.com.activity;


import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by KeKe on 2017/5/22.
 */

public class TestWebPActivity extends BaseActivity {

    @Bind(R.id.iv_webp)
    ImageView ivWebp;
    @Bind(R.id.iv_png)
    ImageView ivPng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_webp);
        ButterKnife.bind(this);
        Glide.with(this).load(R.mipmap.anchor_insert).into(ivWebp);
        Glide.with(this).load(R.mipmap.anchor_insert1).into(ivPng);

    }
}
