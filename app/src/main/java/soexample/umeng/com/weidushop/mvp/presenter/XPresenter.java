package soexample.umeng.com.weidushop.mvp.presenter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import soexample.umeng.com.weidushop.bean.HomeBean;
import soexample.umeng.com.weidushop.bean.ShopXiangBean;
import soexample.umeng.com.weidushop.mvp.model.Model;
import soexample.umeng.com.weidushop.mvp.model.XModel;
import soexample.umeng.com.weidushop.mvp.view.IView;
import soexample.umeng.com.weidushop.mvp.view.XView;
import soexample.umeng.com.weidushop.utils.ICallBack;
import soexample.umeng.com.weidushop.utils.IICallBack;

/**
 * date:2019/2/21
 * author:冯泽林(asus)
 * function:
 */
public class XPresenter {
    private XView iv;
    private XModel mModel;
    public void attch(XView iv){
        this.iv=iv;
        mModel=new XModel();
    }
    public void getXiangqing(int id){
        Type type = new TypeToken<ShopXiangBean>() {
        }.getType();

           mModel.getXiangqing(id, new IICallBack() {
                @Override
                public void OnFailed(Exception e) {
                    iv.failed(e);
                }

                @Override
                public void OnSuccess(Object o) {
                    ShopXiangBean bean= (ShopXiangBean) o;
                    if (bean!=null & "0000".equals(bean.getStatus())){
                        iv.successful(bean);
                    }
                }
            },type);
        }
    public void delete(){
        if(iv!=null){
            iv=null;
        }
    };
}
