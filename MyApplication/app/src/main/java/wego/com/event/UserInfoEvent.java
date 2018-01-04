package wego.com.event;

/**
 * Created by Administrator on 2017/12/28.
 */

public class UserInfoEvent {

    private String name;
    private String headUrl;


    public UserInfoEvent(String name, String headUrl) {
        this.name = name;
        this.headUrl = headUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
}
