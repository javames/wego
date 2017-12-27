package wego.com.login.presenter;

import wego.com.http.common.interz.OnNetRequestListener;
import wego.com.mvp.presenter.BasePresenter;

/**
 * Created by Administrator on 2017/12/27.
 */

public interface LoginPresenter{

    void identifyCode(String phone,String code, OnNetRequestListener listener, int requestCode);
}
