package wego.com.http.response;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import wego.com.util.DES;

/**
 * Created by Administrator on 2017/10/28.
 */

public class JsonResponseBodyConverter <T> implements Converter<ResponseBody, T> {

    private final Gson mGson;//gson对象
    private final TypeAdapter<T> adapter;

    /**
     * 构造器
     */
    public JsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.mGson = gson;
        this.adapter = adapter;
    }

    /**
     * 转换
     *
     * @param responseBody
     * @return
     * @throws IOException
     */
    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        String response = responseBody.string();
        Log.i("xiaozhang", "response：" + response);
        String result = DES.decrypt3DesNOIps(response,null);
        Log.i("xiaozhang", "解密的服务器数据：" + result);
        return (T)mGson.fromJson(result, HttpResult.class);

    }
}
