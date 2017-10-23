package test.mmote.com.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_test_toolbar.*

/**
 * Created by KeKe on 2017/6/20.
 *
 */
class ToolbarActivity :BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}