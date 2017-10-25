package test.mmote.com.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_thumb_up.*

import test.mmote.com.activity.R

class ThumbUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thumb_up)
        tuv.setOnClickListener {

        }
    }
}
