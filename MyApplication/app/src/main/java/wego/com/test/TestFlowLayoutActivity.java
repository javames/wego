package wego.com.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import wego.com.R;
import wego.com.widget.CustomFlowLayout;

/**
 * Created by Administrator on 2017/12/19.
 */

public class TestFlowLayoutActivity extends AppCompatActivity {

    private CustomFlowLayout flowlayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clipimage);
//        flowlayout=findViewById(R.id.flowlayout);
    }
}
