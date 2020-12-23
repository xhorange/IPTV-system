package model;

import com.google.gson.annotations.SerializedName;

public class TvShowModel {
    @SerializedName("channel_id")
    private String channelId;
    @SerializedName("tv_show_id")
    private String tvShowId;
    @SerializedName("title")
    private String tvShowName;
    @SerializedName("startTime")
    private int showStartTime;
    @SerializedName("endTime")
    private int showEndTime;
    @SerializedName("showTime")
    private String showTime;
    @SerializedName("length")
    private int lastTime;
    @SerializedName("columnBackvideourl")
    private String columnBackVideoUrl;


    public String getTvShowName() {
        return tvShowName;
    }

    public int getShowStartTime() {
        return showStartTime;
    }

    public int getShowEndTime() {
        return showEndTime;
    }

    public String getShowTime() {
        return showTime;
    }

    public int getLastTime() {
        return lastTime;
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
}
