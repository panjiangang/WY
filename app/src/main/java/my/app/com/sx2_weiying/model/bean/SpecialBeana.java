package my.app.com.sx2_weiying.model.bean;

public class SpecialBeana {

    private String moreURL;
    private String title;
    private String pic;


    public SpecialBeana(String moreURL, String title, String pic) {
        this.moreURL = moreURL;
        this.title = title;
        this.pic = pic;
    }
    public SpecialBeana( ) {

    }

    public String getMoreURL() {
        return moreURL;
    }

    public void setMoreURL(String moreURL) {
        this.moreURL = moreURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
