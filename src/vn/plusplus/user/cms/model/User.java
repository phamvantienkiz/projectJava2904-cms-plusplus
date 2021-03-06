package vn.plusplus.user.cms.model;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String fullName;
    private String email;
    private String phone;
    private int score;
    private String password;

    public User() {
    }

    public User(String userName, String fullName, String email, String phone, int score, String password) {
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.score = score;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "userName=" + userName  +
                "###fullName=" + fullName  +
                "###email=" + email  +
                "###phone=" + phone +
                "###score=" + score +
                "###password=" + password;
    }
}
