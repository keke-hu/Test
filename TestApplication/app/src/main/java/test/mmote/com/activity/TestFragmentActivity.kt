package test.mmote.com.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_test_fragment.*
import test.mmote.com.fragment.FACommunicationFragment
import test.mmote.com.testinterface.FABridge

class TestFragmentActivity : BaseActivity() ,FABridge{

    lateinit var faCommunicationFragment: FACommunicationFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_fragment)
        faCommunicationFragment = FACommunicationFragment.FACommunication.getInstance()
        supportFragmentManager.beginTransaction().add(R.id.fl_fragment, faCommunicationFragment).commit()
        btn_call_fragment.setOnClickListener({
            faCommunicationFragment.aCallFragment("call from activity ")
        })
    }


    /**
     * fragment调用activity中的方法
     */
    public fun fCallActivity(string: String) {
        log(string)
    }


    override fun callActivity(string: String) {
        log(string)
    }

}
