package com.mding.chatfeng.base_common.request.body;

public class LoginInBody{
    private String sno;
    private String password;

    public LoginInBody(String sno, String password) {
        this.sno = sno;
        this.password = password;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
