package my.app.com.sx2_weiying.model.imodel;

import java.util.Map;

import io.reactivex.Flowable;
import my.app.com.sx2_weiying.model.bean.DiscoverBean;
import my.app.com.sx2_weiying.model.bean.PingLunBean;
import my.app.com.sx2_weiying.model.bean.SearchBean;
import my.app.com.sx2_weiying.model.bean.SpecialBean;
import my.app.com.sx2_weiying.model.bean.UserBean;
import my.app.com.sx2_weiying.model.bean.VideoBean;
import my.app.com.sx2_weiying.presenter.DataPresenter;
import my.app.com.sx2_weiying.utils.RetrofitUtil;

public class DataModel implements IModel {

    private static final String TAG = "DataModel";
    private DataPresenter presenter;

    public DataModel(DataPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getData(String url) {
        Flowable<UserBean> flowable = RetrofitUtil.getInstant(url).getApiService().getData();
        presenter.getDataa(flowable);

    }

    @Override
    public void getDiscover(String url, Map<String, String> map) {
        Flowable<DiscoverBean> flowable1 = RetrofitUtil.getInstant(url).getApiService().getDiscover(map);
        presenter.getDiscovera(flowable1);
    }

    @Override
    public void getSpecial(String url,Map<String,String> map) {
        Flowable<SpecialBean> special = RetrofitUtil.getInstant(url).getApiService().getSpecial(map);

        presenter.getSpeciala(special);
    }

    @Override
    public void getVideo(String url, Map<String, String> map) {
        Flowable<VideoBean> video = RetrofitUtil.getInstant(url).getApiService().getVideo(map);

        presenter.getVideoa(video);
    }

    @Override
    public void getPingLun(String url, Map<String, String> map) {
        Flowable<PingLunBean> pinglun = RetrofitUtil.getInstant(url).getApiService().getPingLun(map);

        presenter.getPingLuna(pinglun);
    }

    @Override
    public void getSearch(String url, Map<String, String> map) {
        Flowable<SearchBean> pinglun1 = RetrofitUtil.getInstant(url).getApiService().getSearch(map);

        presenter.getSearcha(pinglun1);
    }
}
