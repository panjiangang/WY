package my.app.com.sx2_weiying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.app.com.sx2_weiying.R;
import my.app.com.sx2_weiying.model.bean.MessageEventa;
import my.app.com.sx2_weiying.model.bean.SpecialBean;
import my.app.com.sx2_weiying.presenter.DataPresenter;
import my.app.com.sx2_weiying.view.adapter.SpecialActivityAdpater;
import my.app.com.sx2_weiying.view.iview.IView;

import static android.widget.GridLayout.VERTICAL;

public class SpecialActivity extends AppCompatActivity implements IView {

    @BindView(R.id.recy)
    RecyclerView recy;
    private SpecialActivityAdpater specialActivityAdpater;
    private DataPresenter dataPresenter;
    private static final String TAG = "SpecialActivity";
    private String name;
    private List<SpecialBean.RetBean.ListBean> news;
    private HashMap<String, String> map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special);
        ButterKnife.bind(this);

        name = getIntent().getStringExtra("name");

        map = new HashMap<>();
//        catalogId=402834815584e463015584e539700019
        map.put("catalogId", name);
        dataPresenter = new DataPresenter();
        dataPresenter.attachView(this);
        dataPresenter.getSpecial("http://api.svipmovie.com/",map);

    }




    @Override
    public void success(Object o) {
        if (o instanceof List){
            news = (List<SpecialBean.RetBean.ListBean>) o;
            if (news !=null){
                Log.i(TAG, "SpecialActivity 页面: "+ news.size());
                specialActivityAdpater = new SpecialActivityAdpater(this, news);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3 ,VERTICAL,false);
                recy.setLayoutManager(gridLayoutManager);
                recy.setAdapter(specialActivityAdpater);

                //点击事件     调用接口方法
                specialActivityAdpater.setOnRecyclerViewItemClickLintemet(new SpecialActivityAdpater.OnRecyclerViewItemClickLintemet() {
                    @Override
                    public void onItemClick(int position) {

                        String dataId = news.get(position).getDataId();
                        String title = news.get(position).getTitle();
                        String pic = news.get(position).getPic();
                        Intent intent = new Intent(SpecialActivity.this,VideoActivity.class);
//                        intent.putExtra("name",dataId);
                        MessageEventa messageEventa = new MessageEventa();
                        messageEventa.setPic(pic);
                        messageEventa.setTitle(title);
                        messageEventa.setDataId(dataId);
                        //发送事件
                        EventBus.getDefault().postSticky(messageEventa);

                            startActivity(intent);
                        SpecialActivity.this.overridePendingTransition(R.anim.activity_open, R.anim.activity_open_close);
                    }
                });
            }
            specialActivityAdpater.notifyDataSetChanged();
        }

    }

    @Override
    public void Failes(Exception e) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataPresenter.delete();
        finish();
    }
}
