package wego.com;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.CompositeDisposable;
import wego.com.http.common.interz.OnNetRequestListener;
import wego.com.http.response.HttpResult;
import wego.com.interz.Interz;
import wego.com.mvp.model.Price;
import wego.com.mvp.presenter.TxtPricePresenter;
import wego.com.mvp.presenter.TxtPricePresenterImpl;
import wego.com.mvp.view.TxtPriceView;
import wego.com.util.HttpResultParse;
import wego.com.util.JSONUtils;

/**
 * Created by Administrator on 2017/11/2.
 */

public class TestActivity extends AppCompatActivity implements TxtPriceView {


    private Button mBtn;
    private TxtPricePresenter txtPricePresenter;
    @Override
    public void showData(Object obj) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        txtPricePresenter=new TxtPricePresenterImpl(this);
        mBtn=findViewById(R.id.btn);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtPricePresenter.getTxtData("493", new OnNetRequestListener<JSONObject>() {
                    @Override
                    public void onStart() {
                        Log.i("test","onStart()");
                    }

                    @Override
                    public void onFinish() {
                        Log.i("test","onFinish()");

                    }

                    @Override
                    public void onSuccess(JSONObject data) {
                        HttpResultParse.parse(data.toString(), Price.class, new Interz.ParseResponse() {
                            @Override
                            public void error(String error) {

                            }

                            @Override
                            public void success(Object obj) {
                                Price price= (Price) obj;
                                Log.i("test","currentPrice= "+price.getCurrentPrice());
                            }
                        });
                    }
                    @Override
                    public void onFailure(Throwable t) {
                        Log.i("test","onFailure()");
                    }
                },0);
            }
        });
    }

  public static Observable<String> sampleObservable() {
        return Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                // Do some long running operation
                SystemClock.sleep(2000);
                return Observable.just("one", "two", "three", "four", "five");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        txtPricePresenter.unDisposable();//解除绑定
    }
}
