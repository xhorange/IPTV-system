package controller;

import model.ChannelResponse;
import model.UserModel;
import utils.HttpUrl;
import utils.HttpUtil;

public class LoginController {
    private static LoginController userController;
    private UserModel userModel;
    public static final int LOGIN_ERROR = 505;
    public static final int USER_OR_PASSWORD_NULL = 266;
    public static final int LOGIN_SUCCESS = 404;

    public static LoginController getInstance() {
        synchronized (LoginController.class) {
            if (userController == null) {
                userController = new LoginController();
            }
            return userController;
        }
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUser(UserModel user) {
        userModel = user;
    }

    public void clearUser() {
        userModel = null;
    }

    public int login(String userName, String password) {
        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            return USER_OR_PASSWORD_NULL;
        }
        StringBuffer loginUrl = new StringBuffer();
        loginUrl.append(HttpUrl.TV_CHANNEL_INFO)
                .append("?user_name=").append(userName)
                .append("&user_password=").append(password);
        ChannelResponse channelResponse = HttpUtil.getInstance().getChannelInfo(loginUrl.toString());
        if (channelResponse != null && channelResponse.getCode() == 200) {
            ChannelResponse.Data data = channelResponse.getData();
            if (data == null) {
                return LOGIN_ERROR;
            }
            if (userModel == null) {
                userModel = new UserModel(data.getUserId(), userName, password);
            } else {
                //初始化用户
                userModel.setUserName(userName);
                userModel.setPassword(password);
                userModel.setUserId(data.getUserId());
            }
            //初始化频道列表
            if (data.getChannelList() != null) {
                SubscribeController.getInstance().setAllChannels(data.getChannelList());
            }
            return LOGIN_SUCCESS;
        }
        return LOGIN_ERROR;
    }
}
