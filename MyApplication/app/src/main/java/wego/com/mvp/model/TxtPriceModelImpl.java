package wego.com.mvp.model;

import android.util.Log;


import org.json.JSONObject;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import wego.com.http.RetrofitService;
import wego.com.http.common.interz.OnNetRequestListener;
import wego.com.mvp.presenter.BasePresenter;

/**
 * Created by Administrator on 2017/10/27.
 */

public class TxtPriceModelImpl implements TxtPriceModel {

    //从Activity传过来还是在此处new未定
    private OnNetRequestListener onNetRequestListener;
    private BasePresenter basePresenter;
    public TxtPriceModelImpl(OnNetRequestListener onNetRequestListener, BasePresenter basePresenter) {
        this.onNetRequestListener=onNetRequestListener;
        this.basePresenter=basePresenter;
    }

    public TxtPriceModelImpl() {
        super();
    }

    @Override
    public void getCurrentPrice(String topicId) {
        Observable<JSONObject> txtCurrentPrice = RetrofitService.getInstance().createDuomiAPI().getTxtCurrentPrice(topicId);
        txtCurrentPrice
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONObject>(){

                    @Override
                    public void onSubscribe(Disposable d) {
                        basePresenter.addDisposable(d);
                        Log.i("test","onSubscribe.....");
                        onNetRequestListener.onStart();
                    }

                    @Override
                    public void onNext(JSONObject httpResult) {
                        Log.i("test","onNext.....");
                        onNetRequestListener.onSuccess(httpResult);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("test","onError....."+e.getMessage()+"// "+e.getCause());
                        onNetRequestListener.onFailure(e);
                        onNetRequestListener.onFinish();
                    }

                    @Override
                    public void onComplete() {
                        Log.i("test","onCompleted.....");
                        onNetRequestListener.onFinish();
                    }
                });

        //        txtCurrentPrice
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(new Observable<HttpResult>(){});
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {
//                        Log.i("test","call.....");
//                        onNetRequestListener.onStart();
//                    }
//                }).subscribe(new Subscriber<HttpResult<Price>>() {
//            @Override
//            public void onCompleted() {
//                Log.i("test","onCompleted.....");
//                onNetRequestListener.onFinish();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.i("test","onError....."+e.getMsg()+"// "+e.getCause());
//
//                onNetRequestListener.onFailure(e);
//                onNetRequestListener.onFinish();
//            }
//
//            @Override
//            public void onNext(HttpResult<Price> httpResult) {
//                Log.i("test","onNext....."+httpResult.getMsg());
//
//                onNetRequestListener.onSuccess(httpResult);
//            }
//        });
    }


    @Override
    public void getCaseRecord(JSONObject obj) {
        Observable<JSONObject> txtCurrentPrice = RetrofitService.getInstance().createDuomiAPI().getActCaseRecord(obj);
        txtCurrentPrice
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONObject>(){

                    @Override
                    public void onSubscribe(Disposable d) {
                        basePresenter.addDisposable(d);
                        Log.i("test","onSubscribe.....");
                        onNetRequestListener.onStart();
                    }

                    @Override
                    public void onNext(JSONObject httpResult) {
                        Log.i("test","onNext.....");
                        onNetRequestListener.onSuccess(httpResult);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("test","onError....."+e.getMessage()+"// "+e.getCause());
                        onNetRequestListener.onFailure(e);
                        onNetRequestListener.onFinish();
                    }

                    @Override
                    public void onComplete() {
                        Log.i("test","onCompleted.....");
                        onNetRequestListener.onFinish();
                    }
                });
    }
}
