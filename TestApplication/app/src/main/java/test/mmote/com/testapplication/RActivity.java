package test.mmote.com.testapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.mmote.play.bean.OrderBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import test.mmote.com.adapter.OrderListAdapter;

/**
 * Created by KeKe on 2017/7/14.
 */

public class RActivity extends AppCompatActivity {

    Context mContext;
    OrderListAdapter mAdapter;
    List mList = new ArrayList<OrderBean>();
    UltimateRecyclerView xrv_order_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        mContext = this;
        LinearLayoutManager ma= new LinearLayoutManager(this);
        ma.setOrientation(LinearLayoutManager.VERTICAL);
    }
}
