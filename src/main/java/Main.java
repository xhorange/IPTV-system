import Utils.HttpUrl;
import Utils.HttpUtil;

public class Main {
    public static void main(String[] argvs){
         String tvInfo=HttpUtil.getInstance().getInfo(HttpUrl.TV_SHOW_INFO);
         System.out.println(tvInfo);

    }
}
