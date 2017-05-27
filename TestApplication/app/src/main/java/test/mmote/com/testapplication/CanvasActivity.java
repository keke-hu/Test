package test.mmote.com.testapplication;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import test.mmote.com.widge.PuzzleviewHorizontalScrollview;

/**
 * Created by KeKe on 2017/4/17.
 */

public class CanvasActivity extends BaseActivity {
    @Bind(R.id.pvhs)
    PuzzleviewHorizontalScrollview pvhs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//默认是竖屏
        Observable.timer(5, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .map(new Func1<Long, Object>() {
                    @Override
                    public Object call(Long aLong) {
                        pvhs.smoothScrollTo(100,100);
                        return null;
                    }
                })
                .subscribe();
    }
}
