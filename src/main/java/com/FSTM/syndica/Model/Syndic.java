package com.FSTM.syndica.Model;

public class Syndic {
    public String syndicId;
    public String userName;
    public String passWord;

    public Syndic(String syndicId, String retrievedUserName, String passWord) {
    }

    public String getSyndicId() {
        return syndicId;
    }

    public void setSyndicId(String syndicId) {
        this.syndicId = syndicId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
