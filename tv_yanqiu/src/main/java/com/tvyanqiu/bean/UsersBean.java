package com.tvyanqiu.bean;

import java.util.List;

public class UsersBean {

    private List<UserBean> users;

    public List<UserBean> getUsers() {
        return users;
    }

    public void setUsers(List<UserBean> users) {
        this.users = users;
    }

    public static class UserBean {
        /**
         * username : Zhou
         * userpassword : Kevin
         */

        private String username;
        private String userpassword;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserpassword() {
            return userpassword;
        }

        public void setUserpassword(String userpassword) {
            this.userpassword = userpassword;
        }
    }
}
