package controller;

import model.TvChannelModel;
import model.TvShowModel;
import utils.TvUtil;
import view.SubTvShowView;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class SubscribeController {
    private static SubscribeController subscribeController;
    private SubTvShowView subTvShowView;
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

    public SubTvShowView goToSubShows() {
        tvChannelInfo = TvUtil.getInstance().getChannelInfo();
        tvShows = tvChannelInfo.getList();
        String[][] tvShowInfo = new String[tvShows.size()][2];
        String[] title = {"节目名", "开始时间"};
        Iterator<TvShowModel> iterator = tvShows.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            TvShowModel tvShowModel = iterator.next();
            tvShowInfo[count][0] = tvShowModel.getTvShowName();
            tvShowInfo[count][1] = tvShowModel.getShowTime();
            count++;
        }
        subTvShowView = new SubTvShowView(tvShowInfo, title);
        return subTvShowView;
    }
}
