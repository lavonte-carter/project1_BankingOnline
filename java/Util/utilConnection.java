package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class utilConnection {

    private static Connection conn;
    public static Connection getConnection(){
        if(conn == null){
            try{
                String url = "jdbc:postgresql://localhost:5432/postgres";
                String username = "postgres";
                String password = "P@SSWORD123";
                conn = DriverManager.getConnection(url, username, password);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return conn;
    }
}
