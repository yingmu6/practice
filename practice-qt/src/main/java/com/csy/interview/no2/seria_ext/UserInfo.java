package com.csy.interview.no2.seria_ext;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chensy
 * @date 2023/7/13
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date loggingDate = new Date();

    private String uid;

    private transient String pwd;

    public UserInfo (String user, String password) {
        this.uid = user;
        this.pwd = password;
    }

    public String toString() {
        String password = null;
        if (pwd == null) {
            password = "NOT SET";
        } else {
            password = pwd;
        }
        return "用户信息: \n" + "用户名: " + uid + "\n 登录时间:" + loggingDate.toString() + "\n 密码: " + password;
    }
}
