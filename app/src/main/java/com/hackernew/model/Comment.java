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

    private boolean isLoaded = false;

    public void setObject(Comment another){
        this.by = another.by;
        this.id = another.id;
        this.parent = another.parent;
        this.time = another.time;
        this.type = another.type;
        this.text = another.text;
    }

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

    public void setBy(String by) {
        this.by = by;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setParent(long parent) {
        this.parent = parent;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }
}
