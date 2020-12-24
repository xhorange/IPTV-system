package model;

import com.google.gson.annotations.SerializedName;

public class TvShowModel {
    @SerializedName("channel_id")
    private String channelId;
    @SerializedName("tv_show_id")
    private String tvShowId;
    @SerializedName("title")
    private String tvShowName;
    @SerializedName("showTime")
    private String showTime;
    @SerializedName("columnBackvideourl")
    private String columnBackVideoUrl;
    @SerializedName("is_sub")
    private boolean isSub;


    public String getTvShowName() {
        return tvShowName;
    }

    public String getShowTime() {
        return showTime;
    }

    public String getColumnBackVideoUrl() {
        return columnBackVideoUrl;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getTvShowId() {
        return tvShowId;
    }

    public boolean isSub() {
        return isSub;
    }
}
