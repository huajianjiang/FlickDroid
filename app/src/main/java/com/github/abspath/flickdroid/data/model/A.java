package com.github.abspath.flickdroid.data.model;

/**
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/4/25
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class A {

    public User user;
    public String stat;

    public static class User {
        public String id;
        public Username username;

        public static class Username {
            public String _content;
        }
    }
}
