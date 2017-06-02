package test.mmote.com.testapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addViews()

    }

    private fun addViews() {
        testKotlin()
        bitmapWidthButton()
        switchButton()
        canvasButton()
        testWebpButton()
    }


    private fun testKotlin() {
        val button = Button(mContext)
        button.text = "test kotlin"
        button.setOnClickListener {
            startActivity(Intent(mContext, TestKotlinActivity::class.java)) }
        ll_container!!.addView(button)
    }

    private fun bitmapWidthButton() {
        val button = Button(mContext)
        button.text = "查看bitmap"
        button.setOnClickListener { startActivity(Intent(mContext, BitmapWidthActivity::class.java)) }
        ll_container!!.addView(button)
    }

    private fun switchButton() {
        val button = Button(mContext)
        button.text = "开关按钮"
        button.setOnClickListener { startActivity(Intent(mContext, SwitchButtonActivity::class.java)) }
        ll_container!!.addView(button)
    }

    private fun canvasButton() {
        val button = Button(mContext)
        button.text = "1234Aa"
        button.setOnClickListener { startActivity(Intent(mContext, CanvasActivity::class.java)) }
        ll_container!!.addView(button)
    }

    private fun testWebpButton() {
        val button = Button(mContext)
        button.text = "test webp"
        button.setOnClickListener { startActivity(Intent(mContext, TestWebPActivity::class.java)) }
        ll_container!!.addView(button)
    }

}
