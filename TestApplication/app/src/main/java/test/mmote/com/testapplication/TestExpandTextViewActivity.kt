package test.mmote.com.testapplication

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_test_expand_text_view.*
import test.mmote.com.widge.ExpandableLinearLayout


class TestExpandTextViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_expand_text_view)
        expandableTextView.onExpandListener = object : ExpandableLinearLayout.OnExpandListener {
            override fun onExpand(view: ExpandableLinearLayout?) {
            }

            override fun onCollapse(view: ExpandableLinearLayout?) {
            }
        }
        button_toggle.setOnClickListener(View.OnClickListener {
            if (expandableTextView.isExpanded) {
                expandableTextView.collapse()
            } else {
                expandableTextView.expand()
            }
        })
    }
}
