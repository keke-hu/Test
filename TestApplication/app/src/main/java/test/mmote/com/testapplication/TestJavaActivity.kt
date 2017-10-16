package test.mmote.com.testapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import test.mmote.com.testapplication.java.ExecutionOrder

class TestJavaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_java)
        val exexutionOrder = ExecutionOrder()
    }
}
