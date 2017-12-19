package wego.com.hompage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import wego.com.R;
import wego.com.common.BaseActivity;
import wego.com.util.WindowUtil;
import wego.com.widget.SingleTabTiltle;

/**
 * Created by Administrator on 2017/10/28.
 */

public class HPageDtlActivity extends BaseActivity {

    @BindView(R.id.hpage_dtl_tab)
    SingleTabTiltle tabTiltle;
    @Override
    protected void setLayoutRes() {
        layoutResId=R.layout.activity_hpage_dtl;
    }

    @Override
    protected void initView() {
        super.initView();
        WindowUtil.setStatusBarColor(activity,R.color.main_blue);
        tabTiltle.setLeftIcon(R.mipmap.back_white);
        tabTiltle.setTitleColor(getResources().getColor(R.color.white));
        tabTiltle.setTitle("中山陵");
    }
}
