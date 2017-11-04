package wego.com.mvp.presenter;

import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/10/27.
 */

public interface BasePresenter {

    void addDisposable(Disposable subscription);
    void unDisposable();
}
