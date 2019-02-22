package soexample.umeng.com.weidushop.mvp.model;

import java.lang.reflect.Type;

import soexample.umeng.com.weidushop.notback.HttpUtils;
import soexample.umeng.com.weidushop.utils.ICallBack;
import soexample.umeng.com.weidushop.utils.IICallBack;

/**
 * date:2019/2/18
 * author:冯泽林(asus)
 * function:
 */
public class Model {
    public void get(String url, IICallBack callBack, Type type){
        HttpUtils.getIntance().get(url,callBack,type);
    }
}
