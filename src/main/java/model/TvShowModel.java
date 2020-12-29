package model;

import com.google.gson.annotations.SerializedName;

public class TvShowModel {
    @SerializedName("channel_name")
    private String channelName;
    @SerializedName("id")
    private int tvShowId;
    @SerializedName("channel_id")
    private int channelId;
    @SerializedName("name")
    private String tvShowName;
    @SerializedName("show_time")
    private String showTime;
    @SerializedName("url")
    private String url;
    @SerializedName("is_sub")
    private boolean isSub;

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public void setSub(boolean sub) {
        isSub = sub;
    }

    public String getTvShowName() {
        return tvShowName;
    }

    public String getShowTime() {
        return showTime;
    }

    public String getUrl() {
        return url;
    }

    public String getChannelName() {
        return channelName;
    }

    public int getTvShowId() {
        return tvShowId;
    }

    public int getChannelId() {
        return channelId;
    }

    public boolean isSub() {
        return isSub;
    }
}
