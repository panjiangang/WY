package my.app.com.sx2_weiying.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import my.app.com.sx2_weiying.R;
import my.app.com.sx2_weiying.model.bean.SpecialBeana;
import my.app.com.sx2_weiying.model.bean.UserBean;
import my.app.com.sx2_weiying.presenter.DataPresenter;
import my.app.com.sx2_weiying.view.activity.SpecialActivity;
import my.app.com.sx2_weiying.view.adapter.SpecialAdpater;
import my.app.com.sx2_weiying.view.iview.IView;

import static android.content.ContentValues.TAG;
import static android.widget.GridLayout.VERTICAL;

public class SpecialFragment extends Fragment implements IView {
    @BindView(R.id.recy)
    RecyclerView recy;
    Unbinder unbinder;
    private DataPresenter dataPresenter;
    private SpecialAdpater specialAdpater;
    private List<UserBean.RetBean.ListBean> list;
    private List<SpecialBeana> data=new ArrayList<>();
    private String aa;
    private boolean is=true;
    private String pic;
    private String pic11;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.special, container, false);
        unbinder = ButterKnife.bind(this, view);

        dataPresenter = new DataPresenter();
        dataPresenter.attachView(this);
        dataPresenter.getData("http://api.svipmovie.com/");
        return view;
    }



    @Override
    public void success(Object o) {
        UserBean news= (UserBean) o;
        if (news!=null){
            UserBean.RetBean ret = news.getRet();
            if (ret!=null){
                list = ret.getList();
                Log.i(TAG, "第二个页面: "+ list.size());


                for (int i = 2; i <list.size() ; i++) {
                    List<UserBean.RetBean.ListBean.ChildListBean> childList = list.get(i).getChildList();

                    for (int j = 0; j <childList.size() ; j++) {
                        pic11 = childList.get(j).getPic();
                    }
                    if (i!=5&&i!=6&&pic11!=null){

                            SpecialBeana specialBean = new SpecialBeana();
                            specialBean.setTitle(list.get(i).getTitle());
                            specialBean.setMoreURL(list.get(i).getMoreURL());
                            specialBean.setPic(pic11);
                            data.add(specialBean);
                        }
                }




                if (list !=null){
                    specialAdpater = new SpecialAdpater(getActivity(),data);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2 ,VERTICAL,false);
                    recy.setLayoutManager(gridLayoutManager);
                    recy.setAdapter(specialAdpater);
                    specialAdpater.notifyDataSetChanged();
                    //点击事件     调用接口方法
                    specialAdpater.setOnRecyclerViewItemClickLintemet(new SpecialAdpater.OnRecyclerViewItemClickLintemet() {

                        private String s2;
                        private String substring;

                        @Override
                        public void onItemClick(int position) {
                            String moreURL = data.get(position).getMoreURL();
                            if (moreURL!=null){
                                if (moreURL.contains("?")){
                                    substring = moreURL.substring(moreURL.lastIndexOf("?")+11);
                                    if (substring.contains("&")){
                                        String[] split2 = substring.split("\\&");
                                        s2 = split2[0];
                                        Log.i(TAG, "截取的字符串a: "+ substring);
                                        Log.i(TAG, "截取的字符串b: "+ s2);
                                    }


                                }
                            }
                            Intent intent = new Intent(getActivity(), SpecialActivity.class);
                            if (s2!=null){

                                intent.putExtra("name", s2);
                            }
                            startActivity(intent);
                            getActivity().overridePendingTransition(R.anim.activity_open, R.anim.activity_open_close);
                        }
                    });
                }
            }

            specialAdpater.notifyDataSetChanged();
        }



    }

    @Override
    public void Failes(Exception e) {

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
