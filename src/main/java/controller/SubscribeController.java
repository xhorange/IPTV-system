package controller;

import main.Callback;
import model.TvChannelModel;
import model.TvShowModel;
import utils.TvUtil;
import view.SubView;

import javax.swing.*;
import java.util.*;

public class SubscribeController {
    private static SubscribeController subscribeController;
    private SubView subView;
    private TvChannelModel tvChannelInfo;
    private List<TvShowModel> AllTvShows;
    private List<TvShowModel> subTvShows;
    private List<TvChannelModel> allChannels;
    private List<TvChannelModel> SubChannels;

    public static SubscribeController getInstance() {
        synchronized (UserController.class) {
            if (subscribeController == null) {
                subscribeController = new SubscribeController();
            }
            return subscribeController;
        }
    }

    public void getTvInfo() {
        getTvInfo(new Callback());
    }

    public void getTvInfo(Callback callback) {
        tvChannelInfo = TvUtil.getInstance().getChannelInfo();
        AllTvShows = tvChannelInfo.getList();
        subTvShows = new ArrayList<TvShowModel>();
        Iterator<TvShowModel> iterator = AllTvShows.iterator();
        //初始化所有节目和关注节目队列
        while (iterator.hasNext()) {
            TvShowModel tvShowModel = iterator.next();
            tvShowModel.setChannelName(tvChannelInfo.getChannelName());
            if (tvShowModel.isSub()) {
                // TODO: 2020/12/28 未来后端会单独给出关注的节目列表
                subTvShows.add(tvShowModel);
            }
        }
        callback.onEnd();
    }

    public SubView goToSubShows() {
        return showTvShows(AllTvShows);
    }

    public SubView showSubSHows() {

        return showTvShows(subTvShows);
    }

    public SubView goToSubChannels() {
        return showChannels(allChannels);
    }

    public SubView showSubChannels() {

        return showChannels(allChannels);
    }


    public SubView showTvShows(List<TvShowModel> tvShows) {
        if (tvShows == null) {
            getTvInfo();
        }
        String[][] tvShowInfo = new String[tvShows.size()][6];
        String[] title = {"选中", "开始时间", "频道名", "节目名", "订阅情况"};
        Iterator<TvShowModel> iterator = tvShows.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            TvShowModel tvShowModel = iterator.next();
            tvShowInfo[count][1] = tvShowModel.getShowTime();
            tvShowInfo[count][2] = tvShowModel.getChannelName();
            tvShowInfo[count][3] = tvShowModel.getTvShowName();
            tvShowInfo[count][4] = tvShowModel.isSub() ? "已订阅" : "未订阅";
            tvShowInfo[count][5] = String.valueOf(tvShowModel.getTvShowId());
            count++;
        }
        subView = new SubView(tvShowInfo, title);
        return subView;
    }

    public void subscribeShow(JTable table) {
        int index = table.getSelectedRow();
        //int showID = Integer.parseInt((String) table.getValueAt(index, 5));
        TvShowModel selectedTvShow = AllTvShows.get(index);
        selectedTvShow.setSub(!selectedTvShow.isSub());
        table.setValueAt(selectedTvShow.isSub() ? "已订阅" : "未订阅", index, 4);
        // TODO: 2020/12/28 修改关注列表
    }

    public SubView showChannels(List<TvChannelModel> channelList) {
        if (channelList == null) {
            getTvInfo();
        }
        String[][] tvShowInfo = new String[AllTvShows.size()][3];
        String[] title = {"选中", "频道名", "订阅情况"};
        Iterator<TvShowModel> iterator = AllTvShows.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            TvShowModel tvShowModel = iterator.next();
            tvShowInfo[count][1] = tvShowModel.getChannelName();
            tvShowInfo[count][2] = tvShowModel.isSub() ? "已订阅" : "未订阅";
            tvShowInfo[count][3] = String.valueOf(tvShowModel.getTvShowId());
            count++;
        }
        subView = new SubView(tvShowInfo, title);
        return subView;
    }
}

