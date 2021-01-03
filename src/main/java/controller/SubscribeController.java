package controller;

import model.ChannelResponse;
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
    private List<TvChannelModel> subChannels;
    private SubView subView;
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
        getSubChannelInfo();
        getSubShowInfo();
    }

    public void getTvInfo(int index) {
        if (index > allChannels.size() - 1 || index < 0) {
            return;
        }
        StringBuffer showUrl = new StringBuffer();
        showUrl.append(HttpUrl.SUB_TV_SHOW_INFO)
                .append("?channel_id=")
                .append(allChannels.get(index).getChannelId())
                .append("&user_id=")
                .append(LoginController.getInstance().getUserModel().getUserId());
        TvShowResponse response = HttpUtil.getInstance().getShowInfo(showUrl.toString());
        if (response != null && response.getData() != null && response.getData().getTvShowList() != null) {
            allChannels.get(index).setTvShows(response.getData().getTvShowList());
        }
    }

    public void getSubShowInfo() {
        StringBuffer subShowUrl = new StringBuffer();
        subShowUrl.append(HttpUrl.SUB_TV_SHOW_INFO)
                .append("?user_name=")
                .append(LoginController.getInstance().getUserModel().getUserName())
                .append("&user_password=")
                .append(LoginController.getInstance().getUserModel().getPassword());
        TvShowResponse tvResponse = HttpUtil.getInstance().getShowInfo(subShowUrl.toString());
        if (tvResponse != null && tvResponse.getCode() == 200) {
            subTvShows = tvResponse.getData().getTvShowList();
        }
    }

    public void getSubChannelInfo() {
        StringBuffer subChannelUrl = new StringBuffer();
        subChannelUrl.append(HttpUrl.SUB_TV_CHANNEL_INFO)
                .append("?user_id=")
                .append(LoginController.getInstance().getUserModel().getUserId());
        ChannelResponse channelResponse = HttpUtil.getInstance().getChannelInfo(subChannelUrl.toString());
        if (channelResponse != null && channelResponse.getCode() == 200) {
            subChannels = channelResponse.getData().getChannelList();
        }
    }

    public void setAllChannels(List<TvChannelModel> allChannels) {
        this.allChannels = allChannels;
    }

    public SubView goToSubShows() {
        page = 0;
        return showTvShows(allChannels.get(page).getTvShows(), false);
    }

    public SubView showSubSHows() {
        getSubShowInfo();
        return showTvShows(subTvShows, true);
    }

    public SubView goToSubChannels() {
        if (allChannels==null||allChannels.size()==0){
            getSubChannelInfo();
        }
        return showChannels(allChannels, false);
    }

    public SubView showSubChannels() {
        if (allChannels==null||allChannels.size()==0){
            getSubChannelInfo();
        }
        return showChannels(allChannels, true);
    }

    /**
     * 展示订阅
     *
     * @param tvShows
     * @param isSub
     * @return
     */
    public SubView showTvShows(List<TvShowModel> tvShows, boolean isSub) {

        if (tvShows == null) {
            page = 0;
            getTvInfo(0);
        }
        String[][] tvShowInfo = new String[tvShows.size()][6];
        String[] title = {"选中", "开始时间", "频道名", "节目名", "订阅情况"};
        Iterator<TvShowModel> iterator = tvShows.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            TvShowModel tvShowModel = iterator.next();
            if (isSub) {
                if (tvShowModel.isSub()) {
                    tvShowInfo[count][1] = tvShowModel.getShowTime();
                    tvShowInfo[count][2] = tvShowModel.getChannelName();
                    tvShowInfo[count][3] = tvShowModel.getTvShowName();
                    tvShowInfo[count][4] = tvShowModel.isSub() ? "已订阅" : "未订阅";
                    tvShowInfo[count][5] = String.valueOf(tvShowModel.getTvShowId());
                    count++;
                }
            } else {
                tvShowInfo[count][1] = tvShowModel.getShowTime();
                tvShowInfo[count][2] = tvShowModel.getChannelName();
                tvShowInfo[count][3] = tvShowModel.getTvShowName();
                tvShowInfo[count][4] = tvShowModel.isSub() ? "已订阅" : "未订阅";
                tvShowInfo[count][5] = String.valueOf(tvShowModel.getTvShowId());
                count++;
            }
        }
        if (isSub) {
            subView = new SubView(tvShowInfo, title, SubView.SUB_SHOW_VIEW);
        } else {
            subView = new SubView(tvShowInfo, title, tvShows.get(0).getChannelName(), SubView.SHOW_VIEW);
        }
        return subView;
    }

    /**
     * 订阅
     *
     * @param table
     */
    public void subscribeShow(JTable table, int type) {
        TvShowModel selectedTvShow = null;
        TvChannelModel selectedChannelShow = null;
        int index = table.getSelectedRow();
        int subType = 0;
        switch (type) {
            case SubView.SHOW_VIEW:
                selectedTvShow = allChannels.get(page).getTvShows().get(index);
                selectedTvShow.setSub(!selectedTvShow.isSub());
                subType = 2;
                table.setValueAt(selectedTvShow.isSub() ? "已订阅" : "未订阅", index, table.getColumnCount() - 1);
                break;
            case SubView.SUB_SHOW_VIEW:
                selectedTvShow = subTvShows.get(index);
                selectedTvShow.setSub(!selectedTvShow.isSub());
                subType = 2;
                table.setValueAt(selectedTvShow.isSub() ? "已订阅" : "未订阅", index, table.getColumnCount() - 1);
                break;
            case SubView.CHANNEL_VIEW:
                selectedChannelShow = allChannels.get(index);
                selectedChannelShow.setSub(!selectedChannelShow.isSub());
                subType = 1;
                table.setValueAt(selectedChannelShow.isSub() ? "已订阅" : "未订阅", index, table.getColumnCount() - 1);
                break;
            case SubView.SUB_CHANNEL_VIEW:
                selectedChannelShow = subChannels.get(index);
                selectedChannelShow.setSub(!selectedChannelShow.isSub());
                subType = 1;
                table.setValueAt(selectedChannelShow.isSub() ? "已订阅" : "未订阅", index, table.getColumnCount() - 1);
                break;
        }
        StringBuffer subUrl = new StringBuffer();
        subUrl.append(HttpUrl.SUB_REQUEST)
                .append("?user_id=")
                .append(LoginController.getInstance().getUserModel().getUserId())
                .append("&sub_id=");
        if (subType == 1) {
            subUrl.append(selectedChannelShow.getChannelId())
                    .append("&status=")
                    .append(selectedChannelShow.isSub() ? 1 : 0)
                    .append("&sub_type=")
                    .append(subType);
        } else if (subType == 2) {
            subUrl.append(selectedTvShow.getTvShowId())
                    .append("&status=")
                    .append(selectedTvShow.isSub() ? 1 : 0)
                    .append("&sub_type=")
                    .append(subType);
        }
        HttpUtil.getInstance().getInfo(subUrl.toString());
    }

    /**
     * 展示频道
     *
     * @param channelList
     * @return
     */
    public SubView showChannels(List<TvChannelModel> channelList, boolean isSub) {
        String[][] channelInfo = new String[allChannels.size()][3];
        String[] title = {"选中", "频道名", "订阅情况"};
        Iterator<TvChannelModel> iterator = allChannels.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            TvChannelModel tvChannelModel = iterator.next();
            channelInfo[count][1] = tvChannelModel.getChannelName();
            channelInfo[count][2] = tvChannelModel.isSub() ? "已订阅" : "未订阅";
            count++;
        }
        if (isSub) {
            return new SubView(channelInfo, title, SubView.SUB_CHANNEL_VIEW);
        }
        return new SubView(channelInfo, title, SubView.CHANNEL_VIEW);
    }

    public void upDate(boolean up) {
        if (up) {
            page++;
        } else {
            page--;
        }
        if (page < 0 || page > allChannels.size() - 1) {
            JOptionPane.showMessageDialog(null, "没有更多频道", "提示", JOptionPane.CLOSED_OPTION);
            if (up) {
                page--;
            } else {
                page++;
            }
            return;
        }
        getTvInfo(page);
        System.out.println(page);
        List<TvShowModel> tvShows = allChannels.get(page).getTvShows();
        String[][] tvShowInfo = new String[tvShows.size()][5];
        String[] title = {"选中", "开始时间", "频道名", "节目名", "订阅情况"};
        Iterator<TvShowModel> iterator = tvShows.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            TvShowModel tvShowModel = iterator.next();
            tvShowInfo[count][1] = tvShowModel.getShowTime();
            tvShowInfo[count][2] = tvShowModel.getChannelName();
            tvShowInfo[count][3] = tvShowModel.getTvShowName();
            tvShowInfo[count][4] = tvShowModel.isSub() ? "已订阅" : "未订阅";
            count++;
        }
        subView.updateTable(tvShowInfo, title);
    }
}

