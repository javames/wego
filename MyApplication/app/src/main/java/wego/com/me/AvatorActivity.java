package wego.com.me;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import wego.com.R;
import wego.com.common.BaseActivity;
import wego.com.util.WindowUtil;
import wego.com.widget.SingleTabTiltle;

/**
 * Created by Administrator on 2018/1/3.
 */

public class AvatorActivity extends BaseActivity {

    @BindView(R.id.hpage_dtl_tab)
    SingleTabTiltle hpageDtlTab;
    @BindView(R.id.iv_detail_avator)
    CircleImageView ivDetailAvator;
    @BindView(R.id.rl_arrow_avator)
    RelativeLayout rlArrowAvator;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.tv_me_nickName)
    TextView tvMeNickName;
    @BindView(R.id.rl_arrow_nickname)
    RelativeLayout rlArrowNickname;
    @BindView(R.id.ll_nickname)
    LinearLayout llNickname;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.ll_name)
    LinearLayout llName;
    @BindView(R.id.tv_uid)
    TextView tvUid;
    @BindView(R.id.ll_account)
    LinearLayout llAccount;
    @BindView(R.id.tv_me_sex)
    TextView tvMeSex;
    @BindView(R.id.rl_me_sex)
    RelativeLayout rlMeSex;
    @BindView(R.id.ll_sex)
    LinearLayout llSex;
    @BindView(R.id.tv_me_birth)
    TextView tvMeBirth;
    @BindView(R.id.rl_me_birth)
    RelativeLayout rlMeBirth;
    @BindView(R.id.ll_birth)
    LinearLayout llBirth;
    @BindView(R.id.btn_login_out)
    TextView btnLoginOut;
    private Dialog selectIconDialog;

    @Override
    protected void setLayoutRes() {
        layoutResId = R.layout.activity_avator;
    }


    @Override
    protected void initView() {
        super.initView();
        hpageDtlTab.setTitle("个人资料");
    }

    private void openIconDialog() {
        if (selectIconDialog == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_select_avator, null);

            TextView cancelBtn = view.findViewById(R.id.btn_close);
            TextView takePhoto = view.findViewById(R.id.tv_avator_camera);
            TextView takePhum = view.findViewById(R.id.tv_avator_photo);

            cancelBtn.setOnClickListener(iconDiaClick);
            takePhoto.setOnClickListener(iconDiaClick);
            takePhum.setOnClickListener(iconDiaClick);

            selectIconDialog = new Dialog(this, R.style.BottomDialog);
            selectIconDialog.setContentView(view);
            selectIconDialog.setCanceledOnTouchOutside(true);

            Window window = selectIconDialog.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity= Gravity.BOTTOM;
            attributes.width=WindowUtil.getScreenWidth(this);
        }

        selectIconDialog.show();
    }

    private View.OnClickListener iconDiaClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (selectIconDialog != null) {
                selectIconDialog.dismiss();
            }
            switch (v.getId()) {
                case R.id.btn_close:
                    break;
                case R.id.tv_avator_camera:

                    break;
                case R.id.tv_avator_photo:
                    break;
            }
        }
    };


    @OnClick({R.id.hpage_dtl_tab, R.id.rl_arrow_avator, R.id.rl_head, R.id.rl_arrow_nickname, R.id.ll_nickname, R.id.tv_username, R.id.ll_name, R.id.ll_account, R.id.rl_me_sex, R.id.ll_sex, R.id.rl_me_birth, R.id.ll_birth, R.id.btn_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hpage_dtl_tab:
                break;
            case R.id.rl_arrow_avator:
                break;
            case R.id.rl_head:
                openIconDialog();
                break;
            case R.id.rl_arrow_nickname:
                break;
            case R.id.ll_nickname:
                break;
            case R.id.tv_username:
                break;
            case R.id.ll_name:
                break;
            case R.id.ll_account:
                break;
            case R.id.rl_me_sex:
                break;
            case R.id.ll_sex:
                break;
            case R.id.rl_me_birth:
                break;
            case R.id.ll_birth:
                break;
            case R.id.btn_login_out:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(selectIconDialog!=null&&selectIconDialog.isShowing()){
            selectIconDialog.hide();
        }
    }
}
