package wego.com.http.response;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/10/27.
 */

public class HttpResult {

    public HttpResult(String jsonString) {
        initJson(jsonString);
    }

    private void initJson(String jsonString) {
        try {
            JSONObject json = new JSONObject(jsonString);
            code = json.getString("code");

            if (json.has("msg"))
                msg = json.getString("msg");

            if (json.has("data")) {
                data = json.get("data");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String code;
    private String msg;
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "code: "+code+" data: "+data+" msg: "+msg;
    }
}
