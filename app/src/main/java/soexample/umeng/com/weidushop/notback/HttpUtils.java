package soexample.umeng.com.weidushop.notback;

import android.os.Handler;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import soexample.umeng.com.weidushop.utils.ICallBack;
import soexample.umeng.com.weidushop.utils.IICallBack;

/**
 * date:2019/2/18
 * author:冯泽林(asus)
 * function:
 */
public class HttpUtils {
    private OkHttpClient client;
    private static HttpUtils intance;
    private Handler handler=new Handler();
    private HttpUtils(){
        client = new OkHttpClient
                .Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))  //添加日至拦截器
                .build();
    }


    public static HttpUtils getIntance(){
        if(intance==null){
            synchronized (HttpUtils.class){
                if(intance==null){
                    intance=new HttpUtils();
                }
            }
        }
        return intance;
    }


    public void get(String url, final IICallBack callBack, final Type type){
        Request request=new Request.Builder().url(url).get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.OnFailed(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                final Object o = new Gson().fromJson(s, type);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.OnSuccess(o);
                    }
                });
            }
        });
    }
}