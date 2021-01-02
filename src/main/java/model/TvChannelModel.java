package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvChannelModel {
    //频道id
    @SerializedName("channel_id")
    private String channelId;
    @SerializedName("channel_name")
    private String channelName;
    @SerializedName("is_ive")
    private String liveShow;
    //关注，ture表示已关注，false表示为关注
    @SerializedName("status")
    private boolean isSub;
    private List<TvShowModel> tvShows;

    public String getChannelId() {
        return channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public boolean isSub() {
        return isSub;
    }

    public void setSub(boolean sub) {
        isSub = sub;
    }

    public String getLiveShow() {
        return liveShow;
    }

    public List<TvShowModel> getTvShows() {
        return tvShows;
    }

    public void setTvShows(List<TvShowModel> tvShows) {
        this.tvShows = tvShows;
    }
}
