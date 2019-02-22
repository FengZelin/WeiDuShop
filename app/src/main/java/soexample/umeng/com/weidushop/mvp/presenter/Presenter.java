package soexample.umeng.com.weidushop.mvp.presenter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;


import soexample.umeng.com.weidushop.bean.Bean;
import soexample.umeng.com.weidushop.bean.HomeBean;
import soexample.umeng.com.weidushop.bean.ShowBean;
import soexample.umeng.com.weidushop.mvp.model.Model;
import soexample.umeng.com.weidushop.mvp.view.shopView;
import soexample.umeng.com.weidushop.utils.IICallBack;

/**
 * date:2019/2/20
 * author:冯泽林(asus)
 * function:
 */
public class Presenter {
    private Model model;
    private shopView iv;
    public void attch(shopView iv){
        this.iv=iv;
        model=new Model();
    }
    public void get(String url){
        Type type = new TypeToken<ShowBean>() {
        }.getType();
       model.get(url, new IICallBack() {
           @Override
           public void OnFailed(Exception e) {
               iv.failed(e);
           }

           @Override
           public void OnSuccess(Object o) {
                ShowBean bean= (ShowBean) o;
                if(bean!=null){
                    iv.success(bean);
                }
           }
       },type);
    }
    public void getbanner(String url){
        Type type = new TypeToken<HomeBean>() {
        }.getType();
        model.get(url, new IICallBack() {
            @Override
            public void OnFailed(Exception e) {
                iv.bannerfailed(e);
            }

            @Override
            public void OnSuccess(Object o) {
                HomeBean bean= (HomeBean) o;
                if(bean!=null){
                    iv.bannersuccess(bean);
                }
            }
        },type);
    }
    public void delete(){
        if(iv!=null){
            iv=null;
        }
    }
}
