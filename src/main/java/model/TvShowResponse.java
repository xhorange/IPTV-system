package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowResponse {
    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private Data data;
    @SerializedName("msg")
    private String message;
    public static class Data{
        @SerializedName("program_list")
        private List<TvShowModel> tvShowList;

        public List<TvShowModel> getTvShowList() {
            return tvShowList;
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
