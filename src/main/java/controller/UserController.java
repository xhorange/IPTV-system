package controller;

import model.UserModel;

public class UserController {
    private static UserController userController;
    private UserModel userModel;
    public static final int LOGIN_ERROR = 505;
    public static final int USER_OR_PASSWORD_NULL = 266;
    public static final int LOGIN_SUCCESS = 404;

    public static UserController getInstance() {
        synchronized (UserController.class) {
            if (userController == null) {
                userController = new UserController();
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
        // TODO: 2020/12/23 增加网络连接功能
        if (userName.equals("1") && password.equals("1")) {
            if (userModel == null) {
                userModel = new UserModel("", userName, password);
            } else {
                userModel.setUserName(userName);
                userModel.setPassword(password);
                userModel.setUserId("");
            }

            return LOGIN_SUCCESS;
        }
        return LOGIN_ERROR;
    }
}
