package Model;

public class AccountUser {
    int user_id;
    String username;
    String first_name;
    String last_name;
    String user_password;



    public AccountUser(int user_id, String username, String first_name, String last_name, String user_password) {
        this.user_id = user_id;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.user_password = user_password;
    }

    public AccountUser() {

    }
    //testing
    public AccountUser(String username, String user_password) {

    }


    public int getUser_id() {
        return user_id;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }



    @Override
    public String toString() {
        return "AccountUser{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", user_password='" + user_password + '\'' +
                '}';
    }
}