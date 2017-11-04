package wego.com.mvp.model;

/**
 * Created by Administrator on 2017/11/2.
 */

public class PersonalAcct {

    private CashAccount cashAccount;

    private class CashAccount{
        private float balance;

        public float getBalance() {
            return balance;
        }

        public void setBalance(float balance) {
            this.balance = balance;
        }
    }
}
