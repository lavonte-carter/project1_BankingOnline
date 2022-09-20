package Model;

//model object for the account

public class AccountAtm {
    int accountid;
    int balance;

    int account_userid;

    String username;
    String account_name;

    //String first_name;

    //String last_name;


    public AccountAtm(int accountid, int balance, int account_userid, String account_name) {
        this.accountid = accountid;
        this.balance = balance;
        this.account_userid = account_userid;
        this.account_name = account_name;
    }

// create constructor for new

    public AccountAtm(int accountid, int balance, int account_userid, String username, String account_name) {
        this.accountid = accountid;
        this.balance = balance;
        this.account_userid = account_userid;
        this.username = username;
        this.account_name = account_name;
    }

    public AccountAtm(){

    }


    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getAccount_userid() {
        return account_userid;
    }

    public void setAccount_userid(int account_userid) {
        this.account_userid = account_userid;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }


    @Override
    public String toString() {
        return "AccountAtm{" +
                "accountid=" + accountid +
                ", balance=" + balance +
                ", account_userid=" + account_userid +
                ", account_name='" + account_name + '\'' +
                '}';
    }
}