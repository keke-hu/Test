package test.mmote.com.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by KeKe on 2017/8/23.
 */

public class RxJavaActivity extends BaseActivity {
    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
    }

    private void test() {
        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        if (!subscriber.isUnsubscribed()) {//判断观察者的状态，如果没有订阅则不发送数据
                            Thread thread = Thread.currentThread();
                            Log.d(TAG, "create:Thread name:" + thread.getName() + "---->Thread id:" + thread.getId());
                            subscriber.onNext("created 操作符1");
                            subscriber.onNext("created 操作符2");
                            subscriber.onNext("created 操作符3");
                            subscriber.onNext("created 操作符4");
                            subscriber.onCompleted();
                        }

                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        Thread thread = Thread.currentThread();
                        Log.d(TAG, "map1:Thread name:" + thread.getName() + "---->Thread id:" + thread.getId());
                        return s;
                    }
                })
                .observeOn(Schedulers.newThread())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        Thread thread = Thread.currentThread();
                        Log.d(TAG, "map2:Thread name:" + thread.getName() + "---->Thread id:" + thread.getId());
                        return s;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Thread thread = Thread.currentThread();
                        Log.d(TAG, "onCompleted:Thread name:" + thread.getName() + "---->Thread id:" + thread.getId());
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError:" + e.toString());

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, s);
                        Thread thread = Thread.currentThread();
                        Log.d(TAG, "onNext:Thread name:" + thread.getName() + "---->Thread id:" + thread.getId());
                    }
                });
    }
}
