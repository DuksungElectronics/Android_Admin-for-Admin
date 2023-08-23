package kr.ac.duksung.websocketexer;


import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String userID;
    private String userNumber;
    private String timestamp;

    public User() {
        // 기본 생성자는 Firebase에서 데이터를 가져올 때 사용됨.
    }

    public User(String userNumber) {
        this.userNumber = userNumber;
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", userNumber='" + userNumber + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
