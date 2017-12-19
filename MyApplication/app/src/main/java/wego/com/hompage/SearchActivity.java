package wego.com.hompage;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import wego.com.R;
import wego.com.common.BaseActivity;

/**
 * Created by Administrator on 2017/12/19.
 */

public class SearchActivity extends BaseActivity {

    @BindView(R.id.cancel_btn)
    TextView cancelBtn;
    @BindView(R.id.edit_btn)
    EditText editBtn;
    @Override
    protected void setLayoutRes() {
        layoutResId= R.layout.activity_search;
    }

    @Override
    protected void initView() {
        super.initView();

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editBtn.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                toast("即将搜索。。。");
                return true;
            }
        });

    }


}
