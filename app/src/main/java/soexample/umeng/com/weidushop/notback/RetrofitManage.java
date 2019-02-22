package soexample.umeng.com.weidushop.notback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import soexample.umeng.com.weidushop.app.Apis;

/**
 * date:2019/2/20
 * author:冯泽林(asus)
 * function:
 */
public class RetrofitManage {
    private static String BASE_URL="http://mobile.bwstudent.com/small/";
    private static RetrofitManage sRetrofitManage;

    public static synchronized RetrofitManage getRetrofitManage(){
        if(sRetrofitManage==null){
            sRetrofitManage=new RetrofitManage();
        }
        return sRetrofitManage;
    }
    private BaseApis mBaseApis;
    public RetrofitManage() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(15, TimeUnit.SECONDS);
        builder.writeTimeout(15, TimeUnit.SECONDS);
        builder.addInterceptor(interceptor);
        builder.retryOnConnectionFailure(true);
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build();
        mBaseApis = retrofit.create(BaseApis.class);
    }
    //    get请求
    public void get(String url, HttpListener listener) {
        mBaseApis.get(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(listener));
        }
        //post请求
    public void post(String url, Map<String, String> map, HttpListener listener) {
        if (map == null) {
            map = new HashMap<>();
        }
        mBaseApis.post(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(listener));
    }
    private Observer getObserver(final HttpListener listener) {
        Observer mObserver = new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (listener != null) {
                    listener.OnFailed(e.getMessage());
                }
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String data = responseBody.string();
                    if (listener != null) {
                        listener.OnSuccess(data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.OnFailed(e.getMessage());
                    }
                }
            }
        };
        return mObserver;
    }
    public interface HttpListener {
        void OnSuccess(String data);

        void OnFailed(String error);
    }

    private HttpListener listener;

    public void result(HttpListener listener) {
        this.listener = listener;
    }
}
