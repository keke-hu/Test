package test.mmote.com.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.widget.Toast
import test.mmote.com.activity.BuildConfig
import test.mmote.com.testinterface.OnFragmentVisibilityChangedListener

/**
 * Created by KeKe on 2017/10/23.
 * fragment基类
 */
abstract class BaseFragment : Fragment(), View.OnAttachStateChangeListener, OnFragmentVisibilityChangedListener {
    val TAG = this.javaClass.simpleName
    lateinit var mContext: Context

    /**
     * ParentActivity是否可见
     */
    private var mParentActivityVisible = false
    /**
     * 是否可见（Activity处于前台、Tab被选中、Fragment被添加、Fragment没有隐藏、Fragment.View已经Attach）
     */
    private var mVisible = false

    private var mParentFragment: BaseFragment? = null
    private var mListener: OnFragmentVisibilityChangedListener? = null


    override fun onAttach(context: Context) {
        log("onAttach")
        super.onAttach(context)
        mContext = context
        val parentFragment = parentFragment
        if (parentFragment != null && parentFragment is BaseFragment) {
            mParentFragment = parentFragment
            mParentFragment!!.setOnVisibilityChangedListener(this)
        }
        checkVisibility(true)
    }

    override fun onDetach() {
        log("onDetach")
        if (mParentFragment != null) {
            mParentFragment!!.setOnVisibilityChangedListener(null)
        }
        super.onDetach()
        checkVisibility(false)
        mParentFragment = null
    }

    override fun onStart() {
        log("onStart")
        super.onStart()
        onActivityVisibilityChanged(true)
    }

    override fun onStop() {
        log("onStop")
        super.onStop()
        onActivityVisibilityChanged(false)
    }


    /**
     * ParentActivity可见性改变
     */
    protected fun onActivityVisibilityChanged(visible: Boolean) {
        mParentActivityVisible = visible
        checkVisibility(visible)
    }

    /**
     * ParentFragment可见性改变
     */
    override fun onFragmentVisibilityChanged(visible: Boolean) {
        checkVisibility(visible)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view!!.addOnAttachStateChangeListener(this)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        checkVisibility(!hidden)
    }

    /**
     * Tab切换时会回调此方法。对于没有Tab的页面，[Fragment.getUserVisibleHint]默认为true。
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        log("setUserVisibleHint = " + isVisibleToUser)
        super.setUserVisibleHint(isVisibleToUser)
        checkVisibility(isVisibleToUser)
    }

    override fun onViewAttachedToWindow(v: View) {
        log("onViewAttachedToWindow")
        checkVisibility(true)
    }

    override fun onViewDetachedFromWindow(v: View) {
        log("onViewDetachedFromWindow")
        v.removeOnAttachStateChangeListener(this)
        checkVisibility(false)
    }


    fun setOnVisibilityChangedListener(listener: OnFragmentVisibilityChangedListener?) {
        mListener = listener
    }


    /**
     * 检查可见性是否变化
     *
     * @param expected 可见性期望的值。只有当前值和expected不同，才需要做判断
     */
    private fun checkVisibility(expected: Boolean) {
        if (expected == mVisible) return
        val parentVisible = if (mParentFragment == null) mParentActivityVisible else mParentFragment!!.isFragmentVisible()
        val superVisible = super.isVisible()
        val hintVisible = userVisibleHint
        val visible = parentVisible && superVisible && hintVisible
        log(String.format("==> checkVisibility = %s  ( parent = %s, super = %s, hint = %s )",
                visible, parentVisible, superVisible, hintVisible))
        if (visible != mVisible) {
            mVisible = visible
            onVisibilityChanged(mVisible)
        }
    }

    /**
     * 可见性改变
     */
    protected fun onVisibilityChanged(visible: Boolean) {
        log("==> onFragmentVisibilityChanged = " + visible)
        if (mListener != null) {
            mListener!!.onFragmentVisibilityChanged(visible)
        }
    }

    /**
     * 是否可见（Activity处于前台、Tab被选中、Fragment被添加、Fragment没有隐藏、Fragment.View已经Attach）
     */
    fun isFragmentVisible(): Boolean {
        return mVisible
    }

    protected fun log(string: String) {
        if (BuildConfig.DEBUGGING) {
            Log.i(TAG, "(" + hashCode() + ")" + string)
        }
    }

    protected fun toast(s: String) {
        Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show()
    }

    protected fun longToast(s: String) {
        Toast.makeText(mContext, s, Toast.LENGTH_LONG).show()
    }
}