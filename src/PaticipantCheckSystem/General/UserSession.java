package PaticipantCheckSystem.General;

import pojo.Account;

public class UserSession {
    private static Account userAccount;


    public static Account getInstance() {
        return userAccount;
    }

    public static void clearUserAccount() {
        userAccount = null;
    }

    public static void setUserAccount(Account account) {
        userAccount = new Account();
        userAccount.setId(account.getId());
        userAccount.setUsername(account.getUsername());
        userAccount.setPassword(account.getPassword());
    }
    public static Integer getUserID(){
        if(userAccount == null)
            return null;
        return userAccount.getId();
    }
}
