package my.app.com.sx2_weiying.other;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import my.app.com.sx2_weiying.Dao.DaoMaster;
import my.app.com.sx2_weiying.Dao.DaoSession;
import my.app.com.sx2_weiying.Dao.PersonDao;

public class DbHelper {
    private final DaoMaster daoMaster;
    private final DaoSession daoSession;

    //定义单例模式
    private static volatile DbHelper instance;

    public DbHelper(Context context) {

        //初始化数据库的一些配置        第一个参数上下文， 二 ：数据库名
        DaoMaster.DevOpenHelper user = new DaoMaster.DevOpenHelper(context, "a", null);
//        //获取数据库操作对象
        SQLiteDatabase db = user.getWritableDatabase();
//        //获取DaoMaster对象
        daoMaster = new DaoMaster(db);
//       //获取DaoSession对象
        daoSession = daoMaster.newSession();
    }
    public static DbHelper getInstance(Context context){
        if (null==instance){
            synchronized (DbHelper.class){
                if (instance==null){
                    instance=new DbHelper(context);
                }
            }
        }
        return instance;
    }
    //对外定义方法
    public PersonDao getPersonDao(){
        return daoSession.getPersonDao();
    }
}