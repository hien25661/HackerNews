package com.hackernew.model;


/**
 * Created by Hien on 5/29/2017.
 */

public class Comment extends BaseModel{
    private String by;
    private long id;
    private long parent;
    private long time;
    private String text;
    private String type;

    public String getBy() {
        return by;
    }

    public long getId() {
        return id;
    }

    public long getParent() {
        return parent;
    }

    public long getTime() {
        return time;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }
}
