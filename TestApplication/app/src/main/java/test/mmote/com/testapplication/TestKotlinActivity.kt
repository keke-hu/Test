package test.mmote.com.testapplication

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_test_kotlin.*

class TestKotlinActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_kotlin)
        val news=News("title","createdTime","sdfhsdf")
        tv_test.text = news.toString()
        showToast(news.createdTime)
    }


    data class News(val title: String, val createdTime: String, val link: String)
}
