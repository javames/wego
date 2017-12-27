package wego.com.login.presenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import wego.com.mvp.presenter.BasePresenter;

/**
 * Created by Administrator on 2017/12/27.
 */

public class BasePresenterImpl implements BasePresenter{

    private CompositeDisposable compositeDisposable;
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
