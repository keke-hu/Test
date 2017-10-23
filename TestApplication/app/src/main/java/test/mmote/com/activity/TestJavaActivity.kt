package test.mmote.com.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import test.mmote.com.activity.java.ExecutionOrder

class TestJavaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_java)
        val exexutionOrder = ExecutionOrder()
    }
}
