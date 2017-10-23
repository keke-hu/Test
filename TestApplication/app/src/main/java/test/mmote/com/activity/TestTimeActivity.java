package test.mmote.com.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import test.mmote.com.bean.MyDate;

/**
 * Created by KeKe on 2017/9/26.
 */

public class TestTimeActivity extends BaseActivity {
    TextView tvTime;
    Button button;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_test_time);
        tvTime = (TextView) findViewById(R.id.tv_time);
        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDate(context);
            }
        });
    }


    public static void selectDate(Context context) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, 1);
        MyDate tomorrow = new MyDate(calendar.getTime());//明天凌晨
        calendar.add(Calendar.DATE, 1);
        MyDate dayAfterTomorrow = new MyDate(calendar.getTime());//后天凌晨
        calendar.add(Calendar.DATE, 1);
        MyDate dayAfterDay = new MyDate(calendar.getTime());//大后天凌晨
        List<MyDate> todayDates = createDateList(new MyDate(new Date()), tomorrow);
        todayDates.add(0, new MyDate("现在", new Date()));
        List<MyDate> tomorrowDates = createDateList(tomorrow, dayAfterTomorrow);
        List<MyDate> dayAfterTomorrowDates = createDateList(dayAfterTomorrow, dayAfterDay);


        List<MyDate> dates = new ArrayList<>();
        dates.add(new MyDate("今天 " + getWeekOfDate(new Date()), new Date()));
        dates.add(new MyDate("明天 " + getWeekOfDate(tomorrow.getDate()), tomorrow.getDate()));
        dates.add(new MyDate("后天 " + getWeekOfDate(dayAfterTomorrow.getDate()), dayAfterTomorrow.getDate()));

        List<List<MyDate>> lists = new ArrayList<>();
        lists.add(todayDates);
        lists.add(tomorrowDates);
        lists.add(dayAfterTomorrowDates);

        OptionsPickerView optionsPickerView = new
                OptionsPickerView.Builder(context,
                new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {

                    }
                })
                .build();
        optionsPickerView.setPicker(dates, lists);
        optionsPickerView.setSelectOptions(0, 0);
        optionsPickerView.show();
    }

    static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }


    /**
     * 获取时间范围
     *
     * @param start 开始时间 包括在内
     * @param end   截至时间 不包括
     * @return 时间范围
     */
    private static List<MyDate> createDateList(MyDate start, MyDate end) {
        final long INTERVAL = 1000 * 60 * 15;
        List<MyDate> todayDates = new ArrayList<>();
        long lStart = start.getDate().getTime();
        long lEnd = end.getDate().getTime();
        long temp = lEnd - INTERVAL;
        while (temp >= lStart) {
            Date date = new Date();
            date.setTime(temp);
            temp = temp - INTERVAL;
            todayDates.add(new MyDate(date));
        }
        Collections.reverse(todayDates);
        return todayDates;
    }
}
