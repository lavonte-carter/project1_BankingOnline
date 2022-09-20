package Util;

import Model.AccountAtm;
import Model.AccountUser;
import Services.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class mainAtmAPI {
    public static void main(String[] args) throws SQLException {
        AccountService as = new AccountService();
        Javalin app = Javalin.create(JavalinConfig::enableCorsForAllOrigins).start(9000);

        //Searches for all accounts
        app.get("/allaccounts", ctx -> {ctx.json(as.getAllAccounts());
        });
        //Searches for all users
        app.get("/allUsers", ctx -> {ctx.json(as.getAllUsers());
        });

        //gets balance by id
        app.get("/getBalanceByUserID/{user_id}", ctx ->
        {
            ctx.json(as.getBalanceByUserID(Integer.parseInt(ctx.pathParam("user_id"))));
        });

        //get user login by id

        app.get("/getUserLoginByUserID/{user_id}", ctx ->
        {
            ctx.json(as.getUserLoginbyID(Integer.parseInt(ctx.pathParam("user_id"))));
        });
        //get user by name
        app.get("/getUserByName/{username}", ctx ->
        {
            ctx.json(as.getUserByName(ctx.pathParam("username")));

        });

        //3 this does work with postman, but only after you 'addUser'
        app.post("addAccount", ctx -> {
            ObjectMapper mapper = new ObjectMapper();
            AccountAtm requestAccount = mapper.readValue(ctx.body(), AccountAtm.class);
            as.addAccount(requestAccount.getAccountid(), requestAccount.getBalance(), requestAccount.getAccount_userid(), requestAccount.getAccount_name(),
                    requestAccount.getAccount_name());
        });

        //adds user
        app.post("addUser", ctx -> {
            ObjectMapper mapper2 = new ObjectMapper();// jackson objectMapper
            AccountUser requestUser = mapper2.readValue(ctx.body(), AccountUser.class);
            //int user_id, String username, String first_name, String last_name, String user_password
            as.addUser(requestUser.getUser_id(), requestUser.getUsername(), requestUser.getFirst_name(), requestUser.getLast_name(), requestUser.getUser_password());
            Logger logger = Logger.getLogger(mainAtmAPI.class);
            logger.info("User Added!");
        });

        //deletes account
        app.delete("/deleteAccount/{account_userid}", ctx ->
        {
            Logger logger3 = Logger.getLogger(AccountService.class);
            logger3.info("Account Was Removed");
            as.removeAccount(Integer.parseInt(ctx.pathParam("account_userid")));
            ctx.result("Account Deleted");
        });

        //delete user
        app.delete("/deleteUser/{user_id}", ctx ->
        {
            Logger logger3 = Logger.getLogger(AccountService.class);
            logger3.info("User Was Removed");
            as.removeUser(Integer.parseInt(ctx.pathParam("user_id")));
            ctx.result("User Deleted");
        });

        //adds money
        app.put("addMoney", ctx -> {
            Logger logger3 = Logger.getLogger(AccountService.class);
            logger3.info("Transaction Successful!");

            ObjectMapper mapper = new ObjectMapper();// jackson objectMapper
            AccountAtm requestAccount = mapper.readValue(ctx.body(), AccountAtm.class);
            as.addMoney(requestAccount.getAccount_userid(), requestAccount.getBalance());
        });

        //withdraw money
        app.put("withdrawMoney", ctx -> {
            Logger logger3 = Logger.getLogger(AccountService.class);
            logger3.info("Transaction Successful!");

            ObjectMapper mapper = new ObjectMapper();
            AccountAtm requestAccount = mapper.readValue(ctx.body(), AccountAtm.class);
            as.withdrawMoney(requestAccount.getAccount_userid(), requestAccount.getBalance());
        });


        //update password
        app.put("updateUser_Password", ctx -> {
            Logger logger3 = Logger.getLogger(AccountService.class);
            logger3.info("Password Updated!");

            ObjectMapper mapper = new ObjectMapper();
            AccountUser requestUser = mapper.readValue(ctx.body(), AccountUser.class);
            as.updatePassword(requestUser.getUsername(), requestUser.getUser_password());
        });

    }
}