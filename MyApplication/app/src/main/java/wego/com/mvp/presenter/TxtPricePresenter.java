package wego.com.mvp.presenter;

import wego.com.http.common.interz.OnNetRequestListener;
import wego.com.mvp.view.BaseView;

/**
 * Created by Administrator on 2017/10/27.
 */

public interface TxtPricePresenter extends BasePresenter {

    public void getTxtData(String topicId, OnNetRequestListener listener, int requestCode);

}
