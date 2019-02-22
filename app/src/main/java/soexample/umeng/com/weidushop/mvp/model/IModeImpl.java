package soexample.umeng.com.weidushop.mvp.model;

import com.google.gson.Gson;

import java.util.Map;

import soexample.umeng.com.weidushop.notback.RetrofitManage;
import soexample.umeng.com.weidushop.utils.ICallBack;


/**
 * date:2019/1/3
 * author:冯泽林(asus)
 * function:
 */
public class IModeImpl implements IModel {
    @Override
    public void requestData(String url, Map<String, String> params, final Class clazz, final ICallBack callBack) {
        RetrofitManage.getRetrofitManage().post(url, params,new RetrofitManage.HttpListener() {
            @Override
            public void OnSuccess(String data) {
                Object o = new Gson().fromJson(data, clazz);
                if (callBack != null) {
                    callBack.OnSuccess(o);
                }
            }

            @Override
            public void OnFailed(String e) {
                if (callBack != null) {
                    callBack.OnFailed(e);
                }
            }
        });
    }

}
