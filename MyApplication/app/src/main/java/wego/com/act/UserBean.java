package wego.com.act;

import lombok.Data;

/**
 * Created by Administrator on 2017/12/29.
 */

@Data
public class UserBean {

    private String name;
    private String access_token;
    private String token;//用户唯一token,不会改变
}
