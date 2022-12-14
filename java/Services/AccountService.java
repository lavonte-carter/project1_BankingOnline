package Services;

import DAO.AccountRepository;
import Model.AccountUser;
import Model.AccountAtm;
import org.apache.log4j.Logger;

import java.util.List;

public class AccountService {


    static AccountRepository ar;

    public AccountService() {
        ar = new AccountRepository();
    }

    public List<AccountAtm> getAllAccounts() {
        return ar.getAllAccounts();
    }



    public List<AccountUser> getAllUsers() {
        return ar.getAllUsers();
    }

    public Object removeAccount(int account_userid) {
        ar.removeAccount(account_userid);
        return null;
    }

    public Object removeUser(int user_id) {
        ar.removeUser(user_id);
        return null;
    }


    public void updatePassword(String username, String user_password) {
        Logger logger2 = Logger.getLogger(AccountService.class);
        logger2.info("User Password Updated!");
        ar.updatePassword(username, user_password);
    }


    public void addMoney(int account_userid, int balance) {
        Logger logger3 = Logger.getLogger(AccountService.class);
        logger3.info("Transaction Successful!");
        ar.addMoney(account_userid, balance);
    }


    public List<AccountUser> getAccountByUserID(int user_id) {

        return (List<AccountUser>) ar.getAccountByUserID(user_id);
    }


    public void addAccount(int accountid, int balance, int account_userid , String username, String account_name) {
        AccountAtm newAccount = new AccountAtm(accountid, balance, account_userid, username, account_name);
        ar.addAccount(newAccount);
    }
//testing
    public AccountUser getUserLoginbyID(int user_id) {
        return ar.getUserLoginbyID(user_id);
}

    public void addUser(int user_id, String username, String first_name, String last_name, String user_password) {
        AccountUser newUser = new AccountUser(user_id, username, first_name, last_name, user_password);
        ar.addUser(newUser);
    }

    public void withdrawMoney(int userid, int withdrawRequest){
        //AccountAtm newBalance = new AccountAtm(userid, withdrawRequest);
        ar.withdrawMoney(userid, withdrawRequest);
    }

    public int getBalanceByUserID(int user_id) {
        return ar.getBalanceByUserID(user_id);
    }

    public AccountUser getUserByName(String username) {
        System.out.println("hello");
        return ar.getUserByName(username);
    }
}






   /* Connection conn;

    public AccountService(){ conn = utilConnection.getConnection(); }

    public static AccountRepository repository = new AccountRepository();
    public static void addUser(String userName){
        repository.addUser(userName);
    }

    public static void getBalance(int i) {
        repository.getBalance(i);
    }


    /*public List<AccountAtm> getAccounts(){
        return repository.getAccounts();
    }



    public static void removeUser(String userName) {
        repository.removeUser(userName);
    }
}

    */

