package test.mmote.com.testapplication;

import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * Created by KeKe on 2017/3/28.
 */

public class SwitchButtonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_button);
        ButterKnife.bind(this);
    }
}
