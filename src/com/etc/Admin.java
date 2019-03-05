package com.etc;

public class Admin {
    private String name;
    private String pw;

    public Admin() {
         super();
    }
    public Admin(String name,String pw) {
        this.name=name;
        this.pw=pw;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }


}
