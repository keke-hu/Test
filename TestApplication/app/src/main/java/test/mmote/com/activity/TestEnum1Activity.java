package test.mmote.com.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import test.mmote.com.testenum.ACType;

/**
 * Created by KeKe on 2017/8/16.
 */

public class TestEnum1Activity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_test_enum);
        final ACType acType = ACType.getAcType("1");
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (acType == ACType.USER_AGREE_SERVICE) {
                    Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "2", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
