package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowResponse {
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

    public Data getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
