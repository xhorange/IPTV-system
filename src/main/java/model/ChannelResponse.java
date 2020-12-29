package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChannelResponse {
    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private Data data;
    @SerializedName("msg")
    private String message;

    public static class Data {
        @SerializedName("user_id")
        private int userId;

        public int getUserId() {
            return userId;
        }

        @SerializedName("program_list")
        private List<TvChannelModel> channelList;

        public List<TvChannelModel> getChannelList() {
            return channelList;
        }
    }

    public int getCode() {
        return code;
    }

    public Data getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
