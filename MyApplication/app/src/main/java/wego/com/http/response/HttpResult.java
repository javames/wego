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

            if (json.has("message"))
                message = json.getString("message");

            if (json.has("data")) {
                data = json.get("data");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String code;
    private String message;
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
