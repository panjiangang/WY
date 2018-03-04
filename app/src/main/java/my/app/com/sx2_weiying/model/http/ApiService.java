package my.app.com.sx2_weiying.model.http;

import java.util.Map;

import io.reactivex.Flowable;
import my.app.com.sx2_weiying.model.bean.ChoiceItemBean;
import my.app.com.sx2_weiying.model.bean.DiscoverBean;
import my.app.com.sx2_weiying.model.bean.PingLunBean;
import my.app.com.sx2_weiying.model.bean.SearchBean;
import my.app.com.sx2_weiying.model.bean.SpecialBean;
import my.app.com.sx2_weiying.model.bean.UserBean;
import my.app.com.sx2_weiying.model.bean.VideoBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {


    //http://api.svipmovie.com/front/homePageApi/homePage.do
    @GET("front/homePageApi/homePage.do")
    Flowable<UserBean> getData();

    //http://api.svipmovie.com/front/searchKeyWordApi/getVideoListByKeyWord.do?keyword=1&pnum=1
    @GET("front/searchKeyWordApi/getVideoListByKeyWord.do")
    Flowable<DiscoverBean> getDiscover(@QueryMap Map<String, String> map);

    //有错误
    //http://api.svipmovie.com/front/columns/getNewsList.do?catalogId=402834815584e463015584e539700019&information=null
    @GET("front/columns/getNewsList.do")
    Flowable<SpecialBean> getSpecial(@QueryMap Map<String, String> map);



    //http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=0334e38676d54208b1e6b9da28758a2e
    @GET("front/videoDetailApi/videoDetail.do")
    Flowable<VideoBean> getVideo(@QueryMap Map<String, String> map);

    //   //  http://api.svipmovie.com/front/Commentary/getCommentList.do?mediaId=5d21bed962f44c8eac068942745187ef
    @GET("front/Commentary/getCommentList.do")
    Flowable<PingLunBean> getPingLun(@QueryMap Map<String, String> map);

    //http://api.svipmovie.com/front/searchKeyWordApi/getVideoListByKeyWord.do?keyword=%E7%BE%9E%E7%BE%9E%E7%9A%84%E9%93%81%E6%8B%B3

    @GET("front/searchKeyWordApi/getVideoListByKeyWord.do")
    Flowable<SearchBean> getSearch(@QueryMap Map<String, String> map);

//    http://api.svipmovie.com/front/homePageApi/homePage.do
    @GET("/front/homePageApi/homePage.do")
    Call<ChoiceItemBean> getChoiceItem();
}
