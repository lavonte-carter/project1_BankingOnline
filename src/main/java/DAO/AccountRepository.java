package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.AccountAtm;
import Model.AccountUser;
import Util.utilConnection;

public class AccountRepository {
    //Connection conn;
    Connection conn;

    public AccountRepository() {
        conn = utilConnection.getConnection();
    }

    public List<AccountAtm> getAllAccounts() {

        List<AccountAtm> allAccounts = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from AccountAtm");
            while (rs.next()) {
                AccountAtm loadingAccount = new AccountAtm(rs.getInt("accountid"),
                        rs.getInt("balance"), rs.getInt("account_userid"), rs.getString("account_name"));
                allAccounts.add(loadingAccount);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allAccounts;
    }

    public List<AccountUser> getAllUsers() {

        List<AccountUser> allUsers = new ArrayList<>();
        try {
            //Statement statement = conn.createStatement();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from AccountUser");
            while (rs.next()) {
                AccountUser loadingUser = new AccountUser(rs.getInt("user_id"), (rs.getString("username")),
                        rs.getString("first_name"), rs.getString("last_name"), rs.getString("user_password"));
                allUsers.add(loadingUser);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allUsers;
    }

    public List<AccountAtm> addAccount(AccountAtm a) {
        try {
            PreparedStatement statement = conn.prepareStatement("insert into AccountAtm " +
                    "(accountid, balance, account_userid, account_name)" +
                    "values(?,?,?,?)"); //String first_name, String last_name, int accountid, int balance, String account_name, String username

            statement.setInt(1, a.getAccount_userid());
            statement.setInt(2, a.getBalance());
            statement.setInt(3, a.getAccount_userid());
            statement.setString(4, a.getAccount_name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<AccountAtm> addUser(AccountUser u) {
        try {
            PreparedStatement statement = conn.prepareStatement("insert into AccountUser" + "(user_id, username, first_name, last_name, user_password)" +
                    "values(?,?,?,?,?)"); //int user_id, String username, String first_name, String last_name, String password
            statement.setInt(1, u.getUser_id());
            statement.setString(2, u.getUsername());
            statement.setString(3, u.getFirst_name());
            statement.setString(4, u.getLast_name());//last here
            statement.setString(5, u.getUser_password());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<AccountAtm> removeAccount(int account_userid) {
        try {
            PreparedStatement statement = conn.prepareStatement("delete from AccountAtm where account_userid = ? ");
            statement.setInt(1, account_userid);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<AccountAtm> removeUser(int user_id) {
        try {
            PreparedStatement statement = conn.prepareStatement("delete from AccountUser where user_id = ? ");
            statement.setInt(1, user_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<AccountUser> getAccountByUserID(int user_id) {
        List<AccountUser> username_accounts = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement("Select * from AccountUser where userid = ?");
            statement.setInt(1, user_id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                AccountUser loadingAccount = new AccountUser(rs.getInt("user_id"), rs.getString("username"),
                        rs.getString("first_name"), rs.getString("last_name"), rs.getString("password"));
                username_accounts.add(loadingAccount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (username_accounts.size() == 0) {
            return username_accounts;
        } else {
            return username_accounts;
        }

    }

    public List<AccountUser> updatePassword(String username, String user_password) {
        try {
            PreparedStatement statement = conn.prepareStatement("update AccountUser set user_password = ? where username = ? ");
            statement.setString(1, user_password);
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<AccountAtm> addMoney(int account_userid, int balance) {
        try {
            PreparedStatement statement = conn.prepareStatement("update AccountAtm set balance = balance + ? where account_userid = ?");
            statement.setInt(1, balance); // code was backwards until i placed the balance first.
            statement.setInt(2, account_userid);
            //statement.setString(3, account_name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public void withdrawMoney(int account_userid, int withdrawRequest) {
        int currentBalance = getBalanceByUserID(account_userid);

        if (currentBalance >= withdrawRequest){
            try {
                PreparedStatement statement = conn.prepareStatement("update AccountAtm set balance = balance - ? where account_userid = ? ");
                statement.setInt(1, withdrawRequest);
                statement.setInt(2, account_userid);
                statement.executeUpdate();
                System.out.println("Withdrawal Successful!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else System.out.println("User Has Insufficient Funds");

    }

    public int getBalanceByUserID(int user_id) {
        try {
            PreparedStatement statement = conn.prepareStatement("Select balance from AccountATM where account_userid =  ?"); //send query
            statement.setInt(1, user_id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;


    }
    //test
    public AccountUser getUserLoginbyID(int user_id) {
        try {
            PreparedStatement statement = conn.prepareStatement("select * from AccountUser where user_id = ?"); //send query
            statement.setInt(1, user_id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return new AccountUser(rs.getString("username"), (rs.getString("user_password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public AccountUser getUserByName(String username) {
        try {
            System.out.println("hi");
            PreparedStatement statement = conn.prepareStatement("select * from AccountUser where username = ?"); //send query
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return new AccountUser(rs.getInt("user_id"), rs.getString("username"),
                        rs.getString("first_name"), rs.getString("last_name"),rs.getString("user_password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("returning null");
        return null;
    }
}