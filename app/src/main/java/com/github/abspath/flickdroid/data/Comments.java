package com.github.abspath.flickdroid.data;

import java.util.List;

/**
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/4/25
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class Comments {

    public Items items;
    public String stat;

    public static class Items {
        public int page;
        public int pages;
        public int perpage;
        public int total;
        public List<Item> item;

        public static class Item {
            public String type;
            public String id;
            public String owner;
            public String ownername;
            public String realname;
            public String iconserver;
            public int iconfarm;
            public String secret;
            public String server;
            public int farm;
            public Title title;
            public String media;
            public int comments;
            public int notes;
            public int views;
            public int faves;
            public Activity activity;

            public static class Title {
                public String _content;
            }

            public static class Activity {
                public List<Event> event;

                public static class Event {
                    public String type;
                    public String user;
                    public String username;
                    public String iconserver;
                    public int iconfarm;
                    public String dateadded;
                    public Object is_muted;
                    public String realname;
                    public String commentid;
                    public String _content;
                }
            }
        }
    }
}
