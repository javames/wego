package wego.com.httpApi;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import wego.com.http.response.HttpResult;
import wego.com.mvp.model.Price;

/**
 * Created by Administrator on 2017/10/27.
 */

public interface DuomiApi {


    //dynamicId   315
    @GET("txn/getTxnCurrentPrice")
    Observable<HttpResult> getTxtCurrentPrice(@Query("topicId") String topicId);
}
