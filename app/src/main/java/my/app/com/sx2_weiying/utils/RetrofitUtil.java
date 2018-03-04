package my.app.com.sx2_weiying.utils;

import my.app.com.sx2_weiying.model.http.ApiService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private static volatile RetrofitUtil instant;
    private ApiService apiService;

    private RetrofitUtil(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static RetrofitUtil getInstant(String url){
        if (null==instant){
            synchronized (RetrofitUtil.class){
                if (instant==null){
                    instant=new RetrofitUtil(url);
                }
            }
        }
        return instant;

    }
    public ApiService getApiService(){
        return apiService;
    }
}
