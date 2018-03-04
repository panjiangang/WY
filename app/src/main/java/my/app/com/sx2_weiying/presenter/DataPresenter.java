package my.app.com.sx2_weiying.presenter;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import my.app.com.sx2_weiying.model.imodel.DataModel;
import my.app.com.sx2_weiying.model.imodel.IModel;
import my.app.com.sx2_weiying.model.bean.DiscoverBean;
import my.app.com.sx2_weiying.model.bean.PingLunBean;
import my.app.com.sx2_weiying.model.bean.SearchBean;
import my.app.com.sx2_weiying.model.bean.SpecialBean;
import my.app.com.sx2_weiying.model.bean.UserBean;
import my.app.com.sx2_weiying.model.bean.VideoBean;
import my.app.com.sx2_weiying.view.iview.IView;
import my.app.com.sx2_weiying.view.iview.IViewa;

public class DataPresenter implements IPresenter {

    private static final String TAG = "DataPresenter";

    private IView iv;
    private IViewa iva;
    private DisposableSubscriber<UserBean> disposableSubscriber;
    private DisposableSubscriber<DiscoverBean> disposableSubscriber1;
    private DisposableSubscriber<SpecialBean> disposableSubscriber2;
    private DisposableSubscriber<VideoBean> disposableSubscriber3;
    private DisposableSubscriber<PingLunBean> disposableSubscriber4;
    private DisposableSubscriber<SearchBean> disposableSubscriber5;

    public void attachView(IView iv) {
        this.iv = iv;
    }

    public void attachViewa(IViewa iva) {
        this.iva = iva;
    }

    public void delete() {
        if (iv != null) {
            iv = null;
        }
        if (disposableSubscriber != null) {
            if (disposableSubscriber.isDisposed()) {
                disposableSubscriber.dispose();
            }
        }
        if (disposableSubscriber1 != null) {
            if (disposableSubscriber1.isDisposed()) {
                disposableSubscriber1.dispose();
            }
        }
        if (disposableSubscriber2 != null) {
            if (disposableSubscriber2.isDisposed()) {
                disposableSubscriber2.dispose();
            }
        }
        if (disposableSubscriber3 != null) {
            if (disposableSubscriber3.isDisposed()) {
                disposableSubscriber3.dispose();
            }
        }
        if (disposableSubscriber4 != null) {
            if (disposableSubscriber4.isDisposed()) {
                disposableSubscriber4.dispose();
            }
        }
    }

    @Override
    public void getData(String url) {
        IModel model = new DataModel(this);
        model.getData(url);
    }

    @Override
    public void getDiscover(String url, Map<String, String> map) {
        IModel model = new DataModel(this);
        model.getDiscover(url, map);
    }

    @Override
    public void getSpecial(String url, Map<String, String> map) {
        IModel model = new DataModel(this);
        model.getSpecial(url, map);
    }

    @Override
    public void getVideo(String url, Map<String, String> map) {
        IModel model = new DataModel(this);
        model.getVideo(url, map);
    }

    @Override
    public void getPingLun(String url, Map<String, String> map) {
        IModel model = new DataModel(this);
        model.getPingLun(url, map);
    }

    @Override
    public void getSearch(String url, Map<String, String> map) {
        IModel model = new DataModel(this);
        model.getSearch(url, map);
    }


    public void getDataa(Flowable<UserBean> flowable) {
        disposableSubscriber = flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<UserBean>() {
                    @Override
                    public void onNext(UserBean userBean) {

                        if (userBean != null) {
                            iv.success(userBean);

                        }

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void getDiscovera(Flowable<DiscoverBean> flowable1) {
        disposableSubscriber1 = flowable1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<DiscoverBean>() {
                    @Override
                    public void onNext(DiscoverBean discoverBean) {
                        if (discoverBean != null) {
                            List<DiscoverBean.RetBean.ListBean> list = discoverBean.getRet().getList();
                            if (list != null) {
                                iv.success(list);
                            }

                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void getSpeciala(Flowable<SpecialBean> special) {
        disposableSubscriber2 = special.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<SpecialBean>() {
                    @Override
                    public void onNext(SpecialBean specialBean) {
                        if (specialBean != null) {
                            List<SpecialBean.RetBean.ListBean> list = specialBean.getRet().getList();
                            if (list != null) {
                                iv.success(list);
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void getVideoa(Flowable<VideoBean> video) {
        disposableSubscriber3 = video.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<VideoBean>() {
                    @Override
                    public void onNext(VideoBean videoBean) {
                        if (videoBean != null) {
                            iv.success(videoBean);
                        }

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getPingLuna(Flowable<PingLunBean> pinglun) {
        disposableSubscriber4 = pinglun.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<PingLunBean>() {
                    @Override
                    public void onNext(PingLunBean pinglun) {

                        if (pinglun != null) {
                            List<PingLunBean.RetBean.ListBean> list = pinglun.getRet().getList();
                            if (list != null) {
                                iva.successa(list);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void getSearcha(Flowable<SearchBean> pinglun1) {
        disposableSubscriber5 = pinglun1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<SearchBean>() {
                    @Override
                    public void onNext(SearchBean searchBean) {

                        if (searchBean != null) {
                            List<SearchBean.RetBean.ListBean> list = searchBean.getRet().getList();
                            iv.success(list);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
