package soexample.umeng.com.weidushop.mvp.model;

import java.lang.reflect.Type;

import soexample.umeng.com.weidushop.app.Apis;
import soexample.umeng.com.weidushop.notback.HttpUtils;
import soexample.umeng.com.weidushop.utils.ICallBack;
import soexample.umeng.com.weidushop.utils.IICallBack;

/**
 * date:2019/2/21
 * author:冯泽林(asus)
 * function:
 */
public class XModel {
    public  void  getXiangqing(int commodityId, final IICallBack callBack, final Type type){
        String url= Apis.XIANGQING_URL+"?commodityId="+commodityId;
        HttpUtils.getIntance().get(url,callBack,type);
    }
}
