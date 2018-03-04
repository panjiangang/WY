package my.app.com.sx2_weiying.model.imodel;

import java.util.Map;

public interface IModel {
    void getData(String url);
    void getDiscover(String url, Map<String, String> map);
    void getSpecial(String url, Map<String, String> map);
    void getVideo(String url, Map<String, String> map);
    void getPingLun(String url, Map<String, String> map);
    void getSearch(String url, Map<String, String> map);
}
