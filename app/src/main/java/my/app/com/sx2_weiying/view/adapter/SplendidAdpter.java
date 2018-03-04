package my.app.com.sx2_weiying.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import my.app.com.sx2_weiying.R;
import my.app.com.sx2_weiying.model.bean.ChoiceItemBean;
import my.app.com.sx2_weiying.model.bean.MessageEventa;
import my.app.com.sx2_weiying.view.activity.VideoActivity;

public class SplendidAdpter extends RecyclerView.Adapter<SplendidAdpter.ViewHolder> {

    private Context context;
    private ChoiceItemBean mbean;

    public SplendidAdpter(Context context, ChoiceItemBean bean) {
        this.context = context;
        this.mbean = bean;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.splendid_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Uri uri = Uri.parse(mbean.getRet().getList().get(3).getChildList().get(position).getPic());
        holder.img.setImageURI(uri);
        holder.name.setText(mbean.getRet().getList().get(3).getChildList().get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pic = mbean.getRet().getList().get(3).getChildList().get(position).getPic();
                String title = mbean.getRet().getList().get(3).getChildList().get(position).getTitle();
                String dataId = mbean.getRet().getList().get(3).getChildList().get(position).getDataId();

                if (dataId != null && dataId != "") {
                    Intent intent = new Intent(context, VideoActivity.class);
//
                    MessageEventa messageEventa = new MessageEventa();
                    messageEventa.setPic(pic);
                    messageEventa.setTitle(title);
                    messageEventa.setDataId(dataId);

                    //发送事件
                    EventBus.getDefault().postSticky(messageEventa);
                    context.startActivity(intent);
//                               MainActivity a= (MainActivity) getActivity();
//                    getActivity().overridePendingTransition(R.anim.activity_open, R.anim.activity_open_close);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mbean.getRet().getList().get(3).getChildList() == null ? 0 : mbean.getRet().getList().get(3).getChildList().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private SimpleDraweeView img;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            img = itemView.findViewById(R.id.img);
        }
    }
}
