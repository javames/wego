package wego.com.me;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wego.com.R;
import wego.com.ResetApplication;
import wego.com.common.BaseActivity;
import wego.com.http.RetrofitService;
import wego.com.http.common.CommonApi;
import wego.com.http.response.HttpResult;
import wego.com.login.bean.RefreshTokenEntity;
import wego.com.util.FileUtils;
import wego.com.util.JSONUtils;
import wego.com.util.WindowUtil;
import wego.com.widget.SingleTabTiltle;

import static wego.com.util.FileUtils.getRealFilePathFromUri;

/**
 * Created by Administrator on 2018/1/3.
 */

public class AvatorActivity extends BaseActivity {

    @BindView(R.id.tab_title)
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
    private File tempFile;

    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 6;
    private static final int MY_PERMISSIONS_REQUEST_PHOTO = 7;

    private static final int MY_PERMISSIONS_ALMU_WRITE_EXTERNAL_STORAGE=8;
    private static final int MY_PERMISSIONS_CARME_WRITE_EXTERNAL_STORAGE=9;

    private static final int MY_PERMISSIONS_READ_EXTERNAL_STORAGE=10;

    @Override
    protected void setLayoutRes() {
        layoutResId = R.layout.activity_avator;
    }

    // 1: qq, 2: weixin
    private int type=1;
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
            attributes.width=WindowUtil.getScreenWidth(this)-20;
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
                    //调取相机
                    if (ContextCompat.checkSelfPermission(AvatorActivity.this,
                            Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(AvatorActivity.this,
                                new String[]{Manifest.permission.CAMERA},
                                MY_PERMISSIONS_REQUEST_CAMERA);
                    } else {
                        showCameraAction();
                    }
                    break;
                case R.id.tv_avator_photo:
                    //调取相册
                    if (ContextCompat.checkSelfPermission(AvatorActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(AvatorActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_READ_EXTERNAL_STORAGE);
                    } else {
                        gotoPhoto();
                    }
                    break;
            }
        }
    };


    /**
     * 拍照
     */
    private void showCameraAction() {
        if (ContextCompat.checkSelfPermission(AvatorActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //在其他地方获取读取手机内存授权
            ActivityCompat.requestPermissions(AvatorActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_CARME_WRITE_EXTERNAL_STORAGE);
        } else {
            operCamera();
        }
    }

    private void gotoPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.select_picture)), REQUEST_PICK);
    }

    private void operCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(AvatorActivity.this.getPackageManager()) != null) {
            try {
                tempFile = FileUtils.createTmpFile(AvatorActivity.this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (tempFile != null && tempFile.exists()) {
                 /*获取当前系统的android版本号*/
                int currentapiVersion = Build.VERSION.SDK_INT;
                Log.e("currentapiVersion", "currentapiVersion====>" + currentapiVersion);
                if (currentapiVersion < 24) {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                    startActivityForResult(intent, REQUEST_CAPTURE);
                } else {
                    ContentValues contentValues = new ContentValues(1);
                    contentValues.put(MediaStore.Images.Media.DATA, tempFile.getAbsolutePath());
                    Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(intent, REQUEST_CAPTURE);
                }
            } else {
                Toast.makeText(AvatorActivity.this, getResources().getString(R.string.picture_path_error), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(AvatorActivity.this, "", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.tab_title, R.id.rl_arrow_avator, R.id.rl_head, R.id.rl_arrow_nickname, R.id.ll_nickname, R.id.tv_username, R.id.ll_name, R.id.ll_account, R.id.rl_me_sex, R.id.ll_sex, R.id.rl_me_birth, R.id.ll_birth, R.id.btn_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tab_title:
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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_CAMERA:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    operCamera();
                }else {
                    // Permission Denied
                    Toast.makeText(AvatorActivity.this, getResources().getString(R.string.camera_power), Toast.LENGTH_SHORT).show();
                }
                break;
            case MY_PERMISSIONS_READ_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    gotoPhoto();
                }else{
                    Toast.makeText(AvatorActivity.this, getResources().getString(R.string.phone_power), Toast.LENGTH_SHORT).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 打开截图界面
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.putExtra("type", type);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = data.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                    if (type == 1) {
                        ivDetailAvator.setImageBitmap(bitMap);
                    } else {
                        ivDetailAvator.setImageBitmap(bitMap);
                    }

                    //此处后面可以将bitMap转为二进制上传后台网络
                    //......

                    String jsonObj = ResetApplication.get(CommonApi.refreshTokenEntity, null);
                    RefreshTokenEntity refreshTokenEntity = (RefreshTokenEntity) JSONUtils.JSONToObj(jsonObj, RefreshTokenEntity.class);

                    RetrofitService.createDuomiAPI().getUpToken("token").enqueue(new Callback<HttpResult<RefreshTokenEntity>>() {
                        @Override
                        public void onResponse(Call<HttpResult<RefreshTokenEntity>> call, Response<HttpResult<RefreshTokenEntity>> response) {

                        }

                        @Override
                        public void onFailure(Call<HttpResult<RefreshTokenEntity>> call, Throwable t) {

                        }
                    });

                }
                break;
        }
    }
}
