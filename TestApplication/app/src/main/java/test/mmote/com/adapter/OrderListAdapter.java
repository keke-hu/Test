package test.mmote.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mmote.play.bean.OrderBean;

import test.mmote.com.testapplication.R;

/**
 * Created by KeKe on 2017/7/14.
 * 订单列表
 */

public class OrderListAdapter extends BasePlayAdapter<OrderBean> {

    public OrderListAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder newFooterHolder(View view) {
        return null;
    }

    @Override
    public RecyclerView.ViewHolder newHeaderHolder(View view) {
        return null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(mContent).
                inflate(R.layout.layout_consume_order, viewGroup, false);
        return new ConsumeHolder(view);
    }

    @Override
    public long generateHeaderId(int i) {
        return 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ConsumeHolder) holder).tv.setText("sdfsdfsdfdsf");
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }


    static class ConsumeHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        ConsumeHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv);
        }
    }

}
