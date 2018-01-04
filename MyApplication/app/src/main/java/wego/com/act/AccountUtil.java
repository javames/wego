package wego.com.act;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

import wego.com.ResetApplication;

/**
 * Created by Administrator on 2017/12/29.
 */

public class AccountUtil {

    private static AccountManager accountManager;

    private static final String account_type="wego.com.account_type";

    public static AccountManager getAccountManager(){
        if(accountManager==null){
            accountManager=AccountManager.get(ResetApplication.getContext());
        }
        return accountManager;
    }

    public static void addAccount(UserBean userBean){
        clearAccount();
        AccountManager accountManager = getAccountManager();
        Account account=new Account(userBean.getName(),account_type);
        accountManager.addAccountExplicitly(account,null,null);
    }

    public static void clearAccount(){
        AccountManager accountManager = getAccountManager();
        Account[] accountList = accountManager.getAccountsByType(account_type);
        int length = accountList.length;
        for (int i = 0; i <length ; i++) {
            accountManager.removeAccount(accountList[i],null,null);
        }
    }

    public static void updateAccount(UserBean userBean){
        AccountManager accountManager = getAccountManager();
//        accountManager.getAccountsByType()
    }
}
