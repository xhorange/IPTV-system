package model;

import com.google.gson.annotations.SerializedName;

public class TvShowModel {
    @SerializedName("channel_id")
    private String channelName;
    @SerializedName("tv_show_id")
    private int tvShowId;
    @SerializedName("tv_channel_id")
    private int channelId;
    @SerializedName("title")
    private String tvShowName;
    @SerializedName("showTime")
    private String showTime;
    @SerializedName("columnBackvideourl")
    private String columnBackVideoUrl;
    @SerializedName("is_sub")
    private boolean isSub = true;

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

    public String getColumnBackVideoUrl() {
        return columnBackVideoUrl;
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
