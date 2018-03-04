package my.app.com.sx2_weiying.model.bean;

public class MessageEventa {
    private   String pic;
    private   String title;
    private   String dataId;


    public MessageEventa(String pic, String title, String dataId ) {
        this.pic = pic;
        this.title = title;
        this.dataId = dataId;

    }

    public MessageEventa( ) {

    }
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }


}
