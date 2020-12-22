package model;

import com.google.gson.annotations.SerializedName;

public class TvChannelModel{
    //频道id
    @SerializedName("channel_id")
    private String channelId;
    @SerializedName("channel_name")
    private String channelName;
    //关注，ture表示已关注，false表示为关注
    @SerializedName("is_liked")
    private boolean isLiked;

    public String getChannelId() {
        return channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public boolean isLiked() {
        return isLiked;
    }
}
