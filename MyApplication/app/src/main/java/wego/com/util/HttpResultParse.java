package wego.com.util;

import wego.com.http.response.HttpResult;
import wego.com.interz.Interz;

/**
 * Created by Administrator on 2017/11/2.
 */

public class HttpResultParse {

    private Interz.ParseResponse response;
    public static void parse(String jsonString, Class claz,Interz.ParseResponse response){
        HttpResult result=new HttpResult(jsonString);
        if(Constants.RESPONSE_SUCCESS.equals(result.getCode())){
            String str = result.getData().toString();
            Object toObj = JSONUtils.JSONToObj(str, claz);
            response.success(toObj);
        }else{
            response.error(result.getMsg());
        }

    }
}
