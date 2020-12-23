import Utils.HttpUrl;
import Utils.HttpUtil;
import Utils.TvUtil;
import com.google.gson.Gson;
import model.TvChannelModel;

import java.util.List;

import static Utils.HttpUrl.json;

public class Main {
    public static void main(String[] argvs) {
        System.out.println(TvUtil.getInstance().getChannelInfo().getChannelName());
    }
}
