package test.mmote.com.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fargment_fac.*
import test.mmote.com.activity.R
import test.mmote.com.activity.TestFragmentActivity
import test.mmote.com.testinterface.FABridge

/**
 * Created by KeKe on 2017/10/23.
 * 测试fragment和Activity通讯的
 */
class FACommunicationFragment : BaseFragment() {

    lateinit var faBridge: FABridge

    object FACommunication {
        fun getInstance(): FACommunicationFragment {
            return FACommunicationFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        faBridge = context as FABridge
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fargment_fac, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_call_activity.setOnClickListener({
            (mContext as TestFragmentActivity).fCallActivity("call from fragment")
        })
        btn_call_activity_from_interface.setOnClickListener({
            faBridge.callActivity("call from fragment")
        })

    }

    /**
     * activity调用fragment中的方法
     */
    fun aCallFragment(string: String) {
        log(string)
    }
}