package utils;

public class HttpUrl {
    //接口
    public static final String PORT = "http://127.0.0.1:33333";
    //所有节目信息
    public static final String TV_SHOW_INFO = PORT + "/api/v1/iptv/program_list";
    //已订阅节目信息
    public static final String SUB_TV_SHOW_INFO = PORT + "/api/v1/iptv/program_ref_list";
    //所有频道信息
    public static final String TV_CHANNEL_INFO = PORT + "/api/v1/iptv/channel_list";
    //已订阅频道信息
    public static final String SUB_TV_CHANNEL_INFO = PORT + "/api/v1/iptv/channel_ref_list";
    //订阅
    public static final String SUB_REQUEST = "/api/v1/iptv/sub";
}