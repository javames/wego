package wego.com.http.response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import wego.com.encrypt.DES;

/**
 * Created by Administrator on 2017/11/2.
 */

public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    public JsonResponseBodyConverter() {
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        JSONObject jsonObj;
        try {
            String result = DES.decrypt3DesNOIps(value.string(),null);
            jsonObj = new JSONObject(result);
            return (T) jsonObj;
        } catch(JSONException e) {
            return null;
        }
    }
}
