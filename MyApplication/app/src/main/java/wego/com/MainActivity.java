package wego.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import wego.com.http.RetrofitService;
import wego.com.http.common.interz.OnNetRequestListener;
import wego.com.http.response.HttpResult;
import wego.com.mvp.presenter.TxtPricePresenter;
import wego.com.mvp.presenter.TxtPricePresenterImpl;
import wego.com.mvp.view.TxtPriceView;

public class MainActivity extends AppCompatActivity implements TxtPriceView{

    private Button mBtn;
    private TxtPricePresenter txtPricePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPricePresenter=new TxtPricePresenterImpl(this);
        mBtn=(Button) findViewById(R.id.click_btn);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtPricePresenter.getTxtData("493", new OnNetRequestListener() {
                    @Override
                    public void onStart() {
                        Log.i("test","onStart()");
                    }

                    @Override
                    public void onFinish() {
                        Log.i("test","onFinish()");

                    }

                    @Override
                    public void onSuccess(Object data) {
                        HttpResult httpResult = (HttpResult) data;
                        Log.i("test","aaaa= "+httpResult.getCode()+"///"+"bbb= "+httpResult.getMessage()+"///"+"ccc= "+httpResult.getData());
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.i("test","onFailure()");
                    }
                },0);
            }
        });
    }

    @Override
    public void showData(Object obj) {

    }
}
