package wego.com.http.response;

import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;
import wego.com.encrypt.DES;

/**
 * Created by Administrator on 2017/11/2.
 */

public class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    @Override
    public RequestBody convert(T value) throws IOException {
        Log.i("test"," RequestBody: "+value.toString());
        String postBody=null;
        try {
//            postBody = DES.encryptDesNoIps(value.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RequestBody.create(MEDIA_TYPE, value.toString());
    }
}
