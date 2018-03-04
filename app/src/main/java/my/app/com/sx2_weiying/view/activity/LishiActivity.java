package my.app.com.sx2_weiying.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import my.app.com.sx2_weiying.R;
import my.app.com.sx2_weiying.model.bean.LishiJiLu;
import my.app.com.sx2_weiying.model.bean.MessageEventa;
import my.app.com.sx2_weiying.model.bean.MessageEventb;
import my.app.com.sx2_weiying.view.adapter.LishiAdpater;

import static android.widget.GridLayout.VERTICAL;

public class LishiActivity extends AppCompatActivity {
    Unbinder unbinder;
    private static final String TAG = "LishiActivity";
    @BindView(R.id.recyw)
    RecyclerView recyw;
    private List<LishiJiLu> list = new ArrayList<>();
    private LishiAdpater myAdpater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lishi);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        unbinder = ButterKnife.bind(this);

        //设置适配器
        if (list != null) {
            initView();
            myAdpater.notifyDataSetChanged();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (list != null) {
            myAdpater.notifyDataSetChanged();
        }
    }

    private void initView() {
        myAdpater = new LishiAdpater(this, list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, VERTICAL, false);
        recyw.setLayoutManager(gridLayoutManager);
        recyw.setAdapter(myAdpater);
        //点击事件     调用接口方法
        myAdpater.setOnRecyclerViewItemClickLintemet(new LishiAdpater.OnRecyclerViewItemClickLintemet() {
            @Override
            public void onItemClick(int position) {
                String dataId = list.get(position).getDataId();
                String pic = list.get(position).getPic();
                String title = list.get(position).getTitle();
                if (dataId != null && dataId != "") {
                    Intent intent = new Intent(LishiActivity.this, VideoActivity.class);
                    MessageEventa messageEventa1 = new MessageEventa();
                    messageEventa1.setPic(pic);
                    messageEventa1.setTitle(title);
                    messageEventa1.setDataId(dataId);
                    //发送事件
                    EventBus.getDefault().postSticky(messageEventa1);
                    startActivity(intent);
                    LishiActivity.this.overridePendingTransition(R.anim.activity_open, R.anim.activity_open_close);
                }

            }
        });
        myAdpater.notifyDataSetChanged();

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(MessageEventb event) {
        List<LishiJiLu> data = event.getList();
        list.addAll(data);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
