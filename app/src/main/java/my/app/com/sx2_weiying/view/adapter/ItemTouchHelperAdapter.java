package my.app.com.sx2_weiying.view.adapter;

public interface ItemTouchHelperAdapter {
    //数据交换
    void onItemMove(int fromPosition, int toPosition);
    //数据删除
    void onItemDissmiss(int position);
}