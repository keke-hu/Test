package test.mmote.com;

import android.app.Application;

/**
 * Created by KeKe on 2017/4/19.
 */

public class TestApplication extends Application {
    public static TestApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
    }

    public static TestApplication getInstance(){
        return application;
    }
}
