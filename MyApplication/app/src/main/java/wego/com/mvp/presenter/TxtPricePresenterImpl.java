package wego.com.mvp.presenter;

import org.json.JSONObject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import wego.com.http.common.interz.OnNetRequestListener;
import wego.com.mvp.model.Price;
import wego.com.mvp.model.TxtPriceModel;
import wego.com.mvp.model.TxtPriceModelImpl;
import wego.com.mvp.view.BaseView;

/**
 * Created by Administrator on 2017/10/28.
 */

public class TxtPricePresenterImpl implements TxtPricePresenter{

    private BaseView baseView;
    private TxtPriceModel txtPriceModel;
    private CompositeDisposable compositeDisposable;
    public TxtPricePresenterImpl(BaseView baseView) {
        this.baseView=baseView;
    }

    @Override
    public void getTxtData(String topicId, OnNetRequestListener listener, int requestCode) {
        txtPriceModel=new TxtPriceModelImpl(listener,this);
        txtPriceModel.getCurrentPrice(topicId);
    }


    @Override
    public void getCaseRecord(JSONObject obj, OnNetRequestListener listener, int requestCode) {
        if(txtPriceModel==null){
            txtPriceModel=new TxtPriceModelImpl(listener,this);
        }
        txtPriceModel.getCaseRecord(obj);
    }

    /**
     * 将Disposable添加
     *
     * @param subscription
     */
    @Override
    public void addDisposable(Disposable subscription) {
        if(compositeDisposable==null||compositeDisposable.isDisposed()){
            compositeDisposable=new CompositeDisposable();
            compositeDisposable.add(subscription);
        }
    }

    /**
     * 在界面退出等需要解绑观察者的情况下调用此方法统一解绑，防止Rx造成的内存泄漏
     */
    @Override
    public void unDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}
