package my.app.com.sx2_weiying.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import my.app.com.sx2_weiying.R;
import my.app.com.sx2_weiying.model.bean.SearchBean;
import my.app.com.sx2_weiying.presenter.DataPresenter;
import my.app.com.sx2_weiying.view.adapter.SimpleItemTouchHelperCallback;
import my.app.com.sx2_weiying.view.adapter.SouSuoHorizontalRvAdapter;
import my.app.com.sx2_weiying.view.iview.IView;
import my.app.com.sx2_weiying.view.iview.XCFlowLayout;

import static android.widget.LinearLayout.VERTICAL;

public class SearchActivity extends AppCompatActivity implements IView {

    @BindView(R.id.ed_sousuo)
    EditText edSousuo;
    @BindView(R.id.recy2)
    RecyclerView recy2;

    private XCFlowLayout mFlowLayout;
    private List<String> horizontallist = new ArrayList<>();
    private static final String TAG = "SearchActivity";
    private HashMap<String, String> map;
    private DataPresenter dataPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {

        //请求数据
        //   //http://api.svipmovie.com/front/searchKeyWordApi/getVideoListByKeyWord.do?keyword=%E7%BE%9E%E7%BE%9E%E7%9A%84%E9%93%81%E6%8B%B3
        map = new HashMap<>();

        dataPresenter = new DataPresenter();
        dataPresenter.attachView(this);


        horizontallist.add("羞羞的铁拳");
        horizontallist.add("速度与激情");
        horizontallist.add("大话西游");
        horizontallist.add("青年");
        horizontallist.add("合伙人");
        horizontallist.add("极速快递");
        horizontallist.add("哆啦A梦");
        horizontallist.add("笔");
        horizontallist.add("笔仙");
        horizontallist.add("再见吧");
        horizontallist.add("这是什么电影阿");
        initChildViews();

    }

    private void initChildViews() {
        // TODO Auto-generated method stub
        mFlowLayout = (XCFlowLayout) findViewById(R.id.flowlayout);
        MarginLayoutParams lp = new MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;


        for (int i = 0; i < horizontallist.size(); i++) {
            TextView view = new TextView(this);
            view.setText(horizontallist.get(i));
            view.setTextColor(Color.WHITE);
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_bg));
            mFlowLayout.addView(view, lp);
        }

    }

    @OnClick(R.id.te_sousuo)
    public void onClick() {
        //清空当前集合，重新附上数据
        horizontallist.clear();
        String sseditstr = edSousuo.getText().toString().trim();
        if (sseditstr.equals("")) {
            Toast.makeText(this, "搜索内容不能为空", Toast.LENGTH_SHORT).show();
        } else {
            horizontallist.add(sseditstr);
            initChildViews();
            //当触发点击事件时进行网络请求
            map.put("keyword", sseditstr);
            dataPresenter.getSearch("http://api.svipmovie.com/", map);
        }

    }

    @Override
    public void success(Object o) {
        if (o instanceof List) {
            List<SearchBean.RetBean.ListBean> news = (List<SearchBean.RetBean.ListBean>) o;
            if (news != null) {
                SouSuoHorizontalRvAdapter souSuoHorizontalRvAdapter = new SouSuoHorizontalRvAdapter(this, news);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, VERTICAL, false);
                recy2.setLayoutManager(linearLayoutManager);
                recy2.setAdapter(souSuoHorizontalRvAdapter);
                //先实例化Callback
                ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(souSuoHorizontalRvAdapter);
                //用Callback构造ItemtouchHelper
                ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
                //调用ItemTouchHelper的attachToRecyclerView方法建立联系
                touchHelper.attachToRecyclerView(recy2);
                souSuoHorizontalRvAdapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    public void Failes(Exception e) {

    }
}
