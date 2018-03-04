package my.app.com.sx2_weiying.model.bean;

import java.util.List;

public class MessageEventb {

    private List<LishiJiLu> list;

    public MessageEventb(List<LishiJiLu> list) {
        this.list = list;
    }
    public MessageEventb() {

    }

    public List<LishiJiLu> getList() {
        return list;
    }

    public void setList(List<LishiJiLu> list) {
        this.list = list;
    }
}
