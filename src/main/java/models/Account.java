package models;

public class Account {
    private String username;
    private int account_id;
    private int login_status;

    public Account(){
        this.username = "N/E";
        account_id = -1;
        login_status = -1;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setAccount_id(int account_id){
        this.account_id = account_id;
    }
    public void setLogin_status(int login_status){
        this.login_status = login_status;
    }
    public String getUsername() {
        return username;
    }
    public int getAccount_id() {
        return account_id;
    }
    public int getLogin_status() {
        return login_status;
    }

}
