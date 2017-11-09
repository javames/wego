package wego.com.mvp.model;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/10/27.
 */

public interface TxtPriceModel extends BaseModel{
    void getCurrentPrice(String topicId);
    void getCaseRecord(JSONObject obj);
}
