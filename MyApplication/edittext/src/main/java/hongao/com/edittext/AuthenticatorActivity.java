package hongao.com.edittext;

import android.accounts.AccountAuthenticatorActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/12/28.
 */

public class AuthenticatorActivity extends AccountAuthenticatorActivity {

    public static final String ARG_ACCOUNT_TYPE="phone";
    public  static final String ARG_AUTH_TYPE="access_token";
    public static final String ARG_IS_ADDING_NEW_ACCOUNT="is_adding_new_acct";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticator);
    }


}
