package test.mmote.com.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_test_pid.*

/**
 * Created by KeKe on 2017/6/21.
 * 测试pid
 */
class PidActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_pid)
        tv_pid.text = android.os.Process.myPid().toString()
    }
}