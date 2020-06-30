package com.hr.domain;

public class QueryVo {

    //ognl表达式, 直接用(对象.属性名)的方式取值
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
