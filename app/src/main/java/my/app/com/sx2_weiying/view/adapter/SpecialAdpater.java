package my.app.com.sx2_weiying.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import my.app.com.sx2_weiying.R;
import my.app.com.sx2_weiying.model.bean.SpecialBeana;

public class SpecialAdpater extends RecyclerView.Adapter<SpecialAdpater.ViewHolder> {

    //定义一个接口点击事件
    public interface OnRecyclerViewItemClickLintemet {
        void onItemClick(int position);
    }

    //定义接口对象         单击事件
    private OnRecyclerViewItemClickLintemet listener;

    //定义接口方法         单击事件
    public void setOnRecyclerViewItemClickLintemet(OnRecyclerViewItemClickLintemet listener) {

        this.listener = listener;
    }

    ;
    private Context context;
//    private List<UserBean.RetBean.ListBean> list;

    private List<SpecialBeana> data;

    public SpecialAdpater(Context context, List<SpecialBeana> data) {

        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = View.inflate(context, R.layout.special_view, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //条目单击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(position);
            }
        });

        Uri uri = Uri.parse(data.get(position).getPic());

        holder.img.setImageURI(uri);
        holder.name.setText(data.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView img;
        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (SimpleDraweeView) itemView.findViewById(R.id.spe_img);
            name = (TextView) itemView.findViewById(R.id.spe_name);
        }
    }
}
