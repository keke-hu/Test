package test.mmote.com.bean;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by KeKe on 2017/9/26.
 * 下单页面时间选择
 */

public class MyDate {

    String content = "";

    Date date;

    public MyDate(Date date) {
        this.date = date;
    }

    public MyDate(String content, Date date) {
        this.date = date;
        this.content = content;
    }

    @Override
    public String toString() {
        if (!TextUtils.isEmpty(content)) {
            return content;
        } else {
            return new SimpleDateFormat("HH:mm").format(date);
        }
    }

    public Date getDate() {
        return date;
    }
}
