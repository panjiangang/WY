package my.app.com.sx2_weiying.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mcxtzhang.layoutmanager.swipecard.CardConfig;
import com.mcxtzhang.layoutmanager.swipecard.OverLayCardLayoutManager;
import com.mcxtzhang.layoutmanager.swipecard.RenRenCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import my.app.com.sx2_weiying.R;
import my.app.com.sx2_weiying.model.bean.DiscoverBean;
import my.app.com.sx2_weiying.model.bean.MessageEventa;
import my.app.com.sx2_weiying.presenter.DataPresenter;
import my.app.com.sx2_weiying.view.activity.VideoActivity;
import my.app.com.sx2_weiying.view.adapter.DiscoverAdpater;
import my.app.com.sx2_weiying.view.iview.IView;

import static android.content.ContentValues.TAG;

public class DiscoverFragment extends Fragment {
    private DiscoverAdpater discoverAdpater;
    private int number=1;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.recyclerview)
    RecyclerView recy;
    Unbinder unbinder;
    private DataPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getNet(number);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = getRandomNumber(1, 108);
                getNet(number);
                discoverAdpater.notifyDataSetChanged();
            }
        });


    }

    public void getNet(int num){
        //    //http://api.svipmovie.com/front/searchKeyWordApi/getVideoListByKeyWord.do?keyword=1&pnum=1
        Map<String, String> map = new HashMap<>();
        map.put("keyword",num+"");
        map.put("pnum", "1");
        presenter = new DataPresenter();
        presenter.getDiscover("http://api.svipmovie.com/",map);
        presenter.attachView(new IView() {


            private List<DiscoverBean.RetBean.ListBean> news;

            @Override
            public void success(Object o) {
                if (o instanceof List){
                    news = (List<DiscoverBean.RetBean.ListBean>) o;
                    Log.i(TAG, "第三个页面数据: "+ news.size());


                    discoverAdpater = new DiscoverAdpater(news, getActivity());

                    recy.setLayoutManager(new OverLayCardLayoutManager());
                    CardConfig.initConfig(getActivity());

                    //三个参数:rcv:自己的RecyclerView，myAdapter:适配器,list:将自己的集合数据传进去，进行得到size数量
                    ItemTouchHelper.Callback callback = new RenRenCallback(recy, discoverAdpater, news);
                    //v7的触摸事件判断类
                    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
                    itemTouchHelper.attachToRecyclerView(recy);

                    //设置适配器
                    recy.setAdapter(discoverAdpater);

                    //点击事件     调用接口方法
                    discoverAdpater.setOnRecyclerViewItemClickLintemet(new DiscoverAdpater.OnRecyclerViewItemClickLintemet() {
                        @Override
                        public void onItemClick(int position) {

                            String dataId = news.get(position).getDataId();
                            String title = news.get(position).getTitle();
                            String pic = news.get(position).getPic();
                            Intent intent = new Intent(getActivity(),VideoActivity.class);
//                        intent.putExtra("name",dataId);
                            MessageEventa messageEventa = new MessageEventa();
                            messageEventa.setPic(pic);
                            messageEventa.setTitle(title);
                            messageEventa.setDataId(dataId);
                            //发送事件
                            EventBus.getDefault().postSticky(messageEventa);

                            startActivity(intent);
                            getActivity().overridePendingTransition(R.anim.activity_open, R.anim.activity_open_close);
                        }
                    });
                    discoverAdpater.notifyDataSetChanged();
                }
            }

            @Override
            public void Failes(Exception e) {

            }

        });
    }

    public int getRandomNumber(int min, int max) {
        return new Random().nextInt(max) % (max - min + 1)+min;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (presenter!=null){
            presenter.delete();
        }
    }
}
