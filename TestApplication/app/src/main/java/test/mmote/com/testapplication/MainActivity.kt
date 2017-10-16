package test.mmote.com.testapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import test.mmote.com.TestApplication

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addViews()

    }

    private fun addViews() {
        testRunText()
        testExpand()
        testTime()
        testAudio()
        testPermisson()
        testMarquee()
        testRxJava()
        testRadar()
        testConstraintLayout()
        testEnum()
        testApplication()
        testRv()
        testPid()
        testToolbar()
        testKotlin()
        bitmapWidthButton()
        switchButton()
        canvasButton()
        testWebpButton()
    }

    private fun testRunText() {
        val button = Button(mContext)
        button.text = "test run text"
        button.setOnClickListener {
            startActivity(Intent(this, TestRuningTextViewActivity::class.java))
        }
        ll_container!!.addView(button)
    }

    private fun testExpand() {
        val button = Button(mContext)
        button.text = "test expand"
        button.setOnClickListener {
            startActivity(Intent(this, TestExpandTextViewActivity::class.java))
        }
        ll_container!!.addView(button)
    }


    private fun testTime() {
        val button = Button(mContext)
        button.text = "test time"
        button.setOnClickListener {
            startActivity(Intent(this, TestTimeActivity::class.java))
        }
        ll_container!!.addView(button)
    }

    private fun testAudio() {
        val button = Button(mContext)
        button.text = "test Audio"
        button.setOnClickListener {
            startActivity(Intent(this, RecordAudioActivity::class.java))
        }
        ll_container!!.addView(button)
    }


    private fun testPermisson() {
        val button = Button(mContext)
        button.text = "test Permissions"
        button.setOnClickListener {
            startActivity(Intent(this, PermissionsActivity::class.java))
        }
        ll_container!!.addView(button)
    }


    private fun testMarquee() {
        val button = Button(mContext)
        button.text = "test Marquee"
        button.setOnClickListener {
            startActivity(Intent(this, MarqueeActivity::class.java))
        }
        ll_container!!.addView(button)
    }

    private fun testRxJava() {
        val button = Button(mContext)
        button.text = "test Rxjava"
        button.setOnClickListener {
            startActivity(Intent(this, RxJavaActivity::class.java))
        }
        ll_container!!.addView(button)
    }

    private fun testRadar() {
        val button = Button(mContext)
        button.text = "test radar"
        button.setOnClickListener {
            startActivity(Intent(this, TestRadarActivity::class.java))
        }
        ll_container!!.addView(button)
    }

    private fun testConstraintLayout() {
        val button = Button(mContext)
        button.text = "test ConstraintLayout"
        button.setOnClickListener {
            startActivity(Intent(this, TestConstraintLayoutActivity::class.java))
        }
        ll_container!!.addView(button)
    }


    private fun testEnum() {
        val button = Button(mContext)
        button.text = "test Enum"
        button.setOnClickListener {
            startActivity(Intent(this, TestEnumActivity::class.java))
        }
        ll_container!!.addView(button)
    }

    private fun testApplication() {
        val button = Button(mContext)
        button.text = "test Application"
        button.setOnClickListener {
            TestApplication.getInstance().startActivity(Intent(TestApplication.getInstance(), PidActivity::class.java))
        }
        ll_container!!.addView(button)
    }

    private fun testRv() {
        val button = Button(mContext)
        button.text = "test rv"
        button.setOnClickListener {
            startActivity(Intent(mContext, RecycleviewActivity::class.java))
        }
        ll_container!!.addView(button)

    }

    private fun testPid() {
        val button = Button(mContext)
        button.text = "test pid"
        button.setOnClickListener {
            startActivity(Intent(mContext, PidActivity::class.java))
        }
        ll_container!!.addView(button)
    }

    private fun testToolbar() {
        val button = Button(mContext)
        button.text = "test toolbar"
        button.setOnClickListener {
            startActivity(Intent(mContext, ToolbarActivity::class.java))
        }
        ll_container!!.addView(button)
    }

    private fun testKotlin() {
        val button = Button(mContext)
        button.text = "test kotlin"
        button.setOnClickListener {
            startActivity(Intent(mContext, TestKotlinActivity::class.java))
        }
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
