package com.hackernew.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Story extends BaseModel implements Parcelable {

    @SerializedName("by")
    @Expose
    private String by;
    @SerializedName("descendants")
    @Expose
    private int descendants;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("kids")
    @Expose
    private List<Long> kids = null;
    @SerializedName("score")
    @Expose
    private int score;
    @SerializedName("time")
    @Expose
    private long time;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("url")
    @Expose
    private String url;

    private transient boolean isLoaded = false;
    public Story(){

    }
    protected Story(Parcel in) {
        by = in.readString();
        descendants = in.readInt();
        id = in.readLong();
        score = in.readInt();
        time = in.readLong();
        title = in.readString();
        type = in.readString();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(by);
        dest.writeInt(descendants);
        dest.writeLong(id);
        dest.writeInt(score);
        dest.writeLong(time);
        dest.writeString(title);
        dest.writeString(type);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Story> CREATOR = new Creator<Story>() {
        @Override
        public Story createFromParcel(Parcel in) {
            return new Story(in);
        }

        @Override
        public Story[] newArray(int size) {
            return new Story[size];
        }
    };

    public void setObject(Story another){
        this.by = another.by;
        this.descendants = another.descendants;
        this.id = another.id;
        this.kids = another.kids;
        this.score = another.score;
        this.time = another.time;
        this.title = another.title;
        this.type = another.type;
        this.url = another.url;
    }

    public String getBy() {
        return by;
    }

    public int getDescendants() {
        return descendants;
    }

    public void setDescendants(int descendants) {
        this.descendants = descendants;
    }

    public Long getId() {
        return id;
    }

    public List<Long> getKids() {
        return kids;
    }

    public Integer getScore() {
        return score;
    }

    public Long getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setKids(List<Long> kids) {
        this.kids = kids;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }
}

