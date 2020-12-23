package Utils;

import com.google.gson.Gson;
import model.TvChannelModel;

import static Utils.HttpUrl.TV_CHANNEL_INFO;
import static Utils.HttpUrl.json;

/**
 * 获取电视信息工具类
 */
public class TvUtil {
    private static TvUtil tvUtil;

    public static TvUtil getInstance() {
        synchronized (TvUtil.class) {
            if (tvUtil == null) {
                tvUtil = new TvUtil();
            }
            return tvUtil;
        }
    }

    /**
     * 获得频道信息
     *
     * @return
     */
    public TvChannelModel getChannelInfo() {
        String channelInfo = HttpUtil.getInstance().getInfo(TV_CHANNEL_INFO);
        if (channelInfo==null){
            channelInfo=json;
        }
        Gson gson = new Gson();
        TvChannelModel tvChannelModel = gson.fromJson(json, TvChannelModel.class);
        return tvChannelModel;
    }
}
