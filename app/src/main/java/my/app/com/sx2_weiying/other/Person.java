package my.app.com.sx2_weiying.other;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class Person {
    @Id
    private Long id;
    private String pic;
    private String title;
    private String dataId;
    @Generated(hash = 1630309957)
    public Person(Long id, String pic, String title, String dataId) {
        this.id = id;
        this.pic = pic;
        this.title = title;
        this.dataId = dataId;
    }
    @Generated(hash = 1024547259)
    public Person() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPic() {
        return this.pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDataId() {
        return this.dataId;
    }
    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    
}
