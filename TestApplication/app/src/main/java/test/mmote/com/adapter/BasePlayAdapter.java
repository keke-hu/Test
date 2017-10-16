package test.mmote.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KeKe on 2017/7/7.
 */

public abstract class BasePlayAdapter<T> extends UltimateViewAdapter<RecyclerView.ViewHolder> {

    protected Context mContent;

    protected List<T> list;

    protected OnItemClickListener<T> mOnItemClickListener;


    public BasePlayAdapter(Context context) {
        this.mContent = context;
        list = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.mOnItemClickListener = listener;
    }

    public void setList(List<T> t) {
        list.clear();
        append(t);
    }

    public void append(List<T> t) {
        int positionStart = list.size();
        int itemCount = t.size();
        list.addAll(t);
        if (positionStart > 0 && itemCount > 0) {
            notifyItemRangeInserted(positionStart, itemCount);
        } else {
            notifyDataSetChanged();
        }
    }

    public void remove(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }



    @Override
    public int getAdapterItemCount() {
        return list.size();
    }
}
