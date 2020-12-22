package model;

import com.google.gson.annotations.SerializedName;

public class TvShowModel {
    @SerializedName("channel_id")
    private String channelId;
    @SerializedName("tv_show_id")
    private String tvShowId;
    @SerializedName("tv_show_name")
    private String tvShowName;
    @SerializedName("show_start_time")
    private int showStartTime;
    @SerializedName("show_end_time")
    private int showEndTime;
    public String getChannelId() {
        return channelId;
    }

    public String getTvShowId() {
        return tvShowId;
    }

    public String getTvShowName() {
        return tvShowName;
    }

    public int getShowStartTime() {
        return showStartTime;
    }

    public int getShowEndTime() {
        return showEndTime;
    }
}
