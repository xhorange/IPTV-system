package main;

import controller.SubscribeController;
import view.LoginView;
import view.MenuView;
import view.SubView;

public class MainSystem {
    private LoginView loginView;
    private MenuView menuView;

    public MainSystem() {
        initLoginView();
    }

    public void initLoginView() {
        loginView = new LoginView(new Callback() {
            @Override
            public void onEnd() {
                menuView = new MenuView(new Callback());
                SubscribeController.getInstance().getTvInfo();
            }
        });
    }
}
