package wego.com.httpApi;

import org.json.JSONObject;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import wego.com.http.response.HttpResult;
import wego.com.mvp.model.Price;

/**
 * Created by Administrator on 2017/10/27.
 */

public interface DuomiApi {


    //dynamicId   315
    @GET("txn/getTxnCurrentPrice")
    Observable<JSONObject> getTxtCurrentPrice(@Query("topicId") String topicId);

    /**
     * 账户余额明细
     */
    @POST("txnQuery/queryCashList")
    Observable<JSONObject> getActCaseRecord(@Body JSONObject obj);
}
