package controller;

import model.TvChannelModel;
import model.TvShowModel;
import model.TvShowResponse;
import utils.HttpUrl;
import utils.HttpUtil;
import view.SubView;

import javax.swing.*;
import java.util.*;

public class SubscribeController {
    private static SubscribeController subscribeController;
    private TvChannelModel tvChannelInfo;
    private List<TvShowModel> subTvShows;
    private List<TvChannelModel> allChannels;
    private List<TvChannelModel> SubChannels;
    private int page;

    public static SubscribeController getInstance() {
        synchronized (LoginController.class) {
            if (subscribeController == null) {
                subscribeController = new SubscribeController();
            }
            return subscribeController;
        }
    }

    /**
     * 初始化，获取电视节目信息
     */
    public void init() {
        getTvInfo(0);
    }

    public void getTvInfo(int index) {
        if (index > allChannels.size() - 1 || index < 0) {
            return;
        }
        StringBuffer showUrl = new StringBuffer();
        showUrl.append(HttpUrl.TV_SHOW_INFO)
                .append("?channel_id=")
                .append(allChannels.get(index).getChannelId());
        TvShowResponse response = HttpUtil.getInstance().getShowInfo(showUrl.toString());
        if (response != null && response.getData() != null && response.getData().getTvShowList() != null) {
                allChannels.get(index).setTvShows(response.getData().getTvShowList());
        }
    }

    public void setAllChannels(List<TvChannelModel> allChannels) {
        this.allChannels = allChannels;
    }

    public SubView goToSubShows() {
        page=0;
        return showTvShows(allChannels.get(page).getTvShows(),false);
    }

    public SubView showSubSHows() {

        return showTvShows(subTvShows,true);
    }

    public SubView goToSubChannels() {
        return showChannels(allChannels);
    }

    public SubView showSubChannels() {

        return showChannels(allChannels);
    }


    public SubView showTvShows(List<TvShowModel> tvShows,boolean isSub) {
        if (tvShows == null) {
            page=0;
            getTvInfo(0);
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
        SubView subView;
        if (isSub){
            subView = new SubView(tvShowInfo, title);
        }else {
            subView=new SubView(tvShowInfo,title,tvShows.get(0).getChannelName());
        }

        return subView;
    }

    public void subscribeShow(JTable table) {
        int index = table.getSelectedRow();
        //int showID = Integer.parseInt((String) table.getValueAt(index, 5));
        TvShowModel selectedTvShow = allChannels.get(page).getTvShows().get(index);
        selectedTvShow.setSub(!selectedTvShow.isSub());
        table.setValueAt(selectedTvShow.isSub() ? "已订阅" : "未订阅", index, table.getColumnCount()-1);
        // TODO: 2020/12/29 发起网络请求
    }


    public SubView showChannels(List<TvChannelModel> channelList) {
        if (channelList == null) {
        }
        String[][] channelInfo = new String[allChannels.size()][4];
        String[] title = {"选中", "频道名", "订阅情况"};
        Iterator<TvChannelModel> iterator = allChannels.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            TvChannelModel tvChannelModel = iterator.next();
            channelInfo[count][1] = tvChannelModel.getChannelName();
            channelInfo[count][2] = tvChannelModel.isLiked()? "已订阅" : "未订阅";
            channelInfo[count][3] = String.valueOf(tvChannelModel.getChannelId());
            count++;
        }
        subView = new SubView(channelInfo, title);
        return subView;
    }
}

