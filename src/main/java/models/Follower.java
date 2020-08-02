package models;

public class Follower {
    private int accountID = -1;
    private int follower_id = -1;

    public void setFollower_id(int follower_id) {
        this.follower_id = follower_id;
    }
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
    public int getAccountID() {
        return accountID;
    }
    public int getFollower_id() {
        return follower_id;
    }




}
