package com.hyjz.hnovel.bean;

/**
 * Created by jobs on 2016/10/2.
 */

public class UserData {
    private String acount;
    private String passwd;
    private String headurl;

    public void setAcount(String acount) {
        this.acount = acount;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public UserData(String acount, String passwd, String headurl) {
        this.acount = acount;
        this.passwd = passwd;
        this.headurl = headurl;
    }


    public String getAcount() {
        return acount;
    }

    public String getPasswd() {
        return passwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserData)) return false;

        UserData userData = (UserData) o;

        if (!getAcount().equals(userData.getAcount())) return false;
        return getPasswd().equals(userData.getPasswd());

    }

    @Override
    public int hashCode() {
        int result = getAcount().hashCode();
        result = 31 * result + getPasswd().hashCode();
        return result;
    }
}
