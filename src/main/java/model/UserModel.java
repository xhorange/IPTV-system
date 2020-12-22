package model;

import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("user_id")
    private String userId;
    @SerializedName("user_id")
    private String userName;
    @SerializedName("password")
    private String password;

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
