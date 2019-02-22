package soexample.umeng.com.weidushop.mvp.presenter;

import java.util.Map;

import soexample.umeng.com.weidushop.bean.HomeBean;
import soexample.umeng.com.weidushop.bean.ShowBean;
import soexample.umeng.com.weidushop.mvp.model.IModeImpl;
import soexample.umeng.com.weidushop.mvp.presenter.inter.HomePresenter;
import soexample.umeng.com.weidushop.mvp.view.HomeView;
import soexample.umeng.com.weidushop.utils.ICallBack;

/**
 * date:2019/2/21
 * author:冯泽林(asus)
 * function:
 */
public class HomeShopPresenter implements HomePresenter {
    private IModeImpl model;
    private HomeView iv;

    public HomeShopPresenter(HomeView iv){
        this.iv=iv;
        model=new IModeImpl();
    }
    @Override
    public void startBannerHome(String url, Map<String, String> parmas, Class clazz) {
        model.requestData(url, parmas, clazz, new ICallBack() {
            @Override
            public void OnFailed(String e) {
                iv.onBannerFailed(e);
            }

            @Override
            public void OnSuccess(Object o) {
                HomeBean bean= (HomeBean) o;
                if(bean!=null){
                    iv.onBannerSuccess(bean);
                }
            }
        });
    }

}
