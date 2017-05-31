package test.mmote.com.testapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.ll_container)
    LinearLayout llContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        addViews();

    }

    private void addViews() {
        testKotlin();
        bitmapWidthButton();
        switchButton();
        canvasButton();
        testWebpButton();
    }



    private void testKotlin() {
        Button button = new Button(mContext);
        button.setText("test kotlin");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, TestKotlinActivity.class));
            }
        });
        llContainer.addView(button);
    }

    private void bitmapWidthButton() {
        Button button = new Button(mContext);
        button.setText("查看bitmap");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, BitmapWidthActivity.class));
            }
        });
        llContainer.addView(button);
    }

    private void switchButton() {
        Button button = new Button(mContext);
        button.setText("开关按钮");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, SwitchButtonActivity.class));
            }
        });
        llContainer.addView(button);
    }

    private void canvasButton() {
        Button button = new Button(mContext);
        button.setText("canvas");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, CanvasActivity.class));
            }
        });
        llContainer.addView(button);
    }

    private void testWebpButton() {
        Button button = new Button(mContext);
        button.setText("test webp");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, TestWebPActivity.class));
            }
        });
        llContainer.addView(button);
    }

}
