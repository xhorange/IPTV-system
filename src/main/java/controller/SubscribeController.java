package controller;

import model.TvChannelModel;
import model.TvShowModel;
import utils.TvUtil;
import view.SubView;

import java.util.Iterator;
import java.util.List;

public class SubscribeController {
    private static SubscribeController subscribeController;
    private SubView subView;
    private TvChannelModel tvChannelInfo;
    private List<TvShowModel> tvShows;

    public static SubscribeController getInstance() {
        synchronized (UserController.class) {
            if (subscribeController == null) {
                subscribeController = new SubscribeController();
            }
            return subscribeController;
        }
    }

    public void getTvInfo() {
        tvChannelInfo = TvUtil.getInstance().getChannelInfo();
    }

    public SubView goToSubShows() {
        if (tvChannelInfo == null) {
            getTvInfo();
        }
        tvShows = tvChannelInfo.getList();
        String[][] tvShowInfo = new String[tvShows.size()][4];
        String[] title = {"订阅","开始时间", "节目名", "订阅情况"};
        Iterator<TvShowModel> iterator = tvShows.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            TvShowModel tvShowModel = iterator.next();
            tvShowInfo[count][1] = tvShowModel.getShowTime();
            tvShowInfo[count][2] = tvShowModel.getTvShowName();
            tvShowInfo[count][3] = tvShowModel.isSub()?"已订阅":"未订阅";
            count++;
        }
        subView = new SubView(tvShowInfo, title);
        return subView;
    }


}

