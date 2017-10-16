package test.mmote.com.testapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by KeKe on 2017/3/28.
 */

open class BaseActivity : AppCompatActivity() {
    public open var mContext: BaseActivity? = null
    protected open val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
    }

    protected fun showToast(toast: String) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
    }


    protected fun toast(s: String) {
        Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show()
    }

    protected fun longToast(s: String) {
        Toast.makeText(mContext, s, Toast.LENGTH_LONG).show()
    }
}
