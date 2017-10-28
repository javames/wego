package wego.com.mvp.presenter;

import wego.com.http.common.interz.OnNetRequestListener;
import wego.com.mvp.model.TxtPriceModel;
import wego.com.mvp.model.TxtPriceModelImpl;
import wego.com.mvp.view.BaseView;

/**
 * Created by Administrator on 2017/10/28.
 */

public class TxtPricePresenterImpl implements TxtPricePresenter{

    private BaseView baseView;
    private TxtPriceModel txtPriceModel;
    public TxtPricePresenterImpl(BaseView baseView) {
        this.baseView=baseView;
    }

    @Override
    public void getTxtData(String topicId, OnNetRequestListener listener, int requestCode) {
        txtPriceModel=new TxtPriceModelImpl(listener);
        txtPriceModel.getCurrentPrice(topicId);
    }

}
