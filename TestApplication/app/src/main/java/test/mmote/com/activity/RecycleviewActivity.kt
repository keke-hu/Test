package test.mmote.com.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.mmote.play.bean.OrderBean
import kotlinx.android.synthetic.main.activity_recycleview.*
import test.mmote.com.adapter.OrderListAdapter
import java.util.*

class RecycleviewActivity : AppCompatActivity() {

    lateinit var mContext: Context
    lateinit var mAdapter: OrderListAdapter
    var mList = ArrayList<OrderBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycleview)
        mContext = this
        val ma = LinearLayoutManager(this)
        ma.orientation = LinearLayoutManager.VERTICAL
        swipe_target.layoutManager = ma
        mAdapter = OrderListAdapter(mContext)
        swipe_target.setAdapter(mAdapter)
        swipeToLoadLayout.isLoadMoreEnabled=true
        swipeToLoadLayout.setOnRefreshListener {
            val list = ArrayList<OrderBean>()
            list.add(OrderBean("sdfsdfdsf"))
            list.add(OrderBean("sdfsdfdsf"))
            list.add(OrderBean("sdfsdfdsf"))
            list.add(OrderBean("sdfsdfdsf"))
            list.add(OrderBean("sdfsdfdsf"))
            list.add(OrderBean("sdfsdfdsf"))
            list.add(OrderBean("sdfsdfdsf"))
            list.add(OrderBean("sdfsdfdsf"))
            list.add(OrderBean("sdfsdfdsf"))
            list.add(OrderBean("sdfsdfdsf"))
            list.add(OrderBean("sdfsdfdsf"))
            list.add(OrderBean("sdfsdfdsf"))
            list.add(OrderBean("sdfsdfdsf"))
            list.add(OrderBean("sdfsdfdsf"))
            list.add(OrderBean("sdfsdfdsf"))
            list.add(OrderBean("sdfsdfdsf"))
            list.add(OrderBean("sdfsdfdsf"))
            list.add(OrderBean("sdfsdfdsf"))
            list.add(OrderBean("sdfsdfdsf"))
            list.add(OrderBean("sdfsdfdsf"))
            mAdapter.setList(list)
            swipeToLoadLayout.isRefreshing=false
        }
        swipeToLoadLayout.setOnLoadMoreListener {
            val list = ArrayList<OrderBean>()
            list.add(OrderBean("sdfsdfdsf"))
            list.add(OrderBean("sdfsdfdsf"))
            list.add(OrderBean("sdfsdfdsf"))
            list.add(OrderBean("sdfsdfdsf"))
            mAdapter.append(list)
        }
        swipeToLoadLayout.isRefreshing=true
    }


}
