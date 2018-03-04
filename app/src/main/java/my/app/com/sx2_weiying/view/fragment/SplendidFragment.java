package my.app.com.sx2_weiying.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.Unbinder;
import my.app.com.sx2_weiying.R;
import my.app.com.sx2_weiying.model.bean.ChoiceItemBean;
import my.app.com.sx2_weiying.model.bean.UserBean;
import my.app.com.sx2_weiying.model.http.ApiService;
import my.app.com.sx2_weiying.presenter.DataPresenter;
import my.app.com.sx2_weiying.utils.GlideImageLoader;
import my.app.com.sx2_weiying.view.activity.SearchActivity;
import my.app.com.sx2_weiying.view.adapter.SplendidAdpter;
import my.app.com.sx2_weiying.view.iview.IView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.LinearLayout.VERTICAL;

public class SplendidFragment extends Fragment implements IView {
    private static final String TAG = "SplendidFragment";

    Unbinder unbinder;
    private List<String> data = new ArrayList<>();
    private View view;
    private Banner banner;
    private RecyclerView recy;
    private DataPresenter dataPresenter;
    private List<UserBean.RetBean.ListBean.ChildListBean> childList2;
    private EditText etSousuo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fresco.initialize(getActivity());
        view = inflater.inflate(R.layout.splendid, container, false);

        //查找控件
        initView();

        etSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "搜索", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.activity_open, R.anim.activity_open_close);
            }
        });

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        banner = view.findViewById(R.id.banner);
        Toolbar mToolbar = view.findViewById(R.id.toolbar);

        ActionBar actionbar;
//        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (getActivity()).onBackPressed();
            }

        });
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        CollapsingToolbarLayout mCollapsingToolbarLayout =  view.findViewById(R.id.collapsing_toolbar_layout);
        mCollapsingToolbarLayout.setTitle("可下拉");

        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.YELLOW);//设置收缩后Toolbar上字体的颜色

        dataPresenter = new DataPresenter();
        dataPresenter.attachView(this);
        //http://api.svipmovie.com/front/homePageApi/homePage.do
        dataPresenter.getData("http://api.svipmovie.com/");

        return view;
    }

    private void initView() {
        recy = view.findViewById(R.id.recy);
        etSousuo = view.findViewById(R.id.et_sousuo);
    }

    @Override
    public void success(Object o) {
        UserBean news = (UserBean) o;
        if (news != null) {
            UserBean.RetBean ret = news.getRet();
            if (ret != null) {
                List<UserBean.RetBean.ListBean> list = ret.getList();
                List<UserBean.RetBean.ListBean.ChildListBean> childList = list.get(0).getChildList();

                for (int i = 0; i < childList.size(); i++) {
                    String pic = childList.get(i).getPic();
                    data.add(pic);
                }
                if (data != null) {
                    //设置图片加载器
                    banner.setImageLoader(new GlideImageLoader());
                    //设置图片集合
                    banner.setImages(data);
                    //banner设置方法全部调用完毕时最后调用
                    banner.start();
                }
                gethttp();
            }

        }


    }

    private void gethttp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.svipmovie.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<ChoiceItemBean> call = apiService.getChoiceItem();

        call.enqueue(new Callback<ChoiceItemBean>() {
            @Override
            public void onResponse(Call<ChoiceItemBean> call, Response<ChoiceItemBean> response) {
                ChoiceItemBean bean = response.body();
                SplendidAdpter splendidAdpter = new SplendidAdpter(getActivity(),bean);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), VERTICAL, false);
                recy.setLayoutManager(linearLayoutManager);
                recy.setAdapter(splendidAdpter);
            }

            @Override
            public void onFailure(Call<ChoiceItemBean> call, Throwable t) {
                System.out.println("失败");
            }
        });
    }

    @Override
    public void Failes(Exception e) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dataPresenter != null) {
            dataPresenter.delete();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}