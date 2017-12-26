package wego.com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Chen on 2017/6/21.
 */

public class PatternUtil {

    /**
     * 判断用户名和密码 是否是6-20位字母或数字或下划线
     *
     * @param needCheck 用户名或用户密码
     * @return
     */
    public static boolean isValidLogin(String needCheck) {
        Pattern p = Pattern.compile("[A-Za-z_0-9]{6,20}");
        Matcher m = p.matcher(needCheck);
        return m.matches();
    }


    /**
     * 判断是否是手机号，仅用11位，剩余由服务器判断
     */
    public static boolean isPhoneNumber(String uid) {
        //先判断是否是纯数字
        if (isDigitalValue(uid)) {
            //然后判断长度
            if (uid.length() == 11) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    /**
     * 判断用户名是否是纯数字
     */
    public static boolean isDigitalValue(String value) {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(value);
        return m.matches();
    }

    //判断是否是邮箱
    public static boolean isEmail(String needCheck) {
        String str = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(needCheck);
        return m.matches();
    }

    //判断是否是@用户
    public static Matcher isMention(String content) {
        //头为@ 尾为空格 中间是除了@和空格
        String regex = "\\@([^\\@\\s]*)\\s";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        return matcher;
    }

}
