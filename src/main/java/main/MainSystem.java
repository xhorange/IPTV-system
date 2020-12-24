package main;

import controller.SubscribeController;
import view.LoginView;
import view.SubscribeView;

public class MainSystem {
    private LoginView loginView;
    private SubscribeView subscribeView;

    public void init() {
        initLoginView();
    }

    public void initLoginView() {
        loginView = new LoginView(new Callback() {
            @Override
            public void onEnd() {
                subscribeView = new SubscribeView(new Callback());
                SubscribeController.getInstance().getTvInfo();
            }
        });
    }
}
